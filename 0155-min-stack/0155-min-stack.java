import java.util.Stack;

class MinStack {

    Stack<Long> st;
    long mini;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        if (st.isEmpty()) {
            st.push((long) val);
            mini = val;
        } else {
            if (val >= mini) {
                st.push((long) val);
            } else {
                st.push(2L * val - mini); 
                mini = val;
            }
        }
    }

    public void pop() {
        if (st.isEmpty()) {
            return;
        }

        long x = st.pop();

        if (x < mini) {
            mini = 2 * mini - x;
        }
    }

    public int top() {
        long x = st.peek();

        if (x >= mini) {
            return (int) x;
        }

        return (int) mini;
    }

    public int getMin() {
        return (int) mini;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */