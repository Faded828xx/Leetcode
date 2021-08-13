package Math;//Given an integer n, count the total number of digit 1 appearing in all non-neg
//ative integers less than or equal to n. 
//
// 
// Example 1: 
//
// 
//Input: n = 13
//Output: 6
// 
//
// Example 2: 
//
// 
//Input: n = 0
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 0 <= n <= 109 
// 
// Related Topics 递归 数学 动态规划 
// 👍 310 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class NumberOfDigitOne233 {

    // 自个写的模拟
    // 分别计数每一位上1的个数 注意这里都是十进制
    public static int countDigitOne(int n) {

        int pre = 0;
        int post = n;

        int res = 0;

        // 最多有十位数
        int index = 9;

        while(index >= 0) {

            // 该位的位权
            int weight = (int) Math.pow(10, index);

            // 该位的数值
            int cur = n / weight % 10;

            // 该位之后的数值
            post = post - cur * weight;

            // 三种情况： 0 -> 0,    1 -> post + 1,    >=1 -> weight
            int add = cur == 0 ? 0 : (cur == 1 ? post + 1 : weight);

            // 对于第i位数（个位为0）能出现的1的个数
            res += pre * weight + add;

            // 该位之前的数值
            pre = pre * 10 + cur;

            // 前进一位
            index--;
        }

        return res;


    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(13));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
