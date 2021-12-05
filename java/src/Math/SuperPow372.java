package Math;//Your task is to calculate ab mod 1337 where a is a positive integer and b is a
//n extremely large positive integer given in the form of an array. 
//
// 
// Example 1: 
// Input: a = 2, b = [3]
//Output: 8
// Example 2: 
// Input: a = 2, b = [1,0]
//Output: 1024
// Example 3: 
// Input: a = 1, b = [4,3,3,8,5,2]
//Output: 1
// Example 4: 
// Input: a = 2147483647, b = [2,0,0]
//Output: 1198
// 
// 
// Constraints: 
//
// 
// 1 <= a <= 231 - 1 
// 1 <= b.length <= 2000 
// 0 <= b[i] <= 9 
// b doesn't contain leading zeros. 
// 
// Related Topics 数学 分治 
// 👍 200 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SuperPow372 {
    // 十进制快速幂 做的很快啊
    public int superPow(int a, int[] b) {
        int MOD = 1337;
        a = a % MOD;
        int pre = 1;
        int idx = 0;
        int len = b.length;
        while(idx < len) {
            // pow(pre,10) * pow(a,b[idx])
            int x = 1;
            for(int i = 0; i < 10; i++)
                x = (x * pre) % MOD;
            for(int i = 0; i < b[idx]; i++)
                x = (x * a) % MOD;
            idx++;
            pre = x;
        }
        return pre;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
