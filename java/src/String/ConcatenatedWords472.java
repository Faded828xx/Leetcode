package String;
//Given an array of strings words (without duplicates), return all the concatena
//ted words in the given list of words. 
//
// A concatenated word is defined as a string that is comprised entirely of at l
//east two shorter words in the given array. 
//
// 
// Example 1: 
//
// 
//Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses"
//,"rat","ratcatdogcat"]
//Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
//Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
//"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
//"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat". 
//
// Example 2: 
//
// 
//Input: words = ["cat","dog","catdog"]
//Output: ["catdog"]
// 
//
// 
// Constraints: 
//
// 
// 1 <= words.length <= 104 
// 0 <= words[i].length <= 1000 
// words[i] consists of only lowercase English letters. 
// 0 <= sum(words[i].length) <= 105 
// 
// Related Topics 深度优先搜索 字典树 数组 字符串 动态规划 
// 👍 146 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class ConcatenatedWords472 {

    // TLE
    Map<Character, List<Integer>> map;
    String[] dict;
    Set<String> res;
    Map<String, Boolean> visited;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        map = new HashMap<>();
        dict = words;
        res = new HashSet<>();
        visited = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            if(word.length() == 0) continue;
            List<Integer> l = map.getOrDefault(word.charAt(0), new ArrayList<>());
            l.add(i);
            map.put(word.charAt(0), l);
            visited.put(word, true);
        }
        for(String w : words)
            if(dfs(w, true) && w.length() > 0)
                res.add(w);
        return new ArrayList<>(res);
    }
    public boolean dfs(String s, boolean first) {
        if(s.length() == 0) return true;
        if(!first && visited.containsKey(s)) return visited.get(s);
        List<Integer> l = map.getOrDefault(s.charAt(0), new ArrayList<>());
        for(int i : l) {
            String w = dict[i];
            if(w.length() > s.length()) continue;
            if(first && w.equals(s)) continue;
            int idx = s.indexOf(w);
            if(idx != 0) continue;
            if(dfs(s.substring(w.length()), false)) {
                visited.put(s, true);
                return true;
            }
        }
        if(!first)
            visited.put(s, false);
        return false;
    }

    public static void main(String[] args) {
        ConcatenatedWords472 concatenatedWords472 = new ConcatenatedWords472();
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        System.out.println(concatenatedWords472.findAllConcatenatedWordsInADict(words));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
