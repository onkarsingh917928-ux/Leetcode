class Solution {
    public int reverseDegree(String s) {
        int n = s.length();
        int ans = 0;

        for(int i = 0; i < n; i++) {
            int value = 'z' - s.charAt(i) + 1;
            ans += value * ( i + 1);
        }
        return ans;
    }
}