package HashTable;
//Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an
// axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and 
//the top-right point of it is (ai, bi). 
//
// Return true if all the rectangles together form an exact cover of a rectangul
//ar region. 
//
// 
// Example 1: 
//
// 
//Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
//Output: true
//Explanation: All 5 rectangles together form an exact cover of a rectangular re
//gion.
// 
//
// Example 2: 
//
// 
//Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
//Output: false
//Explanation: Because there is a gap between the two rectangular regions.
// 
//
// Example 3: 
//
// 
//Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
//Output: false
//Explanation: Because there is a gap in the top center.
// 
//
// Example 4: 
//
// 
//Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
//Output: false
//Explanation: Because two of the rectangles overlap with each other.
// 
//
// 
// Constraints: 
//
// 
// 1 <= rectangles.length <= 2 * 104 
// rectangles[i].length == 4 
// -105 <= xi, yi, ai, bi <= 105 
// 
// Related Topics 数组 扫描线 
// 👍 130 👎 0

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//leetcode submit region begin(Prohibit modification and deletion)
class PerfectRectangle391 {
    // 1、维护四个顶点 由四个点得到的大矩阵面积与小矩阵的面积和相同 说明大矩阵中间无空隙
    // 2、四个顶点的出现次数必须为1 其他顶点的出现次数必须为2或4 保证小矩阵之间无重叠部分
    public boolean isRectangleCover(int[][] rectangles) {
        Map<Pair, Integer> map = new HashMap<>();
        Pair lu = new Pair(100001, -100001); // min max
        Pair ld = new Pair(100001, 100001);  // min min
        Pair ru = new Pair(-100001, -100001);// max max
        Pair rd = new Pair(-100001, 100001); // max min
        long area = 0;
        for(int[] rect : rectangles) {
            Pair curLU = new Pair(rect[0], rect[3]);
            Pair curLD = new Pair(rect[0], rect[1]);
            Pair curRU = new Pair(rect[2], rect[3]);
            Pair curRD = new Pair(rect[2], rect[1]);
            map.put(curLU, map.getOrDefault(curLU, 0) + 1);
            map.put(curLD, map.getOrDefault(curLD, 0) + 1);
            map.put(curRU, map.getOrDefault(curRU, 0) + 1);
            map.put(curRD, map.getOrDefault(curRD, 0) + 1);
            if(curLU.getX() <= lu.getX() && curLU.getY() >= lu.getY()) lu = curLU;
            if(curLD.getX() <= ld.getX() && curLD.getY() <= ld.getY()) ld = curLD;
            if(curRU.getX() >= ru.getX() && curRU.getY() >= ru.getY()) ru = curRU;
            if(curRD.getX() >= rd.getX() && curRD.getY() <= rd.getY()) rd = curRD;
            area += (long) (rect[3] - rect[1]) * (long) (rect[2] - rect[0]);
        }
//        System.out.println(lu);
//        System.out.println(ld);
//        System.out.println(ru);
//        System.out.println(rd);
        if(lu.getX() != ld.getX() || lu.getY() != ru.getY()) return false;
        if(rd.getX() != ru.getX() || rd.getY() != ld.getY()) return false;
        long area2 = (long) (ru.getX() - ld.getX()) * (long) (ru.getY() - ld.getY());
//        System.out.println(area);
//        System.out.println(area2);
        if(area != area2) return false;
        System.out.println(map);
        for(Pair p : map.keySet()) {
            if(p == lu || p == ld || p == ru || p == rd) {
//                System.out.println(p);
                if(map.get(p) != 1) return false;
            } else if(map.get(p) == 1 || map.get(p) == 3) return false;
        }
        return true;
    }

    class Pair {
        private int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int getX() {return x;}
        int getY() {return y;}
//        void setX(int x) {this.x = x;}
//        void setY(int y) {this.y = y;}
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
//        @Override
//        public String toString() {
//            return "Pair{" +
//                    "x=" + x +
//                    ", y=" + y +
//                    '}';
//        }
    }

    public static void main(String[] args) {
        int[][] rectangles = new int[][] {{1,1,3,3}, {3,1,4,2}, {3,2,4,4}, {1,3,2,4}, {2,3,3,4}};
        PerfectRectangle391 perfectRectangle391 = new PerfectRectangle391();
        System.out.println(perfectRectangle391.isRectangleCover(rectangles));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
