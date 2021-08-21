package Contest.Biweekly_Contest;

/**
 * @author faded828x
 * @date 2021/8/21
 */
public class Contest59 {

    // AC
    public static int minTimeToType(String word) {
        int res = 0;
        int cur = 0;
        for(char ch : word.toCharArray()) {
            int next = ch - 'a';
            int gap = Math.abs(next - cur);
//            System.out.println(gap);
            cur = next;
            res += ((gap > 13 ? 26 - gap : gap) + 1);
        }
        return res;
    }

    // 错误示范
    public static long maxMatrixSum(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] move = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] >= 0)
                    res += matrix[i][j];
                else if(!visited[i][j]) {
                    int[] a = dfs(matrix, visited, i, j, move);
                    if(a[1] % 2 == 0)
                        res += a[0];
                    else res += (a[0] - a[2]);
                }
            }
        }
        return res;
    }
    public static int[] dfs(int[][] matrix, boolean[][] visited, int x, int y, int[][] move) {
//        if(visited[x][y]) return null;
        visited[x][y] = true;
        int[] res = new int[3];
        int row = matrix.length;
        int col = matrix[0].length;
        res[0] = -matrix[x][y]; // sum
        res[1] = 1;             // num
        res[2] = -matrix[x][y]; // min
        for(int i = 0; i < 8; i++) {
            int nextX = x + move[i][0];
            int nextY = y + move[i][1];
            if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && !visited[nextX][nextY] && matrix[nextX][nextY] <= 0) {
                int[] next = dfs(matrix,  visited, nextX, nextY, move);
                res[0] += next[0];
                res[1] += next[1];
                res[2] = Math.min(res[2], next[2]);
            }
        }
        return res;
    }

    // AC
    // 坑比题 找规律
    public long maxMatrixSum2(int[][] matrix) {
        long res = 0L;
        int cnt = 0;
        int min = 100001;
        // int x = -1;
        // int y = -1;
        int row = matrix.length;
        int col = matrix[0].length;
        int m = 100001;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int n = matrix[i][j];
                if(n <= 0) {
                    // min = Math.min(min, -n);
                    if(-n < min) {
//                        x = i;
//                        y = j;
                        min = -n;
                    }
                    cnt++;
                    res += -n;
                    matrix[i][j] = -n;
                }
                else res += n;
                m = Math.min(m, matrix[i][j]);
            }
        }
        if(cnt % 2 != 0)
            res -= 2L * min;
        else return res;

        // System.out.println(res);

        // int[][] move = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        // int m = 100005;
        // for(int i = 0; i < 4; i++) {
        //     int nx = x + move[i][0];
        //     int ny = y + move[i][1];
        //     if(nx >= 0 && nx < row && ny >= 0 && ny < col)
        //         m = Math.min(m, matrix[nx][ny]);
        // }
        if(m - min < 0)
            res += (min - m) * 2L;


        return res;
    }


    public static void main(String[] args) {
//        String str = "zjpc";
//        System.out.println(minTimeToType(str));

    }
}
