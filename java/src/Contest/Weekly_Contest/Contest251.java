package Contest.Weekly_Contest;

import java.util.*;

public class Contest251 {

    // AC
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            sb.append(ch - 'a' + 1);
        }
        String cur = sb.toString();
        for (int i = 0; i < k; i++) {
            int n = 0;
            for (char ch : cur.toCharArray())
                n += ch - '0';
            cur = String.valueOf(n);
        }
        return Integer.parseInt(cur);
    }


    // AC
    public String maximumNumber(String num, int[] change) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            if (change[i] > i)
                map.put(i, change[i]);
            else if (change[i] == i)
                set.add(i);
        }
        int len = num.length();
        char[] numArr = num.toCharArray();
        char[] res = Arrays.copyOf(numArr, len);
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            int cur = numArr[i] - '0';
            if (set.contains(cur))
                continue;
            if (map.containsKey(cur)) {
                flag = true;
                int n = map.get(cur);
                res[i] = (char) (n + '0');
            } else {
                if (flag) break;
            }
        }
        return new String(res);
    }


    // 回溯AC
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length;
        boolean[][] match = new boolean[m][m];
        return backtrack(match, 0, students, mentors);
    }
    public int backtrack(boolean[][] match, int row, int[][] students, int[][] mentors) {
        int len = match.length;
        if (row == len) return 0;
        int res = 0;
        for (int col = 0; col < len; col++) {
            if (!check(match, row, col)) continue;
            match[row][col] = true;
            int[] student = students[row];
            int[] mentor = mentors[col];
            int num = 0;
            for (int i = 0; i < student.length; i++)
                num += (student[i] ^ mentor[i]) ^ 1;
            match[row][col] = true;
            res = Math.max(res, num + backtrack(match, row + 1, students, mentors));
            match[row][col] = false;
        }
        return res;
    }
    public boolean check(boolean[][] match, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (match[i][col])
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(26);
        System.out.println(sb.toString());
    }
}
