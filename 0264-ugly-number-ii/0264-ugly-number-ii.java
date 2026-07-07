class Solution {
    public int nthUglyNumber(int n) {

        int[] ugly = new int[n];
        ugly[0] = 1;

        int a = 0, b = 0, c = 0;

        for(int i = 1; i < n; i++) {

            int x = ugly[a] * 2;
            int y = ugly[b] * 3;
            int z = ugly[c] * 5;

            int min = Math.min(x, Math.min(y, z));

            ugly[i] = min;

            if(min == x) a++;
            if(min == y) b++;
            if(min == z) c++;
        }

        return ugly[n-1];
    }
}