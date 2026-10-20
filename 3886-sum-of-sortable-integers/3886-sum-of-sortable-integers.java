import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                int pivot = start;

                // Find the rotation point
                for (int j = start + 1; j < start + k; j++) {
                    if (nums[j - 1] > nums[j]) {
                        if (pivot != start) {
                            valid = false;
                            break;
                        }
                        pivot = j;
                    }
                }

                if (!valid) {
                    break;
                }

                // Check whether this block can become
                // its corresponding sorted segment
                for (int j = 0; j < k; j++) {
                    int idx = start + (pivot - start + j) % k;

                    if (nums[idx] != sorted[start + j]) {
                        valid = false;
                        break;
                    }
                }

                if (!valid) {
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}