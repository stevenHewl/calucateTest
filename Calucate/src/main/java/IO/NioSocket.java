package IO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSocket {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9898));
            serverSocketChannel.configureBlocking(false); // 非阻塞

            Selector selector = Selector.open(); // 获取一个多路复用器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册serverSocketChannel 到 selector，关注OP_ACCEPT事件
            while (true) {
                if (selector.select(1000) == 0) { // 阻塞1秒得到可用的keys是0
                    System.out.println("selectedKeys-Size:" + selector.keys().size());
                    System.out.println("没有通道有事件发生");
                    continue;
                }
                // 有事件发生，找到发生事件的 SelectionKey 的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 轮询选择器集合
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 发生 OP_ACCEPT 事件，处理连接请求
                    if (selectionKey.isAcceptable()) {
                        System.out.println("有客户端通道申请加入，" + "selectedKeys-Size:" + selector.keys().size());
                        SocketChannel socketChannel = serverSocketChannel.accept(); // 建立一个连接通道
                        socketChannel.configureBlocking(false);
                        // 将 socketChannel 也注册到 selector，关注 OP_READ 事件，并给 socketChannel 关联 Buffer
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println("selectedKeys-Size:" + selector.keys().size());
                    }

                    // 发生 OP_READ 事件，读客户端数据
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        int r = channel.read(buffer);
                        if (r > 0) {
                            System.out.println("msg form client: " + new String(buffer.array()));
                            buffer.flip();
                            buffer.clear();
                        } else {
                            System.out.println("msg is null");
                            selectionKey.cancel(); // 移除连接通道
                        }
                    }
                    // 手动从集合中移除当前的 selectionKey，防止重复处理事件
                    iterator.remove();
                }
            }
        } catch (Exception es) {
            es.printStackTrace();
        }
    }
}
