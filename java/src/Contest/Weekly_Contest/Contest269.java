package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/11/28
 */
public class Contest269 {

    // AC
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++)
            if(nums[i] == target)
                res.add(i);
        return res;
    }


    // AC
    public int[] getAverages(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        if(len <= k * 2) return res;
        int cur = 0;
        for(int i = 0; i <= 2 * k; i++)
            cur += nums[i];
        int d = 2 * k + 1;
        res[k] = cur / d;
        for(int i = k + 1; i <= len - k - 1; i++) {
            cur -= nums[i - k - 1];
            cur += nums[i + k];
            res[i] = cur / d;
        }
        return res;
    }

    // AC
    public int minimumDeletions(int[] nums) {
        int len = nums.length;
        if(len <= 2) return len;
        int idx1 = -1;  // min idx
        int idx2 = -1;  // max idx
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++) {
            int n = nums[i];
            if(n < min) {
                min = n;
                idx1 = i;
            }
            if(n > max) {
                max = n;
                idx2 = i;
            }
        }
        int l = Math.min(idx1, idx2);
        int r = Math.max(idx1, idx2);
        int n1 = l;
        int n2 = r - l - 1;
        int n3 = len - r - 1;
        // 三数中小的两个
        return n1 + n2 + n3 - Math.max(n1, Math.max(n2, n3)) + 2;
    }

    Map<Integer, Integer> res = new HashMap<>();
    int[][] meet;
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        meet = new int[n][n];
        for(int[] m : meet)
            Arrays.fill(m, -1);
        for(int[] m : meetings) {
            meet[m[0]][m[1]] = meet[m[0]][m[1]] == -1 ? m[2] : Math.min(meet[m[0]][m[1]], m[2]);
            meet[m[1]][m[0]] = meet[m[1]][m[0]] == -1 ? m[2] : Math.min(meet[m[1]][m[0]], m[2]);
        }
        dfs(0, 0);
        dfs(firstPerson, 0);
        return new ArrayList<>(res.keySet());
    }
    // 每个专家应该先搜索开会时间近的 即meet[person]的顺序搜索 好像还是错的
    public void dfs(int person, int time) {
        res.put(person, time);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(int i = 0; i < meet[person].length; i++) {
            treeMap.put(meet[person][i], i);
        }
        for(int key : treeMap.keySet()) {
            int next = treeMap.get(key);
            if(res.getOrDefault(next, Integer.MAX_VALUE) <= time) continue;
            if(key >= time)
                dfs(next, key);
        }
//        for(int i = 0; i < meet.length; i++) {
//            if(res.getOrDefault(i, Integer.MAX_VALUE) <= time) continue;
//            if(meet[person][i] >= time)
//                dfs(i, meet[person][i]);
//        }
    }
}
