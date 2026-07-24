class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int max = 0;
        int n = heights.length;
        int ele = 0;
        int nse = 0,pse = 0;

        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && heights[st.peek()] > heights[i]) {
                  ele = st.peek();
                  st.pop();
                   nse  = i;
                   pse = st.isEmpty() ? -1 : st.peek();

                   max =  Math.max(max , heights[ele] * (nse - pse - 1));
            }
            st.push(i);
        }

        while(!st.isEmpty()) {
            nse = n;
            ele = st.peek();
            st.pop();
            pse = st.isEmpty() ? -1 : st.peek();
            max = Math.max(max,heights[ele] * (nse - pse - 1));
        }
        return max;
    }
}