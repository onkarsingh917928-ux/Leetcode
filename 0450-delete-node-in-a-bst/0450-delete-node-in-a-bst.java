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
    public TreeNode deleteNode(TreeNode root, int key) {
        
        // Node not found
        if (root == null) {
            return null;
        }

        // Search in left subtree
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        
        // Search in right subtree
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        
        // Node found
        else {
            
            // Case 1: No left child
            if (root.left == null) {
                return root.right;
            }
            
            // Case 2: No right child
            if (root.right == null) {
                return root.left;
            }
            
            // Case 3: Both children exist
            TreeNode successor = root.right;
            
            // Find minimum node in right subtree
            while (successor.left != null) {
                successor = successor.left;
            }
            
            // Replace current node's value
            root.val = successor.val;
            
            // Delete the successor
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }
}