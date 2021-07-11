package Contest.Weekly_Contest;

import java.util.HashSet;
import java.util.Set;

public class contest249 {

    // AC
    public static int countPalindromicSubsequence(String s) {
        int res = 0;
        int len = s.length();
        boolean[] visited = new boolean[26];
        char[] charArr = s.toCharArray();
        Set<String> set = new HashSet<>();
        for(int i=0; i<=len-3; i++) {
            char ch = charArr[i];
            if(visited[ch-'a']) continue;
            int pre = i;
            for(int j=i+2; j<=len-1; j++) {
                if(charArr[j]==ch) {
                    for(int k=pre+1; k<j; k++) {
                        String cur = String.valueOf(charArr[i]) + charArr[k] +charArr[j];
                        if(set.add(cur))
                            res++;
                    }
                    pre = j - 1;
                }
            }
            visited[ch-'a'] = true;
        }
        return res;
    }



    public int colorTheGrid(int m, int n) {
        int[][] gird = new int[m][n];





        return 0;
    }
    public int backtrack(int[][] grid, int curX, int curY) {
        int row = grid.length;
        int col = grid[0].length;
        if(curX==row-1 && curY==col-1) {
            int res = 0;
            for(int i=0; i<3; i++) {
                if (check(grid, curX, curY, i))
                    res++;
            }
            return res;
        }




        return 0;
    }
    public boolean check(int[][] grid, int curX, int curY, int color) {
        int row = grid.length;
        int col = grid[0].length;
        if(curX>=1 && grid[curX-1][curY]==color) return false;
        if(curY>=1 && grid[curX][curY-1]==color) return false;
        return true;
    }


    public static void main(String[] args) {
        String s = "tlpjzdmtwderpkpmgoyrcxttiheassztncqvnfjeyxxp";
        System.out.println(countPalindromicSubsequence(s));
    }
}
