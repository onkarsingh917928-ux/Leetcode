class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> ans = solve(expression, 0, expression.length() - 1);

        List<String> result = new ArrayList<>(ans);
        Collections.sort(result);

        return result;
    }

    public Set<String> solve(String s, int l, int r) {

        Set<String> result = new HashSet<>();

        // Find top-level comma
        int level = 0;
        int start = l;

        for (int i = l; i <= r; i++) {

            char ch = s.charAt(i);

            if (ch == '{') {
                level++;
            } 
            else if (ch == '}') {
                level--;
            } 
            else if (ch == ',' && level == 0) {

                result.addAll(solve(s, start, i - 1));
                start = i + 1;
            }
        }

        // If comma was found
        if (start != l) {
            result.addAll(solve(s, start, r));
            return result;
        }

        // Remove outer braces
        if (s.charAt(l) == '{' && s.charAt(r) == '}') {

            int level2 = 0;
            boolean complete = true;

            for (int i = l; i <= r; i++) {

                if (s.charAt(i) == '{') {
                    level2++;
                } 
                else if (s.charAt(i) == '}') {
                    level2--;
                }

                // Outer braces close before the end
                if (level2 == 0 && i < r) {
                    complete = false;
                    break;
                }
            }

            if (complete) {
                return solve(s, l + 1, r - 1);
            }
        }

        // Concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        int i = l;

        while (i <= r) {

            Set<String> part;

            if (s.charAt(i) == '{') {

                int level3 = 0;
                int j = i;

                while (j <= r) {

                    if (s.charAt(j) == '{') {
                        level3++;
                    } 
                    else if (s.charAt(j) == '}') {
                        level3--;
                    }

                    if (level3 == 0) {
                        break;
                    }

                    j++;
                }

                part = solve(s, i + 1, j - 1);
                i = j + 1;

            } else {

                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(i)));
                i++;
            }

            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : part) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        return current;
    }
}