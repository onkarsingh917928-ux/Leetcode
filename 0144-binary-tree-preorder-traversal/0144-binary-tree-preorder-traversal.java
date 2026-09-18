/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    class Pair{
        TreeNode node;
        int state;

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        

        if(root == null) {
            return pre;
        }

        Stack<Pair> st = new Stack<Pair>();
        st.push(new Pair(root,1));

        while(!st.isEmpty()) {
            Pair it = st.pop();

            if(it.state == 1) {
                pre.add(it.node.val);
                it.state++;
                st.push(it);

                if(it.node.left != null) {
                    st.push(new Pair(it.node.left, 1));
                }
            }
            else if(it.state == 2) {
                it.state++;
                st.push(it);

                if(it.node.right != null) {
                    st.push(new Pair(it.node.right , 1));
                }
            }
        }
        return pre;
    }
}