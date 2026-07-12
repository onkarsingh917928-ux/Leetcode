class Solution {
    public int secondsBetweenTimes(String startTime, String endTime) {
        return toSeconds(endTime) - toSeconds(startTime);
    }
    public int toSeconds(String s) {
        int h = Integer.parseInt(s.substring(0,2));
         int m = Integer.parseInt(s.substring(3,5));
         int n = Integer.parseInt(s.substring(6,8));

        return h * 3600 + m * 60 + n;
    }
}