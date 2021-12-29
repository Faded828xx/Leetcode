package String;
//Given a string s and a dictionary of strings wordDict, add spaces in s to cons
//truct a sentence where each word is a valid dictionary word. Return all such pos
//sible sentences in any order. 
//
// Note that the same word in the dictionary may be reused multiple times in the
// segmentation. 
//
// 
// Example 1: 
//
// 
//Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//Output: ["cats and dog","cat sand dog"]
// 
//
// Example 2: 
//
// 
//Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","p
//ineapple"]
//Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//Explanation: Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: []
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 20 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 10 
// s and wordDict[i] consist of only lowercase English letters. 
// All the strings of wordDict are unique. 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 
// 👍 546 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class WordBreakII140 {
    // dfs + 记忆化
    Map<Character, List<Integer>> map;
    List<String> dic;
    List<List<String>> visited;
    public List<String> wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        dic = wordDict;
        visited = new ArrayList<>(s.length());
        for(int i = 0; i < s.length(); i++)
            visited.add(null);
        for(int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            List<Integer> l = map.getOrDefault(word.charAt(0), new ArrayList<>());
            l.add(i);
            map.put(word.charAt(0), l);
        }
        return dfs(s, 0);
    }
    public List<String> dfs(String s, int ptr) {
        if(s.length() == 0) return new ArrayList<>();
        if(visited.get(ptr) != null) return visited.get(ptr);
        List<Integer> words = map.getOrDefault(s.charAt(0), new ArrayList<>());
        List<String> res = new ArrayList<>();
        for(int i : words) {
            String w = dic.get(i);
            int idx = s.indexOf(w);
            if(idx != 0) continue;
            if(w.length() == s.length()) {
//                return new ArrayList<>(){{add(w);}};
                res.add(w);
                continue;
            }
            List<String> next = dfs(s.substring(idx + w.length()), ptr + w.length());
            if(next.size() == 0) continue;
            for(String ss : next)
                res.add(w + " " + ss);
        }
        visited.set(ptr, res);
        return res;
    }

    public static void main(String[] args) {
        WordBreakII140 wordBreakII140 = new WordBreakII140();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        System.out.println(wordBreakII140.wordBreak(s, wordDict));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
