import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] a = nums.clone();
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();
        int id = 0;

        for (int x : sorted) {
            if (!map.containsKey(x)) {
                map.put(x, id++);
            }
        }

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = map.get(a[i]);
        }

        Integer[] sa = new Integer[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int[] nr = new int[n];

        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] r = rank;

            Arrays.sort(sa, (x, y) -> {
                if (r[x] != r[y])
                    return Integer.compare(r[x], r[y]);

                int rx = x + len < n ? r[x + len] : -1;
                int ry = y + len < n ? r[y + len] : -1;

                return Integer.compare(rx, ry);
            });

            nr[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int x = sa[i - 1];
                int y = sa[i];

                int x2 = x + len < n ? rank[x + len] : -1;
                int y2 = y + len < n ? rank[y + len] : -1;

                nr[y] = nr[x];

                if (rank[x] != rank[y] || x2 != y2) {
                    nr[y]++;
                }
            }

            int[] temp = rank;
            rank = nr;
            nr = temp;

            if (rank[sa[n - 1]] == n - 1)
                break;
        }

        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int p = pos[i];

            if (p == 0) {
                h = 0;
                continue;
            }

            int j = sa[p - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[p] = h;

            if (h > 0) h--;
        }

        int ans = n;

        for (int i = 0; i < n; i++) {
            int p = pos[i];

            int left = p > 0 ? lcp[p] : 0;
            int right = p + 1 < n ? lcp[p + 1] : 0;

            int len = Math.max(left, right) + 1;

            if (len <= n - i) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }
}