class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int i = 0;

        while (i < n) {

            // length k palindrome
            if (i + k <= n && Palindrom(s, i, i + k - 1)) {
                count++;
                i = i + k;
            }

            else if (i + k + 1 <= n && Palindrom(s, i, i + k)) {
                count++;
                i = i + k + 1;
            }

            else {
                i++;
            }
        }

        return count;
    }

    public boolean Palindrom(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}