class Solution {

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", num, target, 0, 0, 0);
        return ans;
    }

    private void backtrack(List<String> ans, String path, String num,
                           int target, int index,
                           long currValue, long prevOperand) {

        if (index == num.length()) {
            if (currValue == target) {
                ans.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            // Skip numbers with leading zero
            if (i != index && num.charAt(index) == '0')
                break;

            String currStr = num.substring(index, i + 1);
            long currNum = Long.parseLong(currStr);

            // First number (no operator before it)
            if (index == 0) {
                backtrack(ans, currStr, num, target,
                        i + 1, currNum, currNum);
            } else {

                // Addition
                backtrack(ans,
                        path + "+" + currStr,
                        num,
                        target,
                        i + 1,
                        currValue + currNum,
                        currNum);

                // Subtraction
                backtrack(ans,
                        path + "-" + currStr,
                        num,
                        target,
                        i + 1,
                        currValue - currNum,
                        -currNum);

                // Multiplication
                backtrack(ans,
                        path + "*" + currStr,
                        num,
                        target,
                        i + 1,
                        currValue - prevOperand + prevOperand * currNum,
                        prevOperand * currNum);
            }
        }
    }
}