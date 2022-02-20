import java.util.Deque;
import java.util.LinkedList;

public class strTest {
	public static void main(String[] args) {
		String s = "(ed(et(oc))el)";
		reverseParentheses(s);
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
