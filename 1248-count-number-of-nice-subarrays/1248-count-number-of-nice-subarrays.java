class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int odd = 0;
        int ans = 0;
        int even = 0;

        while (r < nums.length) {

            if (nums[r] % 2 == 1) {
                odd++;
                even = 0;
            }

            while (odd > k) {
                if (nums[l] % 2 == 1) {
                    odd--;
                }
                l++;
            }

            if (odd == k) {
                while (nums[l] % 2 == 0) {
                    even++;
                    l++;
                }

                ans += even + 1;
            }

            r++;
        }

        return ans;
    }
}