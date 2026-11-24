class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long pro = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                pro *= nums[j];

                gcd = GCD((int)gcd, nums[j]);

                lcm = (lcm / GCD((int)lcm, nums[j])) * nums[j];

                if (pro == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    public int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }
}