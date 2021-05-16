package String;
//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
//
// 
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给你一个整数，将其转为罗马数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 3
//输出: "III" 
//
// 示例 2: 
//
// 
//输入: num = 4
//输出: "IV" 
//
// 示例 3: 
//
// 
//输入: num = 9
//输出: "IX" 
//
// 示例 4: 
//
// 
//输入: num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
// 
//
// 示例 5: 
//
// 
//输入: num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
//
// 
//
// 提示： 
//
// 
// 1 <= num <= 3999 
// 
// Related Topics 数学 字符串 
// 👍 601 👎 0

//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000


//leetcode submit region begin(Prohibit modification and deletion)
class IntegerToRoman12 {
    public static String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int numM = num / 1000;
        res.append("M".repeat(numM));
        num %= 1000;
        int numD = num / 500;
        res.append("D".repeat(numD));
        num %= 500;
        int numC = num / 100;
        res.append("C".repeat(numC));
        num %= 100;
        int numL = num / 50;
        res.append("L".repeat(numL));
        num %= 50;
        int numX = num / 10;
        res.append("X".repeat(numX));
        num %= 10;
        int numV = num / 5;
        res.append("V".repeat(numV));
        num %= 5;
        int numI = num;
        res.append("I".repeat(numI));
        String result = res.toString();
        result = result.replace("VIIII",  "IX");
        result = result.replace("IIII",  "IV");
        result = result.replace("LXXXX",  "XC");
        result = result.replace("XXXX", "XL");
        result = result.replace("DCCCC",  "CM");
        result = result.replace("CCCC", "CD");
        return result;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(4));
//        String str = "XXXX";
//        str = str.replace("XXXX", "XL");
//        System.out.println(str);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
