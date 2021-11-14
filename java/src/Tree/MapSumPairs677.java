package Tree;
//Design a map that allows you to do the following:
//
// 
// Maps a string key to a given value. 
// Returns the sum of the values that have a key with a prefix equal to a given 
//string. 
// 
//
// Implement the MapSum class: 
//
// 
// MapSum() Initializes the MapSum object. 
// void insert(String key, int val) Inserts the key-val pair into the map. If th
//e key already existed, the original key-value pair will be overridden to the new
// one. 
// int sum(string prefix) Returns the sum of all the pairs' value whose key star
//ts with the prefix. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MapSum", "insert", "sum", "insert", "sum"]
//[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//Output
//[null, null, 3, null, 5]
//
//Explanation
//MapSum mapSum = new MapSum();
//mapSum.insert("apple", 3);  
//mapSum.sum("ap");           // return 3 (apple = 3)
//mapSum.insert("app", 2);    
//mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
// 
//
// 
// Constraints: 
//
// 
// 1 <= key.length, prefix.length <= 50 
// key and prefix consist of only lowercase English letters. 
// 1 <= val <= 1000 
// At most 50 calls will be made to insert and sum. 
// 
// Related Topics 设计 字典树 哈希表 字符串 
// 👍 120 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MapSumPairs677 {

    Trie trie;
    Map<String, Integer> map;


    public MapSumPairs677() {
        trie = new Trie();
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        if(map.containsKey(key))
            trie.insert(key, 0, -map.get(key));
        map.put(key, val);
        trie.insert(key, 0, val);
    }
    
    public int sum(String prefix) {
        return trie.sum(prefix, 0);
    }


    class Trie {
        int value;
//        boolean end;
        Trie[] children = new Trie[26];
        void insert(String key, int idx, int val) {
            value += val;
            if(idx == key.length()) {
//                if(end) value = val;
//                end = true;
                return ;
            }
            int next = key.charAt(idx) - 'a';
            if(children[next] == null)
                children[next] = new Trie();
            children[next].insert(key, idx + 1, val);
        }

        int sum(String prefix, int idx) {
            int next = prefix.charAt(idx) - 'a';
            if(children[next] == null) return 0;
            if(idx == prefix.length() - 1)
                return children[next].value;
            return children[next].sum(prefix, idx + 1);
        }

    }

    public static void main(String[] args) {
        MapSumPairs677 mapSumPairs677 = new MapSumPairs677();
        mapSumPairs677.insert("apple", 3);
        System.out.println(mapSumPairs677.sum("apple"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
