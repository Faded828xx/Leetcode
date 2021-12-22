package String;//Given two strings a and b, return the minimum number of times you should repea
//t string a so that string b is a substring of it. If it is impossible for b to b
//e a substring of a after repeating it, return -1. 
//
// Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and rep
//eated 2 times is "abcabc". 
//
// 
// Example 1: 
//
// 
//Input: a = "abcd", b = "cdabcdab"
//Output: 3
//Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b 
//is a substring of it.
// 
//
// Example 2: 
//
// 
//Input: a = "a", b = "aa"
//Output: 2
// 
//
// Example 3: 
//
// 
//Input: a = "a", b = "a"
//Output: 1
// 
//
// Example 4: 
//
// 
//Input: a = "abc", b = "wxyz"
//Output: -1
// 
//
// 
// Constraints: 
//
// 
// 1 <= a.length <= 104 
// 1 <= b.length <= 104 
// a and b consist of lower-case English letters. 
// 
// Related Topics 字符串 字符串匹配 
// 👍 180 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class RepeatedStringMatch686 {
    // 字符串匹配算法 就不写了
    public int repeatedStringMatch(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int cnt = 1;
        StringBuilder sb = new StringBuilder(a);
        while(sb.length() < len2 && cnt++ > 0) sb.append(a);
        sb.append(a);
        int idx;
        if((idx = sb.indexOf(b)) == -1) return -1;
        return idx > cnt * len1 - len2 ? cnt + 1 : cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
