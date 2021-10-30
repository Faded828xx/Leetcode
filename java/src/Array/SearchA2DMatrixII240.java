package Array;
//Write an efficient algorithm that searches for a target value in an m x n inte
//ger matrix. The matrix has the following properties: 
//
// 
// Integers in each row are sorted in ascending from left to right. 
// Integers in each column are sorted in ascending from top to bottom. 
// 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[1
//8,21,23,26,30]], target = 5
//Output: true
// 
//
// Example 2: 
//
// 
//Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[1
//8,21,23,26,30]], target = 20
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matrix[i][j] <= 109 
// All the integers in each row are sorted in ascending order. 
// All the integers in each column are sorted in ascending order. 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 分治 矩阵 
// 👍 850 👎 0


//leetcode submit region begin(Prohibit modification and deletion)



class SearchA2DMatrixII240 {
    // 边界写的好烦人 没过
    int[][] mat;
    int tar;
    int row;
    int col;
    public boolean searchMatrix(int[][] matrix, int target) {
        row = matrix.length;
        col = matrix[0].length;
        mat = matrix;
        tar = target;
        return binarySearch(0, 0, row - 1, col - 1);
    }
    public boolean binarySearch(int x1, int y1, int x2, int y2) {
        if(mat[x1][y1] == tar || mat[x2][y2] == tar) return true;
        while(x1 < x2 && y1 < y2) {
            int mx = (x1 + x2) / 2;
            int my = (y1 + y2) / 2;
            if(mat[mx][my] == tar) return true;
            if(mat[mx][my] > tar) {
                x2 = mx;
                y2 = my;
            } else {
                return binarySearch(mx + 1, y1, x2, y2) || binarySearch(x1, my + 1, mx, y2);
            }
        }
        return false;
    }

    // 每行二分 换简单的写
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int[] ints : matrix) {
            if (bs(ints, target)) return true;
        }
        return false;
    }
    boolean bs(int[] nums, int tar) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == tar) return true;
            if(nums[mid] > tar)
                right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        SearchA2DMatrixII240 searchA2DMatrixII240 = new SearchA2DMatrixII240();
        System.out.println(searchA2DMatrixII240.searchMatrix(matrix, 20));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
