class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // start, end, weight, original index
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by start time
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // next[i] = first interval with start > current end
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {

            int l = i + 1;
            int r = n - 1;
            int ans = n;

            while (l <= r) {

                int mid = l + (r - l) / 2;

                if (arr[mid][0] > arr[i][1]) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            next[i] = ans;
        }

        // dp[i][k] = best answer from i, taking at most k intervals
        long[][] dp = new long[n + 1][5];

        // Store selected indices
        List<Integer>[][] choice = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                choice[i][k] = new ArrayList<>();
            }
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Skip current interval
                dp[i][k] = dp[i + 1][k];
                choice[i][k] = new ArrayList<>(choice[i + 1][k]);

                // Take current interval
                long takeScore = arr[i][2] + dp[next[i]][k - 1];

                List<Integer> takeList =
                    new ArrayList<>(choice[next[i]][k - 1]);

                takeList.add(arr[i][3]);

                Collections.sort(takeList);

                if (takeScore > dp[i][k]) {

                    dp[i][k] = takeScore;
                    choice[i][k] = takeList;

                } else if (takeScore == dp[i][k]) {

                    if (smaller(takeList, choice[i][k])) {
                        choice[i][k] = takeList;
                    }
                }
            }
        }

        List<Integer> ans = choice[0][4];

        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    // Check lexicographically smaller
    boolean smaller(List<Integer> a, List<Integer> b) {

        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}