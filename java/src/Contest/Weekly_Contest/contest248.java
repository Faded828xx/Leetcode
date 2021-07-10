package Contest.Weekly_Contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class contest248 {
    // AC
    public int[] buildArray(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nums[nums[i]];
        }
        return res;
    }

    // AC
    public static int eliminateMaximum(int[] dist, int[] speed) {
        int len = dist.length;
        int[] time = new int[len];
        for (int i = 0; i < len; i++) {
            int cur = dist[i] / speed[i];
            if (dist[i] > cur * speed[i])
                cur++;
            time[i] = cur;
        }
        Arrays.sort(time);
        for (int i = 0; i < len; i++) {
            if (i >= time[i])
                return i;
        }
        return len;
    }

    // 数据大会超时 可以用模平方法？
    public static int countGoodNumbers(long n) {
        Map<Integer, Integer> map = new HashMap<>();
        return countGoodNumbers(n, map);
    }
    public static int countGoodNumbers(long n, Map<Integer, Integer> map) {
        if (n >= 500000) {
            long m = n / 2;
            return (int) ((long) countGoodNumbers(m) * (long) countGoodNumbers(n - m) % 1000000007);
        }
        if(!map.isEmpty() && map.containsKey((int)n)) return map.get(n);
        long count = n / 2;
        long odd = n % 2;
        long res = 1;
        while (count != 0) {
            res = (res * 20) % (1000000007);
            count--;
        }
        int ans =  odd == 0 ? (int) res : (int) (res * 5 % 1000000007);
        map.put((int)n, ans);
        return ans;
    }


    // 转化为字符串不可行 傻逼了
    public static int longestCommonSubpath(int n, int[][] paths) {
        int len = paths.length;
        String[] path = new String[len];
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < len; i++) {
            int[] p = paths[i];
            if (p.length < min) {
                min = p.length;
                minIndex = i;
            }
            StringBuilder sb = new StringBuilder();
            for (int j : p)
                sb.append(j);
            path[i] = sb.toString();
        }
        String str = path[minIndex];
        for (int i = min; i >= 1; i--) {
            for (int j = 0; j <= min - i; j++) {
                String sub = str.substring(j, j + i);
                boolean flag = true;
                for (int k = 0; k < len; k++) {
                    if (k == minIndex) continue;
                    if (path[k].indexOf(sub) == -1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countGoodNumbers(500000000));
    }
}
