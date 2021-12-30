package Array;
//Alice has some number of cards and she wants to rearrange the cards into group
//s so that each group is of size groupSize, and consists of groupSize consecutive
// cards. 
//
// Given an integer array hand where hand[i] is the value written on the ith car
//d and an integer groupSize, return true if she can rearrange the cards, or false
// otherwise. 
//
// 
// Example 1: 
//
// 
//Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//Output: true
//Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
// 
//
// Example 2: 
//
// 
//Input: hand = [1,2,3,4,5], groupSize = 4
//Output: false
//Explanation: Alice's hand can not be rearranged into groups of 4.
//
// 
//
// 
// Constraints: 
//
// 
// 1 <= hand.length <= 104 
// 0 <= hand[i] <= 109 
// 1 <= groupSize <= hand.length 
// 
//
// 
// Note: This question is the same as 1296: https://leetcode.com/problems/divide
//-array-in-sets-of-k-consecutive-numbers/ 
// Related Topics 贪心 数组 哈希表 排序 
// 👍 135 👎 0

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class HandOfStraights846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int len = hand.length;
        if(len % groupSize != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        while(!map.isEmpty()) {
            int first = map.firstKey();
            for(int i = 1; i < groupSize; i++) {
                if (!map.containsKey(first + i)) return false;
                int num = map.get(first + i) - 1;
                if (num == 0) map.remove(first + i);
                else map.put(first + i, num);
            }
            int num2 = map.get(first) - 1;
            if(num2 == 0) map.remove(first);
            else map.put(first, num2);
        }
        return true;
    }

    public boolean isNStraightHand2(int[] hand, int groupSize) {
        int len = hand.length;
        if(len % groupSize != 0) return false;
        // 维护当前最小值
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 维护每个数的频率
        Map<Integer, Integer> map = new HashMap<>();
        for(int h : hand) {
            if(!map.containsKey(h)) heap.add(h);
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        while(!heap.isEmpty()) {
            int first = heap.peek();
            for(int i = 1; i < groupSize; i++) {
                if(!map.containsKey(first + i)) return false;
                int num1 = map.get(first + i) - 1;
                if(num1 == 0) {
                    map.remove(first + i);
                    heap.remove(first + i);
                } else
                    map.put(first + i, num1);
            }
            int num2 = map.get(first) - 1;
            if(num2 == 0) {
                map.remove(first);
                heap.remove(first);
            } else
                map.put(first, num2);
        }
        return true;
    }

    public static void main(String[] args) {
        HandOfStraights846 handOfStraights846 = new HandOfStraights846();
        System.out.println(handOfStraights846.isNStraightHand2(new int[]{1,2,3,6,2,3,4,7,8}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
