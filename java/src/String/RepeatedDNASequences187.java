package String;//The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C
//', 'G', and 'T'. 
//
// 
// For example, "ACGAATTCCG" is a DNA sequence. 
// 
//
// When studying DNA, it is useful to identify repeated sequences within the DNA
//. 
//
// Given a string s that represents a DNA sequence, return all the 10-letter-lon
//g sequences (substrings) that occur more than once in a DNA molecule. You may re
//turn the answer in any order. 
//
// 
// Example 1: 
// Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//Output: ["AAAAACCCCC","CCCCCAAAAA"]
// Example 2: 
// Input: s = "AAAAAAAAAAAAA"
//Output: ["AAAAAAAAAA"]
// 
// 
// Constraints: 
//
// 
// 1 <= s.length <= 105 
// s[i] is either 'A', 'C', 'G', or 'T'. 
// 
// Related Topics 位运算 哈希表 字符串 滑动窗口 哈希函数 滚动哈希 
// 👍 212 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class RepeatedDNASequences187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        char[] ss = s.toCharArray();
        if(ss.length <= 10) return res;
        int cur = 0;
        for(int i = 0; i < 10; i++) {
            cur = add(cur, ss[i]);
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set.add(cur);
        for(int i = 10; i < ss.length; i++) {
            cur = add(cur, ss[i]);
            if(!set.add(cur) && set2.add(cur)) {
                res.add(s.substring(i - 9, i + 1));
            }
        }
        return res;
    }
    public int add(int n, char c) {
        int next = 0;
        switch (c) {
            case 'A':
                next = 1;
                break;
            case 'C':
                next = 2;
                break;
            case 'G':
                next = 3;
                break;
            case 'T':
                next = 4;
        }
        // 八进制
//        if(n < (1 << 27))
//            n = n * 8 + next;
//        else n = ((n & ((1 << 27) - 1)) << 3) + next;
//        return n;
        return n < (1 << 27) ? (n << 3) + next : ((n & ((1 << 27) - 1)) << 3) + next;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        // 大素数
        int P = 131313;
        int len = s.length();
        int[] hash = new int[len + 1];  // hash
        int[] p = new int[len + 1]; // 底数
        p[0] = 1;
        for(int i = 1; i <= len; i++) {
            hash[i] = hash[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        List<String> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();    // hash -> freq
        for(int i = 10; i <= len; i++) {
            int hash2 = hash[i] - hash[i - 10] * p[10];
            int freq = map.getOrDefault(hash2, 0);
            if(freq == 1) res.add(s.substring(i - 10, i));
            map.put(hash2, freq + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        RepeatedDNASequences187 repeatedDNASequences187 = new RepeatedDNASequences187();
        System.out.println(repeatedDNASequences187.findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
