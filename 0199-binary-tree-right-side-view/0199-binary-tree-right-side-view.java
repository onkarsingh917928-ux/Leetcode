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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightView(root,0,ans);
        return ans;

    }
    public void rightView(TreeNode node,int curr,List<Integer> ds) {
        if(node == null) {
            return;
        }
        if(curr == ds.size()){
            ds.add(node.val);
        }
        rightView(node.right,curr + 1, ds);
        rightView(node.left, curr + 1,ds);
    }
}