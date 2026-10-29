import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] tin, tout, depth, bit, pathMask;
    int[][] up;
    char[] chars;
    int timer, LOG, n;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        this.n = n;
        chars = s.toCharArray();

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        pathMask = new int[n];
        up = new int[LOG][n];
        bit = new int[n + 2];

        timer = 0;
        dfs(0, 0, 0);

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                if (chars[u] != c) {
                    int change = (1 << (chars[u] - 'a'))
                               ^ (1 << (c - 'a'));

                    rangeUpdate(tin[u], tout[u], change);
                    chars[u] = c;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int lca = getLCA(u, v);

                int maskU = pathMask[u] ^ prefix(tin[u]);
                int maskV = pathMask[v] ^ prefix(tin[v]);

                int mask = maskU ^ maskV
                         ^ (1 << (chars[lca] - 'a'));

                ans.add(Integer.bitCount(mask) <= 1);
            }
        }

        return ans;
    }

    private void dfs(int u, int parent, int parentMask) {
        tin[u] = ++timer;
        up[0][u] = parent;

        pathMask[u] = parentMask ^ (1 << (chars[u] - 'a'));

        for (int j = 1; j < LOG; j++) {
            up[j][u] = up[j - 1][up[j - 1][u]];
        }

        for (int v : adj[u]) {
            if (v == parent) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u, pathMask[u]);
        }

        tout[u] = timer;
    }

    private void rangeUpdate(int l, int r, int val) {
        update(l, val);
        update(r + 1, val);
    }

    private void update(int idx, int val) {
        while (idx <= n + 1) {
            bit[idx] ^= val;
            idx += idx & -idx;
        }
    }

    private int prefix(int idx) {
        int res = 0;
        while (idx > 0) {
            res ^= bit[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}