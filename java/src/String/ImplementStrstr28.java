package String;
//实现 strStr() 函数。
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 104 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 
// 👍 820 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class ImplementStrstr28 {
    // KMP字符串匹配 利用前缀数组 当不匹配时 忽略已匹配字符中的相同前后缀
    public int strStr(String haystack, String needle) {
        int lenS = haystack.length();
        int lenN = needle.length();
        if(lenN==0) return 0;
        int[] next = new int[lenN]; // needle的前缀数组 最长公共前后缀
        for(int i=1, j=0; i<lenN; i++) {    // 更新needle的前缀数组
            while(j>0 && needle.charAt(i)!=needle.charAt(j)) {   // 若next[i]与next[j]不相等 则next[i]<=next[i-1]
                j = next[j-1];
            }
            if(needle.charAt(i)==needle.charAt(j))  // next[i]与next[j]相等 则next[i]=next[i-1]+1
                j++;
            next[i] = j;    // i为索引 j为前缀的长度
        }
        for(int i=0, j=0; i<lenS; i++) {    // 开始匹配 i为haystack索引 j为needle中已匹配字符数
            while(j>0 && haystack.charAt(i)!=needle.charAt(j)) {    // 不等则前缀对齐 重新比较
                j = next[j-1];
            }
            if(haystack.charAt(i)==needle.charAt(j))    // needle中匹配下一字符
                j++;
            if(j==lenN) // 匹配成功
                return i - j + 1;
        }
        return -1;
    }

    // Sunday算法 记匹配失败字符后一位元素为cur 在needle找到cur最后出现的索引 将其与cur对齐并匹配 而匹配字符串的首索引的跳转需要偏移表来实现
    // 该偏移表同KMP算法中的前缀数组一样 仅与needle有关
    public static int strStr2(String haystack, String needle) {
        int lenS = haystack.length();
        int lenN = needle.length();
        if(lenN==0) return 0;
        int[] offset = new int[26]; // 偏移表初始化为lenN+1
        Arrays.fill(offset, lenN+1);
        for(int i=0; i<lenN; i++) { // 只有小写字符 可以用26位数组代替哈希表
            int j = needle.charAt(i) - 'a';
            offset[j] = lenN - i;
        }
        int index = 0;
        while(index<=lenS-lenN) {
            if(haystack.substring(index, index+lenN).equals(needle))
                return index;
            if(index>=lenS-lenN) return -1;
            index += offset[haystack.charAt(index+lenN)-'a'];   // 匹配失败 偏移
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr2("hello","ll"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
