class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save the string before '('
                stack.push(current);
                current = new StringBuilder();

            } else if (ch == ')') {
                // Reverse the content inside parentheses
                current.reverse();

                // Add it to the previous string
                StringBuilder previous = stack.pop();
                previous.append(current);

                current = previous;

            } else {
                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}