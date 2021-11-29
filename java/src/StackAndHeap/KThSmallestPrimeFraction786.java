package StackAndHeap;
//You are given a sorted integer array arr containing 1 and prime numbers, where
// all the integers of arr are unique. You are also given an integer k. 
//
// For every i and j where 0 <= i < j < arr.length, we consider the fraction arr
//[i] / arr[j]. 
//
// Return the kth smallest fraction considered. Return your answer as an array o
//f integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j]. 
//
// 
// Example 1: 
//
// 
//Input: arr = [1,2,3,5], k = 3
//Output: [2,5]
//Explanation: The fractions to be considered in sorted order are:
//1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
//The third fraction is 2/5.
// 
//
// Example 2: 
//
// 
//Input: arr = [1,7], k = 1
//Output: [1,7]
// 
//
// 
// Constraints: 
//
// 
// 2 <= arr.length <= 1000 
// 1 <= arr[i] <= 3 * 104 
// arr[0] == 1 
// arr[i] is a prime number for i > 0. 
// All the numbers of arr are unique and sorted in strictly increasing order. 
// 1 <= k <= arr.length * (arr.length - 1) / 2 
// 
// Related Topics 数组 二分查找 堆（优先队列） 
// 👍 166 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class KThSmallestPrimeFraction786 {
    // TopK问题的堆解法
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 最大堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((a1, a2) -> {
            double n1 = (double) a1[0] / a1[1];
            double n2 = (double) a2[0] / a2[1];
            if(n2 - n1 < 0) return -1;
            else if(n2 - n1 == 0) return 0;
            return 1;
        });
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (heap.size() < k) {
                    heap.add(new int[]{arr[i], arr[j]});
                    continue;
                }
                int[] top = heap.peek();
                double n = (double) arr[i] / arr[j];
                double nn = (double) top[0] / top[1];
                if (n < nn) {
                    heap.poll();
                    heap.add(new int[]{arr[i], arr[j]});
                }
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        KThSmallestPrimeFraction786 kThSmallestPrimeFraction786 = new KThSmallestPrimeFraction786();
        int[] arr = new int[]{1, 2, 3, 5};
        int k = 3;
        System.out.println(Arrays.toString(kThSmallestPrimeFraction786.kthSmallestPrimeFraction(arr, k)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
