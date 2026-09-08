class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int sum1 = 0;
        int sum2 = 0;
        int ans = 0;

        
        for (int i = 0; i < k; i++) {
            sum1 += cardPoints[i];
        }

        ans = sum1;

        // Replace left cards one by one with right cards
        int right = n - 1;

        for (int i = k - 1; i >= 0; i--) {
            sum1 -= cardPoints[i];
            sum2 += cardPoints[right];

            right--;

            ans = Math.max(ans, sum1 + sum2);
        }

        return ans;
    }
}

