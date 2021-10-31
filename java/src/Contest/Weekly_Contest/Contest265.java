package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/10/31
 */
public class Contest265 {

    // 超内存喽
    public static int minimumOperations(int[] nums, int start, int goal) {
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> s = new HashSet<>();
        queue.add(start);
        s.add(start);
        int cnt = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            for(int i = 0; i < size; i++) {
                int cur = queue.remove();
                for(int n : nums) {
                    for(int j = 0; j < 3; j++) {
                        int nn = 0;
                        if(j == 0) nn = cur + n;
                        else if(j == 1) nn = cur - n;
                        else nn = cur ^ n;
                        if(nn == goal) return cnt;
                        if(nn < 0 || n > 1000) continue;
                        if(!s.contains(nn)) {
                            s.add(nn);
                            queue.add(nn);
                        }
                    }
                }
            }
        }
        return -1;
    }


    // 超时喽
    public boolean possiblyEquals(String s1, String s2) {
        int char1 = 0;  // 字符位数
        int char2 = 0;
        List<String> l1 = new ArrayList<>();    // 数字string
        List<String> l2 = new ArrayList<>();
        List<String> ss1 = new ArrayList<>();
        List<String> ss2 = new ArrayList<>();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        int idx1 = 0;
        int idx2 = 0;
        boolean numStart1 = false;
        boolean numStart2 = false;
        while(idx1 < len1) {
            int cnt1 = 0;
            String st = "";
            while(idx1 < len1 && str1[idx1] >= 'a' && str1[idx1] <= 'z') {
                st += str1[idx1];
                cnt1++;
                idx1++;
            }
            if(st.length() > 0) ss1.add(st);
            char1 += cnt1;
            String s = "";
            while(idx1 < len1 && str1[idx1] >= '0' && str1[idx1] <= '9') {
                if(idx1 == 0) numStart1 = true;
                s += str1[idx1];
                idx1++;
            }
            if(s.length() > 0) l1.add(s);
        }
        while(idx2 < len2) {
            int cnt2 = 0;
            String st = "";
            while(idx2 < len2 && str2[idx2] >= 'a' && str2[idx2] <= 'z') {
                st += str2[idx2];
                cnt2++;
                idx2++;
            }
            if(st.length() > 0) ss2.add(st);
            char2 += cnt2;
            String s = "";
            while(idx2 < len2 && str2[idx2] >= '0' && str2[idx2] <= '9') {
                if(idx2 == 0) numStart2 = true;
                s += str2[idx2];
                idx2++;
            }
            if(s.length() > 0) l2.add(s);
        }

        Set<Integer> res1 = new HashSet<>();
        Set<Integer> res2 = new HashSet<>();
        dfs(res1, char1, l1, 0, true, new ArrayList<>());
        dfs(res2, char2, l2, 0, false, new ArrayList<>());
        for(int n : res2) {
            if(res1.contains(n)) {
                List<Integer> ll1 = map1.get(n);
                List<Integer> ll2 = map2.get(n);
                String string1 = fun2(ss1, ll1, numStart1);
                String string2 = fun2(ss2, ll2, numStart2);
                boolean success = true;
                for(int i = 0; i < n; i++) {
                    if(string2.charAt(i) == '*') continue;
                    if(string1.charAt(i) != '*' && string1.charAt(i) != string2.charAt(i)) {
                        success = false;
                        break;
                    }
                }
                if(success)
                    return true;

            }
        }
        return false;
    }
    Map<Integer, List<Integer>> map1 = new HashMap<>();
    Map<Integer, List<Integer>> map2 = new HashMap<>();

    void dfs(Set<Integer> set, int num, List<String> list, int idx, boolean flag, List<Integer> ll) {
        if(list.size() == idx) {
            set.add(num);
            if(flag) {
                map1.put(num, new ArrayList<>(ll));
            } else
                map2.put(num, new ArrayList<>(ll));
            return ;
        }
        String s = list.get(idx);
        for(int n : fun(s)) {
            ll.add(n);
            dfs(set, num + n, list, idx + 1, flag, ll);
            ll.remove((Integer) n);
        }

    }
    List<Integer> fun(String s) {
        List<Integer> res = new ArrayList<>();
        res.add(Integer.valueOf(s));
        if(s.length() == 2) {
            res.add((s.charAt(0) - '0') + (s.charAt(1) - '0'));
        } else if(s.length() == 3) {
            res.add((s.charAt(0) - '0') + (s.charAt(1) - '0') + (s.charAt(2) - '0'));
            res.add(Integer.parseInt(s.substring(0,2)) + (s.charAt(2) - '0'));
            res.add(Integer.parseInt(s.substring(1,3)) + (s.charAt(0) - '0'));

        }
        return res;
    }

    // 数字用特殊符号填充
    String fun2(List<String> l1, List<Integer> l2, boolean numS) {
        int i1 = 0;
        int i2 = 0;
        StringBuilder res = new StringBuilder();
        if(numS) {
            char[] c = new char[l2.get(i2)];
            Arrays.fill(c, '*');
            res = new StringBuilder(new String(c));
            i2 = 1;
        }
        while(i1 != l1.size() || i2 != l2.size()) {
            res.append(l1.get(i1++));
            if(i2 == l2.size()) continue;
            char[] c = new char[l2.get(i2++)];
            Arrays.fill(c, '*');
            res.append(new String(c));
        }
        return res.toString();
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{2, 8, 16};
//        System.out.println(minimumOperations(nums, 0, 1));
        Contest265 contest265 = new Contest265();
        System.out.println(contest265.possiblyEquals("a5b", "c5b"));
    }

}
