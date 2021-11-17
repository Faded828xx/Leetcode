package String;
//Given a string array words, return the maximum value of length(word[i]) * leng
//th(word[j]) where the two words do not share common letters. If no such two word
//s exist, return 0. 
//
// 
// Example 1: 
//
// 
//Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//Output: 16
//Explanation: The two words can be "abcw", "xtfn".
// 
//
// Example 2: 
//
// 
//Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
//Output: 4
//Explanation: The two words can be "ab", "cd".
// 
//
// Example 3: 
//
// 
//Input: words = ["a","aa","aaa","aaaa"]
//Output: 0
//Explanation: No such pair of words.
// 
//
// 
// Constraints: 
//
// 
// 2 <= words.length <= 1000 
// 1 <= words[i].length <= 1000 
// words[i] consists only of lowercase English letters. 
// 
// Related Topics 位运算 数组 字符串 
// 👍 209 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumProductOfWordLengths318 {
    boolean[][] ws;
    public int maxProduct(String[] words) {
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
        int res = 0;
        int l = words.length;
        ws = new boolean[l][26];
        for(int i = 0; i < l; i++) {
            String w = words[i];
            for(char c : w.toCharArray()) {
                ws[i][c - 'a'] = true;
            }
        }
        for(int i = 1; i < l; i++) {
            int l1 = words[i].length();
            int l2 = (res - 1) / l1 + 1;
            for(int j = i - 1; j >= 0; j--) {
                int l3 = words[j].length();
                if(l3 < l2) break;
                if(check(i, j)) {
                    res = Math.max(l3 * l1, res);
                }
            }
        }
        return res;
    }
    boolean check(int i, int j) {
        boolean[] w1 = ws[i];
        boolean[] w2 = ws[j];
        for(int k = 0;  k < 26; k++) {
            if (w1[k] && w2[k])
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
