package Contest.Weekly_Contest;

import javax.print.DocFlavor;
import java.util.*;

public class contest250 {

    // AC
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] s = text.split(" ");
        Set<Character> set = new HashSet<>();
        for(char ch : brokenLetters.toCharArray()) {
            set.add(ch);
        }
        int res = s.length;
        for(String str : s) {
            for(char ch : set) {
                if(str.indexOf(ch)!=-1) {
                    res--;
                    break;
                }
            }
        }
        return res;
    }

    // AC
    public int addRungs(int[] rungs, int dist) {
        int res = 0;
        int pre = 0;
        for(int n : rungs) {
            res += (n - pre - 1) / dist;
            pre = n;
        }
        return res;
    }

    // N/A
    public static long maxPoints(int[][] points) {
        int res = -1;
        int row = points.length;
        int col = points[0].length;
//        int preIndex = -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> preInd = new ArrayList<>();
        for(int i = 0; i < col; i++) {
            if(points[0][i] > res) {
                res = points[0][i];
//                preIndex = i;
                preInd = new ArrayList<>();
                preInd.add(i);
            } else if(points[0][i] == res)
                preInd.add(i);
        }
        map.put(res, preInd);
        for(int i = 1; i < row; i++) {
//            int curIndex = -1;
            List<Integer> curInd = new ArrayList<>();
            int curMax = -1;
            for(int j = 0; j < col; j++) {
                for(int preI : preInd) {
                    int cur = points[i][j] - Math.abs(j - preI);
                    if (cur > curMax) {
                        curMax = cur;
                        curInd = new ArrayList<>();
                        curInd.add(j);
//                   curIndex = j;
                    } else if(cur == curMax)
                        curInd.add(j);
                }
            }
            preInd = curInd;
//            preIndex = curIndex;
            res += curMax;
        }
        return res;
    }



    public static void main(String[] args) {
        int[][] points = new int[][]{{1,2,3},{1,5,1},{3,1,1}};
        System.out.println(maxPoints(points));
    }
}
