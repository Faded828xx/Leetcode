package Contest.Weekly_Contest;

import java.util.Arrays;

/**
 * @author faded828x
 * @date 2021/8/15
 */
public class Contest254 {


    // AC
    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] res = new int[len];
        int left = 0;
        int right = len - 1;
        for(int i = 0; i < len; i += 2) {
            res[i] = nums[left];
            if(i == len - 1) break;
            res[i + 1] = nums[right];
            left++;
            right--;
        }
//        if(len % 2 == 1)
//            res[len - 1] = nums[len / 2];
        return res;
    }



    // 超时
    public static int minNonZeroProduct(int p) {
        long l = 1L;
        long loop = (long) Math.pow(2, p - 1);
        long factor = (long) Math.pow(2, p) - 2;
        for(int i = 0; i < loop - 1; i++) {
            l = (l * factor) % 1000000007;
        }
//        System.out.println(l);
        l = (l * (factor + 1)) % 1000000007;
        return (int) l;
    }
    // 快速幂 34一直挂 不知道啥原因
    public static int minNonZeroProduct2(int p) {
        // (2^p-2)^(2^(p-1))*(2^p-1)
        long l;
        long loop = (long) Math.pow(2, p - 1);
        long factor = (long) Math.pow(2, p) - 2;
//        l = (long) Math.pow(factor, loop - 1) % 1000000007;
        l = qpow(factor, loop - 1);
//        System.out.println(l);
        l = (l * ((factor + 1L) % 1000000007)) % 1000000007;
        return (int) l;
    }
    public static long qpow(long a, long n) {
        if(n == 0) return 1;
        else if(n % 2 == 1) return qpow(a, n - 1) * a % 1000000007;
        long tmp = qpow(a, n / 2) % 1000000007;
        return (tmp * tmp) % 1000000007;
    }


    public static void main(String[] args) {
//        System.out.println(minNonZeroProduct(34));
        System.out.println(minNonZeroProduct2(34));
    }
}
