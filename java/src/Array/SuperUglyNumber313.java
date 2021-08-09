package Array;
//A super ugly number is a positive integer whose prime factors are in the array
// primes. 
//
// Given an integer n and an array of integers primes, return the nth super ugly
// number. 
//
// The nth super ugly number is guaranteed to fit in a 32-bit signed integer. 
//
// 
// Example 1: 
//
// 
//Input: n = 12, primes = [2,7,13,19]
//Output: 32
//Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
//super ugly numbers given primes = [2,7,13,19].
// 
//
// Example 2: 
//
// 
//Input: n = 1, primes = [2,3,5]
//Output: 1
//Explanation: 1 has no prime factors, therefore all of its prime factors are in
// the array primes = [2,3,5].
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 106 
// 1 <= primes.length <= 100 
// 2 <= primes[i] <= 1000 
// primes[i] is guaranteed to be a prime number. 
// All the values of primes are unique and sorted in ascending order. 
// 
// Related Topics 数组 哈希表 数学 动态规划 堆（优先队列） 
// 👍 183 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SuperUglyNumber313 {
    // primes 数组元素的组合 乘积最小的n个数
    // 最小堆 + set
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int count = 0;
        Set<Long> set = new HashSet<>();
        set.add(1L);
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1L);
        while(!priorityQueue.isEmpty()) {
            long top = priorityQueue.poll();
//            System.out.println(top);
            count++;
            if(count == n) return (int)top;
            for(int num : primes) {
                long cur = top * num;
                if (!set.contains(cur)) {
                    priorityQueue.add(cur);
                    set.add(cur);
                }
            }
        }
        return -1;
    }

    // TreeSet 代替 堆 + set
    public static int nthSuperUglyNumber2(int n, int[] primes) {
        TreeSet<Long> set = new TreeSet<>();
        set.add(1L);
        int count = 0;
        while(!set.isEmpty()) {
            long top = set.pollFirst();
            count++;
            if(count == n) return (int) top;
            for(int num : primes) {
                set.add(top * num);
            }
        }
        return -1;
    }

    public static int nthSuperUglyNumber3(int n, int[] primes) {
        int len = primes.length;
        int[] ptr = new int[len];
        Arrays.fill(ptr, 1);
        int[] res = new int[n + 1];
        res[1] = 1;
        for(int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for(int j = 0; j < len; j++) {
                if(primes[j] * res[ptr[j]] < min) {
                    min = primes[j] * res[ptr[j]];
                    index = j;
                }
            }
            if(res[i - 1] != min) {
                res[i] = min;
            } else i--;
//            System.out.println(Arrays.toString(res));
            ptr[index]++;
        }
        return res[n];
    }

    public static void main(String[] args) {
        int[] primes = new int[]{2,7,13,19};
        System.out.println(nthSuperUglyNumber3(12, primes));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
