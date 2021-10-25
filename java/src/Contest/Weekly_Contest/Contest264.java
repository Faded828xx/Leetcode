package Contest.Weekly_Contest;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/10/24
 */
public class Contest264 {

    // AC
    public int countValidWords(String sentence) {
        int res = 0;
        int idx = 0;
        int l = sentence.length();
        char[] ss = sentence.toCharArray();
        while(idx < l) {
            while(idx < l && ss[idx] == ' ') idx++;
            if(idx == l) break;
            int idx2 = idx;
            while(idx2 < l && ss[idx2 ] != ' ') idx2++;
            String curS = sentence.substring(idx, idx2);
            if(check1(curS)) {
                res++;
                // System.out.println(curS);
            }
            idx = idx2;
        }
        return res;
    }
    public boolean check1(String s) {
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if(ch <= '9' && ch >= '0') return false;
            if(ch == '-') {
                int j;
                for(j = s.length() - 1; j > i; j--)
                    if(s.charAt(j) <= 'z' && s.charAt(j) >= 'a') break;
                if(j == i)return false;
                for(j = 0; j < i; j++) {
                    if(s.charAt(j) <= 'z' && s.charAt(j) >= 'a') break;
                }
                if(j == i) return false;
            }
            if((ch == '!' || ch == ',' || ch == '.') && i != s.length() - 1) return false;
            if(ch == '-' && cnt1 == 1) return false;
            if((ch == '!' || ch == ',' || ch == '.') && cnt2 == 1) return false;
            if(ch == '-') cnt1++;
            if(ch == '!' || ch == ',' || ch == '.') cnt2++;
            // System.out.println(cnt1);
        }
        return true;
    }

    // AC
    public int nextBeautifulNumber(int n) {
        int[] arr = new int[]{1,22,122,212,221,333,1333,3133,3313,3331,4444,14444,22333,23233,23323,23332,32233,32323,32332,33223,33232,33322,41444,44144,44414,44441,55555,122333,123233,123323,123332,132233,132323,132332,133223,133232,133322,155555,212333,213233,213323,213332,221333,223133,223313,223331,224444,231233,231323,231332,232133,232313,232331,233123,233132,233213,233231,233312,233321,242444,244244,244424,244442,312233,312323,312332,313223,313232,313322,321233,321323,321332,322133,322313,322331,323123,323132,323213,323231,323312,323321,331223,331232,331322,332123,332132,332213,332231,332312,332321,333122,333121,333212,333221,422444,424244,424424,424442,442244,442424,442442,444224,444242,444422,515555,551555,555155,555515,555551,666666,1224444};
        // {}
        for(int m : arr) {
            if(m > n) return m;
        }
        return 0;
    }

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Set<Integer>> list = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
            list.add(new HashSet<>());
        for(int i = 1; i < n; i++) {
            int parent = parents[i];
            Set<Integer> set = list.get(parent);
            set.add(i);
        }
        int[] score = new int[n];
        for(int i = 0; i < n; i++) {
            Set<Integer> children = list.get(i);
            int res = 1;    // 子树积
            int cnt = 1;    // 除去子树的节点数量
            for(int c : children) {
                int size = list.get(c).size() + 1;  // bug 应该还加上子子节点的size
                cnt += size;
                res *= size;
            }
            if(i != 0) {
                cnt = n - cnt;
            } else cnt = 1;
            score[i] = res * cnt;
        }
        int max = 0;
        for(int sco : score) {
            max = Math.max(max, sco);
        }
        int ans = 0;
        for(int sco : score) {
            if(sco == max) ans++;
        }
        return ans;

    }

    public static void main(String[] args) {
        String a = "abc    pp";
        String[] b = a.split("/ ");
        System.out.println(Arrays.toString(b));
    }


}
