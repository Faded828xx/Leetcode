package StackAndHeap;//Suppose LeetCode will start its IPO soon. In order to sell a good price of its
// shares to Venture Capital, LeetCode would like to work on some projects to incr
//ease its capital before the IPO. Since it has limited resources, it can only fin
//ish at most k distinct projects before the IPO. Help LeetCode design the best wa
//y to maximize its total capital after finishing at most k distinct projects. 
//
// You are given n projects where the ith project has a pure profit profits[i] a
//nd a minimum capital of capital[i] is needed to start it. 
//
// Initially, you have w capital. When you finish a project, you will obtain its
// pure profit and the profit will be added to your total capital. 
//
// Pick a list of at most k distinct projects from given projects to maximize yo
//ur final capital, and return the final maximized capital. 
//
// The answer is guaranteed to fit in a 32-bit signed integer. 
//
// 
// Example 1: 
//
// 
//Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//Output: 4
//Explanation: Since your initial capital is 0, you can only start the project i
//ndexed 0.
//After finishing it you will obtain profit 1 and your capital becomes 1.
//With capital 1, you can either start the project indexed 1 or the project inde
//xed 2.
//Since you can choose at most 2 projects, you need to finish the project indexe
//d 2 to get the maximum capital.
//Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
// 
//
// Example 2: 
//
// 
//Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//Output: 6
// 
//
// 
// Constraints: 
//
// 
// 1 <= k <= 105 
// 0 <= w <= 109 
// n == profits.length 
// n == capital.length 
// 1 <= n <= 105 
// 0 <= profits[i] <= 104 
// 0 <= capital[i] <= 109 
// 
// Related Topics 贪心 数组 排序 堆（优先队列） 
// 👍 103 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class IPO502 {
    // TLE
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<int[]> list = new ArrayList<>();
        int len = profits.length;
        for(int i = 0; i < len; i++) {
//            if(profits[i] > capital[i]) {
//                list.add(new int[]{profits[i] - capital[i], i});
//            }
            list.add(new int[]{profits[i], i});
        }
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0])
                    return o1[0] - o2[0];   // 按利润排序
                return o2[1] - o1[1];   // 利润和资本相同 则资本小的在后面
            }
        };
        Collections.sort(list, comparator);
        for(int[] a : list)
            System.out.println(Arrays.toString(a));
//        len = list.size();
        boolean[] vis = new boolean[len];

        // 选择资本小于等于w的最大利润
        while(k != 0) {
            boolean flag = false;
            for(int i = len - 1; i >= 0; i--) {
                if(vis[i]) continue;
                int[] cur = list.get(i);
                if(capital[cur[1]] <= w) {
                    w += cur[0];
                    flag = true;
                    k--;
                    vis[i] = true;
                    break;
                }
            }
            if(!flag) return w;
        }
        return w;
    }

    // 最大堆
    public int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        List<int[]> list = new ArrayList<>();
        int len = profits.length;
        for(int i = 0; i < len; i++) {
            list.add(new int[]{profits[i], capital[i]});
        }
        Collections.sort(list, (a1, a2) -> a1[1] - a2[1]);
        int ptr = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for(int i = 0; i < k; i++) {
            while(ptr < len && list.get(ptr)[1] <= w) {
                heap.add(list.get(ptr)[0]);
                ptr++;
            }
            if(heap.isEmpty()) {
                return w;
            }
            w += heap.poll();
        }

        return w;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
