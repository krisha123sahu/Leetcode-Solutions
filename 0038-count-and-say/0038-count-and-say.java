
class Solution {
    public String countAndSay(int n) {
        String s = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int j = 0;

            while (j < s.length()) {
                char ch = s.charAt(j);
                int count = 0;

                while (j < s.length() && s.charAt(j) == ch) {
                    count++;
                    j++;
                }

                sb.append(count).append(ch);
            }

            s = sb.toString();
        }

        return s;
    }
}
