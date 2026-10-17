
class Solution {
    public int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        int ans = 0;

        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
                ans += 2;
            } else {
                set.add(ch);
            }
        }

        if (!set.isEmpty()) {
            ans++;
        }

        return ans;
    }
}
