package DFS;
//给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
//
// 请返回所有可行解 s 中最长长度。 
//
// 
//
// 示例 1： 
//
// 输入：arr = ["un","iq","ue"]
//输出：4
//解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
// 
//
// 示例 2： 
//
// 输入：arr = ["cha","r","act","ers"]
//输出：6
//解释：可能的解答有 "chaers" 和 "acters"。
// 
//
// 示例 3： 
//
// 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
//输出：26
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 16 
// 1 <= arr[i].length <= 26 
// arr[i] 中只含有小写英文字母 
// 
// Related Topics 位运算 回溯算法 
// 👍 107 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumLengthOfAConcatenatedStringWithUniqueCharacters1239 {
    // 回溯很好想 位运算也很好想 回溯需记录前面的信息
    public static int maxLength(List<String> arr) {
        return dfs(arr, 0, 0, 0);
    }

    public static int dfs(List<String> arr, int index, int preBit, int preCount) {
        int len = arr.size();
        if (index == len) {
            return preCount;
        }
        String curStr = arr.get(index);
        int curCount = 0;
        int curBit = 0;
        boolean flag = true;    // curStr若存在相同字符，则无效
        for (char ch : curStr.toCharArray()) {
            int tmp = curBit;
            curBit |= 1 << (ch - 'a');
            if (tmp == curBit)
                flag = false;
            curCount++;
        }
        // 前面的str是否选择已经确定 只有当前str可选择时才会考虑添加与否
        int and = preBit + curBit;
        int xor = preBit | curBit;
        int res = 0;
        if (flag && and == xor) {
            res = dfs(arr, index + 1, and, preCount + curCount);
        }
        res = Math.max(res, dfs(arr, index + 1, preBit, preCount));
        return res;
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>() {
            {
                add("yy");
                add("bkhwmpbiisbldzknpm");
            }
        };
        System.out.println(maxLength(arr));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
