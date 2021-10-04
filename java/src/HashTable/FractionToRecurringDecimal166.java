package HashTable;//Given two integers representing the numerator and denominator of a fraction, r
//eturn the fraction in string format. 
//
// If the fractional part is repeating, enclose the repeating part in parenthese
//s. 
//
// If multiple answers are possible, return any of them. 
//
// It is guaranteed that the length of the answer string is less than 104 for al
//l the given inputs. 
//
// 
// Example 1: 
// Input: numerator = 1, denominator = 2
//Output: "0.5"
// Example 2: 
// Input: numerator = 2, denominator = 1
//Output: "2"
// Example 3: 
// Input: numerator = 2, denominator = 3
//Output: "0.(6)"
// Example 4: 
// Input: numerator = 4, denominator = 333
//Output: "0.(012)"
// Example 5: 
// Input: numerator = 1, denominator = 5
//Output: "0.2"
// 
// 
// Constraints: 
//
// 
// -231 <= numerator, denominator <= 231 - 1 
// denominator != 0 
// 
// Related Topics 哈希表 数学 字符串 
// 👍 301 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class FractionToRecurringDecimal166 {
    public String fractionToDecimal(int numerator, int denominator) {
        boolean neg = (numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0); // 正负号
        long nn = numerator > 0 ? numerator : -(long)numerator;
        long d = denominator > 0 ? denominator : -(long)denominator;
        long a = nn / d;
        long b = nn % d;    // 这里有很多冗余变量
        if(b == 0) return (neg ? "-" : "") + a; // 无小数部分
        Map<Long, Integer> map = new HashMap<>();   // 记录当前余数对应的小数索引(k,v) 当前余数k若在map中出现过 则对应v到末位为循环部分
        map.put(b * 10, 0); // 第一个余数 若重复出现该余数 则 0～末位 为循环部分
        long q = d;
        StringBuilder sb = new StringBuilder(); // 小数部分
        sb.append(b * 10 / q);  // 小数首位
        b = (b * 10 % q) * 10;
        map.put(b * 10, 1); // 第二个余数 若重复出现该余数 则 1～末位 为循环部分
        int idx = 2;
        while(b != 0) {
            if(b < q) { // 该小数位为0
                b *= 10;
                sb.append('0');
                map.put(b, idx++);
                continue;
            }
            if(b % q == 0) {    // 无循环小数
                sb.append(b / q);
                break;
            }
            long m = b / q; // 小数位
            long n = b % q; // 余数
            if(!map.containsKey(n * 10)) {
                sb.append(m);
                map.put(n * 10, idx++);
                b = n * 10;
            } else {    // 出现循环
                sb.append(m);   // 添加当前小数部分
                int i = map.get(n * 10);    // i～末位为一个循环
                String s = sb.toString();
                // 构造循环部分
                String sb2 = s.substring(0, i) +    // 非循环部分
                        '(' +
                        s.substring(i) + // 循环部分
                        ')';
                return (neg ? "-" : "") + a + "." + sb2;
            }
        }
        // System.out.println(sb);
        return (neg ? "-" : "") + a + "." + sb;
    }

    public static void main(String[] args) {
        FractionToRecurringDecimal166 fractionToRecurringDecimal166 = new FractionToRecurringDecimal166();
        System.out.println(fractionToRecurringDecimal166.fractionToDecimal(1, -2147483648));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
