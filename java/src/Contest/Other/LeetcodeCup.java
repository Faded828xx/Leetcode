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

//        System.out.println(Arrays.toString(cards2));


        int limit1 = 1;
        int limit2 = 1;
        for(int i = 0; i < len; i++) {
            int card = cards2[i];
            boolean isOdd = card % 2 == 1;
//            int j = Math.min(i + 1, cnt);
//            for(; j >= 1; j--) {
//                if(isOdd) {
//                    if(dp[j - 1][1] != -1)
//                        dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + card);
//                    if(dp[j - 1][0] != -1 && j != 1)
//                        dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] + card);
//                } else {
//                    if(dp[j - 1][1] != -1)
//                        dp[j][1] = Math.max(dp[j][1], dp[j - 1][1] + card);
//                    if(dp[j - 1][0] != -1 && j != 1)
//                        dp[j][0] = Math.max(dp[j][0], dp[j - 1][0] + card);
//                }
//            }


            if(isOdd) {
                if(dp[limit1 - 1][1] != -1) {
                    dp[limit1][0] = Math.max(dp[limit1][0], dp[limit1 - 1][1] + card);
                    if(limit1 != cnt)
                        limit1++;
                }
                if(dp[limit2 - 1][0] != -1 && limit2 != 1) {
                    dp[limit2][1] = Math.max(dp[limit2][1], dp[limit2 - 1][0] + card);
                    if(limit2 != cnt)
                        limit2++;
                }
            } else {
                if(dp[limit1 - 1][0] != -1 && limit1 != 1) {
                    dp[limit1][0] = Math.max(dp[limit1][0], dp[limit1 - 1][0] + card);
                    if(limit1 != cnt)
                        limit1++;
                }
                if(dp[limit2 - 1][1] != -1) {
                    dp[limit2][1] = Math.max(dp[limit2][1], dp[limit2 - 1][1] + card);
                    if(limit2 != cnt)
                        limit2++;
                }
            }

            for(int[] d : dp)
                System.out.println(Arrays.toString(d));
            System.out.println("--------");

//            if(limit2 == cnt + 1 && limit1 == cnt + 1)
//                return dp[cnt][1];
        }
        return dp[cnt][1] == -1 ? 0 : dp[cnt][1];
    }


    public static void main(String[] args) {
        int[] cards = new int[]{1,2,8,9};
        System.out.println(maxmiumScore(cards, 3));
    }

}
