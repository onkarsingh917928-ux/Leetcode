class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int m = nums[n - 1] - nums[2];
        int p = nums[n - 2] - nums[1];
        int o = nums[n - 3] - nums[0];

        return Math.min(m , Math.min(p,o));
    }
}