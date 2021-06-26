package BFS;
//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
//
// 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 示例： 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//输入：board = [[3,2,4],[1,5,0]]
//输出：14
// 
//
// 提示： 
//
// 
// board 是一个如上所述的 2 x 3 的数组. 
// board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列. 
// 
// Related Topics 广度优先搜索 数组 矩阵 
// 👍 142 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SlidingPuzzle773 {
    // BFS很好想 主要是觉得数组不好处理 将其转换为string 主要就是写交换元素的方法 其他都是套模版
    public static int slidingPuzzle(int[][] board) {
        char[] boardArr = new char[6];
        // board转换为String
        for (int i = 0; i < 6; i++) {
            boardArr[i] = (char) (board[i / 3][i % 3] + '0');
        }
        String boardStr = new String(boardArr);
        // 原串即目标串
        if (boardStr.equals("123450")) return 0;
        // BFS模版
        Deque<String> queue = new ArrayDeque<>();
        queue.add(boardStr);
        Set<String> visited = new HashSet<>();
        visited.add(boardStr);
        int count = 0;  // 按层遍历 计数
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                String cur = queue.remove();
                // 3种变换方法
                for (int j = 0; j < 3; j++) {
                    String next = change(cur, j);
                    if (next.equals("123450"))
                        return count;
                    if (visited.add(next))
                        queue.add(next);
                }
            }
        }
        return -1;
    }

    // 看错题 这里改了好几遍 将数组转为len为6的string
    // 只能用0跟左右上下（3种）交换 且0索引在0和3时不能左换 在2和5时不能右换
    public static String change(String board, int index) {  // index表示左右上下方法 就是喜欢这么命名😍
        int index1 = 0; // 0索引
        int index2 = 0; // 待交换元素索引
        char[] res = board.toCharArray();
        // 找到0索引
        for (int i = 0; i < 6; i++) {
            if (res[i] == '0')
                index1 = i;
        }
        // 不能左换和右换时返回原字符串
        if ((index1 % 3 == 0 && index == 0) || (index1 % 3 == 2 && index == 1))
            return board;
        // 找到待交换元素索引
        switch (index) {
            case 0:
                index2 = (index1 - 1) % 6;
                break;
            case 1:
                index2 = (index1 + 1) % 6;
                break;
            case 2:
                index2 = (index1 + 3) % 6;
        }
        // 实现元素交换
        char tmp = res[index1];
        res[index1] = res[index2];
        res[index2] = tmp;
        return new String(res);
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{1, 2, 3}, {4, 5, 0}};
        System.out.println(slidingPuzzle(board));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
