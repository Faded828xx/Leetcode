package Contest.Biweekly_Contest;

import LinkedList.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2022/1/8
 */
public class Contest69 {

    // AC
    public String capitalizeTitle(String title) {
        String[] s = title.split(" ");
        System.out.println(Arrays.toString(s));
        StringBuilder sb = new StringBuilder();
        for(String ss : s) {
            ss = ss.trim();
            if(ss.length() == 1 || ss.length() == 2) {
                ss = ss.toLowerCase();
            } else {
                char c = ss.charAt(0);
                c = (c <= 'z' && c >= 'a') ? (char) (c - 32) : c;
                ss = c + ss.substring(1, ss.length()).toLowerCase();
            }
            sb.append(ss + ' ');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    // AC
//    public int pairSum(ListNode head) {
//        int[] nums = new int[100001];
//        int size = 0;
//        ListNode cur = new ListNode();
//        cur.next = head;
//        while(cur.next != null) {
//            cur = cur.next;
//            nums[size++] = cur.val;
//        }
//        System.out.println(Arrays.toString(nums));
//        int res = 0;
//        for(int i = 0; i <= (size / 2) - 1; i++) {
//            int n = nums[i] + nums[size - 1 - i];
//            res = Math.max(res, n);
//        }
//        return res;
//    }


    // AC
    public int longestPalindrome(String[] words) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        boolean flag = false;
        for(String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        for(String w : map.keySet()) {
            if(check(w)) {
                int freq = map.get(w);
                if(freq % 2 == 1) {
                    freq--;
                    flag = true;
                }
                res += freq * 2;
            } else {
                String ww = w.charAt(1) + "" + w.charAt(0);
                if(!map.containsKey(ww)) continue;
                int freq = Math.min(map.get(w), map.get(ww));
                res += freq * 2;
            }
        }
        return res + (flag ? 2 : 0);
    }
    public boolean check(String s) {
        return s.charAt(0) == s.charAt(1);
    }

    // TLE
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int row = grid.length;
        int col = grid[0].length;
//        boolean flag2 = false;
//        if(stampHeight > row || stampWidth > col) return false;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1 || j > col - stampWidth || i > row - stampHeight) continue;
//                flag2 = true;
                boolean flag1 = false;
                boolean flag0 = false;
                int[][] grid2 = new int[row][];
                for(int m = 0; m < row; m++)
                    grid2[m] = Arrays.copyOf(grid[m], col);
                for(int k = i; k <= i + stampHeight - 1; k++) {
                    for(int h = j; h <= j + stampWidth - 1; h++) {
                        if(grid[k][h] == 1)
                            flag1 = true;
                        if(grid[k][h] == 0) {
                            flag0 = true;
                            grid[k][h] = 2;
                        }
                        if(flag0 && flag1) {
                            if(grid2[i][j] == 0) return false;
                            else grid = grid2;
                        }
                    }
                }
            }
        }
//        if(!flag2) return true;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++)
                if(grid[i][j] == 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Contest69 contest69 = new Contest69();
        int[][] grid = new int[][]{{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};
        System.out.println(contest69.possibleToStamp(grid, 4,3));
    }

}
