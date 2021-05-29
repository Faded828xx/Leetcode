package Array;
//给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
//
// 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。 
//
//
// 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵
//也不同。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//输出：4
//解释：四个只含 0 的 1x1 子矩阵。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,-1],[-1,1]], target = 0
//输出：5
//解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[904]], target = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= matrix.length <= 100 
// 1 <= matrix[0].length <= 100 
// -1000 <= matrix[i] <= 1000 
// -10^8 <= target <= 10^8 
// 
// Related Topics 数组 动态规划 Sliding Window 
// 👍 84 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class NumberOfSubMatricesThatSumToTarget1074 {
    // 自己写的暴力
    public static int numSubMatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] preMatrix = new int[row][col];
        int[][] subMatrix = new int[row][col]; //   以i,j为右下界的矩阵中目标矩阵的数量
        preMatrix[0][0] = matrix[0][0];
        // 三个for计算前缀和
        for (int i = 1; i < col; i++) {
            preMatrix[0][i] = preMatrix[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < row; i++) {
            preMatrix[i][0] = preMatrix[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                preMatrix[i][j] = preMatrix[i - 1][j] + preMatrix[i][j - 1] - preMatrix[i - 1][j - 1] + matrix[i][j];
            }
        }
        // subMatrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int cur = preMatrix[i][j];
                // m,n不从0,0开始 因此preMatrix[i][j]==target则直接加一
                if (cur == target)
                    subMatrix[i][j]++;
                if (i == 0) {   // i,j首行
                    for (int k = 1; k <= j; k++) {
                        if (cur - preMatrix[0][k - 1] == target)
                            subMatrix[0][j]++;
                    }
                } else if (j == 0) {    // i,j首列
                    for (int k = 1; k <= i; k++) {
                        if (cur - preMatrix[k - 1][0] == target)
                            subMatrix[i][0]++;
                    }
                } else {    // i,j从1，1至末尾
                    for (int n = 1; n <= j; n++) { // m,n 首行
                        if (cur - preMatrix[i][n - 1] == target)
                            subMatrix[i][j]++;
                    }
                    for (int m = 1; m <= i; m++) { // m,n 首列
                        if (cur - preMatrix[m - 1][j] == target)
                            subMatrix[i][j]++;
                    }
                    for (int m = 1; m <= i; m++) {  // m,n从1,1至末尾
                        for (int n = 1; n <= j; n++) {
                            if (cur - preMatrix[m - 1][j] - preMatrix[i][n - 1] + preMatrix[m - 1][n - 1] == target)
                                subMatrix[i][j]++;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res += subMatrix[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, -1}, {-1, 1}};
        System.out.println(numSubMatrixSumTarget(matrix, 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
