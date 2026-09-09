
class Solution {
    public String minWindow(String s, String t) {

        int l = 0;
        int r = 0;
        int minlen = Integer.MAX_VALUE;
        int sIndex = -1;

        int n = s.length();
        int m = t.length();
        int count = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        // Store frequency of characters in t
        for (int i = 0; i < m; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        while (r < n) {

            // Include s[r]
            if (map.containsKey(s.charAt(r)) && map.get(s.charAt(r)) > 0) {
                count++;
            }

            if (map.containsKey(s.charAt(r))) {
                map.put(s.charAt(r), map.get(s.charAt(r)) - 1);
            }

            // Window contains all characters of t
            while (count == m) {

                // Update minimum window
                if (r - l + 1 < minlen) {
                    minlen = r - l + 1;
                    sIndex = l;
                }

                // Remove s[l]
                if (map.containsKey(s.charAt(l))) {
                    map.put(s.charAt(l), map.get(s.charAt(l)) + 1);

                    if (map.get(s.charAt(l)) > 0) {
                        count--;
                    }
                }

                l++;
            }

            r++;
        }

        return sIndex == -1 ? "" : s.substring(sIndex, sIndex + minlen);
    }
}





