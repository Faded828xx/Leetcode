package String;
//Given a string s and a string array dictionary, return the longest string in t
//he dictionary that can be formed by deleting some of the given string characters
//. If there is more than one possible result, return the longest word with the sm
//allest lexicographical order. If there is no possible result, return the empty s
//tring. 
//
// 
// Example 1: 
//
// 
//Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//Output: "apple"
// 
//
// Example 2: 
//
// 
//Input: s = "abpcplea", dictionary = ["a","b","c"]
//Output: "a"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// 1 <= dictionary.length <= 1000 
// 1 <= dictionary[i].length <= 1000 
// s and dictionary[i] consist of lowercase English letters. 
// 
// Related Topics 数组 双指针 字符串 排序 
// 👍 171 👎 0


import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class LongestWordInDictionaryThroughDeleting524 {
    public String findLongestWord(String s, List<String> dictionary) {
        int lenS = s.length();
        int lenD = dictionary.size();

        int resIdx = -1;
        int resLen = 0;

        for(int i = 0; i < lenD; i++) {
            String str = dictionary.get(i);
            int l = str.length();
            if(l < resLen || l > lenS) continue;
            int ptr1 = 0, ptr2 = 0;
            boolean isOK = true;
            while(ptr1 < l) {
                char c = str.charAt(ptr1);
                if(ptr2 == lenS) {
                    isOK = false;
                    break;
                }
                while(ptr2 < lenS && s.charAt(ptr2++) != c);
                if(ptr2 == lenS && s.charAt(lenS - 1) != c) {
                    isOK = false;
                    break;
                }
                ptr1++;
            }
            if(l > resLen && isOK) {
                resIdx = i;
                resLen = l;
            } else if(l == resLen && isOK) {
                resIdx = dictionary.get(resIdx).compareTo(dictionary.get(i)) < 0 ? resIdx : i;
                resLen = dictionary.get(resIdx).length();
            }
        }


        return resIdx == -1 ? "" : dictionary.get(resIdx);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
