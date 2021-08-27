package StackAndHeap;
//The median is the middle value in an ordered integer list. If the size of the
//list is even, there is no middle value and the median is the mean of the two mid
//dle values. 
//
// 
// For example, for arr = [2,3,4], the median is 3. 
// For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5. 
// 
//
// Implement the MedianFinder class: 
//
// 
// MedianFinder() initializes the MedianFinder object. 
// void addNum(int num) adds the integer num from the data stream to the data st
//ructure. 
// double findMedian() returns the median of all elements so far. Answers within
// 10-5 of the actual answer will be accepted. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//Output
//[null, null, null, 1.5, null, 2.0]
//
//Explanation
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0
// 
//
// 
// Constraints: 
//
// 
// -105 <= num <= 105 
// There will be at least one element in the data structure before calling findM
//edian. 
// At most 5 * 104 calls will be made to addNum and findMedian. 
// 
//
// 
// Follow up: 
//
// 
// If all integer numbers from the stream are in the range [0, 100], how would y
//ou optimize your solution? 
// If 99% of all integer numbers from the stream are in the range [0, 100], how 
//would you optimize your solution? 
// 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 
// 👍 479 👎 0

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class FindMedianFromDataStream295 {


    // lHeap小于等于中位数
    PriorityQueue<Integer> lHeap;
    // rHeap大于等于中位数
    PriorityQueue<Integer> rHeap;

    /** initialize your data structure here. */
    public FindMedianFromDataStream295() {

        // 大顶堆
        lHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        // 小顶堆
        rHeap = new PriorityQueue<>();

    }

    // 保证两个堆的元素个数差不超过1
    public void addNum(int num) {

        int lSize = lHeap.size();

        // 首次添加
        if(lSize == 0) {
            lHeap.add(num);
            return ;
        }

        int l = lHeap.peek();   // 小于等于当前中位数的最大值
        if(num <= l)
            lHeap.add(num); // 加入左堆
        else rHeap.add(num);    // 加入右堆

        // 调整两个堆 保持平衡
        lSize = lHeap.size();
        int rSize = rHeap.size();
        if(lSize == rSize + 2) {
            l = lHeap.poll();
            rHeap.add(l);
        } else if(rSize == lSize + 2) {
            int r = rHeap.poll();
            lHeap.add(r);
        }

    }
    
    public double findMedian() {
        int lSize = lHeap.size();
        int rSize = rHeap.size();
        if(lSize == rSize + 1)
            return lHeap.peek();
        else if(rSize == lSize + 1)
            return rHeap.peek();
        else return (lHeap.peek() + rHeap.peek()) / 2.0;

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
