package 面试;

import java.util.*;

/**
 * @author faded828x
 * @date 2022/9/5
 */
public class f {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++)
            nums[i] = scanner.nextInt();
        int k = scanner.nextInt();
        double[] mid = medianSlidingWindow(nums, k);
        int[] res = new int[n + 1 - k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, ((o1, o2) -> o2 - o1));
        for(int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
            maxHeap.add(nums[i]);
        }
        if(maxHeap.peek() - mid[0] >= mid[0] - minHeap.peek())
            res[0] = maxHeap.peek();
        else res[0] = minHeap.peek();
        int idx = 1;
        for(int i = k; i < n; i++) {
            maxHeap.add(nums[i]);
            maxHeap.remove(nums[i - k]);
            minHeap.add(nums[i]);
            minHeap.remove(nums[i - k]);
            if(maxHeap.peek() - mid[idx] >= mid[idx] - minHeap.peek())
                res[idx] = maxHeap.peek();
            else res[idx] = minHeap.peek();
            idx++;
        }
        System.out.println(Arrays.toString(res));
    }


    static PriorityQueue<Integer> lower;
    static PriorityQueue<Integer> higher;
    static int lowerSize;
    static int higherSize;
    static Map<Integer, Integer> removeMap;

    public static double[] medianSlidingWindow(int[] nums, int k) {
        lower = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));
        higher = new PriorityQueue<Integer>((a, b) -> a.compareTo(b));
        lowerSize = 0;
        higherSize = 0;
        removeMap = new HashMap<Integer, Integer>();
        int length = nums.length;
        double[] medianArray = new double[length - k + 1];
        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }
        medianArray[0] = findMedian();
        for (int i = k; i < length; i++) {
            removeNum(nums[i - k]);
            addNum(nums[i]);
            medianArray[i - k + 1] = findMedian();
        }
        return medianArray;
    }

    public static void addNum(int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
            lowerSize++;
        } else {
            higher.offer(num);
            higherSize++;
        }
        adjustPriorityQueues();
    }

    public static void removeNum(int num) {
        removeMap.put(num, removeMap.getOrDefault(num, 0) + 1);
        if (num <= lower.peek()) {
            lowerSize--;
        } else {
            higherSize--;
        }
        adjustPriorityQueues();
    }

    public static double findMedian() {
        return lowerSize == higherSize ? ((double) lower.peek() + higher.peek()) / 2 : lower.peek();
    }

    public static void adjustPriorityQueues() {
        if (lowerSize > higherSize + 1) {
            higher.offer(lower.poll());
            lowerSize--;
            higherSize++;
        } else if (lowerSize < higherSize) {
            lower.offer(higher.poll());
            lowerSize++;
            higherSize--;
        }
        for (PriorityQueue<Integer> pq : Arrays.asList(lower, higher)) {
            while (!pq.isEmpty() && removeMap.containsKey(pq.peek())) {
                int num = pq.poll();
                removeMap.put(num, removeMap.get(num) - 1);
                if (removeMap.get(num) == 0) {
                    removeMap.remove(num);
                }
            }
        }
    }


}
