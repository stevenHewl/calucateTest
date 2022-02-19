import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 笔类型为 orderTypei 、价格为 pricei 的订单。

    订单类型 orderTypei 可以分为两种：
        0 表示这是一批采购订单 buy
        1 表示这是一批销售订单 sell

    注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。

    存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
        如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。
        反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。
        输入所有订单后，返回积压订单中的 订单总数 。

        由于数字可能很大，所以需要返回对 10^9 + 7 取余的结果。

    无论是采购，还是销售单，将匹配的移走，剩下的就是要放入积压
*/

public class priorityQueue {

    public static void main(String[] args) {
        int orders[][] = {{1,29,1},{22,7,1},{24,1,0},{25,15,1},{18,8,1},{8,22,0},{25,15,1},{30,1,1},{27,30,0}};

                //{{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}};

        int r = getNumberOfBacklogOrders(orders);
        System.out.println(r);
    }
    
    public static int getNumberOfBacklogOrders(int[][] orders) {
        Queue<int[]> buyQ = new PriorityQueue<>((o1, o2) -> (o2[0] - o1[0])); //<价格, 数目>，大顶堆
        Queue<int[]> sellQ = new PriorityQueue<>((o1, o2) -> (o1[0] - o2[0])); //<价格, 数目>，小顶堆
        for (int[] order : orders) {
            if (order[2] == 0) {   // 当前订单是Buy，需要找最小的Sell，如果Buy订单数量大于0时，遍历满足条件的所有销售订单
                while (order[1] > 0 && !sellQ.isEmpty() && sellQ.peek()[0] <= order[0]) {  //当前订单数目大于0，Sell最低价<= 当前订单价格, peek 查询队顶元素
                    if (order[1] >= sellQ.peek()[1]) {  //当前订单数 > Sell最低价的订单数
                        order[1] -= sellQ.poll()[1];  //Sell中最低价订单被删除， poll 删除一个元素，并返回删除的元素
                    } else {
                        int[] minSell = sellQ.poll(); //更新Sell最低价的订单数量
                        minSell[1] -= order[1];
                        sellQ.add(minSell);
                        order[1] = 0;
                    }
                }
                if (order[1] > 0) {
                    buyQ.add(new int[] {order[0], order[1]});//当前订单数还有余留，则压入队列中
                }
            } else {  //当前订单是Sell，需要找最大的Buy。 如果Sell订单数量大于0时，遍历满足条件的所有采购订单
                while (order[1] > 0 && !buyQ.isEmpty() && buyQ.peek()[0] >= order[0]) {  //当前订单数目大于0，Buy最高价>= 当前订单价格
                    if (order[1] >= buyQ.peek()[1]) {  //当前订单数 > Buy最高价的订单数
                        order[1] -= buyQ.poll()[1];  //Buy最高价被删除
                    } else {
                        int[] maxBuy = buyQ.poll(); //更新Buy最高价的订单数量
                        maxBuy[1] -= order[1];
                        buyQ.add(maxBuy);
                        order[1] = 0;
                    }
                }
                if (order[1] > 0){
                    sellQ.add(new int[] {order[0], order[1]});//当前订单数还有余留，则压入队列中
                }
            }
        }
        int res = 0;
        while (!buyQ.isEmpty()) {
            res = (res + buyQ.poll()[1]) % 1000000007;
        }
        while (!sellQ.isEmpty()) {
            res = (res + sellQ.poll()[1]) % 1000000007;
        }
        return res;
    }
}
