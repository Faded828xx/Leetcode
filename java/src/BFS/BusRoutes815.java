package BFS;
//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
//
// 
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 
//-> ... 这样的车站路线行驶。 
// 
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。 
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
// 
//
// 示例 2： 
//
// 
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= routes.length <= 500. 
// 1 <= routes[i].length <= 105 
// routes[i] 中的所有值 互不相同 
// sum(routes[i].length) <= 105 
// 0 <= routes[i][j] < 106 
// 0 <= source, target < 106 
// 
// Related Topics 广度优先搜索 数组 哈希表 
// 👍 157 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class BusRoutes815 {
    // 自己写的 效率挺拉的
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        // 车站->车站映射 超时
//        Map<Integer, List<Integer>> map = new HashMap<>();
//        for(int[] route : routes) {
//            List<Integer> nextList = new ArrayList<>();
//            for(int next : route) {
//                nextList.add(next);
//            }
//            for(int cur : route) {
//                List<Integer> list = map.getOrDefault(cur, new ArrayList<>());
//                list.addAll(nextList);
//                map.put(cur, list);
//            }
//        }
        // 车站->路线映射
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 路线->车站映射
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i< routes.length; i++) {
            List<Integer> curWay = new ArrayList<>();
            for(int station : routes[i]) {
                curWay.add(station);
                List<Integer> ways = map.getOrDefault(station, new ArrayList<>());
                ways.add(i);
                map.put(station, ways);
            }
            list.add(curWay);
        }
        if(!map.containsKey(target)) return -1;
        // 车站队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for(int i=0; i<size; i++) {
                int cur = queue.remove();
                // 转换为车站->车站
                for(int nextWay : map.get(cur)) {
                    for(int nextStation : list.get(nextWay)) {
                        if(nextStation == target) return count;
                        if(visited.add(nextStation))
                            queue.add(nextStation);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = new int[][]{{1, 2, 7}, {3, 6, 7}};
        int source = 1;
        int target = 6;
        System.out.println(numBusesToDestination(routes, source, target));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
