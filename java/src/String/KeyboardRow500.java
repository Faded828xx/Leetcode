package String;
//Given an array of strings words, return the words that can be typed using lett
//ers of the alphabet on only one row of American keyboard like the image below. 
//
// In the American keyboard: 
//
// 
// the first row consists of the characters "qwertyuiop", 
// the second row consists of the characters "asdfghjkl", and 
// the third row consists of the characters "zxcvbnm". 
// 
//
// 
// Example 1: 
//
// 
//Input: words = ["Hello","Alaska","Dad","Peace"]
//Output: ["Alaska","Dad"]
// 
//
// Example 2: 
//
// 
//Input: words = ["omk"]
//Output: []
// 
//
// Example 3: 
//
// 
//Input: words = ["adsdf","sfd"]
//Output: ["adsdf","sfd"]
// 
//
// 
// Constraints: 
//
// 
// 1 <= words.length <= 20 
// 1 <= words[i].length <= 100 
// words[i] consists of English letters (both lowercase and uppercase). 
// 
// Related Topics 数组 哈希表 字符串 
// 👍 141 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class KeyboardRow500 {
    Set<Character> set1 = new HashSet<>();
    Set<Character> set2 = new HashSet<>();
    Set<Character> set3 = new HashSet<>();

    public String[] findWords(String[] words) {
        String s1 = "qwertyuiop";
        String s2 = "asdfghjkl";
        String s3 = "zxcvbnm";
        for(char c : s1.toCharArray())
            set1.add(c);
        for(char c : s2.toCharArray())
            set2.add(c);
        for(char c : s3.toCharArray())
            set3.add(c);
        List<String> res = new ArrayList<>();
        for(String word : words) {
            if(check(word))
                res.add(word);
        }
        String[] ans = new String[res.size()];
        for(int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);
        return ans;
    }
    boolean check(String word) {
        word = word.toLowerCase();
        char c = word.charAt(0);
        Set<Character> s = new HashSet<>();
        if(set1.contains(c)) s = set1;
        else if(set2.contains(c)) s = set2;
        else if(set3.contains(c)) s = set3;
        for(int i = 1; i < word.length(); i++) {
            if (!s.contains(word.charAt(i)))
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
