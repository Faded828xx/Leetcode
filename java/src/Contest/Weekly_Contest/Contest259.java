package Contest.Weekly_Contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2021/9/19
 */
public class Contest259 {

    // AC
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for(String s : operations){
            char ch = s.charAt(1);
            if(ch == '+')
                res++;
            else res--;

        }
        return res;

    }

    // AC
    public int sumOfBeauties(int[] nums) {
        int len = nums.length;
        int[] n = Arrays.copyOf(nums, len);
        Arrays.sort(n);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            map.put(n[i], i);
        }
        int curMax = nums[0];
        int res = 0;
        for(int i = 1; i < len - 1; i++) {
            if(nums[i] > curMax && map.get(nums[i]) >= i)
                res += 2;
            else if(nums[i] > nums[i - 1] && nums[i] < nums[i + 1])
                res++;
        }
        return res;
    }

    // AC
    class DetectSquares {

        int[][] grid;


        public DetectSquares() {
            grid = new int[1001][1001];
        }

        public void add(int[] point) {
            grid[point[0]][point[1]]++;
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            int res = 0;
            for(int i = 0; i <= 1000; i++) {
                if(i == y || grid[x][i] == 0) continue;
                int n1 = grid[x][i];
                int edge = Math.abs(y - i);
                int n2 = 0;
                int n3 = 0;
                if(x + edge <= 1000) {
                    n2 = grid[x + edge][i];
                    n3 = grid[x + edge][y];
                    res += n1 * n2 * n3;
                }
                if(x - edge >= 0) {
                    n2 = grid[x - edge][i];
                    n3 = grid[x - edge][y];
                    res += n1 * n2 * n3;
                }
            }
            return res;
        }
    }

}




/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
