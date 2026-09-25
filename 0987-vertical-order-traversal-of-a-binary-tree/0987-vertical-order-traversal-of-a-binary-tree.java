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

    class Pair {
        int val;
        int row;
        int col;

        Pair(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    List<Pair> list = new ArrayList<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        inorder(root, 0, 0);

        Collections.sort(list, (a, b) -> {
            if (a.col != b.col)
                return a.col - b.col;

            if (a.row != b.row)
                return a.row - b.row;

            return a.val - b.val;
        });

        List<List<Integer>> ans = new ArrayList<>();

        int col = Integer.MIN_VALUE;

        for (Pair p : list) {

            if (p.col != col) {
                ans.add(new ArrayList<>());
                col = p.col;
            }

            ans.get(ans.size() - 1).add(p.val);
        }

        return ans;
    }

    public void inorder(TreeNode root, int row, int col) {

        if (root == null)
            return;

        inorder(root.left, row + 1, col - 1);

        list.add(new Pair(root.val, row, col));

        inorder(root.right, row + 1, col + 1);
    }
}