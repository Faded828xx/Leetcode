package Contest.Biweekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/9/18
 */
public class Contest61 {

    // AC
    public int countKDifference(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        for(int i = 0; i < len - 1; i++) {
            int a = nums[i];
            for(int j = i + 1; j < len; j++) {
                if(a - nums[j] == k || nums[j] - a == k)
                    res++;
            }
        }
        return res;
    }


    // AC
    public int[] findOriginalArray(int[] changed) {
        int len = changed.length;
        if(len % 2 == 1) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> s = new TreeSet<>();
        for(int n : changed) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            s.add(n);
        }
        int[] res = new int[len / 2];
        int idx = 0;
        int cnt0 = map.getOrDefault(0, 0);
        if(cnt0 != 0) {
            if(cnt0 % 2 == 1) return new int[0];
            for(int i = 0; i < cnt0/2; i++)
                res[idx++] = 0;
        }
        for(int k : s) {
            if(k == 0) continue;
            int c = map.get(k);
            if(c == 0) continue;
            int next = map.getOrDefault(k * 2, 0);
            if(next == 0) return new int[0];
            for(int i = 0; i < c; i++)
                res[idx++] = k;
            map.put(k * 2, next - c);
        }
        return res;
    }


    // AC
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a1, a2) -> a1[1] - a2[1]);
        int len = rides.length;
        int[] val = new int[len];
        for(int i = 0; i < len; i++) {
            int[] r = rides[i];
            val[i] = r[1] - r[0] + r[2];
        }
        long[] dp = new long[n + 1];
        long res = 0;
        for(int i = 0; i < len; i++) {
            int from = rides[i][0];
            int to = rides[i][1];
            int v = val[i];
            long preMax = 0;
            for(int j = from; j >= 0; j--) {
                if(dp[j] != 0) {
                    preMax = dp[j];
                    break;
                }
            }
            long cur = preMax + v;
            if(cur > res) {
                dp[to] = cur;
                res = cur;
            }
        }
        return res;
    }


    // AC
    public int minOperations(int[] nums) {
        int res = Integer.MAX_VALUE;
        int len = nums.length;
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i < len; i++) {
            if(!s.add(nums[i]))
                nums[i] = Integer.MIN_VALUE;
        }
        Arrays.sort(nums);
        for(int i = 0; i < len; i++) {
            if(i >= res) break;
            int j = i + 1;
            int cur = nums[i];
            while(j < len && nums[j] != Integer.MIN_VALUE && nums[j] <= cur + len - 1)
                j++;
            int tmp = len - j + i;
            res = Math.min(res, tmp);
        }
        return res;
    }

}
