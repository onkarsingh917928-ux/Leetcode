class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] sumSq;
    int[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new int[4 * n];
        sumSq = new int[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int prev = last[x];
            last[x] = i + 1;

            // Increase distinct counts for subarrays
            // starting after the previous occurrence.
            update(1, 1, n, prev + 1, i + 1, 1);

            // Sum of squares for subarrays ending at i.
            ans = (ans + sumSq[1]) % MOD;
        }

        return (int) ans;
    }

    void apply(int node, int l, int r, int val) {
        long len = r - l + 1L;

        sumSq[node] = (int) (
            (sumSq[node]
            + 2L * val * sum[node]
            + len * val * val) % MOD
        );

        sum[node] = (int) (
            (sum[node] + len * val) % MOD
        );

        lazy[node] += val;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = (l + r) / 2;
        int val = lazy[node];

        apply(node * 2, l, mid, val);
        apply(node * 2 + 1, mid + 1, r, val);

        lazy[node] = 0;
    }

    void update(int node, int l, int r,
                int ql, int qr, int val) {

        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);

        sum[node] = (int) (
            ((long) sum[node * 2] + sum[node * 2 + 1])
            % MOD
        );

        sumSq[node] = (int) (
            ((long) sumSq[node * 2] + sumSq[node * 2 + 1])
            % MOD
        );
    }
}