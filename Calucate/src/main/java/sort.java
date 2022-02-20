public class sort {

	public static void main(String[] args) {
		int num[] = {45,28,80,90,50,16,100,10,33,18};
		//mpsort(num);
		qsort(num, 0, num.length - 1);
	}

	// 快速排序
	private static void qsort(int num[], int left, int right) {
		int base = num[left];
		int ll = left;
		int rr = right;
		while (ll < rr) {
			for (;rr > ll; rr--){
				if (num[rr] < base){
					int t = num[rr];
					num[rr] = num[ll];
					num[ll] = t;
					ll++;
					break;
				}
			}
			for (; ll < rr; ll++){
				if (num[ll] > base){
					int t = num[ll];
					num[ll] = num[rr];
					num[rr] = t;
					rr--;
					break;
				}
			}
		}
		System.out.println("base:" + base);
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
		if ((ll-1) > left) {
			qsort(num, left, ll - 1);
		}
		if ((ll + 1) < right) {
			qsort(num, ll + 1, right);
		}
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
		System.out.println();
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
	}
}
