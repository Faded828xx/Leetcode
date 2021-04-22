package DP;
//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -100 <= matrix[i][j] <= 100 
// -105 <= k <= 105 
// 
//
// 
//
// 进阶：如果行数远大于列数，该如何设计解决方案？ 
// Related Topics 队列 二分查找 动态规划 
// 👍 218 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class MaxSumOfRectangleNoLargerThanK363 {
    // 暴力超时
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = -2147483648;
        for (int col1 = 0; col1 < col; col1++) {
            for (int col2 = col1; col2 < col; col2++) {
                for (int row1 = 0; row1 < row; row1++) {
                    for (int row2 = row1; row2 < row; row2++) {
                        int sum = 0;
                        for (int i = row1; i <= row2; i++) {
                            for (int j = col1; j <= col2; j++) {
                                sum += matrix[i][j];
                            }
                        }
                        if (sum > k) continue;
                        res = Math.max(res, sum);
                    }
                }
            }
        }
        return res;
    }


    // l遍历矩阵左边界 r遍历矩阵右边界 arr[row]数组记录每行从l到r的矩阵元素和 再对arr数组求子数组和
    public static int maxSumSubmatrix2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for(int l = 0; l < col; l++) {
            int[] arr = new int[row];
            for(int r = l; r < col; r++) {
                for(int i = 0; i < row; i++) {
                    arr[i] += matrix[i][r];
                }
                res = Math.max(res, arrSum(arr, k));
            }
        }
        return res;
    }

    // 求数组arr中 不超过k的 最大连续子数组的和
    public static int arrSum(int[] arr, int k) {
        int len = arr.length;
        // 求最大连续子数组的和用动态规划 复杂度为n 不考虑k的条件
        int[] dp = new int[len];
        dp[0] = arr[0];
        for(int i=1; i<len; i++) {
            if(dp[i-1]<0)
                dp[i] = arr[i];
            else dp[i] = dp[i-1] + arr[i];
        }
        if(dp[len-1]<=k) return dp[len-1];  // 这段dp代码好像有bug 遇到倒数第二个测试用例出现问题 待调试
        // 但加上不超过k则不成立 只能暴力
        int res = Integer.MIN_VALUE;
        for(int i=0; i<len; i++) {
            int sum = 0;
            for(int j=i; j<len; j++) {
                sum += arr[j];
                if(sum>k) continue;
                if(sum==k) return k;
                res = Math.max(res, sum);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {27, 5, -20, -9, 1, 26, 1, 12, 7, -4, 8, 7, -1, 5, 8},
                {16, 28, 8, 3, 16, 28, -10, -7, -5, -13, 7, 9, 20, -9, 26},
                {24, -14, 20, 23, 25, -16, -15, 8, 8, -6, -14, -6, 12, -19, -13},
                {28, 13, -17, 20, -3, -18, 12, 5, 1, 25, 25, -14, 22, 17, 12},
                {7, 29, -12, 5, -5, 26, -5, 10, -5, 24, -9, -19, 20, 0, 18},
                {-7, -11, -8, 12, 19, 18, -15, 17, 7, -1, -11, -10, -1, 25, 17},
                {-3, -20, -20, -7, 14, -12, 22, 1, -9, 11, 14, -16, -5, -12, 14},
                {-20, -4, -17, 3, 3, -18, 22, -13, -1, 16, -11, 29, 17, -2, 22},
                {23, -15, 24, 26, 28, -13, 10, 18, -6, 29, 27, -19, -19, -8, 0},
                {5, 9, 23, 11, -4, -20, 18, 29, -6, -4, -11, 21, -6, 24, 12},
                {13, 16, 0, -20, 22, 21, 26, -3, 15, 14, 26, 17, 19, 20, -5},
                {15, 1, 22, -6, 1, -9, 0, 21, 12, 27, 5, 8, 8, 18, -1},
                {15, 29, 13, 6, -11, 7, -6, 27, 22, 18, 22, -3, -9, 20, 14},
                {26, -6, 12, -10, 0, 26, 10, 1, 11, -10, -16, -18, 29, 8, -8},
                {-19, 14, 15, 18, -10, 24, -9, -7, -19, -14, 23, 23, 17, -5, 6}
        };
        int[][] arr = new int[][]{{2,2,-1}};
        System.out.println(maxSumSubmatrix(matrix,-101));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
