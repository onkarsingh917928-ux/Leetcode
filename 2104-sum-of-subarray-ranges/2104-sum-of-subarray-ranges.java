class Solution {
    public long subArrayRanges(int[] nums) {
        long sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int largest = nums[i];
            int smallest = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                largest = Math.max(largest, nums[j]);
                smallest = Math.min(smallest, nums[j]);

                sum += (long)(largest - smallest);
            }
        }
        return sum;
    }
}