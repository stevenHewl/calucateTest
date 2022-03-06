import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
 * 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * */
public class stepGame {
	public static void main(String[] args) {
		int a[] = {1, 1, 2};
		//int r = countTz(a);
		int t[] = dailyTemperatures(new int[]{89,62,70,58,47,47,46,76,100,70});
		int nums[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0};//{2,3,1,1,0};
		//int n = jump(nums);
		System.out.println("n:" + Arrays.toString(t));
	}

	public static int jump(int[] nums) {
		int index = 0;
		int loopn = 0;
		while (index < (nums.length - 1)) {
			index = getStep(index, nums);
			loopn++;
		}
		return loopn;
	}

	private static int getStep(int index, int[] nums){
		int n = nums[index];
		int maxIndex = 0;
		int indexNew = 0;
		for (int i = 1; i <= n; i++){
			int j = index + i;
			if (j >= (nums.length - 1)){
				indexNew = nums.length - 1;
				return indexNew;
			}
			if (maxIndex < i + nums[j]){
				maxIndex = i + nums[j];
				indexNew = j;
			}
		}
		return indexNew;
	}

	public static int countTz(int[] answers) {
		Map<Integer, Integer> m = new HashMap();
		for (int k : answers) {
			if (m.containsKey(k)) {
				m.put(k, m.get(k) + 1);
			} else {
				m.put(k, 1);
			}
		}
		int numres = 0;
		for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
			int y = entry.getKey();
			int x = entry.getValue();
			int t = x % (y + 1);
			if (t > 0) {
				numres += (x / (y + 1) + 1) * (y + 1);
			} else {
				numres += x / (y + 1) * (y + 1);
			}
			//numres += (x + y) / (y + 1) * (y + 1);
		}
		return numres;
	}

	public static int[] dailyTemperatures(int[] temperatures) {
		if (temperatures == null || temperatures.length == 1) {
			return new int[]{0};
		}
		int answer[] = new int[temperatures.length];
		Deque<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
				int e = stack.pop();
				answer[e] = i - e;
			}
			if ((i + 1) < temperatures.length && temperatures[i + 1] > temperatures[i]) {
				answer[i] = 1;
			} else {
				stack.push(i);
				answer[i] = 0;
			}
		}
		return answer;
	}

    public String largestOddNumber(String num) {
        int l = num.length();
        for(int i = l-1; i >= 0; i--){
            int cr = Integer.valueOf(num.charAt(i));
            if (cr % 2 != 0){
                return num.substring(0, i+1);
            }
        }
        return "";
    }
}
