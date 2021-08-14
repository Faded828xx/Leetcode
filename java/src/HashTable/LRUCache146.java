package HashTable;
//Design a data structure that follows the constraints of a Least Recently Used
//(LRU) cache. 
//
// Implement the LRUCache class: 
//
// 
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
//
// int get(int key) Return the value of the key if the key exists, otherwise ret
//urn -1. 
// void put(int key, int value) Update the value of the key if the key exists. O
//therwise, add the key-value pair to the cache. If the number of keys exceeds the
// capacity from this operation, evict the least recently used key. 
// 
//
// The functions get and put must each run in O(1) average time complexity. 
//
// 
// Example 1: 
//
// 
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 104 
// 0 <= value <= 105 
// At most 2 * 105 calls will be made to get and put. 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1556 👎 0

import java.util.LinkedHashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache146 {

    // LinkedHashMap 实现 LRU
    Map<Integer, Integer> map;
    int capacity;

    // map 是动态的 指定了容量也没什么用 而且这个容量是会调整的 且获取不到
    // 因此需要添加一个容量属性
    public LRUCache146(int capacity) {
        map = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    // 访问需要将其移到最近的位置
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        int val = map.get(key);
        map.remove(key);
        map.put(key, val);
        return val;
    }

    // 更新只需要将其移到最近的位置
    // 添加需要将最远的元素踢出 然后将当前元素添加到最近的位置
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.remove(key);
        } else if(map.size() == capacity) {
            for(int head : map.keySet()) {
                map.remove(head);
                break;
            }
        }
        map.put(key, value);
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
