package Contest.Weekly_Contest;

import java.util.LinkedList;
import java.util.List;

/**
 * @author faded828x
 * @date 2021/12/20
 */
public class Contest272 {
    // 上班补题 这次挺简单的
    // AC
    public String firstPalindrome(String[] words) {
        for(String word : words)
            if(check(word))
                return word;
        return "";
    }
    boolean check(String word) {
        int l = 0;
        int r = word.length() - 1;
        while(l < r) {
            if(word.charAt(l) != word.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    // AC
    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder(s);
        int offset = 0;
        for(int i : spaces) {
            sb.insert(i + offset, " ");
            offset++;
        }
        return sb.toString();
    }

    // AC
    public long getDescentPeriods(int[] prices) {
        long res = 0;
        int len = prices.length;
        int idx = 0;
        while(idx < len) {
            idx++;
            int cnt = 1;
            while(idx < len && prices[idx] == prices[idx - 1] - 1) {
                idx++;
                cnt++;
            }
            res += (long)(cnt) * (cnt + 1) / 2;
        }
        return res;
    }

    // AC
    public int kIncreasing(int[] arr, int k) {
        int res = 0;
        for(int i = 0; i < k; i++) {
            res += LIS(arr, k, i);
        }
        return res;
    }
    // k为偏移量找最长递增子序列
    int LIS(int[] arr, int k, int offset) {
        int len = arr.length;
        List<Integer> l = new LinkedList<>();
        int cnt = 0;
        for(int i = offset; i < len; i += k) {
            cnt++;
            int cur = arr[i];
            if(l.size() == 0)
                l.add(cur);
            else if(cur >= l.get(l.size() - 1)) {
                l.add(cur);
            } else {
//                int j;
//                for(j = l.size() - 2; j >= 0; j--) {
//                    if(l.get(j) <= cur) break;
//                }
                // 二分
                int left = 0;
                int right = l.size() - 2;
                while(left < right) {
                    int mid = left + (right - left) / 2;
                    if(l.get(mid) <= cur)
                        left = mid + 1;
                    else if(l.get(mid) > cur)
                        right = mid - 1;
                }
                if(l.get(left) > cur)
                    left--;
                l.set(left + 1, cur);
            }
        }
        // 修改为LIS的最小次数
        return cnt - l.size();
    }

}
