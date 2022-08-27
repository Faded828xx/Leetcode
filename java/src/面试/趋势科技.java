package 面试;

import java.util.HashMap;
import java.util.Map;

/**
 * @author faded828x
 * @date 2022/8/5
 */
public class 趋势科技 {
    // 跟蒋璐一起做
    // 就是将字符串中的%xxx%匹配keys中的模版 并替换成对应values中的实际值 没啥难度
    // 第二道曼哈顿的题没做出来
    public String token_replace (String my_template, String[] keys, String[] values) {
        Map<String, String> map = new HashMap<>();
        for(int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        String s = my_template;
        while(idx < s.length()) {
            if(s.charAt(idx) == '%') {
                idx++;
                int start = idx;
                while(idx < s.length() && s.charAt(idx) != '%') idx++;
                String k = s.substring(start, idx);
                if(map.containsKey(k)) {sb.append(map.get(k));idx++;}
                else sb.append(s.substring(start-1, Math.min(idx, s.length())));
            } else {
                sb.append(s.charAt(idx));
                idx++;
            }

        }
        return sb.toString();
    }
}
