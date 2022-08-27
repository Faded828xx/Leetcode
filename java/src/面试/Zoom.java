package 面试;

import java.util.*;

/**
 * @author faded828x
 * @date 2022/8/13
 */

public class Zoom {

//    树结点染色计算总体权重
//    https://mp.weixin.qq.com/s/-_ALl1I9EIKAfR8gTvIovw
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String c = scanner.next();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < n - 1; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            List<Integer> l1 = map.getOrDefault(a, new ArrayList<>(2));
            List<Integer> l2 = map.getOrDefault(b, new ArrayList<>(2));
            l1.add(b);
            l2.add(a);
            map.put(a, l1);
            map.put(b, l2);
        }
        char[] color = c.toCharArray();
        int[] cntColor = new int[2];
        cntColor[color[0]=='R'?0:1]++;
        int res = dfs(1, cntColor, map, visited, color);
        System.out.println(res);
    }

    public static int dfs(int idx, int[] cntColor, Map<Integer, List<Integer>> map, Set<Integer> visited, char[] color) {
        visited.add(idx);
        List<Integer> next = map.get(idx);
        int res = 0;
        res += Math.abs(cntColor[0] - cntColor[1]);
        for(int n : next) {
            if(visited.contains(n)) continue;
            cntColor[color[n-1]=='R'?0:1]++;
            res += dfs(n, cntColor, map, visited, color);
            cntColor[color[n-1]=='R'?0:1]--;
        }
        return res;
    }

}
