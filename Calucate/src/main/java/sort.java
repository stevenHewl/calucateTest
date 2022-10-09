import java.util.*;

public class sort {

    public static void main(String[] args) {
        int count = 10000, range = 10000;
        int num[] = createArray(count, range);
        int num1[] = new int[count];
        int num2[] = new int[count];
        int num3[] = new int[count];
        System.arraycopy(num, 0, num1, 0, count);
        System.arraycopy(num, 0, num2, 0, count);
        System.arraycopy(num, 0, num3, 0, count);
        System.out.println("个数：" + count + ", 范围：" + range);
        System.out.println(Arrays.toString(num));

        long s1, s2;

        s1 = System.currentTimeMillis();
        qsort(num1, 0, (num.length - 1));  // n*log2n   100万 116 ms
        s2 = System.currentTimeMillis();
        System.out.println("快排时间：" + (s2 - s1));
        //System.out.println(Arrays.toString(num1));

        s1 = System.currentTimeMillis();
        mergeSort(num2, 0, (num2.length - 1));  // n*log2n   100万 206 ms
        s2 = System.currentTimeMillis();
        System.out.println("归并时间：" + (s2 - s1));
        //System.out.println(Arrays.toString(num2));

        s1 = System.currentTimeMillis();
        mpsort(num3);  // n * n    15305 ms
        s2 = System.currentTimeMillis();
        System.out.println("冒泡时间：" + (s2 - s1));
        //System.out.println(Arrays.toString(num3));
    }

    // 快速排序
    private static void qsort(int num[], int left, int right) {
        int base = num[left];
        //System.out.println("base:" + base);
        int ll = left;
        int rr = right;
        while (ll < rr) {
            for (; rr > ll; rr--) {
                if (num[rr] < base) {
                    int t = num[rr];
                    num[rr] = num[ll];
                    num[ll] = t;
                    ll++;
                    break;
                }
            }
            for (; ll < rr; ll++) {
                if (num[ll] > base) {
                    int t = num[ll];
                    num[ll] = num[rr];
                    num[rr] = t;
                    rr--;
                    break;
                }
            }
        }
        if ((ll - 1) > left) {
            qsort(num, left, ll - 1);
        }
        if ((ll + 1) < right) {
            qsort(num, ll + 1, right);
        }
    }

    // 归并排序
    private static void mergeSort(int num[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(num, low, mid);
            mergeSort(num, mid + 1, high);
            merge(num, low, mid, high);
        }
    }

    private static void merge(int num[], int low, int mid, int high) {
        int tempArray[] = new int[high - low + 1]; // 本段数组长度
        int right = mid + 1;
        int left = low;
        int k = 0;
        //System.out.println(String.format("low:%s,mid:%s,high:%s", low, mid, high));
        while (right <= high && left <= mid) {
            if (num[right] < num[left]) {
                tempArray[k] = num[right];
                k++;
                right++;
            } else {
                tempArray[k] = num[left];
                k++;
                left++;
            }
        }
        while (right <= high) {
            tempArray[k] = num[right];
            k++;
            right++;
        }
        while (left <= mid) {
            tempArray[k] = num[left];
            k++;
            left++;
        }
        for (int j = 0; j < tempArray.length; j++) {
            num[low + j] = tempArray[j];
        }
        //System.out.println(Arrays.toString(num));
    }

    // 冒泡排序
    private static void mpsort(int num[]) {
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] > num[j]) {
                    int t = num[i];
                    num[i] = num[j];
                    num[j] = t;
                }
            }
        }
    }

    private static int[] createArray(int count, int range) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = (int) (Math.random() * range);
        }
        return array;
    }
}
