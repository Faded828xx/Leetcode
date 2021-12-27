package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/12/27
 */
public class Contest273 {
    // 补题
    // AC
    public boolean isSameAfterReversals(int num) {
        if(num == 0) return true;
        return !(num % 10 == 0);
    }


    // AC
    int[][] move = new int[][]{{1, 0}, {-1,0}, {0,1}, {0,-1}};   // D,U,R,L
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int len = s.length();
        int[] res = new int[len];
        for(int i = 0; i < len; i++) {
            char[] ss = s.substring(i).toCharArray();
            res[i] = go(startPos, ss, n);
        }
        return res;
    }
    // 从start开始按s指令行走,能走的步数
    public int go(int[] start, char[] s, int n) {
        int cnt = 0;
        int x = start[0], y = start[1];
        Map<Character, Integer> map = new HashMap<>(){{
            put('D', 0);
            put('U', 1);
            put('R', 2);
            put('L', 3);
        }};
        while(cnt < s.length) {
            int idx = map.get(s[cnt]);
            x += move[idx][0];
            y += move[idx][1];
            if(!(x >= 0 && x < n && y >= 0 && y < n))
                return cnt;
            cnt++;
        }
        return cnt;
    }


    // TLE
    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            int n = arr[i];
            List<Integer> l = map.getOrDefault(n, new ArrayList<>());
            l.add(i);
            map.put(n, l);
        }
        long[] res = new long[arr.length];
        for(int i = 0; i < arr.length; i++) {
            List<Integer> l = map.get(arr[i]);
            for(int idx : l)
                res[i] += Math.abs(idx - i);
        }
        return res;
    }
    // AC
    public long[] getDistances2(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            List<Integer> l = map.getOrDefault(arr[i], new ArrayList<>());
            l.add(i);
            map.put(arr[i], l);
        }
        long[] res = new long[arr.length];
        for(List<Integer> list : map.values()) {
            long[] l = new long[list.size()];   // 前缀和
            long[] r = new long[list.size()];   // 后缀和
            for(int i = 1; i < l.length; i++) {
                l[i] = l[i - 1] + (long) (list.get(i) - list.get(i - 1)) * i;
            }
            for(int i = r.length - 2; i >= 0; i--) {
                r[i] = r[i + 1] + (long) (list.get(i + 1) - list.get(i)) * (r.length - i - 1);
            }
            for(int i = 0; i < list.size(); i++) {
                res[list.get(i)] = l[i] + r[i];
            }
        }
        return res;
    }


    // AC
    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            int k = (nums[i] - nums[0]) / 2;
            if(k == 0) continue;
            int[] a = check(nums, k);
            if(a.length != 0) return a;
        }
        return new int[]{};
    }
    public int[] check(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = nums.length / 2;
        int[] res = new int[l];
        int idx = 0;
        for(int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        for(int n : nums) {
            if(map.get(n) == 0) continue;
            int next = n + 2 * k;
            if(!map.containsKey(next) || map.get(next) == 0) return new int[]{};
            int nn = Math.min(map.get(n), map.get(next));
            for(int i = 0; i < nn; i++)
                res[idx++] = n + k;
            map.put(n, map.get(n) - nn);
            map.put(next, map.get(next) - nn);
        }
        return res;
    }

    public static void main(String[] args) {
        Contest273 contest273 = new Contest273();
//        System.out.println(Arrays.toString(contest273.getDistances2(new int[]{2,1,3,1,2,3,3})));
        System.out.println(Arrays.toString(contest273.recoverArray(new int[]{1, 1, 3, 3})));
    }
}
