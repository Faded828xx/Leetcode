package StackAndHeap;
//Given n pairs of parentheses, write a function to generate all combinations of
// well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 2217 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class GenerateParentheses22 {
    List<String> res;
    StringBuilder sb;
    int nn;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        nn = n;
        dfs(0, 0);
        return res;
    }
    void dfs(int left, int right) {
        if(left == right && left == nn) {
            res.add(sb.toString());
            return ;
        }
        else if(left < right || left > nn)
            return ;
        sb.append('(');
        dfs(left + 1, right);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        dfs(left, right + 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        GenerateParentheses22 generateParentheses22 = new GenerateParentheses22();
        System.out.println(generateParentheses22.generateParenthesis(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
