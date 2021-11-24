package String;//Given a string s containing an out-of-order English representation of digits 0
//-9, return the digits in ascending order. 
//
// 
// Example 1: 
// Input: s = "owoztneoer"
//Output: "012"
// Example 2: 
// Input: s = "fviefuro"
//Output: "45"
// 
// 
// Constraints: 
//
// 
// 1 <= s.length <= 105 
// s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w
//","v","x","z"]. 
// s is guaranteed to be valid. 
// 
// Related Topics 哈希表 数学 字符串 
// 👍 134 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class ReconstructOriginalDigitsFromEnglish423 {
    int[] cnt = new int[26];
    int[] order = new int[]{0, 4, 6, 8, 7, 5, 9, 1, 2, 3};
    public String originalDigits(String s) {
        for(char c : s.toCharArray())
            cnt[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            int n = check(order[i]);
            sb.append(String.valueOf(order[i]).repeat(n));
        }
        char[] cc = sb.toString().toCharArray();
        Arrays.sort(cc);
        return new String(cc);
    }
    String[] ss = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    int check(int n) {
        String s = ss[n];
        int min = 100000;
        for(char c : s.toCharArray())
            min = Math.min(min, cnt[c - 'a']);
        for(char c : s.toCharArray())
            cnt[c - 'a'] -= min;
        return Math.max(0, min);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
