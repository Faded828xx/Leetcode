package Contest.Weekly_Contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author faded828x
 * @date 2021/8/22
 */
public class Contest255 {

    // AC
    public int findGCD(int[] nums) {
        int min = 1001;
        int max = 0;
        for(int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        return fun(max, min);
    }
    public int fun(int a, int b) {
        if(b == 0) return a;
        return fun(b, a % b);
    }

    // AC
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>();
        for(String str : nums) {
            set.add(str);
        }
        for(int i = 0; i < (1 << n); i++) {
            String s = Integer.toBinaryString(i);
            int len = s.length();
            char[] ch = new char[n-len];
            Arrays.fill(ch, '0');
            String res = new String(ch) + s;
            if(!set.contains(res)) return res;
        }
        return "";
    }

}
