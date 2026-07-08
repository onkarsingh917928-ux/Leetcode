class Solution {
    public int[] countBits(int n) {
        int[] nums = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            nums[i] = countOnes(i);
        }

        return nums;
    }

    public int countOnes(int num) {
        int count = 0;

        while(num > 0) {
            if((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }

        return count;
    }
}