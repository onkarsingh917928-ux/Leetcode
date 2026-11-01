
class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;

        // Find minimum element
        for (int num : nums) {
            min = Math.min(min, num);
        }

        int ans = 0;

        // Calculate total moves
        for (int num : nums) {
            ans += num - min;
        }

        return ans;
    }
}
