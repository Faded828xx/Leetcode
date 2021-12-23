package String;
//Given a string s, consider all duplicated substrings: (contiguous) substrings
//of s that occur 2 or more times. The occurrences may overlap. 
//
// Return any duplicated substring that has the longest possible length. If s do
//es not have a duplicated substring, the answer is "". 
//
// 
// Example 1: 
// Input: s = "banana"
//Output: "ana"
// Example 2: 
// Input: s = "abcd"
//Output: ""
// 
// 
// Constraints: 
//
// 
// 2 <= s.length <= 3 * 104 
// s consists of lowercase English letters. 
// 
// Related Topics 字符串 二分查找 后缀数组 滑动窗口 哈希函数 滚动哈希 
// 👍 179 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class LongestDuplicateSubstring1044 {
    // 暴力
    public String longestDupSubstring(String s) {
        int len = s.length();
        // i遍历目标长度
        for(int i = len - 1; i >= 0; i--) {
            Set<String> set = new HashSet<>();
            // 当前子串[j,j+i)
            for(int j = 0; j <= len - i; j++) {
                String ss = s.substring(j, j + i);
                if(!set.add(ss)) return ss;
            }
        }
        return "";
    }

    // 二分优化
    public String longestDupSubstring2(String s) {
        int l = 0;
        int r = s.length();
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(check(s, mid).equals(""))
                r = mid - 1;
            else l = mid + 1;
        }
        String ss = check(s, l);
        if(ss.equals("")) return check(s, l - 1);
        return ss;
    }
    String check(String s, int l) {
        if(l <= 0) return "";
        Set<String> set = new HashSet<>();
        for(int i = 0; i <= s.length() - l; i++) {
            String ss = s.substring(i, i + l);
            if(!set.add(ss)) return ss;
        }
        return "";
    }

    // 字符串hash优化
    long[] hash, p;
    public String longestDupSubstring3(String s) {
        int len = s.length();
        int P = 131313;
        hash = new long[len + 1];
        p = new long[len + 1];
        p[0] = 1;
        for(int i = 1; i <= len; i++) {
            hash[i] = hash[i - 1] * P + s.charAt(i - 1);    // s[i-1] + s[i-2]*P + s[i-3]*PP + s[i-4]*PPP + ...
            p[i] = p[i - 1] * P;    // pow(P, i)
        }
        int l = 0;
        int r = s.length();
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(check2(s, mid).equals(""))
                r = mid - 1;
            else l = mid + 1;
        }
        String ss = check2(s, l);
        if(ss.equals("")) return check2(s, l - 1);
        return ss;
    }
    // 利用前缀数组求长度为l的字符串hash
    // s[i+1,j+1] hash -> h[j] - h[i-1] * p[j-i+1] 例如 345 = 12345 - 12 * 100 底为10
    String check2(String s, int l) {
        if(l <= 0) return "";
        Set<Long> set = new HashSet<>();
        for(int i = l; i <= s.length(); i++) {
            long hash2 = hash[i] - hash[i - l] * p[l];
            if(!set.add(hash2)) return s.substring(i - l, i);
        }
        return "";
    }


}
//leetcode submit region end(Prohibit modification and deletion)
