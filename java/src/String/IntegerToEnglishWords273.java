package String;
//Convert a non-negative integer num to its English words representation.
//
// 
// Example 1: 
// Input: num = 123
//Output: "One Hundred Twenty Three"
// Example 2: 
// Input: num = 12345
//Output: "Twelve Thousand Three Hundred Forty Five"
// Example 3: 
// Input: num = 1234567
//Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven
//"
// Example 4: 
// Input: num = 1234567891
//Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven 
//Thousand Eight Hundred Ninety One"
// 
// 
// Constraints: 
//
// 
// 0 <= num <= 231 - 1 
// 
// Related Topics 递归 数学 字符串 
// 👍 207 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class IntegerToEnglishWords273 {
    String[] sep = new String[]{"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        StringBuilder res = new StringBuilder();
        int idx = -1;
        while(num != 0) {
            idx++;
            int cur = num % 1000;
            num /= 1000;
            if(cur == 0) continue;
            res.insert(0, wordThreeBit(cur) + sep[idx] + " ");
        }
        return res.toString().trim();
    }
    String[] s = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] ss = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] sss = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String wordThreeBit(int num) {
        String res = "";
        int a = num % 10;
        int b = (num /= 10) % 10;
        int c = (num / 10) % 10;
        if(c > 0) res += s[c] + " Hundred ";
        if(b == 0) res += s[a] + (a != 0 ? " " : "");
        else if(b == 1) {
            res += ss[a] + " ";
        } else {
            res += sss[b] + " " + s[a] + (a != 0 ? " " : "");
        }
        return res;
    }

    public static void main(String[] args) {
        IntegerToEnglishWords273 integerToEnglishWords273 = new IntegerToEnglishWords273();
        System.out.println(integerToEnglishWords273.numberToWords(100000));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
