import java.util.*;

// 字符串反转
public class strTest {
    public static void main(String[] args) {
        String s = "(ed(et(oc))el)";
        String r = reverseParentheses(s);
        System.out.println(r);

        test();

        int num[] = {6,3,5};
        Arrays.sort(num);
        //countTriplets(num);
    }

    public static String reverseParentheses(String s) {
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse(); // 反转
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // 是a == b
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    int a = 0, b = 0;
                    for (int x = i; x < j; x++) {
                        a ^= arr[x];
                    }
                    for (int y = j; y <= k; y++) {
                        b ^= arr[y];
                    }
                    if (a == b) {
                        ans++;
                        System.out.println(a);
                    }
                }
            }
        }
        System.out.println(ans);
        return ans;
    }


    public static void test(){
        int n = 4;
        int num[] = {6,3,5};
        Arrays.sort(num);

        List<Integer> listKK = new ArrayList<>();
        List<Integer> listSS = new ArrayList<>();
        int ss = 0;
        int kk = 0;
        int j = n - 1;
        int ss2 = 0;
        int kk2 = 0;
        for (int i = 0; i < j; i++) {
            Integer it = Integer.valueOf(Integer.toBinaryString(num[i]));
            kk2 += num[i];
            listKK.add(num[i]);
            kk = kk ^ it;
            for (; j > i; j--) {
                int nb = num[j];
                int jt = Integer.valueOf(Integer.toBinaryString(nb));
                ss = ss ^ jt;
                ss2 += nb;
                listSS.add(nb);
                if (kk == ss && kk2 < ss2) {
                    break;
                }
            }
            if (kk == ss && kk2 < ss2) {
                System.out.println(kk);
                System.out.println(kk2);
                System.out.println(listKK);
                System.out.println(ss2);
                System.out.println(listSS);
                break;
            }
            ss = ss2 = 0;
            j = n - 1;
            listSS.clear();
        }
    }
}
