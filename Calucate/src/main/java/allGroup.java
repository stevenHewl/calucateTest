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
    static boolean used[] ; // 标识是否已在排列中使用了

    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0)
            return resultList;

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

    /*
    你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。
    编写一个方法，生成跳水板所有可能的长度。
    返回的长度需要从小到大排列。
    示例 1
    输入：
    shorter = 1
    longer = 2
    k = 3
    输出： [3,4,5,6]
    解释：
    可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer){
            return new int[] { k*shorter };
        }
        int r[] = new int[k+1];
        for(int i = k; i >= 0; i--){
            if (i == k){
                r[k-i] = shorter * k;
            } else {
                r[k-i] = shorter * i + longer * (k-i);
            }
        }
        return r;
    }
}
