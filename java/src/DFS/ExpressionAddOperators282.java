package DFS;//Given a string num that contains only digits and an integer target, return all
// possibilities to insert the binary operators '+', '-', and/or '*' between the d
//igits of num so that the resultant expression evaluates to the target value. 
//
// Note that operands in the returned expressions should not contain leading zer
//os. 
//
// 
// Example 1: 
//
// 
//Input: num = "123", target = 6
//Output: ["1*2*3","1+2+3"]
//Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
// 
//
// Example 2: 
//
// 
//Input: num = "232", target = 8
//Output: ["2*3+2","2+3*2"]
//Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
// 
//
// Example 3: 
//
// 
//Input: num = "105", target = 5
//Output: ["1*0+5","10-5"]
//Explanation: Both "1*0+5" and "10-5" evaluate to 5.
//Note that "1-05" is not a valid expression because the 5 has a leading zero.
// 
//
// Example 4: 
//
// 
//Input: num = "00", target = 0
//Output: ["0*0","0+0","0-0"]
//Explanation: "0*0", "0+0", and "0-0" all evaluate to 0.
//Note that "00" is not a valid expression because the 0 has a leading zero.
// 
//
// Example 5: 
//
// 
//Input: num = "3456237490", target = 9191
//Output: []
//Explanation: There are no expressions that can be created from "3456237490" to
// evaluate to 9191.
// 
//
// 
// Constraints: 
//
// 
// 1 <= num.length <= 10 
// num consists of only digits. 
// -231 <= target <= 231 - 1 
// 
// Related Topics 数学 字符串 回溯 
// 👍 276 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class ExpressionAddOperators282 {
    List<String> res = new ArrayList<>();
    int l;
    int tar;
    public List<String> addOperators(String num, int target) {
        l = num.length();
        if(l == 1) {

            return target == Integer.parseInt(num) ? new ArrayList<>(){{add(num);}} : res;
        }
        tar = target;
        char[] cur = new char[(l << 1) - 1];
        for(int i = 0; i < l - 1; i++) {
            cur[i << 1] = num.charAt(i);
            cur[(i << 1) + 1] = '.';
        }
        cur[(l << 1) - 2] = num.charAt(l - 1);
        dfs(cur, 1);
        return res;
    }
    public long calculate(char[] cur) {
        List<Long> l1 = new ArrayList<>();
        List<Character> l2 = new ArrayList<>();
        int ll = cur.length;
        for(int i = 0; i < ll; i++) {
            long n = cur[i] - '0';
            long head = n;
            int idx = i;
            i++;
            while(i < ll && (cur[i] != '+' && cur[i] != '-' && cur[i] != '*')) {
                if(cur[i] == ' ') {
                    i++;
                    continue;
                }
                n = n * 10 + cur[i] - '0';
                i++;
            }
            if(head == 0 && i >= idx + 2) return tar - 1;  // 当前数首位为0 则该表达式不符条件 不用继续计算 直接返回一个不为target的值
            l1.add(n);
            if(i < ll)
                l2.add(cur[i]);
        }
        long res = l1.get(0);
        long pre = res;
        for(int i = 1; i < l1.size(); i++) {
            switch (l2.get(i - 1)) {
                case '+':
                    res += l1.get(i);
                    pre = l1.get(i);
                    break;
                case '-' :
                    res -= l1.get(i);
                    pre = -l1.get(i);
                    break;
                case '*':
                    res = res - pre + pre * l1.get(i);
                    pre = pre * l1.get(i);
            }
        }
        return res;
    }
    public String convert(char[] cur) {
        StringBuilder sb = new StringBuilder();
        for(char ch : cur) {
            if (ch != ' ') {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    public void dfs(char[] cur, int idx) {
        if(idx == (l << 1) - 1) {
            if(tar == calculate(cur))
                res.add(convert(cur));
            return ;
        }
        cur[idx] = ' ';
        dfs(cur, idx + 2);
        cur[idx] = '+';
        dfs(cur, idx + 2);
        cur[idx] = '-';
        dfs(cur, idx + 2);
        cur[idx] = '*';
        dfs(cur, idx + 2);
    }

    public static void main(String[] args) {
        ExpressionAddOperators282 expressionAddOperators282 = new ExpressionAddOperators282();
        System.out.println(expressionAddOperators282.addOperators("2147483648", -2147483648));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
