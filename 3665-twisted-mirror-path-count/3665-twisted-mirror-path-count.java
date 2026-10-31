
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // Paths entering a cell from left (moving right)
        int[][] right = new int[m][n];

        // Paths entering a cell from above (moving down)
        int[][] down = new int[m][n];

        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {

                    // Normal cell: can move right or down
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (int)((right[i][j] + (long)down[i][j]) % MOD);
                    }

                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (int)((right[i][j] + (long)down[i][j]) % MOD);
                    }

                } else {

                    // Mirror cell:
                    // Entering from left -> reflect down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (int)((down[i + 1][j] + (long)right[i][j]) % MOD);
                    }

                    // Entering from above -> reflect right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (int)((right[i][j + 1] + (long)down[i][j]) % MOD);
                    }
                }
            }
        }

        return (int)(((long)right[m - 1][n - 1]
                    + down[m - 1][n - 1]) % MOD);
    }
}
