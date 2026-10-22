class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n) {
                increasing = false;
            }

            if (nums[i] != (nums[0] - i + n) % n) {
                decreasing = false;
            }
        }

        int k = nums[0];

        // Case 1: Cyclic rotation of sorted array
        if (increasing) {
            int left = (n - k) % n;
            int right = (k == 0) ? 0 : k + 2;

            return Math.min(left, right);
        }

        // Case 2: Cyclic rotation of reversed sorted array
        if (decreasing) {
            int first = (k + 1) % n + 1;
            int second = n - k;

            return Math.min(first, second);
        }

        return -1;
    }
}