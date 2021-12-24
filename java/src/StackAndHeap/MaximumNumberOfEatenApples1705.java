package StackAndHeap;
//There is a special kind of apple tree that grows apples every day for n days.
//On the ith day, the tree grows apples[i] apples that will rot after days[i] days
//, that is on day i + days[i] the apples will be rotten and cannot be eaten. On s
//ome days, the apple tree does not grow any apples, which are denoted by apples[i
//] == 0 and days[i] == 0. 
//
// You decided to eat at most one apple a day (to keep the doctors away). Note t
//hat you can keep eating after the first n days. 
//
// Given two integer arrays days and apples of length n, return the maximum numb
//er of apples you can eat. 
//
// 
// Example 1: 
//
// 
//Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
//Output: 7
//Explanation: You can eat 7 apples:
//- On the first day, you eat an apple that grew on the first day.
//- On the second day, you eat an apple that grew on the second day.
//- On the third day, you eat an apple that grew on the second day. After this d
//ay, the apples that grew on the third day rot.
//- On the fourth to the seventh days, you eat apples that grew on the fourth da
//y.
// 
//
// Example 2: 
//
// 
//Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
//Output: 5
//Explanation: You can eat 5 apples:
//- On the first to the third day you eat apples that grew on the first day.
//- Do nothing on the fouth and fifth days.
//- On the sixth and seventh days you eat apples that grew on the sixth day.
// 
//
// 
// Constraints: 
//
// 
// apples.length == n 
// days.length == n 
// 1 <= n <= 2 * 104 
// 0 <= apples[i], days[i] <= 2 * 104 
// days[i] = 0 if and only if apples[i] = 0. 
// 
// Related Topics 贪心 数组 堆（优先队列） 
// 👍 71 👎 0

import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumNumberOfEatenApples1705 {
    public int eatenApples(int[] apples, int[] days) {
        int len = apples.length;
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int cnt = 0;
        for(int day = 0;;day++) {
            // 前len天可添加苹果
            if(day < len) {
                // 第dd天还有a个苹果可以吃
                int a = apples[day];
                int d = days[day];
                int dd = day + d - 1;
                // 只能看今天的苹果能不能吃
                if(heap.isEmpty()) {
                    if(a == 0) continue;    // 不能吃
                    cnt++;
                    if(a > 1 && d > 1)  // 能保存到明天
                        heap.add(new int[]{dd, a - 1}); // 减掉今天吃的一个苹果
                    continue;
                }
                // 加入今天的苹果
                heap.add(new int[]{dd, a});
            }
            // 找最快烂的且在当天还没烂的苹果
            while(!heap.isEmpty() && heap.peek()[0] < day) heap.poll();
            if(heap.isEmpty()) if(day < len) continue; else break;
            cnt++;
            int[] cur = heap.peek();
            if(--cur[1] == 0) heap.poll();
        }
        return cnt;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
