import java.util.Arrays;

public static double findMedian(int[] nums) {
    int midIndex = nums.length >> 1;
    return nums.length % 2 == 0 ? (nums[midIndex] + nums[midIndex - 1]) / 2.0 : nums[midIndex];
}

// n = len(nums)
// median = (nums[n >> 1] + nums[~(n >> 1)]) / 2