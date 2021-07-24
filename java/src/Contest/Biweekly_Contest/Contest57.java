package Contest.Biweekly_Contest;

import java.util.Arrays;

public class Contest57 {

    // AC
    public boolean areOccurrencesEqual(String s) {
        int[] map = new int[26];
        char[] arr = s.toCharArray();
        for(char ch : arr) {
            map[ch - 'a']++;
        }
        int num = 0;
        boolean flag = false;
        for(int n : map) {
            if(n == 0) continue;
            else {
                if(flag == false) {
                    flag = true;
                    num = n;
                } else if(n != num)
                    return false;
            }
        }
        return true;

    }

    // 状态机吗？ 没思路 果断放弃
    public int smallestChair(int[][] times, int targetFriend) {
        int arrive = times[targetFriend][0];
        Arrays.sort(times, (times1, times2) -> times1[0] - times2[0]);
        int len = times.length;
        boolean[] state = new boolean[len];
        int minIndex = 0;

        return 0;
    }

    // 思路很清晰 TTL 但不知道咋优化 每次调用count都是独立的 咋联系起来啊
    public int[] canSeePersonsCount(int[] heights) {
        int len = heights.length;
        int[] res = new int[len];
        for(int i = 0; i < len; i++) {
            res[i] = count(heights, i + 1, heights[i]);
        }
        return res;
    }
    public static int count(int[] heights, int index, int preH) {
        int len = heights.length;
        if (index == len) return 0;
        int res = 0;
        int max = 0;
        for (int i = index; i < len; i++) {
            if (heights[i] >= preH)
                return res + 1;
            if (heights[i] > max) {
                max = heights[i];
                res++;
            }
        }
        return res;
    }

}
