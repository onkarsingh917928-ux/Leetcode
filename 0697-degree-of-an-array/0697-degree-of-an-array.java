
class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        HashMap<Integer, Integer> first = new HashMap<>();
        HashMap<Integer, Integer> last = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            freq.put(x, freq.getOrDefault(x, 0) + 1);
            first.putIfAbsent(x, i);
            last.put(x, i);
        }

        int degree = 0;
        int ans = nums.length;

        for (int x : freq.keySet()) {
            degree = Math.max(degree, freq.get(x));
        }

        for (int x : freq.keySet()) {
            if (freq.get(x) == degree) {
                ans = Math.min(ans, last.get(x) - first.get(x) + 1);
            }
        }

        return ans;
    }
}
