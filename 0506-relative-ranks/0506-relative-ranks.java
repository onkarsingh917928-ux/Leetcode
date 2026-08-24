import java.util.*;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] ans = new String[n];

        int[] sorted = score.clone();
        Arrays.sort(sorted);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (score[i] == sorted[n - 1 - j]) {

                    if (j == 0)
                        ans[i] = "Gold Medal";
                    else if (j == 1)
                        ans[i] = "Silver Medal";
                    else if (j == 2)
                        ans[i] = "Bronze Medal";
                    else
                        ans[i] = String.valueOf(j + 1);
                }
            }
        }

        return ans;
    }
}