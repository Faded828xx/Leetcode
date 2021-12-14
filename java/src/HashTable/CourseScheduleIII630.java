package HashTable;
//There are n different online courses numbered from 1 to n. You are given an ar
//ray courses where courses[i] = [durationi, lastDayi] indicate that the ith cours
//e should be taken continuously for durationi days and must be finished before or
// on lastDayi. 
//
// You will start on the 1st day and you cannot take two or more courses simulta
//neously. 
//
// Return the maximum number of courses that you can take. 
//
// 
// Example 1: 
//
// 
//Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
//Output: 3
//Explanation: 
//There are totally 4 courses, but you can take 3 courses at most:
//First, take the 1st course, it costs 100 days so you will finish it on the 100
//th day, and ready to take the next course on the 101st day.
//Second, take the 3rd course, it costs 1000 days so you will finish it on the 1
//100th day, and ready to take the next course on the 1101st day. 
//Third, take the 2nd course, it costs 200 days so you will finish it on the 130
//0th day. 
//The 4th course cannot be taken now, since you will finish it on the 3300th day
//, which exceeds the closed date.
// 
//
// Example 2: 
//
// 
//Input: courses = [[1,2]]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: courses = [[3,2],[4,3]]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 1 <= courses.length <= 104 
// 1 <= durationi, lastDayi <= 104 
// 
// Related Topics 贪心 数组 堆（优先队列） 
// 👍 214 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class CourseScheduleIII630 {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a1, a2) -> a1[1] - a2[1]);
//        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
//        for(int i = 0; i < courses.length; i++) {
//            int[] course = courses[i];
//            if(course[0] > course[1]) continue;
//            int before = course[1] - course[0];
//            Integer start = treeMap.floorKey(before);
//            start = start == null ? 0 : start;
//            int days = treeMap.getOrDefault(start, 0) + 1;
//            treeMap.put(start + course[0], days);
//        }
//        return treeMap.get(treeMap.lastKey());
        List<Integer> list = new ArrayList<>();
        for(int[] course : courses) {
            if(course[0] > course[1]) continue;
            if(list.size() == 0) {
                list.add(course[0]);
                continue;
            }
            int before = course[1] - course[0];
            int last = list.get(list.size() - 1);
            if(last <= before)
                list.add(last + course[0]);
        }
        return list.size();
    }

    public int scheduleCourse2(int[][] courses) {
        Arrays.sort(courses, (a1, a2) -> a1[1] - a2[1]);
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        int sum = 0;
        for(int[] course : courses) {
            if(sum + course[0] <= course[1]) {
                heap.offer(course[0]);
                sum += course[0];
            } else if(!heap.isEmpty() && heap.peek() > course[0]) {
                int top = heap.poll();
                heap.offer(course[0]);
                sum = sum - top + course[0];
            }
        }
        return heap.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
