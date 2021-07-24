package Contest.Weekly_Contest;

import java.util.HashMap;
import java.util.Map;

public class Contest245 {

    // AC
    public static boolean makeEqual(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String str : words) {
            for (char ch : str.toCharArray())
                map.put(ch - 'a', map.getOrDefault(ch - 'a', 0) + 1);
        }
        for (int n : map.values()) {
            if (n != words.length)
                return false;
        }
        return true;
    }

    // 补AC
    public static int maximumRemovals(String s, String p, int[] removable) {
        int left = 0;
        int right = removable.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(s, p, removable, mid))
                left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public static boolean check(String s, String p, int[] removable, int mid) {
        if (mid >= s.length()) return false;
        char[] arrS = s.toCharArray();
        for (int i = 0; i <= mid; i++) {
            arrS[removable[i]] = '0';
        }
        int index = 0;
        for (char ch : p.toCharArray()) {
            while (index < arrS.length && arrS[index] != ch)
                index++;
            if (index == arrS.length) return false;
            index++;
        }
        return true;
    }

    // AC
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean flag0 = false;
        boolean flag1 = false;
        boolean flag2 = false;
        for (int[] cur : triplets) {
            if (cur[0] == target[0]) {
                if (cur[1] <= target[1] && cur[2] <= target[2])
                    flag0 = true;
            }
            if (cur[1] == target[1]) {
                if (cur[0] <= target[0] && cur[2] <= target[2])
                    flag1 = true;
            }
            if (cur[2] == target[2]) {
                if (cur[1] <= target[1] && cur[0] <= target[0])
                    flag2 = true;
            }
        }
        return flag0 && flag1 && flag2;
    }


    public static void main(String[] args) {
        String s = "abcacb";
        String p = "ab";
        int[] removable = new int[]{3, 1, 0};
        System.out.println(maximumRemovals(s, p, removable));
    }

}
