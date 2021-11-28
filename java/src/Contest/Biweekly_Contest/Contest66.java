package Contest.Biweekly_Contest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2021/11/27
 */
public class Contest66 {

    // AC
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for(String s : words1)
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        for(String s : words2)
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        int cnt = 0;
        for(String s : words1)
            if(map1.get(s) == 1 && map2.getOrDefault(s, 0) == 1)
                cnt++;

            return cnt;
    }

    // AC 不太擅长这类题 挂了五次才过
    public int minimumBuckets(String street) {
        char[] ss = street.toCharArray();
        int l = ss.length;
        if(l == 1)
            return ss[0] == 'H' ? -1 : 0;
        if(ss[0] == ss[1] && ss[0] == 'H') return -1;
        int res = 1;    // number of H
        if(ss[0] == ss[1] && ss[0] == '.') res = 0;
        for(int i = 2; i < l; i++) {
            if(ss[i] == '.') continue;
            res++;
            if((ss[i - 1] == 'H' || ss[i - 1] == '@') && (ss[i - 2] == 'H' || ss[i - 2] == '@')) return -1;
            if(ss[i - 2] == 'H') {
                ss[i] = '@';
                res--;
            }
        }
        if((ss[l-1]=='H'||ss[l-1]=='@') && (ss[l-2]=='H'||ss[l-2]=='@')) return -1;
        return res;
    }

    // AC
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int cntY = 0;
        if(startPos[0] < homePos[0]) {
            for(int i = startPos[0] + 1; i <= homePos[0]; i++)
                cntY += rowCosts[i];
        } else {
            for(int i = homePos[0]; i < startPos[0]; i++)
                cntY += rowCosts[i];
        }
        int cntX = 0;
        if(startPos[1] < homePos[1]) {
            for(int i = startPos[1] + 1; i <= homePos[1]; i++)
                cntX += colCosts[i];
        } else {
            for(int i = homePos[1]; i < startPos[1]; i++)
                cntX += colCosts[i];
        }
        return cntX + cntY;
    }

    // AC 过分简单了
    int[][] g;
    int row, col;
    public int countPyramids(int[][] grid) {
        g = grid;
        row = grid.length;
        col = grid[0].length;
        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++)
                if(grid[i][j] == 1)
                    res += check(i, j) + check2(i, j);
        }
        return res;
    }
    // 以x,y为顶端的正金子塔数
    public int check(int x, int y) {
        int lev = 1;
        int r = x + 1;
//        boolean fail = false;
        while(r < row) {
            if(g[r][y] == 0) return lev - 1;
            for(int i = 1; i <= lev; i++) {
                if(y - i < 0 || y + i >= col || g[r][y + i] == 0 || g[r][y - i] == 0) {
                    return lev - 1;
                }
            }
            lev++;
            r++;
        }
        return lev - 1;
    }
    public int check2(int x, int y) {
        int lev = 1;
        int r = x - 1;
        while(r >= 0) {
            if(g[r][y] == 0) return lev - 1;
            for(int i = 1; i <= lev; i++) {
                if(y - i < 0 || y + i >= col || g[r][y + i] == 0 || g[r][y - i] == 0) {
                    return lev - 1;
                }
            }
            lev++;
            r--;
        }
        return lev - 1;
    }

}
