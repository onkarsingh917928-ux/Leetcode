class Pair {
    int first, second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
class StockSpanner {

    Stack<Pair> st;
    int ind;

    public StockSpanner() {
        st = new Stack<>();
        ind = -1;
    }

    public int next(int price) {
        ind++;

        while (!st.isEmpty() && st.peek().first <= price) {
            st.pop();
        }

        int ans = ind - (st.isEmpty() ? -1 : st.peek().second);

        st.push(new Pair(price, ind));

        return ans;
    }
}


/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */