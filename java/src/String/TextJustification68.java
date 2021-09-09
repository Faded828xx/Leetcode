package String;
//Given an array of strings words and a width maxWidth, format the text such tha
//t each line has exactly maxWidth characters and is fully (left and right) justif
//ied. 
//
// You should pack your words in a greedy approach; that is, pack as many words 
//as you can in each line. Pad extra spaces ' ' when necessary so that each line h
//as exactly maxWidth characters. 
//
// Extra spaces between words should be distributed as evenly as possible. If th
//e number of spaces on a line does not divide evenly between words, the empty slo
//ts on the left will be assigned more spaces than the slots on the right. 
//
// For the last line of text, it should be left-justified and no extra space is 
//inserted between words. 
//
// Note: 
//
// 
// A word is defined as a character sequence consisting of non-space characters 
//only. 
// Each word's length is guaranteed to be greater than 0 and not exceed maxWidth
//. 
// The input array words contains at least one word. 
// 
//
// 
// Example 1: 
//
// 
//Input: words = ["This", "is", "an", "example", "of", "text", "justification."]
//, maxWidth = 16
//Output:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//] 
//
// Example 2: 
//
// 
//Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 
//16
//Output:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//Explanation: Note that the last line is "shall be    " instead of "shall     b
//e", because the last line must be left-justified instead of fully-justified.
//Note that the second line is also left-justified becase it contains only one w
//ord. 
//
// Example 3: 
//
// 
//Input: words = ["Science","is","what","we","understand","well","enough","to","
//explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidt
//h = 20
//Output:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//] 
//
// 
// Constraints: 
//
// 
// 1 <= words.length <= 300 
// 1 <= words[i].length <= 20 
// words[i] consists of only English letters and symbols. 
// 1 <= maxWidth <= 100 
// words[i].length <= maxWidth 
// 
// Related Topics 字符串 模拟 
// 👍 177 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class TextJustification68 {
    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> res = new ArrayList<>();

        int len = words.length;
        int ptr = 0;
        while(ptr != len) {
            int curL = 0;
            int curCnt = 0;
            int from = ptr;
            int to = ptr;
            // [from, to)
            while(to < len && curL <= maxWidth + 1) {
                curL += words[to].length() + 1;
                curCnt++;
                to++;
            }
            if(curL > maxWidth + 1) {
                to--;
                curL -= words[to].length() + 1;
                curCnt--;
            }
            // 该行一个单词
            if(curCnt == 1) {
                int last = maxWidth - words[from].length();
                char[] a = new char[last];
                Arrays.fill(a, ' ');
                res.add(words[from] + new String(a));
                ptr = to;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            if(to != len) {
                int cntB = curCnt - 1; // 该行空白部分个数
                int numB = maxWidth - curL + curCnt;     // 该行空白总长度
                int q = numB / cntB;    // 每个空白平均长度
                int mod = numB % cntB;  // 前mod个空白长度为 q + 1
                while(from != to) {
                    sb.append(words[from]);
                    from++;
                    if(cntB > 0) {
                        char[] a = new char[q];
                        Arrays.fill(a, ' ');
                        sb.append(a);
                        cntB--;
                    }
                    if(mod > 0) {
                        sb.append(' ');
                        mod--;
                    }
                }

                // 最后一行
            } else {
                while(from != to - 1) {
                    sb.append(words[from]).append(" "); // 空白部分长度均为1
                    from++;
                }
                sb.append(words[from]);
                int last = maxWidth - sb.length();
                char[] a = new char[last];
                Arrays.fill(a, ' ');    // 填补剩余长度
                sb.append(a);
            }
            res.add(sb.toString());


            ptr = to;
        }



        return res;
    }


    public static void main(String[] args) {
        String[] words = new String[]{"ask","not","what","your","country","can","do","for","you","ask"
                ,"what","you","can","do","for","your","country"};
        System.out.println(fullJustify(words, 16));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
