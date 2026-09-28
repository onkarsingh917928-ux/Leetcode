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
    private int postIndex;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // Store value -> index of inorder
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        postIndex = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {

        if (left > right) {
            return null;
        }

        // Last element of postorder is root
        int rootValue = postorder[postIndex--];

        TreeNode root = new TreeNode(rootValue);

        // Position of root in inorder
        int mid = map.get(rootValue);

        // IMPORTANT: build right subtree first
        root.right = build(postorder, mid + 1, right);

        // Then build left subtree
        root.left = build(postorder, left, mid - 1);

        return root;
    }
}