import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

public class strTest {
	public static void main(String[] args) {

        Date d = new Date((new Date()).getTime() + 72 * 60 * 60 * 1000);


		String s = "(ed(et(oc))el)";
		System.out.println((new Date()).getTime());
		try {
			Thread.sleep(1000);//使当前线程阻塞1秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println((new Date()).getTime());
		//reverseParentheses(s);
		test();
	}

	public static void test(){
		int n = 3;
		int num[] = {6,3,5};
		Arrays.sort(num);

		int ss = 0;
		int kk = 0;
		int j = n - 1;
		for (int i = 0; i < j; i++) {
			Integer it = Integer.valueOf(Integer.toBinaryString(num[i]));
			kk = kk ^ it;
			if (kk < ss) {
				continue;
			}
			for (; j > i; j--) {
				int jt = Integer.valueOf(Integer.toBinaryString(num[j]));
				ss = ss ^ jt;
				if (kk < ss) {
					break;
				}
			}
			if (i == j) {
				if (ss == kk) {
					System.out.println(ss);
				} else {
					System.out.println("No");
				}
			}
		}
	}

	public static String reverseParentheses(String s) {
		Deque<String> stack = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++){
			char ch = s.charAt(i);
			if (ch == '('){
				stack.push(sb.toString());
				sb.setLength(0);
			} else if (ch == ')'){
				sb.reverse(); // 反转
				sb.insert(0,stack.pop());
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
}
