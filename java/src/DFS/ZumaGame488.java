package DFS;
//You are playing a variation of the game Zuma.
//
// In this variation of Zuma, there is a single row of colored balls on a board,
// where each ball can be colored red 'R', yellow 'Y', blue 'B', green 'G', or whi
//te 'W'. You also have several colored balls in your hand. 
//
// Your goal is to clear all of the balls from the board. On each turn: 
//
// 
// Pick any ball from your hand and insert it in between two balls in the row or
// on either end of the row. 
// If there is a group of three or more consecutive balls of the same color, rem
//ove the group of balls from the board.
// 
// If this removal causes more groups of three or more of the same color to form
//, then continue removing each group until there are none left. 
// 
// 
// If there are no more balls on the board, then you win the game. 
// Repeat this process until you either win or do not have any more balls in you
//r hand. 
// 
//
// Given a string board, representing the row of balls on the board, and a strin
//g hand, representing the balls in your hand, return the minimum number of balls 
//you have to insert to clear all the balls from the board. If you cannot clear al
//l the balls from the board using the balls in your hand, return -1. 
//
// 
// Example 1: 
//
// 
//Input: board = "WRRBBW", hand = "RB"
//Output: -1
//Explanation: It is impossible to clear all the balls. The best you can do is:
//- Insert 'R' so the board becomes WRRRBBW. WRRRBBW -> WBBW.
//- Insert 'B' so the board becomes WBBBW. WBBBW -> WW.
//There are still balls remaining on the board, and you are out of balls to inse
//rt. 
//
// Example 2: 
//
// 
//Input: board = "WWRRBBWW", hand = "WRBRW"
//Output: 2
//Explanation: To make the board empty:
//- Insert 'R' so the board becomes WWRRRBBWW. WWRRRBBWW -> WWBBWW.
//- Insert 'B' so the board becomes WWBBBWW. WWBBBWW -> WWWW -> empty.
//2 balls from your hand were needed to clear the board.
// 
//
// Example 3: 
//
// 
//Input: board = "G", hand = "GGGGG"
//Output: 2
//Explanation: To make the board empty:
//- Insert 'G' so the board becomes GG.
//- Insert 'G' so the board becomes GGG. GGG -> empty.
//2 balls from your hand were needed to clear the board.
// 
//
// Example 4: 
//
// 
//Input: board = "RBYYBBRRB", hand = "YRBGB"
//Output: 3
//Explanation: To make the board empty:
//- Insert 'Y' so the board becomes RBYYYBBRRB. RBYYYBBRRB -> RBBBRRB -> RRRB ->
// B.
//- Insert 'B' so the board becomes BB.
//- Insert 'B' so the board becomes BBB. BBB -> empty.
//3 balls from your hand were needed to clear the board.
// 
//
// 
// Constraints: 
//
// 
// 1 <= board.length <= 16 
// 1 <= hand.length <= 5 
// board and hand consist of the characters 'R', 'Y', 'B', 'G', and 'W'. 
// The initial row of balls on the board will not have any groups of three or mo
//re consecutive balls of the same color. 
// 
// Related Topics 字符串 回溯 
// 👍 123 👎 0

import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class ZumaGame488 {

    int res = 6;
    int ll;
    public int findMinStep(String board, String hand) {
        ll = hand.length();
        dfs(board, hand);
        return res == 6 ? -1 : res;
    }
    // 关键是剪枝条件比较复杂
    public void dfs(String board, String hand) {
        board = convert(board);
        if(ll - hand.length() >= res) return ;  // 剪枝 当前移除的球数不小于res
        if(board.length() == 0) {   // 终止
            res = ll - hand.length();
        }
        if(hand.length() == 0) return ; // 手里没球了
        for(int i = 0; i < board.length(); i++) {
            Set<Character> s = new HashSet<>();
            for(int j = 0; j < hand.length(); j++) {
                char c = hand.charAt(j);
                if(!s.add(c)) continue;    // hand中相同颜色球只试一个即可
                if(i > 0 && board.charAt(i) == board.charAt(i - 1) && board.charAt(i) == c) continue; // hand中当前球 对board中相同颜色的串 插在哪都一样
                if(!((c == board.charAt(i))||(i > 0 && board.charAt(i) == board.charAt(i - 1)))) continue;  // 当前hand放在相同颜色的子串的首位 或者 不同颜色的子串的中间 将其隔开 case31
                dfs(board.substring(0, i) + c + board.substring(i), hand.substring(0, j) + hand.substring(j + 1));
            }
        }
    }
    // 消消乐
    public String convert(String board) {
        int len = board.length();
        int idx = 0;
        StringBuilder res = new StringBuilder();
        boolean flag = false;
        while(idx < len) {
            int cnt = 0;
            char c = board.charAt(idx);
            while(idx < len && c == board.charAt(idx)) {
                idx++;
                cnt++;
            }
            if(cnt < 3)
                res.append(String.valueOf(c).repeat(cnt));
            else flag = true;
        }
        return flag ? convert(res.toString()) : res.toString();
    }

    public static void main(String[] args) {
        ZumaGame488 zumaGame488 = new ZumaGame488();
        System.out.println(zumaGame488.findMinStep("BGGRRYY", "BBYRG"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
