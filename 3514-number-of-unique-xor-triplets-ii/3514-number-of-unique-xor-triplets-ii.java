class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] pairXor = new boolean[2048];

        int n = nums.length;

        // All XORs of two elements (i <= j)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }

        boolean[] ans = new boolean[2048];

        // XOR each pair XOR with every element
        for (int x = 0; x < 2048; x++) {
            if (!pairXor[x]) continue;

            for (int num : nums) {
                ans[x ^ num] = true;
            }
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }

        return count;
    }
}