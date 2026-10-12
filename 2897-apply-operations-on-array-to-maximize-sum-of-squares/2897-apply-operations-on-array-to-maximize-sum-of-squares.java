class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bits  = new int[31];

        for(int num : nums){
            for(int i = 0; i < 31; i++) {
                 if((num & (1 << i)) != 0) {
                     bits[i]++;
                 }
            }
        }
        long ans = 0;
        long MOD = 1000000007;

        for(int i = 0; i < k; i++) {
            long num = 0;

            for(int b = 30; b >= 0; b--) {
                if(bits[b] > 0) {
                    num |= (1L << b);
                    bits[b]--;
                }
            }
            ans  = (ans + num * num) % MOD;
        }
        return (int)ans;
    }
}