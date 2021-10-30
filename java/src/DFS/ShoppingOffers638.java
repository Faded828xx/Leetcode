package DFS;
//In LeetCode Store, there are n items to sell. Each item has a price. However,
//there are some special offers, and a special offer consists of one or more diffe
//rent kinds of items with a sale price. 
//
// You are given an integer array price where price[i] is the price of the ith i
//tem, and an integer array needs where needs[i] is the number of pieces of the it
//h item you want to buy. 
//
// You are also given an array special where special[i] is of size n + 1 where s
//pecial[i][j] is the number of pieces of the jth item in the ith offer and specia
//l[i][n] (i.e., the last integer in the array) is the price of the ith offer. 
//
// Return the lowest price you have to pay for exactly certain items as given, w
//here you could make optimal use of the special offers. You are not allowed to bu
//y more items than you want, even if that would lower the overall price. You coul
//d use any of the special offers as many times as you want. 
//
// 
// Example 1: 
//
// 
//Input: price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
//Output: 14
//Explanation: There are two kinds of items, A and B. Their prices are $2 and $5
// respectively. 
//In special offer 1, you can pay $5 for 3A and 0B
//In special offer 2, you can pay $10 for 1A and 2B. 
//You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2)
//, and $4 for 2A.
// 
//
// Example 2: 
//
// 
//Input: price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
//Output: 11
//Explanation: The price of A is $2, and $3 for B, $4 for C. 
//You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
//You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer 
//#1), and $3 for 1B, $4 for 1C. 
//You cannot add more items, though only $9 for 2A ,2B and 1C.
// 
//
// 
// Constraints: 
//
// 
// n == price.length 
// n == needs.length 
// 1 <= n <= 6 
// 0 <= price[i] <= 10 
// 0 <= needs[i] <= 10 
// 1 <= special.length <= 100 
// special[i].length == n + 1 
// 0 <= special[i][j] <= 50 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 
// 👍 292 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class ShoppingOffers638 {

    List<Integer> pri;
    List<List<Integer>> sp;
    int n;

    // dfs暴力搜索
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        n = price.size();
        List<Integer> l = new ArrayList<>();
        for(int j = 0; j < special.size(); j++) {
            List<Integer> spe = special.get(j);
            int num = 0;
            for(int i = 0; i < spe.size() - 1; i++)
                num += spe.get(i) * price.get(i);
            if(num <= spe.get(spe.size() - 1)) l.add(j);
        }
//        for(int i : l) special.remove(i);
        int size = l.size();
        for(int i = size - 1; i >= 0; i--)
            special.remove((int)l.get(i));
        pri = price;
        sp = special;

        int[] needsArr = new int[n];
        for(int i = 0; i < n; i++)
            needsArr[i] = needs.get(i);
        return dfs(needsArr);
    }

    public int dfs(int[] needs) {
        int sum1 = 0;
        for(int i = 0; i < n; i++) {
            sum1 += needs[i] * pri.get(i);
        }
        for(List<Integer> s : sp) {
            boolean flag = false;
            int[] nextNeeds = Arrays.copyOf(needs, n);
            for(int i = 0; i < n; i++) {
                nextNeeds[i] -= s.get(i);
                if(nextNeeds[i] < 0) {
                    flag = true;
                    break;
                }
            }
            if(flag) continue;
            sum1 = Math.min(sum1, s.get(n) + dfs(nextNeeds));
        }
        return sum1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
