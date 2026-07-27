class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;

            HashMap<Integer, Integer> map = new HashMap<>();
            boolean forward = nums[i] > 0;
            int curr = i;
            int step = 0;

            while (true) {
                // Direction changes
                if ((nums[curr] > 0) != forward)
                    break;

                // Self-loop
                int next = ((curr + nums[curr]) % n + n) % n;
                if (next == curr)
                    break;

                // Cycle found
                if (map.containsKey(curr))
                    return true;

                map.put(curr, step++);
                visited[curr] = true;
                curr = next;
            }
        }

        return false;
    }
}