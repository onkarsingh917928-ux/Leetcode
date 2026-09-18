class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> pre = new ArrayList<>();

        if (root == null) {
            return pre;
        }

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {

            TreeNode node = st.pop();

            pre.add(node.val);

            // Right first
            if (node.right != null) {
                st.push(node.right);
            }

            // Left second
            if (node.left != null) {
                st.push(node.left);
            }
        }

        return pre;
    }
}