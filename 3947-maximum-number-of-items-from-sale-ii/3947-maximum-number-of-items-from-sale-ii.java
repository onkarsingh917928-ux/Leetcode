import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Calculate free items for each factor
        int[] gain = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j += i) {
                gain[i] += freq[j];
            }

            if (freq[i] > 0) {
                gain[i]--;
            }
        }

        // Sort items by price
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long ans = 0;
        long remaining = budget;

        // Buy profitable items (each gives 2 copies)
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (price > 2L * minPrice) {
                break;
            }

            int count = gain[factor];

            if (count == 0) {
                continue;
            }

            long buy = Math.min(count, remaining / price);

            ans += buy * 2;
            remaining -= buy * price;
        }

        // Buy remaining cheapest items
        ans += remaining / minPrice;

        return (int) ans;
    }
}