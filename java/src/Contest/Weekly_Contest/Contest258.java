package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/9/12
 */
public class Contest258 {



    // AC
    public String reversePrefix(String word, char ch) {
        int len = word.length();
        int idx = -1;
        for(int i = 0; i < len; i++) {
            if(word.charAt(i) == ch) {
                idx = i;
                break;
            }
        }
        System.out.println(idx);
        if(idx == -1) return word;
        StringBuilder sb = new StringBuilder(word.substring(0, idx + 1));
        sb.reverse();
        sb.append(word, idx + 1, len);
        return sb.toString();
    }


    // 没意思 看不懂题
    public long interchangeableRectangles(int[][] rectangles) {

        long res = 0;
        Map<Double, Set<Integer>> map1 = new HashMap<>();
        Map<Double, Set<Integer>> map2 = new HashMap<>();
        for(int[] re : rectangles) {
            int a = Math.max(re[0], re[1]);
            int b = Math.min(re[0], re[1]);
            double c = a * 1.0 / b;
            Set<Integer> s1 = map1.getOrDefault(c, new HashSet<>());

            if(!s1.add(a)) {
                Set<Integer> s2 = map2.getOrDefault(c, new HashSet<>());
                s2.add(a);
                map2.put(c, s2);
            }
            map1.put(c, s1);
        }
        for(Set<Integer> s : map1.values()) {
            int n = s.size();
            res += (long) n * (n - 1) / 2;
            System.out.println(s);
        }
        for(Set<Integer> s : map2.values()) {
            res += s.size();
            System.out.println(s);
        }
        return res;
    }




    public static void main(String[] args) {

    }

}
