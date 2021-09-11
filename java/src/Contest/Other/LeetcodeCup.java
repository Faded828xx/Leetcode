package Contest.Other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2021/9/11
 */
public class LeetcodeCup {




    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        Map<Integer, Integer> mapS = new HashMap<>();
        Map<Integer, Integer> mapT = new HashMap<>();
        for(int[] s : source) {
            for (int s1 : s)
                mapS.put(s1, mapS.getOrDefault(s1, 0) + 1);
        }


        for(int[] t : target) {
            for (int t1 : t)
                mapT.put(t1, mapT.getOrDefault(t1, 0) + 1);
        }
        int res = 0;
        for(int k : mapS.keySet()) {
            int a = mapT.getOrDefault(k, 0);
            int b = mapS.get(k);
            if(a >= b) continue;
            res += (b - a);
        }
        return res;

    }


    // 比赛的时候一直挂 赛后看到错误用例 一会就调出来了 好菜啊
    public static int maxmiumScore(int[] cards, int cnt) {
        int[][] dp = new int[cnt + 1][2];
        for(int[] d : dp)
            Arrays.fill(d, -1);

        dp[0][0] = 0;
        dp[0][1] = 0;

        int len = cards.length;
        Integer[] cards2 = new Integer[len];
        for(int i = 0; i < len; i++)
            cards2[i] = cards[i];
        Arrays.sort(cards2, (a1, a2) -> a2 - a1);

        int limit1 = 1;
        int limit2 = 1;
        for(int i = 0; i < len; i++) {
            int card = cards2[i];
            boolean isOdd = card % 2 == 1;
            int j = Math.min(i + 1, cnt);
            int limit = Math.min(limit1, limit2);
            for(; j >= limit; j--) {
                if(isOdd) {
                    if(dp[j - 1][1] != -1) {
                        dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + card);
                        limit1 = Math.max(limit1, j);
                    }
                    if(dp[j - 1][0] != -1 && j != 1) {
                        dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] + card);
                        limit2 = Math.max(limit2, j);
                    }
                } else {
                    if(dp[j - 1][1] != -1) {
                        dp[j][1] = Math.max(dp[j][1], dp[j - 1][1] + card);
                        limit2 = Math.max(limit2, j);
                    }
                    if(dp[j - 1][0] != -1 && j != 1) {
                        dp[j][0] = Math.max(dp[j][0], dp[j - 1][0] + card);
                        limit1 = Math.max(limit1, j);
                    }
                }
            }

            for(int[] d : dp)
                System.out.println(Arrays.toString(d));
            System.out.println("--------");

//            if(limit2 == cnt + 1 && limit1 == cnt + 1)
//                return dp[cnt][1];
        }

        if(limit1 == cnt && limit2 == cnt && dp[cnt][0] != -1 && dp[cnt][1] != -1)
            return dp[cnt][1];
        return dp[cnt][1] == -1 ? 0 : dp[cnt][1];
    }

    // 上一题贪心解法
    public int maxmiumScore2(int[] cards, int cnt) {
        int len = cards.length;
        Integer[] cards2 = new Integer[len];
        for(int i = 0; i < len; i++)
            cards2[i] = cards[i];
        Arrays.sort(cards2, (a1, a2) -> a2 - a1);

        int res = 0;
        for(int i = 0; i < cnt; i++)
            res += cards2[i];
        if(res % 2 == 0) return res;
        int preOdd = 0, preEven = 0, postOdd = 0, postEven = 0;
        for(int i = cnt - 1; i >= 0; i--) {
            if(preOdd == 0 && cards2[i] % 2 == 1)
                preOdd = cards2[i];
            if(preEven == 0 && cards2[i] % 2 == 0)
                preEven = cards2[i];
            if(preOdd != 0 && preEven != 0)
                break;
        }
        for(int i = cnt; i < len; i++) {
            if(postOdd == 0 && cards2[i] % 2 == 1)
                postOdd = cards2[i];
            if(postEven == 0 && cards2[i] % 2 == 0)
                postEven = cards2[i];
            if(postOdd != 0 && postEven != 0)
                break;
        }
        int diff1 = (preOdd != 0 && postEven != 0) ? preOdd - postEven : Integer.MAX_VALUE;;
        int diff2 = (preEven != 0 && postOdd != 0) ? preEven - postOdd : Integer.MAX_VALUE;
        if(diff1 == Integer.MAX_VALUE && diff2 == Integer.MAX_VALUE)
            return 0;
        return res - Math.min(diff1, diff2);
    }





    public static void main(String[] args) {
        int[] cards = new int[]{1,10,5,2,9};
        System.out.println(maxmiumScore(cards, 4));
    }

}
