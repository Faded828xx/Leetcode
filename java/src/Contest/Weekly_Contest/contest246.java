package Contest.Weekly_Contest;

import java.util.*;

public class contest246 {
    public String largestOddNumber1(String num) {
        int len = num.length();
        for (int i = len - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    // Memory limit exceeded
    public int[] minDifference(int[] nums, int[][] queries) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        int resLen = queries.length;
        int[] res = new int[resLen];
        for (int i = 0; i <= len - 1; i++)
            dp[i][i] = Integer.MAX_VALUE;
        for(int i=0; i<=len-2; i++) {
            for(int j=0; j<=len-2-i; j++) {
                int tmp = Math.abs(nums[j]-nums[j+i+1])==0 ? Integer.MAX_VALUE : Math.abs(nums[j]-nums[j+i+1]);
                dp[j][j+i+1] = Math.min(tmp, dp[j][j+i]<dp[j+1][j+i+1]
                        ? dp[j][j+i] : dp[j+1][j+i+1]);
            }
        }

        int index = 0;
        for(int[] query : queries) {
            res[index] = dp[query[0]][query[1]] == Integer.MAX_VALUE ? -1 : dp[query[0]][query[1]];
            index++;
        }

        return res;
    }

    // Time limit exceeded
    public static int[] minDifference2(int[] nums, int[][] queries) {
        int len = nums.length;
        int[] dp = new int[len];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < queries.length; i++) {  // 超时 想用set存queries来解决
            set.add(queries[i][1]);
            set.add(queries[i][0]);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i <= len - 1; i++)
            dp[i] = Integer.MAX_VALUE;
        for (int i = 0; i <= len - 2; i++) {
            for (int j = len - 2 - i; j >= 0; j--) {
                int tmp = Math.abs(nums[j] - nums[j + i + 1]) == 0 ? Integer.MAX_VALUE : Math.abs(nums[j] - nums[j + i + 1]);
                dp[j + i + 1] = Math.min(tmp, Math.min(dp[j + i], dp[j + i + 1]));
//                int[] cur = new int[]{j, j+i+1};
//                if(map.containsKey(cur))
//                    res[map.get(cur)] = dp[j+i+1] == Integer.MAX_VALUE ? -1 : dp[j+i+1];
                if (!set.contains(j) || !set.contains(j+i+1)) continue;
                for (int k = 0; k < queries.length; k++) {
                    if (queries[k][0] == j && queries[k][1] == j + i + 1)
                        res[k] = dp[j + i + 1] == Integer.MAX_VALUE ? -1 : dp[j + i + 1];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,8};
        int[][] queries = new int[][]{{0,1},{1,2},{2,3},{0,3}};
        System.out.println(Arrays.toString(minDifference2(arr, queries)));
    }
}
