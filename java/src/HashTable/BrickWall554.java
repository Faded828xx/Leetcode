package HashTable;
//你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。
//
// 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没
//有穿过一块砖的。 
//
// 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的
//砖块数量最少 ，并且返回 穿过的砖块数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：wall = [[1],[1],[1]]
//输出：3
// 
// 
//
// 提示： 
//
// 
// n == wall.length 
// 1 <= n <= 104 
// 1 <= wall[i].length <= 104 
// 1 <= sum(wall[i].length) <= 2 * 104 
// 对于每一行 i ，sum(wall[i]) 是相同的 
// 1 <= wall[i][j] <= 231 - 1 
// 
// Related Topics 哈希表 
// 👍 236 👎 0

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class BrickWall554 {
    public static int leastBricks(List<List<Integer>> wall) {
        int row = wall.size();
        int col = 0;
        for(int n : wall.get(0))
            col += n;
        Set<Integer> resIndex = new HashSet<>();
        // boolean[][] walls = new boolean[row][col+1]; 当每块砖的长度很大时 会出现很多false 浪费内存
        List<Set<Integer>> walls = new ArrayList<>();
        for(List<Integer> list : wall) {
            int index = 0;
            Set<Integer> set = new HashSet<>();
            for(int n : list) {
                index += n;
                if(index!=col)
                    resIndex.add(index);
                set.add(index);
            }
            walls.add(set);
        }
        if(resIndex.isEmpty()) return row;
        int res = Integer.MAX_VALUE;
        for(int i : resIndex) {
            int num = 0;
            for (int j = 0; j < row; j++) {
                if (!walls.get(j).contains(i)) {
                    num++;
                }
            }
            res = Math.min(res, num);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> w = new ArrayList<>(){{
            add(100000000);
        }};
        List<List<Integer>> wall = new ArrayList<>(){{
            add(w);
            add(w);
            add(w);
        }};
        System.out.println(leastBricks(wall));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
