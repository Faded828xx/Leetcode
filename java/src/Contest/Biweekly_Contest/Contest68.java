package Contest.Biweekly_Contest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author faded828x
 * @date 2021/12/27
 */
public class Contest68 {
    // 补题
    // AC
    public int mostWordsFound(String[] sentences) {
        int res = 0;
        for(String s : sentences) {
            res = Math.max(res, s.split(" ").length);
        }
        return res;
    }

    // AC
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> res = new HashSet<>();
        Set<String> s = new HashSet<>();
        for(String sup : supplies)
            s.add(sup);
        int len = recipes.length;
        for(int j = 0; j < len; j++) {
            for(int i = 0; i < len; i++) {
                if(res.contains(recipes[i])) continue;
                List<String> ing = ingredients.get(i);
                boolean fail = false;
                for(String in : ing) {
                    if(!s.contains(in)) {
                        fail = true;
                        break;
                    }
                }
                if(!fail) {
                    res.add(recipes[i]);
                    s.add(recipes[i]);
                }
            }
        }
        return new ArrayList<>(res);
    }

    // AC
    public boolean canBeValid(String s, String locked) {
        int len = s.length();
        if(len % 2 == 1) return false;
        char[] lockk = locked.toCharArray();
        char[] ss = s.toCharArray();
        int cnt = 0;    // can be changed from 0 to i
        int l = 0;  // count of ( from 0 to i
        for(int i = 0; i < len; i++) {
            if(lockk[i] == '0') {
                cnt++;
                continue;
            }
            if(ss[i] == '(') l++;
            else {
                if(l > 0) l--;
                else if(cnt > 0) cnt--;
                else return false;
            }
        }
        cnt = 0;
        int r = 0;  // count of ) from len-1 to i
        for(int i = len - 1; i >= 0; i--) {
            if(lockk[i] == '0') {
                cnt++;
                continue;
            }
            if(ss[i] == ')') r++;
            else {
                if(r > 0) r--;
                else if(cnt > 0) cnt--;
                else return false;
            }
        }
        return true;
    }

}
