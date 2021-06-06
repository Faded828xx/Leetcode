package Contest.Weekly_Contest;

import java.util.*;

public class contest244 {

    // AC
    public boolean findRotation1(int[][] mat, int[][] target) {
        for(int i=0; i<4; i++) {
            mat = rotate(mat);
            if(isSame(mat, target))
                return true;
        }
        return false;
    }
    public int[][] rotate(int[][] mat) {
        int len = mat.length;
        int[][] res = new int[len][len];
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                res[j][len-1-i] = mat[i][j];
            }
        }
        return res;
    }
    public boolean isSame(int[][] mat1, int[][] mat2) {
        int len = mat1.length;
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(mat1[i][j]!=mat2[i][j])
                    return false;
            }
        }
        return true;
    }

    // AC
    public static int reductionOperations2(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int index = -1;
        for(int n : nums) {
            if(map.containsKey(n)) {
                map.put(n, map.get(n)+1);
            } else {
                index++;
                list.add(n);
                map.put(n, 1);
            }
        }
        int res = 0;
        for(int i=1; i<list.size(); i++) {
            int n = list.get(i);
            res += map.get(n) * i;
        }
        return res;
    }

    // N/A
    public int minFlips3(String s) {
        int len = s.length();
        if(len%2==0)
            return fun(s);
        else return Math.min(fun(s.substring(0, len-1)), fun(s.substring(1, len)));
    }
    public int fun(String s) {  // s.length is even
        int res1 = 0;
        int res2 = 0;
        for(int i=0; i<s.length(); i++) {
            if(i%2==0) {
                if(s.charAt(i)!='0')
                    res1++;
            } else {
                if(s.charAt(i)!='1')
                    res1++;
            }
        }
        for(int i=0; i<s.length(); i++) {
            if(i%2==0) {
                if(s.charAt(i)!='1')
                    res2++;
            } else {
                if(s.charAt(i)!='0')
                    res2++;
            }
        }
        return Math.min(res1, res2);
    }

    // Timeout
    public int minWastedSpace4(int[] packages, int[][] boxes) {
        int res = Integer.MAX_VALUE;
        boolean change = false;
        for(int[] box : boxes) {
            Map<Integer, Integer> map = new HashMap<>();
            int cur = 0;
            Arrays.sort(box);
            boolean isValid = true;
            for(int pack : packages) {
                if(map.containsKey(pack)) {
                    cur = (cur + map.get(pack) - pack) % (int)(1e9+7);
                    continue;
                }
                boolean flag = false;
                for(int n : box) {
                    if(n>=pack) {
                        cur = (cur + n - pack) % (int)(1e9+7);
                        map.put(pack, n);
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    isValid = false;
                    break;
                }
            }
            if(!isValid) continue;
            change = true;
            res = Math.min(res, cur);
        }
        return change ? res : -1 ;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,3};
        System.out.println(reductionOperations2(arr));
    }
}
