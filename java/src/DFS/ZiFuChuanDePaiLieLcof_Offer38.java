package DFS;
//输入一个字符串，打印出该字符串中字符的所有排列。
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 327 👎 0

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class ZiFuChuanDePaiLieLcof_Offer38 {

    public static String[] permutation(String s) {
        Set<String> set = dfs(s, 0);
        String[] res = new String[set.size()];
        int index = 0;
        for(String str : set) {
            res[index++] = str;
        }
        return res;
    }

    public static Set<String> dfs(String s, int index) {
        int len = s.length();
        char cur = s.charAt(index);
        Set<String> nextSet = new HashSet<>();
        if(index==len-1) {
            nextSet.add(String.valueOf(cur));
            return nextSet;
        }
        Set<String> set = dfs(s, index+1);
        for(String str : set) {
            for(int i=0; i<=str.length(); i++) {
                StringBuilder sb = new StringBuilder(str);
                nextSet.add(sb.insert(i, cur).toString());
            }
        }
        return nextSet;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abc")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
