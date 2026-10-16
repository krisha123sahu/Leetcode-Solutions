
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] squareSum;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            // Update all subarrays ending at i
            update(1, 0, n - 1, prev + 1, i);

            // Sum of squared distinct counts
            ans = (ans + squareSum[1]) % MOD;

            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                       int left, int right) {

        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end);
            return;
        }

        pushDown(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] =
            (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end) {
        long length = end - start + 1L;

        // (x + 1)^2 = x^2 + 2x + 1
        squareSum[node] =
            (squareSum[node] + 2L * sum[node] + length) % MOD;

        sum[node] = (sum[node] + length) % MOD;

        lazy[node]++;
        lazy[node] %= MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        applyMultiple(node * 2, start, mid, lazy[node]);
        applyMultiple(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }

    private void applyMultiple(int node, int start, int end, long add) {
        long length = end - start + 1L;

        // (x + add)^2 = x^2 + 2*add*x + add^2
        long extra = (2L * add % MOD * sum[node]) % MOD;
        long addSquare = (add * add) % MOD * length % MOD;

        squareSum[node] =
            (squareSum[node] + extra + addSquare) % MOD;

        sum[node] = (sum[node] + add * length) % MOD;

        lazy[node] = (lazy[node] + add) % MOD;
    }
}
