package Contest.Weekly_Contest;

import org.junit.Test;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/8/8
 */
public class Contest253 {

    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (sb.toString().equals(s))
                return true;
            if (sb.length() > s.length())
                return false;
        }
        return false;
    }

    public int minStoneSum(int[] piles, int k) {
//        int len = piles.length;
//        for(int i = 0; i < k; i++) {
//            Arrays.sort(piles);
//            piles[len - 1] = (piles[len - 1] + 1) / 2;
//        }
//        int sum = 0;
//        for(int n : piles)
//            sum += n;
//        return sum;

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int n : piles)
            heap.add(n);
        for (int i = 0; i < k; i++) {
            if (!heap.isEmpty()) {
                int top = heap.poll();
                heap.add((top + 1) / 2);
            }
        }
        int sum = 0;
        for (int n : heap)
            sum += n;
        return sum;
    }

    public int minSwaps(String s) {
        int countR = 0;
        int countL = 0;
        for(char ch : s.toCharArray()) {
            if(ch == ']') {
                if (countL == 0)
                    countR++;
                else countL--;
            }
            else if(ch == '[') {
                countL++;
            }
        }
        return (countR + 1) / 2;
    }

    // Time Limit Exceeded
    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int len = obstacles.length;
        int[] dp = new int[len];
        dp[0] = 1;
        map.put(obstacles[0], 1);
        for(int i = 1; i < len; i++) {
            int cur = obstacles[i];

//            Integer pre = map.floorKey(cur);
//            if(pre == null) {
//                dp[i] = 1;
//            } else dp[i] = dp[map.get(pre)] + 1;
//

            int res = 1;
            for(int k : map.keySet()) {
                if(k > cur) {
                    break;
                }
                res = Math.max(res, map.get(k) + 1);
            }
            dp[i] = res;

            map.put(cur, res);
        }
        return dp;
    }

    // AC ????????????????????? ???????????????
    public static int[] longestObstacleCourseAtEachPosition2(int[] obstacles) {
        int len = obstacles.length;
        // ???????????? res[i]??????obstacles[i]??????????????????????????????
        int[] res = new int[len];
        // list[i]???????????? i+1 ??? ????????????????????? ??? ??????????????????
        List<Integer> list = new ArrayList<>();
        list.add(obstacles[0]);
        res[0] = 1;
        // ??????list ???obstacles[i]??? list ??????????????? ???LIS???????????? ??????????????????????????????
        // ????????? list ???????????????????????? obstacles[i]????????? ???????????? ?????????????????????LIS?????????????????????
        for(int i = 1; i < len; i++) {
            int size = list.size();
            int cur = obstacles[i];
            if (cur >= list.get(size - 1)) {
                list.add(cur);
                res[i] = size + 1;
            } else {
                int index = Collections.binarySearch(list, cur);
                if(index >= 0) {
                    // list???????????????????????? ????????????????????????
                    for(; index < size && list.get(index+1).equals(list.get(index)); index++);
                    list.set(index + 1, cur);
                    res[i] = index + 2;
                } else {
                    list.set(-index - 1, cur);
                    res[i] = -index;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] obstacles = new int[]{5,1,5,5,1,3,4,5,1,4};
        System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition2(obstacles)));
//        List<Integer> list = new ArrayList<>(){{
//            add(1);
//            add(3);
//            add(6);
//            add(19);
//        }};
//        int index = Collections.binarySearch(list, 7);
//        System.out.println(index);
    }
}
