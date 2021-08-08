package Contest.Biweekly_Contest;

/**
 * @author faded828x
 * @date 2021/8/7
 */
public class Contest58 {

    // AC
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int j;
            for (j = i; j < arr.length && arr[j] == arr[i]; j++) {
            }
            sb.append(arr[i]);
            if (j - i >= 2) {
                sb.append(arr[i]);
            }
            i = j - 1;
        }
        return sb.toString();
    }

    // AC
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int row = board.length;
        int col = board[0].length;
        int[][] boardInt = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'W')
                    boardInt[i][j] = -1;
                else if (board[i][j] == 'B')
                    boardInt[i][j] = 1;
            }
        }
        int curColor = color == 'W' ? -1 : 1;
        int[][] direArr = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        for (int i = 0; i < 8; i++) {
            if (check(boardInt, rMove, cMove, curColor, i, direArr))
                return true;
        }
        return false;
    }

    public boolean check(int[][] board, int x, int y, int color, int dire, int[][] direArr) {
        int row = board.length;
        int col = board[0].length;
        int[] next = direArr[dire];
        int count = 0;
        int nextX = x + next[0];
        int nextY = y + next[1];
        while (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
            if (board[nextX][nextY] * color == -1) {
                count++;
                nextX += next[0];
                nextY += next[1];
            } else break;
        }
        if (count == 0) return false;
        return (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) && board[nextX][nextY] == color;
    }

}
