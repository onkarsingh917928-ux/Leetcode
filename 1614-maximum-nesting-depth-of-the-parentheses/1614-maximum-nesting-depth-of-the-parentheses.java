class Solution {
    public int maxDepth(String s) {
        int curr = 0 , ans = 0;

        for(int i = 0 ; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                curr++;
            }
            ans = Math.max(ans,curr);
            if(s.charAt(i) == ')') {
                curr--;
            }
        }
        return ans;
    }
}