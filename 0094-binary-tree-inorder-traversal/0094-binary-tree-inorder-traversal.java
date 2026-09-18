class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> in = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        TreeNode node = root;

        while (node != null || !st.isEmpty()) {

            // Go as left as possible
            while (node != null) {
                st.push(node);
                node = node.left;
            }

            // Left done, process Root
            node = st.pop();
            in.add(node.val);

            // Now go Right
            node = node.right;
        }

        return in;
    }
}