class Solution {
    public ArrayList<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> ans = new ArrayList<>();

        Map<TreeNode, TreeNode> parent = new HashMap<>();

        // Build parent map
        buildParent(root, null, parent);

        // BFS from target
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int distance = 0;

        while (!q.isEmpty()) {

            if (distance == k) {
                while (!q.isEmpty()) {
                    ans.add(q.poll().val);
                }
                return ans;
            }

            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // Left child
                if (curr.left != null && visited.add(curr.left)) {
                    q.offer(curr.left);
                }

                // Right child
                if (curr.right != null && visited.add(curr.right)) {
                    q.offer(curr.right);
                }

                // Parent
                TreeNode p = parent.get(curr);

                if (p != null && visited.add(p)) {
                    q.offer(p);
                }
            }

            distance++;
        }

        return ans;
    }

    private void buildParent(
        TreeNode root,
        TreeNode parent,
        Map<TreeNode, TreeNode> map
    ) {
        if (root == null) {
            return;
        }

        if (parent != null) {
            map.put(root, parent);
        }

        buildParent(root.left, root, map);
        buildParent(root.right, root, map);
    }
}