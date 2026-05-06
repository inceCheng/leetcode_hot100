package com.chg.hot100;

import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 */
public class FindKthLargest {
    private final Random random = new Random();

    // 找数组中第 k 大的数，就是返回排序后的数组下标为 n-k 位置的元素
    // 使用快拍思想，因为快排每一轮都可以确定枢轴元素的位置，当排完一轮之后，判断当前元素的下标是否是 N- k即可，
    // 如果是，则直接返回，如果不是，则判断当前元素与 target大小关系，如果小于，说明 target在右侧，递归找右侧即可；反之递归找左侧
    // 注意：当原数组相对有序时，快拍会陷入 O(n^2)复杂度，解决办法就是加入随机值，随机获取一个枢轴元素
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int target = n - k;
        return quickSort(nums, 0, n - 1, target);
    }

    // private int quickSort(int[] nums, int left, int right, int target) {
    //     if (left == right) return nums[left];
    //     // random.nextInt(n)，随机的结果是[0,n)，左闭右开
    //     int pivotIndex = left + random.nextInt(right - left + 1);
    //     swap(nums, left, pivotIndex);
    //
    //     int p = partition(nums, left, right);
    //     if (p == target) {
    //         return nums[p];
    //     } else if (p < target) {
    //         return quickSort(nums, p + 1, right, target);
    //     } else {
    //         return quickSort(nums, left, p - 1, target);
    //     }
    // }

    private int quickSort(int[] nums, int left, int right, int target) {
        if (left >= right) return nums[left];
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = nums[pivotIndex];
        int lt = left;
        int gt = right;
        int i = left;
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt, i);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        if (target >= lt && target <= gt) {
            return nums[lt];
        } else if (target < lt) {
            return quickSort(nums, left, lt - 1, target);
        } else {
            return quickSort(nums, gt + 1, right, target);
        }

    }

    private int partition(int[] nums, int left, int right) {
        // 在划分函数里，直接取左侧元素是枢轴元素
        int pivot = nums[left];
        int i = left + 1;
        int j = right;
        while (true) {
            while (i <= j && nums[i] <= pivot) i++;
            while (i <= j && nums[j] >= pivot) j--;
            if (i >= j) break;
            swap(nums, i, j);
        }
        // 将基准值换到它最终该在的位置，此时，下标 j 就是 pivot的最终排好序的位置
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FindKthLargest findKthLargest = new FindKthLargest();
        int[] num1 = {3, 65, 7, 2, 99, 7, 2, 7, 32, 45, 6, 8, 8, 87};
        System.out.println(findKthLargest.findKthLargest(num1, 3));
    }

}
