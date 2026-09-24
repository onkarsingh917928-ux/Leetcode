class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(sum(nums[i]) == i) {
                return i;
            }
        }
        return -1;
    }
    public int sum(int n) {
        int sum = 0;
        while(n > 0) {
        int ld = n % 10;
        sum += ld;
        n /= 10;
        }
        return sum;
    }
}