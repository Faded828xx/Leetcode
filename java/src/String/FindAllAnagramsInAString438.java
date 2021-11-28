package String;//Given two strings s and p, return an array of all the start indices of p's ana
//grams in s. You may return the answer in any order. 
//
// An Anagram is a word or phrase formed by rearranging the letters of a differe
//nt word or phrase, typically using all the original letters exactly once. 
//
// 
// Example 1: 
//
// 
//Input: s = "cbaebabacd", p = "abc"
//Output: [0,6]
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
// 
//
// Example 2: 
//
// 
//Input: s = "abab", p = "ab"
//Output: [0,1,2]
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s and p consist of lowercase English letters. 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 665 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class FindAllAnagramsInAString438 {
    // map + 滑动窗口
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int lenS = s.length();
        int lenP = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        if(lenS < lenP) return res;
        int[] cur = new int[26];
        int[] tar = new int[26];
        for(int i = 0; i < lenP; i++) {
            cur[ss[i] - 'a']++;
            tar[pp[i] - 'a']++;
        }
        if(check(cur, tar))
            res.add(0);
        for(int i = lenP; i < lenS; i++) {
            cur[ss[i - lenP] - 'a']--;
            cur[ss[i] - 'a']++;
            if(check(cur, tar))
                res.add(i - lenP + 1);
        }
        return res;
    }
    public boolean check(int[] arr1, int[] arr2) {
        for(int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
