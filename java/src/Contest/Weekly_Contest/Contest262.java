package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/10/10
 */
public class Contest262 {

    // AC
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> s = new HashSet<>();
        for(int n : nums1)
            s.add(n);
        Set<Integer> l = new HashSet<>();
        for(int n : nums2)
            if(s.contains(n))
                l.add(n);
        for(int n : nums2)
            s.add(n);
        for(int n : nums3)
            if(s.contains(n))
                l.add(n);
        List<Integer> ll = new ArrayList<>();
        ll.addAll(l);
        return ll;
    }

    // 卡点AC 好像想复杂了
    public int minOperations(int[][] grid, int x) {
        int mod = -1;
        int row = grid.length;
        int col = grid[0].length;
        int sum = 0;
        List<Integer> l = new ArrayList<>();
        for(int[] g : grid) {
            for (int n : g) {
                if (mod == -1)
                    mod = n % x;
                else if (n % x != mod)
                    return -1;
                int k = n / x;
                sum += k;
                l.add(k);
            }
        }
//        System.out.println(l);
//        int kk = sum / (row * col);
        int minK = -1;
        int minN = Integer.MAX_VALUE;
        int rc = row * col;
        int curs = 0;
        Collections.sort(l);
        for(int i = 0; i < l.size(); i++) {
            int c = Math.abs(sum - 2 * curs - (rc - 2 * i) * l.get(i));
            if(c < minN) {
                minN = c;
                minK = l.get(i);
            }
            curs += l.get(i);
        }
//        System.out.println(minK);
        int res = 0;
        for(int i = 0; i < l.size(); i++)
            res += Math.abs(l.get(i) - minK);
        return res;
    }

    public static void main(String[] args) {
        Contest262 contest262 = new Contest262();
        int[][] grid = new int[][]{{2,4}, {6, 8}};
        System.out.println(contest262.minOperations(grid, 2));
    }
}
