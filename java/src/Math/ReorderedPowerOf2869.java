package Math;
//You are given an integer n. We reorder the digits in any order (including the
//original order) such that the leading digit is not zero. 
//
// Return true if and only if we can do this so that the resulting number is a p
//ower of two. 
//
// 
// Example 1: 
//
// 
//Input: n = 1
//Output: true
// 
//
// Example 2: 
//
// 
//Input: n = 10
//Output: false
// 
//
// Example 3: 
//
// 
//Input: n = 16
//Output: true
// 
//
// Example 4: 
//
// 
//Input: n = 24
//Output: false
// 
//
// Example 5: 
//
// 
//Input: n = 46
//Output: true
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 109 
// 
// Related Topics 数学 计数 枚举 排序 
// 👍 56 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class ReorderedPowerOf2869 {
    public boolean reorderedPowerOf2(int n) {
        // 这里直接打表了 当然也是程序跑出来的
        int[] pow = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048,
                4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576,
                2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728,
                268435456, 536870912, 1073741824};
        char[] nn = String.valueOf(n).toCharArray();
        Arrays.sort(nn);
        for(int num : pow) {
            char[] cur = String.valueOf(num).toCharArray();
            Arrays.sort(cur);
            if(Arrays.equals(nn, cur))
                return true;
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
