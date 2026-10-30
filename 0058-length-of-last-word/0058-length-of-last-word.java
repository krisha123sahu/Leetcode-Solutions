class Solution {
    public int lengthOfLastWord(String s) {

        int i = s.length() - 1;

        // Step 1: Ignore trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Step 2: Count characters of last word
        int count = 0;

        while (i >= 0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }
}
