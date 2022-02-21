package String;
//There are n dominoes in a line, and we place each domino vertically upright. I
//n the beginning, we simultaneously push some of the dominoes either to the left 
//or to the right. 
//
// After each second, each domino that is falling to the left pushes the adjacen
//t domino on the left. Similarly, the dominoes falling to the right push their ad
//jacent dominoes standing on the right. 
//
// When a vertical domino has dominoes falling on it from both sides, it stays s
//till due to the balance of the forces. 
//
// For the purposes of this question, we will consider that a falling domino exp
//ends no additional force to a falling or already fallen domino. 
//
// You are given a string dominoes representing the initial state where: 
//
// 
// dominoes[i] = 'L', if the ith domino has been pushed to the left, 
// dominoes[i] = 'R', if the ith domino has been pushed to the right, and 
// dominoes[i] = '.', if the ith domino has not been pushed. 
// 
//
// Return a string representing the final state. 
//
// 
// Example 1: 
//
// 
//Input: dominoes = "RR.L"
//Output: "RR.L"
//Explanation: The first domino expends no additional force on the second domino
//.
// 
//
// Example 2: 
//
// 
//Input: dominoes = ".L.R...LR..L.."
//Output: "LL.RR.LLRRLL.."
// 
//
// 
// Constraints: 
//
// 
// n == dominoes.length 
// 1 <= n <= 105 
// dominoes[i] is either 'L', 'R', or '.'. 
// 
// Related Topics 双指针 字符串 动态规划 
// 👍 149 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class PushDominoes838 {
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        StringBuilder builder = new StringBuilder();
        int idx = 0;
        int r = -1;
        int l = -1;
        char[] dd = dominoes.toCharArray();
        while(idx < len) {
            if(dd[idx] == 'L') {
                if(r == -1) {   // l前无r
                    char[] c = new char[idx - l];
                    Arrays.fill(c, 'L');
                    builder.append(c);
                } else {
                    // r...l 合并
                    // gap/2 - 1 个R 和gap/2 个L
                    int gap = idx - r + 1;
                    char[] c = new char[gap / 2];
                    Arrays.fill(c, 'R');
                    builder.append(c);
                    builder.delete(builder.length() - 1, builder.length());
                    // .看奇偶
                    if(gap % 2 == 1) builder.append('.');
                    Arrays.fill(c, 'L');
                    builder.append(c);
                    r = -1;
                }
                l = idx;
            } else if(dd[idx] == 'R') {
                if(r == -1) {
                    char[] c = new char[idx - l - 1];
                    Arrays.fill(c, '.');
                    builder.append(c);
                    builder.append('R');
                } else {
                    char[] c = new char[idx - r];
                    Arrays.fill(c, 'R');
                    builder.append(c);
                }
                r = idx;
            }
            idx++;
        }
        if(r == -1) {
            char[] c = new char[len - l - 1];
            Arrays.fill(c, '.');
            builder.append(c);
        } else {
            char[] c = new char[len - r - 1];
            Arrays.fill(c, 'R');
            builder.append(c);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        PushDominoes838 pushDominoes838 = new PushDominoes838();
        String dominoes = ".L.R...LR..L..";
        System.out.println(pushDominoes838.pushDominoes(dominoes));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
