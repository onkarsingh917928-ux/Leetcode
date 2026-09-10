class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        for(int i = 1; i <= n/2; i++) {
            if(num.charAt(0) == '0' && i > 1) {
                break;
            }
            for (int j = i + 1; j <= n - 1; j++) {

                // second number cannot have leading zero
                if (num.charAt(i) == '0' && j - i > 1)
                    break;

                String a = num.substring(0, i);
                String b = num.substring(i, j);

                if (check(num, j, a, b))
                    return true;
            }
        }
        return false;
    }
    public boolean check(String s, int index, String a, String b) {
        boolean found = false;
        while(index < s.length()) {
            String sum = add(a,b);

            if(!s.startsWith(sum,index)) {
                return false;
            }
            index += sum.length();

            a = b;
            b = sum;
        }
        return true;
    }
    public String add(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while(i >= 0 || j >= 0 || carry > 0) { 
        
        int x = (i >= 0) ? a.charAt(i) - '0': 0;
        int y = (j >= 0) ? b.charAt(j) - '0': 0;

        int sum = x + y + carry;

        sb.append(sum % 10);

        carry = sum / 10;

        i--;
        j--;

        }
        return sb.reverse().toString();
    }

}