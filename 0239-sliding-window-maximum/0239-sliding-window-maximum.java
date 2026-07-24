class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>(); 

        int n = nums.length;

        for(int i = 0; i < n; i++) {
            while(!deque.isEmpty() && deque.getFirst() <= i - k) {
                deque.removeFirst();
            }
            while(!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                 deque.removeLast();
            }
            deque.addLast(i);

            if(i >= k-1) {
              list.add(nums[deque.getFirst()]);
            }

        }
        int[] ans = new int[list.size()];

        for(int i = 0 ; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans; 
    }
}