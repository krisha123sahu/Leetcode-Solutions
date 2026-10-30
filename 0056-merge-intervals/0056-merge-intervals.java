import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {

        // Step 1: Sort intervals by starting value
        Arrays.sort(intervals, (a, b) -> 
            Integer.compare(a[0], b[0])
        );

        // Step 2: Store merged intervals
        List<int[]> result = new ArrayList<>();

        // Step 3: Traverse all intervals
        for (int[] interval : intervals) {

            // If result is empty OR no overlap
            if (result.isEmpty() ||
                result.get(result.size() - 1)[1] < interval[0]) {

                result.add(interval);

            } else {

                // Merge overlapping intervals
                int[] last = result.get(result.size() - 1);

                last[1] = Math.max(last[1], interval[1]);
            }
        }

        // Step 4: Convert list into 2D array
        return result.toArray(new int[result.size()][]);
    }
}
