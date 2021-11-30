package Math;
//Given an integer n, return the nth digit of the infinite integer sequence [1,
//2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]. 
//
// 
// Example 1: 
//
// 
//Input: n = 3
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: n = 11
//Output: 0
//Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
// ... is a 0, which is part of the number 10.
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 数学 二分查找 
// 👍 205 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class NthDigit400 {
    // 写完发现是官方题解二 注意下索引从0还是1开始
    public int findNthDigit(int n) {
        if(n < 10) return n;
        int cnt = 9;
        int cur = 2;
        for(; cur < 11; cur++) {
            int num = cur * 9 * (int)Math.pow(10, cur - 1);
            if(n <= cnt + num || num < 0) {
                int start = (int) Math.pow(10, cur - 1);
                int nn = start + (n - cnt) / cur - ((n - cnt) % cur == 0 ? 1 : 0);
                int bit = (n - cnt) % cur == 0 ? cur : (n - cnt) % cur;
                // bit = bit == 0 ? cur : bit;
                return String.valueOf(nn).charAt(bit - 1) - '0';
            }
            cnt += num;
        }
        return -1;
    }

    public static void main(String[] args) {
        NthDigit400 nthDigit400 = new NthDigit400();
        System.out.println(nthDigit400.findNthDigit(11));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
