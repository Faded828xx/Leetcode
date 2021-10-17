package Contest.Weekly_Contest;

import java.util.Arrays;

/**
 * @author faded828x
 * @date 2021/10/17
 */
public class Contest263 {

    // AC
    public boolean areNumbersAscending(String s) {
        String[] ss = s.split(" ");
        int pre = 0;
        for(String str : ss) {
            if(str.charAt(0) <= '9' && str.charAt(0) >= '0') {
                int cur = Integer.parseInt(str);
                if(cur <= pre) return false;
                pre = cur;
            }
        }
        return true;
    }

    // AC
    int res = 0;
    int max = 0;
    public int countMaxOrSubsets(int[] nums) {
        max = 0;
        for(int n : nums) {
            max |= n;
        }
        dfs(nums, 0, 0);
        return res;
    }
    public void dfs(int[] nums, int cur, int idx) {
        if(cur == max) {
            res += (1 << (nums.length - idx));
            return ;
        } else if(idx == nums.length) {
            if(cur == max) res++;
            return ;
        }
        dfs(nums, cur | nums[idx], idx + 1);
        dfs(nums, cur, idx + 1);
    }



    boolean flag = false;
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int tt = 0;
        int diff = 0;
        if(time <= change) {
            tt = change;
        } else {
            tt = time + change - (time % change);
        }
        diff = tt - time;
        // dijkstra
        boolean[][] gg = new boolean[n + 1][n + 1];
        for(int[] edge : edges) {
            gg[edge[0]][edge[1]] = true;
            gg[edge[1]][edge[0]] = true;
        }
        int min2 = dfs(gg, 1, n);
        if(flag) min2 += 2;
        return min2 * tt - diff;
//      不需要dijkstra
//        int[] res = new int[n + 1];
//        boolean[] isOK = new boolean[n + 1];
//        Arrays.fill(res, Integer.MAX_VALUE);
//        res[1] = 0;
//
//        int secondMin = Integer.MAX_VALUE;
//
//        for(int i = 0; i < n; i++) {
//
//            int min = Integer.MAX_VALUE;
//            int idx = -1;
//            for(int j = 1; j <= n; j++) {
//                if(!isOK[j] && res[j] < min) {
//                    min = res[j];
//                    idx = j;
//                }
//            }
//            if(idx == -1) break;
//
//            isOK[idx] = true;
//
//            for(int j = 1; j <= n; j++) {
//                if(isOK[j] || !gg[idx][j]) continue;
//                if(j == n) {
//                    if(res[j] == Integer.MAX_VALUE)
//                        res[j] = res[idx] + 1;
//                    else if(secondMin == Integer.MAX_VALUE) {
//                        if(res[j] > res[idx] + 1) {
//                            secondMin = res[j];
//                            res[j] = res[idx] + 1;
//                        } else if(res[j] < res[idx] + 1)
//                            secondMin = res[idx] + 1;
//                    } else {
//                        if(res[idx] + 1 == res[j] || res[idx] + 1 == secondMin) continue;
//                        int a = res[j];
//                        int b = secondMin;
//                        int c = res[idx] + 1;
//                        if(c > b) continue;
//                        if(c > a) secondMin = c;
//                        else if(c < a) {
//                            secondMin = a;
//                            res[j] = c;
//                        }
//                    }
//                } else {
//                    res[j] = Math.min(res[j], res[idx] + 1);
//                }
//            }
//
//        }
//        System.out.println(res[n]);
//        System.out.println(secondMin);
//        return secondMin == Integer.MAX_VALUE ? (res[n] + 2) * tt - diff : secondMin * tt - diff;
    }
    // 双向边 这里递归爆栈了
    public int dfs(boolean[][] gg, int from, int n) {
        if(from == n) return 0;
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            if(i != from && gg[from][i]) {
                int dis = dfs(gg, i, n);
                if(dis == min || dis == min2 || dis > min2) continue;
                else if(dis > min) min2 = dis;
                else if(dis < min) {
                    min2 = min;
                    min = dis;
                }
            }
        }
        if(from == 1) {
            if(min2 == Integer.MAX_VALUE) {
                flag = true;
                return min + 1;
            } else return min2 + 1;
        }
        return min + 1;
    }

    public static void main(String[] args) {
        Contest263 contest263 = new Contest263();
        System.out.println(contest263.secondMinimum(5, new int[][]{{1,2}, {1,3}, {1,4}, {3,4}, {4,5}}, 3, 5));
    }

}
