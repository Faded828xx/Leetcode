package Contest.Weekly_Contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author faded828x
 * @date 2021/9/5
 */
public class Contest257 {

    // AC
    int res = 0;
    public int countQuadruplets(int[] nums) {
        dfs(nums, 0, 0, 0);
        return res;
    }
    public void dfs(int[] nums, int idx, int curSize, int curSum) {
        if(idx == nums.length) return ;
        if(curSize < 3) {
            dfs(nums, idx + 1, curSize + 1, curSum + nums[idx]);
            dfs(nums, idx + 1, curSize, curSum);
        } else {
            if(nums[idx] ==  curSum) res++;
            dfs(nums, idx + 1, curSize, curSum);
        }
    }

    // AC
    public int numberOfWeakCharacters(int[][] properties) {
        int res = 0;
        int len = properties.length;
        Arrays.sort(properties, (a1, a2) -> {
            return (a1[0] + a1[1]) - (a2[0] + a2[1]);
        });
        for(int i = 0; i < len; i++) {
            boolean flag = false;
            int a = properties[i][0];
            int b = properties[i][1];
            for(int j = len - 1; j >= i + 1; j--) {
                if((properties[j][0] + properties[j][1]) <= (a + b)) break;
                if(properties[j][0] > a && properties[j][1] > b) {
                    flag = true;
                    break;
                }
            }
            if(flag) res++;
        }
        return res;
    }

//    long days = 0;
//    int INF = 1000000007;
//    public int firstDayBeenInAllRooms(int[] nextVisit) {
//
//        dfs(nextVisit, new int[nextVisit.length], new HashSet<>(), 0);
//
//        return (int) days;
//    }
//    public void dfs(int[] nextVisit, int[] times, Set<Integer> curV, int curIdx) {
//        if(curV.size() == nextVisit.length) {
//            return ;
//        }
//        days = (days + 1) % INF;
//        times[curIdx]++;
//        curV.add(curIdx);
//        if(times[curIdx] % 2 == 1) {
//            dfs(nextVisit, times, curV, nextVisit[curIdx]);
//        } else {
//            dfs(nextVisit, times, curV, (curIdx + 1) % nextVisit.length);
//        }
//    }

    // TLE 往前往后应该都可以快速计算
    public static int firstDayBeenInAllRooms(int[] nextVisit) {
        Set<Integer> visited = new HashSet<>();
        int n = nextVisit.length;
        long res = -1;
        int INF = 1000000007;
        int idx = 0;
        int[] times = new int[n];
        while(visited.size() != n) {
            res = (res + 1) % INF;
            times[idx]++;
            visited.add(idx);
            if(times[idx] % 2 == 1)
                idx = nextVisit[idx];
            else idx = (idx + 1) % n;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        int[] a = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(firstDayBeenInAllRooms(a));
    }

}
