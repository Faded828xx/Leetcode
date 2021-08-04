package Array;
//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
//
// 示例 1: 
//
// 
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是: 
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
// 
//
// 注意: 
//
// 
// 数组长度不超过1000。 
// 数组里整数的范围为 [0, 1000]。 
// 
// Related Topics 贪心 数组 双指针 二分查找 排序 
// 👍 197 👎 0

import java.util.Arrays;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class ValidTriangleNumber611 {
    // 暴力 O(n^3)
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for(int i = 0; i <= len - 3; i++) {
            int a = nums[i];
            if(a == 0) continue;
            for(int j = i + 1; j <= len - 2; j++) {
                int b = nums[j];
                if(b == 0) continue;
                for(int k = j + 1; k <= len - 1; k++) {
                    int c = nums[k];
                    if(c == 0) continue;
                    if(a + b > c) res++;
                    else break;
                }
            }
        }
        return res;
    }

    // 寻找k用TreeMap 也可以用二分
    public int triangleNumber2(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < len; i++) {
            if(!map.containsKey(nums[i]))
                map.put(nums[i], i);
        }
        for(int i = 0; i <= len - 3; i++) {
            int a = nums[i];
            if(a == 0) continue;
            for(int j = i + 1; j <= len - 2; j++) {
                int b = nums[j];
                if(b == 0) continue;
//                int cIndex = map.getOrDefault(map.ceilingKey(a + b), len);
                Integer cMax;
                int cIndex = (cMax = map.ceilingKey(a + b)) == null ? len : map.get(cMax);
                res += (cIndex - j - 1);
            }
        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
