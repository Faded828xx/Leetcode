package 面试;

import java.util.*;

/**
 * @author faded828x
 * @date 2022/9/6
 */
public class g {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int cnt = 0;
        if(s.charAt(0) != '1' || s.length() == 1) {
            System.out.println(-1);
            return ;
        }
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) != '3') {
                System.out.println(-1);
                return ;
            } else cnt++;
        }
         if(cnt != 0)
        System.out.println(cnt);


//        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = scanner.nextInt();
        int res = 0;
        int len = a.length;
        Arrays.sort(a);
        for(int i = 0; i < len;) {
            if(i + 2 < len && a[i+2] - a[i] <=10) {
                i  += 3;
            } else if(i + 1 < len && a[i+1] - a[i]<= 20) {
                i += 2;
            } else {
                i += 1;
            }
            res++;
        }
        System.out.println(res);
    }


}
