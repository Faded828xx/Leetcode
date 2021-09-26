package Contest.Weekly_Contest;

/**
 * @author faded828x
 * @date 2021/9/26
 */
public class Contest260 {

    // AC
    public int maximumDifference(int[] nums) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int n : nums) {
            if(n < min)
                min = n;
            else if(n > min)
                res = Math.max(res, n - min);
        }
        return res == 0 ? -1 : res;
    }


    // AC
    public long gridGame(int[][] grid) {
        int len = grid[0].length;
        long sum = 0;
        for(int i = len - 1; i > 0; i--)
            sum += grid[0][i];
        long n1 = sum;
        long n2 = 0;
        long res1 = 0;
        long res2 = 0;
        for(int i = 1; i < len; i++) {
            n1 -= grid[0][i];
            n2 += grid[1][i - 1];
            if(n1 == n2) return n1;
            if(n1 < n2) {
                res1 = n1 + grid[0][i];
                res2 = n2;
                break;
            }
        }
        return Math.min(res1, res2);
    }


    // AC
    int row;
    int col;
    char[][] b;
    char[] w;
    int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int l;
    public boolean placeWordInCrossword(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        b = board;
        w = word.toCharArray();
        l = word.length();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                // ' '或与word首字符相同 则进行check
                if(!(board[i][j] == ' ' || board[i][j] == w[0])) continue;
                if(check(i, j)) return true;
            }
        }
        return false;
    }
    public boolean check(int x, int y) {
        for(int i = 0; i < 4; i++) {
            // 末位字符不超边界
            int desX = x + (l - 1) * move[i][0];
            int desY = y + (l - 1) * move[i][1];
            if(desX < 0 || desX >= row || desY < 0 || desY >= col)
                continue;
            // 首位前和末位后 超边界或为障碍才可继续
            int nx = desX + move[i][0];
            int ny = desY + move[i][1];
            int mx = x - move[i][0];
            int my = y - move[i][1];
            if(!((nx < 0 || nx >= row || ny < 0 || ny >= col) || b[nx][ny] == '#')) continue;
            if(!((mx < 0 || mx >= row || my < 0 || my >= col) || b[mx][my] == '#')) continue;
            // 从x,y匹配到desX,desY
            int idx = 1;
            int curX = x;
            int curY = y;
            boolean isOK = true;
            for(int j = 1; j < l; j++) {
                curX += move[i][0];
                curY += move[i][1];
                // ' ' 或与 word[idx] 相同
                if(b[curX][curY] == ' ' || b[curX][curY] == w[idx])
                    idx++;
                else {
                    isOK = false;
                    break;
                }
            }
            if(isOK) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Contest260 contest260 = new Contest260();
        char[][] board = new char[][]{{' ','#','a'}, {' ','#','c'}, {' ','#','a'}};
        String word = "ac";
        System.out.println(contest260.placeWordInCrossword(board, word));
    }

}
