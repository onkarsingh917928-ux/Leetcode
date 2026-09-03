class Solution {
    public int characterReplacement(String s, int k) {
        int l = 0;
        int maxFreq = 0;
        int maxLen = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int r = 0; r < s.length(); r++) {

            char ch = s.charAt(r);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            maxFreq = Math.max(maxFreq, map.get(ch));

            // Characters that need to be replaced
            while ((r - l + 1) - maxFreq > k) {
                char leftChar = s.charAt(l);
                map.put(leftChar, map.get(leftChar) - 1);
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }
}