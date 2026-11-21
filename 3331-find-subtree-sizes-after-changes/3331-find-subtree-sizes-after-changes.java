
class Solution {
    int[] ans;
    int[] newParent;
    ArrayList<Integer>[] adj;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        newParent = parent.clone();

        // Store nearest ancestor for each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Find new parents
        dfs(0, last, s);

        // Build modified tree
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        ans = new int[n];
        calculate(0, tree);

        return ans;
    }

    void dfs(int node, int[] last, String s) {
        int c = s.charAt(node) - 'a';

        int old = last[c];

        if (node != 0 && old != -1) {
            newParent[node] = old;
        }

        last[c] = node;

        for (int child : adj[node]) {
            dfs(child, last, s);
        }

        last[c] = old;
    }

    int calculate(int node, ArrayList<Integer>[] tree) {
        int size = 1;

        for (int child : tree[node]) {
            size += calculate(child, tree);
        }

        ans[node] = size;
        return size;
    }
}
