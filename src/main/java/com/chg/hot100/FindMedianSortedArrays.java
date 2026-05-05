package com.chg.hot100;

/**
 * 4. 寻找两个正序数组的中位数
 */
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        // 在这个算法里，我们要找的 i 不是数组的下标，而是“切分线”的位置。
        // 对于一个长度为 m 的数组，切分线可以切在最左边（i = 0，左半边 0 个元素），也可以切在最右边（i = m，左半边包含所有 m 个元素）。
        // 因此，总共有 m + 1 个可能的切分位置。二分查找的搜索空间必须包含 m，所以右边界必须初始化为 right = m;。
        //
        // 如果设为 m - 1 会怎样？
        // 考虑 nums1 = [1, 2]，nums2 = [3, 4]。
        // 正确的切分线应该是 i = 2（把 nums1 全部划到左边）。但如果 right = 1，二分查找永远碰不到 i = 2 的情况，
        // 最终 left > right 循环结束，触发你代码最后的 throw new IllegalArgumentException();
        int right = m;
        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = (m + n + 1) / 2 - i;
            int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

            if (nums1LeftMax > nums2RightMin) {
                right = i - 1;
            } else if (nums2LeftMax > nums1RightMin) {
                left = i + 1;
            } else {
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    // 注意返回的是 double类型，所以应该是除以 2.0，防止向下取整，精度丢失
                    return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
