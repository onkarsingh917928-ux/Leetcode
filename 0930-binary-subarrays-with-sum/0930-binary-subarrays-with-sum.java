class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;  // har new starting index par reset

            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                if (sum == goal) {
                    count++;
                }
            }
        }

        return count;
    }
}