package HashTable;
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1371 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class LetterCombinationsOfAPhoneNumber17 {
    // BFS 去年打卡直接抄的题解 回溯快
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        int len = digits.length();
        if (len == 0) return res;
        Deque<String> queue = new ArrayDeque<>();
        Map<Character, String> map = new HashMap<>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        char[] digitsArr = digits.toCharArray();
        // 加入首元素
        String first = map.get(digitsArr[0]);
        for (char digit : first.toCharArray()) {
            queue.add(String.valueOf(digit));
        }
        int index = 0;
        while (index < len - 1) {
            index++;
            int size = queue.size();
            String append = map.get(digitsArr[index]);
            for (int i = 0; i < size; i++) {
                String cur = queue.remove();
                for (int j = 0; j < append.length(); j++) {
                    queue.add(cur + append.charAt(j));
                }
            }
        }
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            res.add(queue.remove());
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
