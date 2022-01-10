package DFS;
//An additive number is a string whose digits can form an additive sequence.
//
// A valid additive sequence should contain at least three numbers. Except for t
//he first two numbers, each subsequent number in the sequence must be the sum of 
//the preceding two. 
//
// Given a string containing only digits, return true if it is an additive numbe
//r or false otherwise. 
//
// Note: Numbers in the additive sequence cannot have leading zeros, so sequence
// 1, 2, 03 or 1, 02, 3 is invalid. 
//
// 
// Example 1: 
//
// 
//Input: "112358"
//Output: true
//Explanation: 
//The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
//1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
// 
//
// Example 2: 
//
// 
//Input: "199100199"
//Output: true
//Explanation: 
//The additive sequence is: 1, 99, 100, 199. 
//1 + 99 = 100, 99 + 100 = 199
// 
//
// 
// Constraints: 
//
// 
// 1 <= num.length <= 35 
// num consists only of digits. 
// 
//
// 
// Follow up: How would you handle overflow for very large input integers? 
// Related Topics 字符串 回溯 
// 👍 238 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class AdditiveNumber306 {
    String num;
    public boolean isAdditiveNumber(String num) {
        this.num = num;
        int len = num.length();
        for(int i = 0; i < len - 2; i++) {
            if(num.charAt(0) == '0' && i > 0) return false;
            for(int j = i + 1; j < len - 1; j++) {
                if(num.charAt(i + 1) == '0' && j > i + 1) break;
                if(check(i, j)) return true;
            }
        }
        return false;
    }
    public boolean check(int a, int b) {
        int len = num.length();
        int z = -1;
        while(b < len - 1) {
            long n1 = Long.parseLong(num.substring(z + 1, a + 1));
            long n2 = Long.parseLong(num.substring(a + 1, b + 1));
            String n3 = (n1 + n2) + "";
            int c = b + n3.length();
            if(c >= len) return false;
            if(num.substring(b + 1, c + 1).equals(n3)) {
                z = a;
                a = b;
                b = c;
            } else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        AdditiveNumber306 additiveNumber306 = new AdditiveNumber306();
        System.out.println(additiveNumber306.isAdditiveNumber("000"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
