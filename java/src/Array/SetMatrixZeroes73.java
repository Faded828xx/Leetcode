package Array;//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
//
// 进阶： 
//
// 
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个仅使用常量空间的解决方案吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics 数组 
// 👍 419 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SetMatrixZeroes73 {

    // 空间复杂度O(m+n) 自己写的
    public void setZeroes1(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] rowArr = new boolean[row];
        boolean[] colArr = new boolean[col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(matrix[i][j]!=0)
                    continue;
                rowArr[i] = true;
                colArr[j] = true;
            }
        }
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(matrix[i][j]==0 || (!rowArr[i] && !colArr[j]))
                    continue;
                matrix[i][j] = 0;
            }
        }
    }

    // 空间复杂度O(1) 参考题解
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false;
        boolean firstCol = false;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < col; i++) // 首行是否有0
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        for(int i=0; i<row; i++)    // 首列是否有0
            if(matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        for(int i=1; i<row; i++)    // 去掉首行首列的矩阵中元素若为0 则将对应的行首列首置0
            for(int j=1; j<col; j++) {
                if(matrix[i][j]==0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        for(int i=1; i<col; i++)    // 将首行中0元素所在列置0
            if(matrix[0][i]==0)
                for(int j=1; j<row; j++)
                    matrix[j][i] = 0;
        for(int i=1; i<row; i++)    // 将首列中0元素所在行置0
            if(matrix[i][0]==0)
                for(int j=1; j<col; j++)
                    matrix[i][j] = 0;
        if(firstRow)    // 原矩阵首行若有0 则将首行置0
            for(int i=0; i<col; i++)
                matrix[0][i] = 0;
        if(firstCol)    // 原矩阵首列若有0 则将首列置0
            for(int i=0; i<row; i++)
                matrix[i][0] = 0;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
