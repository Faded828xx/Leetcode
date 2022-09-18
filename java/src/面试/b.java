package 面试;

import java.util.*;

/**
 * @author faded828x
 * @date 2022/8/30
 */

// Keep 笔试
public class b {
    public static void main(String[] args) {
        b b = new b();
        System.out.println(b.maximumSwap(222));
    }


    public static int[] process(int[][] queries) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            int a = q[0];
            int b = q[1];
            if (a == 1) deque.addFirst(b);
            else if (a == 2) deque.addLast(b);
            else if (a == 0) {
                int size = deque.size();
                int cnt = 1;
                for (int n : deque) {
                    if (n == b) {
                        break;
                    }
                    cnt++;
                }
                res.add(Math.min(cnt - 1, size - cnt));
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);
        return ans;
    }


    public int maximumSwap(int num) {
        String s = ((Integer) num).toString();
        int cnt = 0;
        String res = "";
        while (cnt <= s.length()) {
            res = s.substring(0, cnt) + fun(s.substring(cnt, s.length()));
            if (!res.equals(s)) break;
            cnt++;
        }
        return Integer.parseInt(res);
    }

    public String fun(String s) {
        if(s.length() == 0) return "";
        int idx = s.length() - 1;
        char c = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) > c) {
                c = s.charAt(i);
                idx = i;
            }
        }
        char[] cc = s.toCharArray();
        char d = cc[0];
        cc[0] = cc[idx];
        cc[idx] = d;
        return new String(cc);
    }


    public int[] sleepTime(int[] timeToBed, int[][] alarms) {
        Arrays.sort(alarms, (o1, o2) -> {
            if(o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        int[] res = new int[2];
        int i = 0;
        for(; i < alarms.length; i++) {
            int[] al = alarms[i];
            if(al[0] == timeToBed[0] && al[1] >= timeToBed[1]) break;
            if(al[0] > timeToBed[0]) break;
        }
        if(i < alarms.length) {
            int min1 = timeToBed[0] * 60 + timeToBed[1];
            int min2 = alarms[i][0] * 60 + alarms[i][1];
            int diff = min2 - min1;
            res[0] = diff / 60;
            res[1] = diff % 60;
        } else {
            int min1 = 24 * 60 - timeToBed[0] * 60 - timeToBed[1];
            int min2 = alarms[0][0] * 60 + alarms[0][1];
            int sum = min1 + min2;
            res[0] = sum / 60;
            res[1] = sum % 60;
        }
        return res;
    }

}
