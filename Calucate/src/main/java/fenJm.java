import java.util.Arrays;
import java.util.Scanner;

public class fenJm {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nums[] = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		Arrays.sort(nums);

		int kk = 0;
		int ss = 0;
		int j = nums.length - 1;
		for (int i = 0; i < nums.length; i++) {
			int t = Integer.valueOf(Integer.toBinaryString(nums[i]));
			kk = kk ^ t;
			if (kk < ss) {
				continue;
			}
			for (; j > i; j--) {
				ss += nums[j];
				if (kk < ss) {
					break;
				}
			}
			if (i == j) {
				break;
			}
		}
		if (kk == ss) {
			System.out.println(ss);
		} else {
			System.out.println("NO");
		}
	}
}
