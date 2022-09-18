package Math;
//Let f(x) be the number of zeroes at the end of x!. Recall that x! = 1 * 2 * 3
//* ... * x and by convention, 0! = 1. 
//
// 
// For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 
//2 because 11! = 39916800 has two zeroes at the end. 
// 
//
// Given an integer k, return the number of non-negative integers x have the 
//property that f(x) = k. 
//
// 
// Example 1: 
//
// 
//Input: k = 0
//Output: 5
//Explanation: 0!, 1!, 2!, 3!, and 4! end with k = 0 zeroes.
// 
//
// Example 2: 
//
// 
//Input: k = 5
//Output: 0
//Explanation: There is no x such that x! ends in k = 5 zeroes.
// 
//
// Example 3: 
//
// 
//Input: k = 3
//Output: 5
// 
//
// 
// Constraints: 
//
// 
// 0 <= k <= 10⁹ 
// 
//
// Related Topics 数学 二分查找 👍 123 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class PreimageSizeOfFactorialZeroesFunction793 {
    // 未完成

    public static int preimageSizeFZF(int k) {
        Set<Integer> set = new HashSet<>();
        int[] a = new int[k >> 1];
        for(int i = 1; i < a.length; i++) {
            a[i] = 6 * i;
            set.add(a[i] - 1);
        }
        int loop = 1;
        for(int i = (int)Math.pow(5, loop); i < a.length; i += (int)Math.pow(5, loop)) {
            set.add(a[i]);
            a[i]++;
        }
        System.out.print(set);
        System.out.println(k);
        if(set.contains(k)) {return 0;}
        return 5;
    }

    public static void main(String[] args) {
        for(int n : new int[]{5, 11, 17, 23, 29, 30})
        System.out.println(preimageSizeFZF(n));
//        System.out.println(6 << (1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
