package lgystudy.LeetCode.Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 《两数之和》
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 */
public class Top001_0404_TwoSum {
    public static void main(String[] args) {
        int nums[] = {2,11, 7,15};
        Top001_0404_TwoSum ts = new Top001_0404_TwoSum();
        int arrs[] = ts.twoSum(nums, 9);
        System.out.println("["+arrs[0]+","+arrs[1]+"]");

        int arrs2[] = ts.twoSum2(nums, 9);
        System.out.println("["+arrs2[0]+","+arrs2[1]+"]");

        System.out.println("--------------------");
        int arrs3[] = ts.twoSum3(nums, 9);
        System.out.println("["+arrs3[0]+","+arrs3[1]+"]");
    }

    /**
     * 方法一：暴力枚举
     * 思路及算法
     *
     * 最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
     * 当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，
     * 因此不需要再进行匹配。而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N^2)，其中N是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1)。

     */
    public int[] twoSum(int[] nums, int target) {
        int arrs[] = {0, 0};
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    //System.out.println(nums[i]+"+"+nums[j]+" == "+ target );
                    arrs[0] = i;
                    arrs[1] = j;
                    return arrs;
                }
            }
        }
        return null;
    }

    /**
     * 方法二：哈希表
     * 思路及算法
     *
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，
     * 能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)。
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N是数组中的元素数量。对于每一个元素 x，我们可以 O(1)地寻找 target - x。
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。

     */
    public int[] twoSum2(int[] nums, int target){
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
            /**
             * 2  0
             * 7  1
             *
             */
        }
        return new int[0];
    }

    //方法三：自研拓展
    public int[] twoSum3(int nums[], int target){
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();

        /**
         * 9-2 = 7
         * 0  2
         * 1  7
         *
         *
         */
        int key = 0;
        for(int j = 0; j < nums.length; ++j){
            if(hashtable.containsValue(target - nums[j])){
                for(Integer getKey :hashtable.keySet()){
                    if(hashtable.get(getKey).equals(target - nums[j])){
                        key = getKey;
                        return new int[]{key,j};
                    }
                }
            }
            hashtable.put(j, nums[j]);

        }
        return null;
    }



}
