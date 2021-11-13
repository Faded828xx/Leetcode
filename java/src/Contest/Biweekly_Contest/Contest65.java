package Contest.Biweekly_Contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2021/11/13
 */
public class Contest65 {

    // AC
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for(char ch : word1.toCharArray())
            map1[ch-'a']++;
        for(char ch : word2.toCharArray()) {
            map2[ch-'a']++;
        }
        for(int i = 0; i < 26; i++)
            if(Math.abs(map2[i] - map1[i]) > 3)
                return false;

            return true;
    }

    // AC
    class Robot {

        int len;
        int idx;
        boolean start;
        int w;
        int h;
        String[] dire = new String[]{"East", "North", "West", "South"};

        public Robot(int width, int height) {
            len = (width + height) * 2 - 4;
            w = width;
            h = height;
            idx = 0;
            start = true;
        }

        public void move(int num) {
            idx = (idx + num) % len;
            start = false;
        }

        public int[] getPos() {
            if(idx >= 0 && idx <= w - 1)
                return new int[]{idx, 0};
            else if(idx >= w && idx <= w + h - 2)
                return new int[]{w - 1, idx - w + 1};
            else if(idx >= w + h - 1 && idx <= 2 * w + h - 3)
                return new int[]{2 * w + h - 3 - idx, h - 1};
            else return new int[]{0, len - idx};
        }

        public String getDir() {
            if(start) return dire[0];
            int i = -1;
            if(idx >= 1 && idx <= w - 1) i = 0;
            else if(idx >= w && idx <= w + h - 2) i = 1;
            else if(idx >= w + h - 1 && idx <=2 * w + h - 3) i = 2;
            else i = 3;
            return dire[i];
        }
    }


    // AC
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a1, a2) -> a1[0] - a2[0]);
        int[] res = new int[queries.length];
        int preM = items[0][1];
        Map<Integer, Integer> s = new HashMap<>();
        for(int i = 1; i < items.length; i++) {
            items[i][1] = Math.max(preM, items[i][1]);
            preM = items[i][1];
        }
        Map<Integer, Integer> map = new HashMap<>(queries.length);
        for(int i = 0; i < queries.length; i++) {
            if(map.containsKey(queries[i]))
                s.put(i, queries[i]);
            else map.put(queries[i], i);
        }
        Arrays.sort(queries);
        int idx = 0;
        int cur = 0;
        for(int q : queries) {
            while(idx < items.length && q >= items[idx][0]) {
                cur = items[idx][1];
                idx++;
            }
            res[map.get(q)] = cur;
        }
        for(int i : s.keySet()) {
            res[i] = res[map.get(s.get(i))];
        }
        return res;
    }


}
