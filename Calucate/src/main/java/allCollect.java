import java.util.*;

// 全部组合
public class allCollect {

    public static int count = 0;
    static List<List<Integer>> allList = new ArrayList<>();
    public static void main(String[] args) {
        int num[] = {6, 3, 5};

        printLetterCombine(num);
        System.out.println(allList);
        System.out.println(count);
    }


    public static void printLetterCombine(int num[]) {
        for (int i = 1; i <= num.length; i++) { // i表示组合的数组长度，最小是1，最大就是原数组的长度
            int tmpNum[] = new int[i];
            getLetterCombine(tmpNum, 0, 0, i, num);
        }
    }

    /*
     * tmpNum 组合的临时数组
     * index 临时数组的下标
     * starti 起始数组下标
     * ln 组合数组长度
     * */
    public static void getLetterCombine(int[] tmpNum, int index, int starti, int ln, int num[]) {
        int length = num.length;
        for (int i = starti; i <= length - ln + index; i++) {
            tmpNum[index] = num[i];
            if (index == ln - 1) {
                List<Integer> tmpList = new ArrayList<>();
                for (int j = 0; j < ln; j++) {  // 输出组合数字
                    tmpList.add(tmpNum[j]);
                }
                allList.add(tmpList);
                count++;
                continue;
            }
            getLetterCombine(tmpNum, index + 1, i + 1, ln, num);
        }
    }
}


