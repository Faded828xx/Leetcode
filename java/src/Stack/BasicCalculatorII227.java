package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* s表达式中运算符仅包含'+' '-' '*' '/' 不包含括号
* 每当遇到运算符时 此时记录的是前一个数字num 因此需记录preSign来操作num
* 将所有待加元素入栈 乘除只需将栈顶元素替换
* */
public class BasicCalculatorII227 {
    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+'; // 令首数字前运算符为'+'
        int num = 0;
        for(int i=0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i)))  // 记录多位运算数
                num = num * 10 + s.charAt(i) - '0';
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i)!=' ' || i==s.length()-1) {
                switch (preSign) {  // 运算数分隔 注意不要遗漏最后一个数
                    case '+' :
                        stack.push(num);
                        break;
                    case '-' :
                        stack.push(-num);
                        break;
                    case '*' :
                        stack.push(stack.pop()*num);    // '*' 将栈顶元素替换
                        break;
                    case '/' :
                        stack.push(stack.pop()/num);
                        break;
                }
                preSign = s.charAt(i);
                num = 0;    // 准备记录下一个运算数 将其置零
            }
        }
        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "1+9/3";
        System.out.println(calculate(s));
    }
}
