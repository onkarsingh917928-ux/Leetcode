class Solution {
    public boolean checkString(String s) {
        int a = 0;
        int b = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'b') {
                b++;
            }

            if(s.charAt(i) == 'a') {
                if(b > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}