package String;
//A complex number can be represented as a string on the form "real+imaginaryi"
//where: 
//
// 
// real is the real part and is an integer in the range [-100, 100]. 
// imaginary is the imaginary part and is an integer in the range [-100, 100]. 
// i2 == -1. 
// 
//
// Given two complex numbers num1 and num2 as strings, return a string of the co
//mplex number that represents their multiplications. 
//
// 
// Example 1: 
//
// 
//Input: num1 = "1+1i", num2 = "1+1i"
//Output: "0+2i"
//Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it 
//to the form of 0+2i.
// 
//
// Example 2: 
//
// 
//Input: num1 = "1+-1i", num2 = "1+-1i"
//Output: "0+-2i"
//Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it
// to the form of 0+-2i.
// 
//
// 
// Constraints: 
//
// 
// num1 and num2 are valid complex numbers. 
// 
// Related Topics 数学 字符串 模拟 
// 👍 104 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class ComplexNumberMultiplication537 {
    // 直接调api字符转整数
    public String complexNumberMultiply(String num1, String num2) {
        int n1 = Integer.parseInt(num1.substring(0, num1.indexOf('+')));
        int n2 = Integer.parseInt(num1.substring(num1.indexOf('+') + 1, num1.length() - 1));
        int n3 = Integer.parseInt(num2.substring(0, num2.indexOf('+')));
        int n4 = Integer.parseInt(num2.substring(num2.indexOf('+') + 1, num2.length() - 1));
        return (n1 * n3 - n2 * n4) + "+" + (n2 * n3 + n1 * n4) + "i";
    }

    public static void main(String[] args) {
        ComplexNumberMultiplication537 complexNumberMultiplication537 = new ComplexNumberMultiplication537();
        String num1 = "1+1i";
        String num2 = "1+1i";
        System.out.println(complexNumberMultiplication537.complexNumberMultiply(num1, num2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)