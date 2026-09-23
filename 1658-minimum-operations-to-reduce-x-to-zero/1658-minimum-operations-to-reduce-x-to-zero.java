class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // We need to remove everything
        if (target == 0) {
            return n;
        }

        int l = 0;
        int sum = 0;
        int maxLen = -1;

        for (int r = 0; r < n; r++) {

            sum += nums[r];

            while (l <= r && sum > target) {
                sum -= nums[l];
                l++;
            }

            if (sum == target) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }

        if (maxLen == -1) {
            return -1;
        }

        return n - maxLen;
    }
}