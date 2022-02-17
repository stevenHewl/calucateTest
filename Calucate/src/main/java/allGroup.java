import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
        示例 1：

        输入：nums = [1,2,3]
        输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*/
public class allGroup {
    static List<List<Integer>> resultList = new ArrayList<List<Integer>>(); // 结果
    static LinkedList<Integer> path = new LinkedList<Integer>(); // 一次排列结果，长度应该登录数组长度
    static boolean[] used; // 标识是否已在排列中使用了
    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return resultList;
        used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        loopProcess(nums);
        System.out.println(resultList.toString());
        return resultList;
    }

    private static void loopProcess(int[] nums){
        if (nums.length == path.size()){
            resultList.add(new ArrayList<Integer>(path));
            return; // 一次排列结束
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]){
                continue;
            }
            // 去掉重复组合
            if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            loopProcess(nums);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int nums[] = {1,1,2};
        permute(nums);
    }
}
