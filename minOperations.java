class Solution {
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            long picks = getPicksUpTo(r) - getPicksUpTo(l - 1); // total "picks" needed for [l,r]
            ans += (picks + 1) / 2; // each operation does two picks -> ceil(picks/2)
        }
        return ans;
    }

    // Returns total number of picks required for all integers in [1..n].
    // For numbers in [4^k, 4^{k+1}-1] each needs (k+1) picks.
    private long getPicksUpTo(long n) {
        if (n <= 0) return 0;
        long res = 0;
        long powerOfFour = 1; // 4^0
        long ops = 0;         // picks-per-number for current block (starts at 1)
        while (powerOfFour <= n) {
            ops++;
            long left = powerOfFour;
            long right = Math.min(n, powerOfFour * 4 - 1);
            res += (right - left + 1) * ops;
            // prepare next block; safe because n <= 1e9 in constraints
            powerOfFour *= 4;
        }
        return res;
    }
}
