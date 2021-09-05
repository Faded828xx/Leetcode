package Contest.Biweekly_Contest;


import java.util.*;

/**
 * @author faded828x
 * @date 2021/9/4
 */
public class Contest60 {

    // AC
    public int findMiddleIndex(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        if (pre[n - 1] - pre[0] == 0) return 0;
        if (pre[n - 2] == 0) return n - 1;
        for (int i = 1; i < n - 1; i++) {
            if ((pre[n - 1] - pre[i]) == (pre[i - 1]))
                return i;
        }
        return -1;
    }

    // AC
    public int[][] findFarmland(int[][] land) {
        List<List<Integer>> res = new ArrayList<>();
        int r = land.length;
        int c = land[0].length;
        boolean[][] vis = new boolean[r][c];
        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {
                if (land[i][j] == 0 || vis[i][j])
                    continue;
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j);
                int m;
                int n;
                for (m = i; m < r; m++) {
                    if (land[m][j] == 0) break;
                }
                for (n = j; n < c; n++) {
                    if (land[i][n] == 0) break;
                }
                for (int x = i; x <= m - 1; x++) {
                    for (int y = j; y <= n - 1; y++) {
                        vis[x][y] = true;
                    }
                }
                list.add(m - 1);
                list.add(n - 1);
                res.add(list);
            }
        }
        int[][] ans = new int[res.size()][4];
        int idx = 0;
        for (List<Integer> l : res) {
            int[] a = new int[4];
            for (int i = 0; i < 4; i++)
                a[i] = l.get(i);
            ans[idx++] = a;
        }
        return ans;
    }



    // T3 细节太多了 第二天才改完 AC了
    long res = 0;
    final int INF = 1000000007;
    Map<Integer, Set<Integer>> factor;
    public int numberOfGoodSubsets(int[] nums) {
        int[] map = new int[31];    // 只与元素个数有关 因此用map存起来
        for(int n : nums)
            map[n]++;
        factor = new HashMap<>();   // 存储30以内每个元素的不能共存的因子
        init(factor);
        // 2~30的所有情况
        dfs(map, 2, 1L, new HashSet<>());
        res--;  // 去掉空元素的情况
        // 数字1的情况 可以用多次数字1
        long cnt1 = 1L;
        for(int i = 0; i < map[1]; i++)
            cnt1 = (cnt1 << 1) % INF;   // 防止溢出2^map[1]
        return (int) ((cnt1 * res) % INF);
    }
    // idx:2~30当前元素 curS:当前的元素集合 curN:形成当前集合的情况数
    public void dfs(int[] map, int idx, long curN, Set<Integer> curS) {
        if(idx == 31) {
            res = (res + curN) % INF;
            return ;
        }
        boolean canBeChosen = true;
        if(map[idx] == 0 || !factor.containsKey(idx)) { // 原数组中不存在该元素或本身不满足条件的元素 直接跳过
            dfs(map, idx + 1, curN, curS);
            return ;
        }
        for(int fac : curS) {   // 当前集合是否存在idx元素的因子
            if (factor.get(idx).contains(fac)) {
                canBeChosen = false;
                break;
            }
        }
        // 回溯
        if(canBeChosen) {
            long tmp = (curN * map[idx]) % INF;
            for(int f : factor.get(idx))    // 添加当前元素的因子
                curS.add(f);
            curS.add(idx);  // 添加自身
            dfs(map, idx + 1, tmp, curS);
            for(int f : factor.get(idx))
                curS.remove(f);
            curS.remove(idx);
        }
        // 当前元素未被选中的情况
        dfs(map, idx + 1, curN, curS);
    }
    // 注意到有些元素自身分解就已不满足条件
    public void init(Map<Integer, Set<Integer>> factor) {
        factor.put(2, new HashSet<>());
        factor.put(3, new HashSet<>());
        factor.put(5, new HashSet<>());
        factor.put(6, new HashSet<>(){{add(2);add(3);}});
        factor.put(7, new HashSet<>());
        factor.put(10, new HashSet<>(){{add(2);add(5);}});
        factor.put(11, new HashSet<>());
        factor.put(13, new HashSet<>());
        factor.put(14, new HashSet<>(){{add(2);add(7);}});
        factor.put(15, new HashSet<>(){{add(3);add(5);}});
        factor.put(17, new HashSet<>());
        factor.put(19, new HashSet<>());
        factor.put(21, new HashSet<>(){{add(3);add(7);}});
        factor.put(22, new HashSet<>(){{add(2);add(11);}});
        factor.put(23, new HashSet<>());
        factor.put(26, new HashSet<>(){{add(2);add(13);}});
        factor.put(29, new HashSet<>());
        factor.put(30, new HashSet<>(){{add(2);add(3);add(5);add(6);add(10);add(15);}});
    }


    public static void main(String[] args) {
        int[] a = new int[]{10,11,5,1,10,1,3,1,26,11,6,1,1,15,1,7,22,1,1,1,1,1,23,1,29,5,6,1,1,29,1,1,21,19,1,1,1,2,1,11,1,15,1,22,14,1,1,1,1,6,7,1,14,3,5,1,22,1,1,1,17,1,29,2,1,15,10,1,5,7,1,1,1,30,1,30,1,21,10,1,1,1,1,1,2,6,5,7,3,1,1,19,29,1,7,13,14,1,5,26,19,11,1,1,1,1,1,1,1,1,22,15,1,1,13,1,17,1,1,1,13,6,1,10,1,1,17,1,1,3,14,7,17,1,13,1,1,1,1,1,11,1,1,6,1,1,1,1,1,2,1,30,2,26,1,1,14,1,26,29,30,1,13,21,1,1,14,21,1,23,1,15,23,21,1,30,19,19,1,10,23,3,3,17,22,2,26,1,11,1,23,1,1,1,15,1,1,13,1,1};
        int res = new Contest60().numberOfGoodSubsets(a);
        System.out.println(res);
    }

}
