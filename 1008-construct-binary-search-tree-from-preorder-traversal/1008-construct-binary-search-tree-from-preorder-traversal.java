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
    int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int min, int max) {
        if (index == preorder.length) {
            return null;
        }

        int value = preorder[index];

        // Value doesn't belong to this subtree
        if (value < min || value > max) {
            return null;
        }

        // Create current node
        TreeNode root = new TreeNode(value);
        index++;

        // Build left subtree
        root.left = build(preorder, min, value);

        // Build right subtree
        root.right = build(preorder, value, max);

        return root;
    }
}