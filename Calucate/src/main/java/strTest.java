import java.util.*;


public class strTest {
    public static void main(String[] args) {
        String s = "(ed(et(oc))el)";
        String r = reverseParentheses(s);
        System.out.println(r);

        //test();

        int num[] = {2,3,1,6,7};  //{6,3,5};
        //Arrays.sort(num);
        countTriplets(num);
    }

    // 字符串反转
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

    /*
    给你一个整数数组 arr 。
    现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
    a 和 b 定义如下：
    a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
    b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
    注意：^ 表示 按位异或 操作。

    请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。

    首先，数组长度小于 2 的时候，必然是 0。其次，观察到 a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1], b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]，
    那么根据位运算的规则， a^b=arr[i]^ arr[i + 1] ^ ... ^ arr[k]，即 i 到 k。
    此外，根据位运算，如果 a^b==0，那么 a==b，这是逆否命题，即反推也成立。
    而固定了两端之后，中间的j可以任意取，故有 k-i 种。每次计算完，如果满足条件，在 ans 里加上 k-i即可。
     */
    public static int countTriplets(int[] arr) {
        int n = arr.length;
        if (n < 2) return 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int t = arr[i];
            for (int j = i + 1; j < n; j++) {
                t ^= arr[j];
                if (t == 0){
                    ans += j-i;
                }
            }
        }
        System.out.println(ans);
        return ans;
    }


    public static void test(){
        int n = 3;
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
