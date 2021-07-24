package Contest.Biweekly_Contest;

import java.util.ArrayDeque;
import java.util.Deque;

public class Contest56 {

    // AC
    public int countTriples(int n) {
        int res = 0;
        for(int c = n; c >= 5; c--) {
            for(int b = c - 1; b >= 3; b--) {
                int a2 = (c + b) * (c - b);
                int a = (int)Math.sqrt(a2);
                if(a * a == a2)
                    res++;
            }
        }
        return res;
    }

    // AC
    public int nearestExit(char[][] maze, int[] entrance) {
        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] move = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<Integer> queueX = new ArrayDeque<>();
        Deque<Integer> queueY = new ArrayDeque<>();
        queueX.add(entrance[0]);
        queueY.add(entrance[1]);
        visited[entrance[0]][entrance[1]] = true;
        int res = 0;
        while(!queueX.isEmpty()) {
            int size = queueX.size();
            res++;
            for(int i = 0; i < size; i++) {
                int curX = queueX.remove();
                int curY = queueY.remove();
                for(int j = 0; j < 4; j++) {
                    int nextX = move[j][0] + curX;
                    int nextY = move[j][1] + curY;
                    if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || visited[nextX][nextY]) continue;
                    if(maze[nextX][nextY] == '.') {
                        if(nextX == 0 || nextX == row - 1 || nextY == 0 || nextY == col - 1)
                            return res;
                        queueX.add(nextX);
                        queueY.add(nextY);
                    }
                    visited[nextX][nextY] = true;
                }
            }
        }
        return -1;
    }

    public static boolean sumGame(String num) {
        int leftSum = 0;
        int rightSum = 0;
        int countLeft = 0;
        int countRight = 0;
        int len = num.length();
        for(int i = 0; i < len; i++) {
            char ch = num.charAt(i);
            if(i < len / 2) {
                if(ch == '?')
                    countLeft++;
                else leftSum += ch - '0';
            } else {
                if(ch == '?')
                    countRight++;
                else rightSum += ch - '0';
            }
        }
        int bigSum = leftSum > rightSum ? leftSum : rightSum;
        int smallSum = leftSum < rightSum ? leftSum : rightSum;
        int countBig = leftSum > rightSum ? countLeft : countRight;
        int countSmall = leftSum < rightSum ? countLeft : countRight;
        if(bigSum - smallSum < 9) return true;
        if(bigSum - smallSum <= (countSmall - countBig) / 2 * 9) return false;
        return !(leftSum == rightSum);
    }

    public static void main(String[] args) {
        System.out.println(sumGame("5023"));
    }
}
