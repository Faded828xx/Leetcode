package Math;
//给定一个正整数，返回它在 Excel 表中相对应的列名称。
//
// 例如， 
//
//     1 -> A
//    2 -> B
//    3 -> C
//    ...
//    26 -> Z
//    27 -> AA
//    28 -> AB 
//    ...
// 
//
// 示例 1: 
//
// 输入: 1
//输出: "A"
// 
//
// 示例 2: 
//
// 输入: 28
//输出: "AB"
// 
//
// 示例 3: 
//
// 输入: 701
//输出: "ZY"
// 
// Related Topics 数学 字符串 
// 👍 366 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class ExcelSheetColumnTitle168 {
    public static String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        int n = columnNumber;
        while(n != 0) {
            int mod = n % 26;
            mod = mod == 0 ? 26 : mod;
            n = (n - mod) / 26;
            char cur = (char) (mod - 1 + 'A');
            res.append(cur);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(52));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
