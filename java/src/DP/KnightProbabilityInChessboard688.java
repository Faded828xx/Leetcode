package DP;//On an n x n chessboard, a knight starts at the cell (row, column) and attempts
// to make exactly k moves. The rows and columns are 0-indexed, so the top-left ce
//ll is (0, 0), and the bottom-right cell is (n - 1, n - 1). 
//
// A chess knight has eight possible moves it can make, as illustrated below. Ea
//ch move is two cells in a cardinal direction, then one cell in an orthogonal dir
//ection. 
//
// Each time the knight is to move, it chooses one of eight possible moves unifo
//rmly at random (even if the piece would go off the chessboard) and moves there. 
//
//
// The knight continues moving until it has made exactly k moves or has moved of
//f the chessboard. 
//
// Return the probability that the knight remains on the board after it has stop
//ped moving. 
//
// 
// Example 1: 
//
// 
//Input: n = 3, k = 2, row = 0, column = 0
//Output: 0.06250
//Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight o
//n the board.
//From each of those positions, there are also two moves that will keep the knig
//ht on the board.
//The total probability the knight stays on the board is 0.0625.
// 
//
// Example 2: 
//
// 
//Input: n = 1, k = 0, row = 0, column = 0
//Output: 1.00000
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 25 
// 0 <= k <= 100 
// 0 <= row, column <= n 
// 
// Related Topics 动态规划 
// 👍 185 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class KnightProbabilityInChessboard688 {
    // 三维dp
    public double knightProbability(int n, int k, int row, int column) {
        // x,y 位置还需走z步 能留在棋盘上的概率
        // 初始时无需走动 因此每个位置的概率为1
        double[][][] dp = new double[n][][];
        for(int i = 0; i < n; i++) {
            dp[i] = new double[n][];
            for(int j = 0; j < n; j++) {
                dp[i][j] = new double[k + 1];
                dp[i][j][0] = 1;
            }
        }
        int[][] move = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        // 八个方向上的位置在k-1步时的概率/8 之和 超边界时概率为0
        for(int i = 1; i <= k; i++) {
            for(int p = 0; p < n; p++) {
                for(int q = 0; q < n; q++) {
                    double prop = 0;
                    for(int idx = 0; idx < 8; idx++) {
                        int xx = p + move[idx][0];
                        int yy = q + move[idx][1];
                        if(xx < 0 || xx >= n || yy < 0 || yy >= n) continue;
                        prop += dp[xx][yy][i - 1] / 8.0;
                    }
                    dp[p][q][i] = prop;
                }
            }
        }
        return dp[row][column][k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
