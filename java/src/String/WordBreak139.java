package String;
//Given a string s and a dictionary of strings wordDict, return true if s can be
// segmented into a space-separated sequence of one or more dictionary words. 
//
// Note that the same word in the dictionary may be reused multiple times in the
// segmentation. 
//
// 
// Example 1: 
//
// 
//Input: s = "leetcode", wordDict = ["leet","code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
// 
//
// Example 2: 
//
// 
//Input: s = "applepenapple", wordDict = ["apple","pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pe
//n apple".
//Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s and wordDict[i] consist of only lowercase English letters. 
// All the strings of wordDict are unique. 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 
// 👍 1310 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class WordBreak139 {
    // dfs + 记忆化
    Map<Character, List<Integer>> map;
    List<String> dic;
    boolean[] visited;
    public boolean wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        dic = wordDict;
        visited = new boolean[s.length()];
        for(int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            List<Integer> l = map.getOrDefault(word.charAt(0), new ArrayList<>());
            l.add(i);
            if(l.size() == 1)
                map.put(word.charAt(0), l);
        }
        return dfs(s, 0);
    }
    public boolean dfs(String s, int ptr) {
        if(s.length() == 0) return true;
        if(visited[ptr]) return false;
        List<Integer> words = map.getOrDefault(s.charAt(0), new ArrayList<>());
        for(int i : words) {
            int idx = s.indexOf(dic.get(i));
            if(idx != 0) continue;
            if(dfs(s.substring(idx + dic.get(i).length()), ptr + dic.get(i).length())) return true;
        }
        visited[ptr] = true;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
