package HashTable;
//There is a group of n people labeled from 0 to n - 1 where each person has a d
//ifferent amount of money and a different level of quietness. 
//
// You are given an array richer where richer[i] = [ai, bi] indicates that ai ha
//s more money than bi and an integer array quiet where quiet[i] is the quietness 
//of the ith person. All the given data in richer are logically correct (i.e., the
// data will not lead you to a situation where x is richer than y and y is richer 
//than x at the same time). 
//
// Return an integer array answer where answer[x] = y if y is the least quiet pe
//rson (that is, the person y with the smallest value of quiet[y]) among all peopl
//e who definitely have equal to or more money than the person x. 
//
// 
// Example 1: 
//
// 
//Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,
//6,1,7,0]
//Output: [5,5,2,5,4,5,6,7]
//Explanation: 
//answer[0] = 5.
//Person 5 has more money than 3, which has more money than 1, which has more mo
//ney than 0.
//The only person who is quieter (has lower quiet[x]) is person 7, but it is not
// clear if they have more money than person 0.
//answer[7] = 7.
//Among all people that definitely have equal to or more money than person 7 (wh
//ich could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lowe
//r quiet[x]) is person 7.
//The other answers can be filled out with similar reasoning.
// 
//
// Example 2: 
//
// 
//Input: richer = [], quiet = [0]
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// n == quiet.length 
// 1 <= n <= 500 
// 0 <= quiet[i] < n 
// All the values of quiet are unique. 
// 0 <= richer.length <= n * (n - 1) / 2 
// 0 <= ai, bi < n 
// ai != bi 
// All the pairs of richer are unique. 
// The observations in richer are all logically consistent. 
// 
// Related Topics 深度优先搜索 图 拓扑排序 数组 
// 👍 83 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class LoudAndRich851 {
    // 写的好丑
    Map<Integer, Set<Integer>> map;
    int[] quiet;
    int[][] visited;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        map = new HashMap<>(n);
        visited = new int[n][2];
        for(int i = 0; i < n; i++)
            visited[i][1] = -1;
        this.quiet = quiet;
        for(int[] r : richer) {
            Set<Integer> s = map.getOrDefault(r[1], new HashSet<>());
            s.add(r[0]);
            map.put(r[1], s);
        }
        int[] res = new int[n];
        for(int i = 0; i < n; i++)
            res[i] = dfs(i)[0];
        return res;
    }
    // 比person更有钱的最小quiet值的人和值
    int[] dfs(int person) {
        int min = quiet[person];
        int per = person;
        if(!map.containsKey(person)) {
            visited[person][1] = min;
            visited[person][0] = person;
            return new int[]{per, min};
        }
        for(int p : map.get(person)) {
            if(visited[p][1] != -1) {
                if(visited[p][1] < min) {
                    min = visited[p][1];
                    per = visited[p][0];
                }
            } else {
                int[] nn = dfs(p);
                if(nn[1] < min) {
                    min = nn[1];
                    per = nn[0];
                }
            }
        }
        visited[person][1] = min;
        visited[person][0] = per;
        return new int[]{per, min};
    }

    public static void main(String[] args) {
        LoudAndRich851 loudAndRich851 = new LoudAndRich851();
        int[][] richer = new int[][]{{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = new int[]{3,2,5,4,6,1,7,0};
        System.out.println(Arrays.toString(loudAndRich851.loudAndRich(richer, quiet)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
