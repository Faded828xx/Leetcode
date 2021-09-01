package String;//Given two version numbers, version1 and version2, compare them.
//
// 
// 
//
// Version numbers consist of one or more revisions joined by a dot '.'. Each re
//vision consists of digits and may contain leading zeros. Every revision contains
// at least one character. Revisions are 0-indexed from left to right, with the le
//ftmost revision being revision 0, the next revision being revision 1, and so on.
// For example 2.5.33 and 0.1 are valid version numbers. 
//
// To compare version numbers, compare their revisions in left-to-right order. R
//evisions are compared using their integer value ignoring any leading zeros. This
// means that revisions 1 and 001 are considered equal. If a version number does n
//ot specify a revision at an index, then treat the revision as 0. For example, ve
//rsion 1.0 is less than version 1.1 because their revision 0s are the same, but t
//heir revision 1s are 0 and 1 respectively, and 0 < 1. 
//
// Return the following: 
//
// 
// If version1 < version2, return -1. 
// If version1 > version2, return 1. 
// Otherwise, return 0. 
// 
//
// 
// Example 1: 
//
// 
//Input: version1 = "1.01", version2 = "1.001"
//Output: 0
//Explanation: Ignoring leading zeroes, both "01" and "001" represent the same i
//nteger "1".
// 
//
// Example 2: 
//
// 
//Input: version1 = "1.0", version2 = "1.0.0"
//Output: 0
//Explanation: version1 does not specify revision 2, which means it is treated a
//s "0".
// 
//
// Example 3: 
//
// 
//Input: version1 = "0.1", version2 = "1.1"
//Output: -1
//Explanation: version1's revision 0 is "0", while version2's revision 0 is "1".
// 0 < 1, so version1 < version2.
// 
//
// Example 4: 
//
// 
//Input: version1 = "1.0.1", version2 = "1"
//Output: 1
// 
//
// Example 5: 
//
// 
//Input: version1 = "7.5.2.4", version2 = "7.5.3"
//Output: -1
// 
//
// 
// Constraints: 
//
// 
// 1 <= version1.length, version2.length <= 500 
// version1 and version2 only contain digits and '.'. 
// version1 and version2 are valid version numbers. 
// All the given revisions in version1 and version2 can be stored in a 32-bit in
//teger. 
// 
// Related Topics 双指针 字符串 
// 👍 190 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class CompareVersionNumbers165 {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        System.out.println(Arrays.toString(s1));
        System.out.println(Arrays.toString(s1));
        return s1.length >= s2.length ? match(s1, s2) : match(s2, s1);
    }
    public int match(String[] s1, String[] s2) {
        for(int i = 0; i < s2.length; i++) {
            int n1 = Integer.parseInt(s1[i]);
            int n2 = Integer.parseInt(s2[i]);
            if(n1 < n2) return -1;
            if(n2 > n2) return 1;
        }
        for(int i = s2.length; i < s1.length; i++)
            if(Integer.parseInt(s1[i]) > 0) return 1;
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
