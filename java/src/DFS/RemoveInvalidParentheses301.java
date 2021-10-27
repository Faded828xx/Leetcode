package DFS;//Given a string s that contains parentheses and letters, remove the minimum num
//ber of invalid parentheses to make the input string valid. 
//
// Return all the possible results. You may return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: s = "()())()"
//Output: ["(())()","()()()"]
// 
//
// Example 2: 
//
// 
//Input: s = "(a)())()"
//Output: ["(a())()","(a)()()"]
// 
//
// Example 3: 
//
// 
//Input: s = ")("
//Output: [""]
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 25 
// s consists of lowercase English letters and parentheses '(' and ')'. 
// There will be at most 20 parentheses in s. 
// 
// Related Topics 广度优先搜索 字符串 回溯 
// 👍 533 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class RemoveInvalidParentheses301 {

    Set<String> res = new HashSet<>();  // 删除不同位可能得到相同的符合条件的string 去重
    char[] str; // s转化为数组
    int min;    // 最小删除次数
    int right;  // 总右括号数

    public List<String> removeInvalidParentheses(String s) {
        min = 26;
        str = s.toCharArray();
        for(char c : str) {
            if(c == ')')
                right++;
        }
        dfs(0, "", 0, 0, 0);
        return new ArrayList<>(res);
    }
    // usedL usedR 方便剪枝
    public void dfs(int idx, String sb, int delL, int usedL, int usedR) {
        if(delL > min) return ; // 最小删除次数
        if(usedL < usedR || usedL > right) return ; // 剪枝: 已用右括号数大于已用左括号数 已用左括号数大于总的右括号数  都是无效
        if(idx == str.length) {
            if(check(sb)) {
                res.add(sb);
                min = delL; // 这里貌似只会更新一次？
            }
            return ;
        }
        int offL = 0, offR = 0; // 都是为了剪枝
        if(str[idx] == '(') offL++;
        else if(str[idx] == ')') offR++;
        dfs(idx + 1, sb + str[idx], delL, usedL + offL, usedR + offR);
        dfs(idx + 1, sb, delL + 1, usedL, usedR);
    }
    // 栈 检查
    public boolean check(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] ss = s.toCharArray();
        for(char ch : ss) {
            switch (ch) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if(stack.isEmpty())
                        return false;
                    stack.pop();
                    break;
                default:
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses301 removeInvalidParentheses301 = new RemoveInvalidParentheses301();
        System.out.println(removeInvalidParentheses301.removeInvalidParentheses("()())()"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
