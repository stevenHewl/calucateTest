import java.util.ArrayList;
import java.util.List;

/*
* 跳跃游戏
* 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
* 数组中的每个元素代表你在该位置可以跳跃的最大长度。
* 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
* 假设你总是可以到达数组的最后一个位置。
* */
public class stepGame {
    public static void main(String[] args) {
        int nums[] = {10,9,8,7,6,5,4,3,2,1,1,0};//{2,3,1,1,0};
        int n = jump(nums);
        System.out.println("n:" + n);
    }

    public static int jump(int[] nums) {
        int index = 0;
        int loopn = 0;
        while(index < (nums.length - 1)){
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
}
