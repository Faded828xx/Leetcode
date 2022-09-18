package Contest.Weekly_Contest;


import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2022/9/18
 */
public class Contest311 {

    public int smallestEvenMultiple(int n) {
        if(n % 2 == 1) return 2 * n;
        return n;
    }

    public int longestContinuousSubstring(String s) {
        int res = 1;
        int cur = 1;
        char[] cc = s.toCharArray();
        for(int i = 1; i < cc.length; i++) {
            if(cc[i] - cc[i - 1] == 1) cur++;
            else {
                res = Math.max(res, cur);
                cur = 1;
            }
        }
        return Math.max(res, cur);
    }

//    public TreeNode reverseOddLevels(TreeNode root) {
//        Deque<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        int lev = 0;
//        while(!queue.isEmpty()) {
//            lev++;
//            int size = queue.size();
//            TreeNode[] tr = new TreeNode[size];
//            int[] num = new int[size];
//            for(int i = 0; i < size; i++) {
//                tr[i] = queue.remove();
//                if(tr[i].left != null) queue.add(tr[i].left);
//                if(tr[i].right != null) queue.add(tr[i].right);
//                num[size - 1 - i] = tr[i].val;
//            }
//            if(lev % 2 == 1) continue;
//            for(int i = 0; i < size; i++)
//                tr[i].val = num[i];
//        }
//        return root;
//    }


    // TLE
    public int[] sumPrefixScores(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int n = words.length;
        int[] res = new int[n];
        int idx = 0;
        for(String word : words) {
            int cur = 0;
            for(int i = 0; i < word.length(); i++) {
                String w = word.substring(0, i + 1);
                if(map.containsKey(w)) {cur += map.get(w);continue;}
                map.put(w, cnt(word.substring(0, i + 1), words));
                cur += map.get(w);
                // if(map.get(w) == 1 && words.length != 1) break;
                if(map.get(w) == 1) {
                    cur += word.length() - 1 - i;
                    break;
                }
            }
            res[idx++] = cur;
        }
        return res;
    }
    public int cnt(String word, String[] words) {
        int res = 0;
        for(String w : words)
            if(w.startsWith(word))
                res++;

        return res;
    }
}
