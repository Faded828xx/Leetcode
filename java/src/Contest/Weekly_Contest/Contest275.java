package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2022/1/9
 */
public class Contest275 {

    // AC
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for(int j = 0; j < n; j++) {
                if(!set.add(matrix[i][j])) return false;
                if(!set2.add(matrix[j][i])) return false;
            }
        }
        return true;
    }


    // 赛后AC
    public int minSwaps(int[] nums) {
        int len = nums.length;
        int cnt = 0;    // size of window
        for(int n : nums)
            if(n == 1) cnt++;
//        if(cnt == len) return 0;
        int cur = 0;    // count of 1 in current window
        for(int i = 0; i < cnt; i++) {
            if(nums[i] == 1)
                cur++;
        }
        // int res = 0; // bug
        int res = cur;    // the max of cur
        for(int i = 1; i < len; i++) {
            int idx = (int)(((long)i + cnt - 1) % len);
            cur = cur - (nums[i - 1] == 1 ? 1 : 0) + (nums[idx] == 1 ? 1 : 0);
            res = Math.max(res, cur);
        }
        return cnt - res;
    }


    // AC
    public int wordCount(String[] startWords, String[] targetWords) {
        Map<String, Integer> map = new HashMap<>();
        for(String tar : targetWords) {
            tar = convert(tar);
            map.put(tar, map.getOrDefault(tar, 0) + 1);
        }
        int res = 0;
        for(String start : startWords) {
            List<String> l = list(start);
            for(String s : l) {
                res += map.getOrDefault(s, 0);
                map.remove(s);
            }
        }
        return res;
    }
    String convert(String word) {
        char[] c = word.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
    List<String> list(String start) {
        List<String> l = new LinkedList<>();
        for(int i = 0; i < 26; i++) {
            char c = (char)('a' + i);
            if(start.contains(c + "")) continue;
            l.add(convert(start + c));
        }
        return l;
    }


    // AC 这题太傻叉了 贪心竟然过了 想到了进程调度 plant是cpu运行时间 grow是io时间
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int len = plantTime.length;
        int[][] flo = new int[len][];
        for(int i = 0; i < len; i++) {
            flo[i] = new int[2];
            flo[i][0] = plantTime[i];
            flo[i][1] = growTime[i];
        }
        Arrays.sort(flo, (a1, a2) -> {
            if(a1[1] == a2[1]) return a2[0] - a1[0];
            return a2[1] - a1[1];
        });
        int t = 0;
        int res = 0;
        for(int[] f : flo) {
            int p = f[0];
            int g = f[1];
            t = Math.max(t, res + p + g);
            res += p;
        }
        return t;
    }

    public static void main(String[] args) {
        Contest275 contest275 = new Contest275();
        int[] nums = new int[100000];
        Arrays.fill(nums, 0);
        nums[50] = 1;
        nums[9] = 1;
        nums[2] = 1;
        nums[100000 - 2] = 1;
        System.out.println(contest275.minSwaps(nums));

    }
}
