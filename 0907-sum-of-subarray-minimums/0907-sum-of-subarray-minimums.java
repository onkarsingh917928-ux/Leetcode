class Solution {

    public int sumSubarrayMins(int[] arr) {

        int[] nse = nextSmallerElement(arr);
        int[] pse = previousSmallerElement(arr);

        long total = 0;
        int mod = 1000000007;

        for(int i = 0; i < arr.length; i++) {

            long left = i - pse[i];
            long right = nse[i] - i;

            total = (total + (left * right * arr[i]) % mod) % mod;
        }

        return (int)total;
    }


    public int[] nextSmallerElement(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Stack<Integer> st = new Stack<>();

    for(int i = n-1; i >= 0; i--) {

        while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
            st.pop();
        }

        ans[i] = st.isEmpty() ? n : st.peek();

        st.push(i);
    }

    return ans;
}


public int[] previousSmallerElement(int[] arr) {

    int n = arr.length;
    int[] ans = new int[n];

    Stack<Integer> st = new Stack<>();

    for(int i = 0; i < n; i++) {

        while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
            st.pop();
        }

        ans[i] = st.isEmpty() ? -1 : st.peek();

        st.push(i);
    }

    return ans;
}
}