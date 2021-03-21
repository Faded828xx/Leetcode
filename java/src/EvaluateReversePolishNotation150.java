import java.util.ArrayDeque;
import java.util.Deque;

// 逆波兰表达式求值
// 扫描字符串 遇到数字入栈 遇到运算符出栈两个数字 并将运算结果入栈
public class EvaluateReversePolishNotation150 {
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s : tokens) {
            if(s.length()>1||(s.charAt(0)>=48 && s.charAt(0)<=57))
                stack.push(Integer.parseInt(s));
            else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                if(s.equals("*"))
                    stack.push(num1*num2);
                else if(s.equals("/"))
                    stack.push(num1/num2);
                else if(s.equals("+"))
                    stack.push(num1+num2);
                else if(s.equals("-"))
                    stack.push(num1-num2);
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] tokens = new String[]{"4","3","-"};
        System.out.println(evalRPN(tokens));
    }
}
