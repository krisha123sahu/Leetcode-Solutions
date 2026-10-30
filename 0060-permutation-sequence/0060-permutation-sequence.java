import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {

        // Step 1: Calculate factorials
        int[] fact = new int[n + 1];
        fact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        // Step 2: Store numbers from 1 to n
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // Step 3: Convert k to zero-based index
        k--;

        StringBuilder result = new StringBuilder();

        // Step 4: Find each digit
        for (int i = n; i >= 1; i--) {

            int blockSize = fact[i - 1];

            int index = k / blockSize;

            result.append(numbers.get(index));

            numbers.remove(index);

            k = k % blockSize;
        }

        return result.toString();
    }
}
