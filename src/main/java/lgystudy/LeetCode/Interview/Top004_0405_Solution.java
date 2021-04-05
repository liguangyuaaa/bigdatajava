package lgystudy.LeetCode.Interview;

import java.util.Arrays;

public class Top004_0405_Solution {
    /**
     * 2,3   4,5,6
     * 2 + 3 =5  /   2  =   2 ...1
     *
     * 2,3,4  5,6,7
     * 3 + 3 =6  /   2  =   3 ...0
     * @return
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        System.out.println("+++++++++++");
        double ff = new Top004_0405_Solution().findMedianSortedArrays2(nums1, nums2);
        System.out.println(ff);

        System.out.println("---------------------");
        double gg = new Top004_0405_Solution().findMedianSortedArrays3(nums1, nums2);
        System.out.println(gg);
        System.out.println("+++++++++++");
        double hh = new Top004_0405_Solution().findMedianSortedArrays4(nums1, nums2);
        System.out.println(hh);
    }


    //方法三：(自研）拼接成一个字符数组，自带排序，取中间（实用性强）
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int[] result = Arrays.copyOf(nums1, nums1.length + nums2.length);
        System.arraycopy(nums2, 0, result, nums1.length, nums2.length);
        Arrays.stream(result).sorted();
        Arrays.sort(result);
        int m = result.length % 2;
        // 2, 3, 4, 5
        if(m == 0){
            return ((double)result[result.length / 2 - 1] + (double)result[result.length / 2 ])/2;
        }else{
            // 2, 3, 4
            return result[result.length / 2];
        }
    }



    //方法二：从中间拆分数组，左右大小数目一样

    /**
     * 时间复杂度：O(logmin(m,n)))，
     *
     * 空间复杂度：O(1)O(1)。
     *
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    //方法一：利用二分法

    /**
     * 时间复杂度：O(log(m+n))，其中 mm 和 nn 分别是数组nums1 nums2的长度。
     *       初始时有 k=(m+n)/2k=(m+n)/2 或 k=(m+n)/2+1k=(m+n)/2+1，每一轮循环可以将查找范围减少一半，因此时间复杂度是 O(\log(m+n))O(log(m+n))。
     *
     * 空间复杂度：O(1)O(1)。
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length1 = nums1.length,length2 = nums2.length;
        int totalLength = length1 + length2;
        if(totalLength % 2 ==1){
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        }else{
            int midIndex1 = totalLength / 2 - 1,midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex1 + 1)) / 2.0;
            return median;
        }
    }

    //二分法
    public int getKthElement(int[] nums1, int[] nums2, int k){
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }

    }
//自己方法，没有排序（错误）
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int qian = (nums1.length + nums2.length)/2;
        int hou = (nums1.length + nums2.length)%2;
        if(hou == 1){
            //2,3   4,5,6
            if(qian >= nums1.length){
                return nums2[qian - nums1.length];
                //2,3,4   5,6
            }else {
                return nums1[qian];
            }
            //2,3,4  5,6,7
            //2,3,4,5   6,7
            //(hou + hou+1)/2
        }else{
            //2,3   4,5,6,7    3...0
            if(qian > nums1.length){
                return (double)(nums2[qian - nums1.length - 1] + nums2[qian - nums1.length])/2;
                //2,3,4,5   6,7    3...0
            }else if(hou <= nums1.length - 1){
                return (double)(nums1[qian -1] + nums1[qian])/2;
            }else{
                return (double)(nums1[nums1.length - 1] + nums2[0])/2;
            }
        }

    }
}
