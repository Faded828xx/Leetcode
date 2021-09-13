package HashTable;//You are given n points in the plane that are all distinct, where points[i] = [
//xi, yi]. A boomerang is a tuple of points (i, j, k) such that the distance betwe
//en i and j equals the distance between i and k (the order of the tuple matters).
// 
//
// Return the number of boomerangs. 
//
// 
// Example 1: 
//
// 
//Input: points = [[0,0],[1,0],[2,0]]
//Output: 2
//Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]
//].
// 
//
// Example 2: 
//
// 
//Input: points = [[1,1],[2,2],[3,3]]
//Output: 2
// 
//
// Example 3: 
//
// 
//Input: points = [[1,1]]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// n == points.length 
// 1 <= n <= 500 
// points[i].length == 2 
// -104 <= xi, yi <= 104 
// All the points are unique. 
// 
// Related Topics 数组 哈希表 数学 
// 👍 152 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class NumberOfBoomerangs447 {
    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        Map<Integer, Map<Long, Integer>> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < len; i++) {
            Map<Long, Integer> m1 = map.getOrDefault(i, new HashMap<>());
            for(int j = i + 1; j < len; j++) {
                long xDis = points[j][0] - points[i][0];
                long yDis = points[j][1] - points[i][1];
                long dis = xDis * xDis + yDis * yDis;
                m1.put(dis, m1.getOrDefault(dis, 0) + 1);
                Map<Long,Integer> m2 = map.getOrDefault(j, new HashMap<>());
                m2.put(dis, m2.getOrDefault(dis, 0) + 1);
                map.put(j, m2);
            }
            map.put(i, m1);
            for(int n : map.get(i).values()) {
                res += n * (n - 1);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
