package String;
//Given an m x n board of characters and a list of strings words, return all wor
//ds on the board. 
//
// Each word must be constructed from letters of sequentially adjacent cells, wh
//ere adjacent cells are horizontally or vertically neighboring. The same letter c
//ell may not be used more than once in a word. 
//
// 
// Example 1: 
//
// 
//Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f"
//,"l","v"]], words = ["oath","pea","eat","rain"]
//Output: ["eat","oath"]
// 
//
// Example 2: 
//
// 
//Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//Output: []
// 
//
// 
// Constraints: 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] is a lowercase English letter. 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] consists of lowercase English letters. 
// All the strings of words are unique. 
// 
// Related Topics 字典树 数组 字符串 回溯 矩阵 
// 👍 443 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class WordSearchII212 {


    char[][] b;
    int[][] move = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};   // right, left, up, down
    int row;
    int col;

    public List<String> findWords(char[][] board, String[] words) {
        Map<Character, Set<int[]>> map = new HashMap<>(26);
        b = board;
        row = board.length;
        col = board[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                Set<int[]> s = map.getOrDefault(board[i][j], new HashSet<>());
                s.add(new int[]{i, j});
                map.put(board[i][j], s);
            }
        }
        List<String> res = new ArrayList<>();
        boolean[][] vis = new boolean[row][col];
        for(String word : words) {
            char c = word.charAt(0);
            if(!map.containsKey(c)) continue;
            Set<int[]> s = map.get(c);
            for(int[] pos : s){

                if(check(pos[0], pos[1], word, vis, 0)) {
                    res.add(word);
                    break;
                }
            }
        }
        return res;
    }
    // dfs
    public boolean check(int x, int y, String word, boolean[][] vis, int idx) {
        int l = word.length();
        if(idx == l - 1) return true;
        vis[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + move[i][0];
            int ny = y + move[i][1];
            if(nx >= 0 && nx < row && ny >= 0 && ny < col && !vis[nx][ny] && b[nx][ny] == word.charAt(idx + 1)) {
                if(check(nx, ny, word, vis, idx + 1)) {
                    vis[x][y] = false;
                    return true;
                }
            }
        }
        vis[x][y] = false;
        return false;
    }


    public static void main(String[] args) {
        WordSearchII212 a = new WordSearchII212();
        char[][] board = new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'a','h','k','r'}, {'t','f','l','v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println(a.findWords(board, words));
//        System.out.println(a.check(0, 0, "oath", new boolean[4][4], 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
