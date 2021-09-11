package DP;//Given a positive integer n, return the number of the integers in the range [0,
// n] whose binary representations do not contain consecutive ones. 
//
// 
// Example 1: 
//
// 
//Input: n = 5
//Output: 5
//Explanation:
//Here are the non-negative integers <= 5 with their corresponding binary repres
//entations:
//0 : 0
//1 : 1
//2 : 10
//3 : 11
//4 : 100
//5 : 101
//Among them, only integer 3 disobeys the rule (two consecutive ones) and the ot
//her 5 satisfy the rule. 
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: 2
// 
//
// Example 3: 
//
// 
//Input: n = 2
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 109 
// 
// Related Topics 动态规划 
// 👍 141 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class NonNegativeIntegersWithoutConsecutiveOnes600 {
    // dfs超时
    int res;
    int len;
    public int findIntegers(int n) {
        int m = n;
        while(m != 0) {
            m >>= 1;
            len++;
        }
        dfs(n, 0, 0, 0);
        return res;
    }
    // preBit为1: cur->0  preBit为0: cur->0or1
    public void dfs(int n, int cur, int preBit, int l) {
        if(cur > n) return ;
        if(l == len) {
            res++;
            return ;
        }
        if(preBit == 0) {
            dfs(n, (cur << 1) + 1, 1, l + 1);
        }
        dfs(n, cur << 1, 0, l + 1);
    }

    public int findIntegers2(int n) {
        // s[i]:(0~2**(i+1)-1)中不包含连续1的个数
        int[][] s = new int[32][3];
        s[0][0] = s[0][1] = 1;
        s[0][2] = 2;
        for(int i = 1; i <= 31; i++) {
            s[i][0] = s[i - 1][2];
            s[i][1] = s[i - 1][0];
            s[i][2] = s[i][0] + s[i][1];
        }
        for(int i = 2; i <= 31; i++)
            s[i][2] = s[i - 1][2] + 1;
        int res = 0;
        int pre = 0;
        for(int i = 31; i >= 0; i--) {
            int cur = (n >> i) & 1; // 从低到高第i位数
            int p = pre;
            pre = cur;
            if(cur == 0 || p == 1) continue;
            res += s[i][2] - 1;
        }
        return res + 1;


    }
}
//leetcode submit region end(Prohibit modification and deletion)
