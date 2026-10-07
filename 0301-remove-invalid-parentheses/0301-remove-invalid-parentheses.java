class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum removals
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        backtrack(
            s, 0, 0,
            leftRemove, rightRemove,
            new StringBuilder(),
            set
        );

        return new ArrayList<>(set);
    }

    private void backtrack(String s, int index, int balance,
                            int leftRemove, int rightRemove,
                            StringBuilder path,
                            Set<String> set) {

        if (balance < 0) {
            return;
        }

        if (index == s.length()) {
            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                set.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);

        // '('
        if (c == '(') {

            // Remove '('
            if (leftRemove > 0) {
                backtrack(
                    s, index + 1, balance,
                    leftRemove - 1, rightRemove,
                    path, set
                );
            }

            // Keep '('
            path.append('(');

            backtrack(
                s, index + 1, balance + 1,
                leftRemove, rightRemove,
                path, set
            );

            path.deleteCharAt(path.length() - 1);
        }

        // ')'
        else if (c == ')') {

            // Remove ')'
            if (rightRemove > 0) {
                backtrack(
                    s, index + 1, balance,
                    leftRemove, rightRemove - 1,
                    path, set
                );
            }

            // Keep ')'
            if (balance > 0) {
                path.append(')');

                backtrack(
                    s, index + 1, balance - 1,
                    leftRemove, rightRemove,
                    path, set
                );

                path.deleteCharAt(path.length() - 1);
            }
        }

        // Letter
        else {
            path.append(c);

            backtrack(
                s, index + 1, balance,
                leftRemove, rightRemove,
                path, set
            );

            path.deleteCharAt(path.length() - 1);
        }
    }
}