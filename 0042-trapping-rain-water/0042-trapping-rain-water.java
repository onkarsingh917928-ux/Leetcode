class Solution {
    public int trap(int[] height) {
        int n = height.length;

        if (n == 0) return 0;

        int[] leftMax = prefix(height);
        int[] rightMax = suffix(height);

        int total = 0;

        for (int i = 0; i < n; i++) {
            total += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return total;
    }

    public int[] prefix(int[] height) {
        int n = height.length;
        int[] prefix = new int[n];

        prefix[0] = height[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }

        return prefix;
    }

    public int[] suffix(int[] height) {
        int n = height.length;
        int[] suffix = new int[n];

        suffix[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }

        return suffix;
    }
}