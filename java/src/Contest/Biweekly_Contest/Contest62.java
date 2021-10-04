package Contest.Biweekly_Contest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2021/10/4
 */
public class Contest62 {

    // AC  (T1)
    public int[][] construct2DArray(int[] original, int m, int n) {
        int l = original.length;
        if(l != m * n) return new int[][]{};
        int[][] res = new int[m][];
        for(int i = 0; i < m; i++) {
            int[] c = new int[n];
            for(int j = 0; j < n; j++)
                c[j] = original[i * n + j];
            res[i] = c;
        }
        return res;
    }

    // AC  (T2)
    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        int len = target.length();
        for(String num : nums) {
            int l = num.length();
            if(l >= len) continue;
            String s1 = target.substring(0, l);
            if(s1.equals(num)) {
                String ss1 = target.substring(l, len);
                res += map.getOrDefault(ss1, 0);
            }
            String s2 = target.substring(len - l, len);
            if(s2.equals(num)) {
                String ss2 = target.substring(0, len - l);
                res += map.getOrDefault(ss2, 0);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    // AC  (T4)
    public int waysToPartition(int[] nums, int k) {
        // 0～pivot-1的和为所有元素和的一半
        int len = nums.length;
        long[] preS = new long[len];    // 前缀和
        Map<Long, Integer> map = new HashMap<>();   // 每个前缀和出现次数
        preS[0] = nums[0];
        map.put(preS[0], 1);
        // pivot为1～len-1 map存放0～pivot-1的和 则对应为0～len-2
        for(int i = 1; i < len - 1; i++) {  // 构造前缀和数组
            preS[i] = preS[i - 1] + nums[i];
            map.put(preS[i], map.getOrDefault(preS[i], 0) + 1);
        }
        preS[len - 1] = preS[len - 2] + nums[len - 1];
        // 对当前元素和n map中n/2的数量即为pivot的数量
        long n = preS[len - 1];
        int res = 0;
        if(n % 2 == 0)
            res = map.getOrDefault(n / 2, 0);   // 不改动数组
        // 改动nums[i]
        Map<Long, Integer> m = new HashMap<>(); // 0到i-1中的前缀和
        for(int i = 0; i < len; i++) {  // 每个元素都可以修改
            int gap = k - nums[i];
            long nn = n + gap;  // 前缀和数组i～len-1都增大gap
            if(nn % 2 != 0) {   // 和为奇数 不存在pivot
                m.put((long)preS[i], m.getOrDefault(preS[i], 0) + 1);
                continue;
            }
            // 此时要在更改后的前缀和数组找到 nn/2 的个数 即为当前修改后数组的pivot的数量
            int n1 = m.getOrDefault(nn / 2, 0); // 0～i-1的前缀和未修改 从m中找nn/2
            // i～len-2的前缀和需增加gap 因此找到原前缀和数组中nn/2-gap的数量且索引为i～len-2 用两个map之差则得到该索引范围内的数量
            int n2 = m.getOrDefault(nn / 2 - gap, 0);
            int n3 = map.getOrDefault(nn / 2 - gap, 0);
            res = Math.max(res, n1 - n2 + n3);
            m.put((long)preS[i], m.getOrDefault(preS[i], 0) + 1);
        }
        return res;
    }

}
