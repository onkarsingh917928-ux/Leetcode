class Solution {
    public int maximalRectangle(char[][] matrix) {
        int maxArea  = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
               if(matrix[i][j] == '1') {
                 heights[j]++;
               }else{
                  heights[j] = 0;
               }
            }
            int area  = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
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