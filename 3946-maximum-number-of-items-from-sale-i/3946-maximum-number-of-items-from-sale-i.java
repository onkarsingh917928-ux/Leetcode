class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] value = new int[n];

        // Calculate total value contributed by each item
        for (int i = 0; i < n; i++) {
            value[i] = 1;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    value[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];
        java.util.Arrays.fill(dp, -1);
        dp[0] = 0;

        int ans = 0;

        // Process prices in descending order
        for (int p = 1500; p >= 1; p--) {

            int[] one = new int[budget + 1];
            java.util.Arrays.fill(one, -1);

            // Process items having price p
            for (int i = 0; i < n; i++) {
                if (items[i][1] != p) continue;

                for (int b = budget; b >= p; b--) {

                    // Buy this item as the first item of this price
                    if (dp[b - p] != -1) {
                        one[b] = Math.max(one[b],
                                         dp[b - p] + value[i]);
                    }

                    // Buy this item after another item of same price
                    if (one[b - p] != -1) {
                        one[b] = Math.max(one[b],
                                         one[b - p] + value[i]);
                    }
                }
            }

            // Calculate maximum answer
            for (int b = 0; b <= budget; b++) {
                if (one[b] != -1) {
                    ans = Math.max(ans,
                        one[b] + (budget - b) / p);

                    dp[b] = Math.max(dp[b], one[b]);
                }
            }
        }

        return ans;
    }
}