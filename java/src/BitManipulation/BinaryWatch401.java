package BitManipulation;
//二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
// 
//
// 
// 例如，下面的二进制手表读取 "3:25" 。 
// 
//
// 
//
// （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 
//Unported (CC BY-SA 3.0) ） 
//
// 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。 
//
// 小时不会以零开头： 
//
// 
// 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。 
// 
//
// 分钟必须由两位数组成，可能会以零开头： 
//
// 
// 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：turnedOn = 1
//输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
// 
//
// 示例 2： 
//
// 
//输入：turnedOn = 9
//输出：[]
// 
//
// 
//
// 解释： 
//
// 
// 0 <= turnedOn <= 10 
// 
// Related Topics 位运算 回溯算法 
// 👍 274 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class BinaryWatch401 {
    // 位运算
    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int countI = Integer.bitCount(i);
            if (countI > turnedOn) continue;
            for (int j = 0; j < 60; j++) {
                int countJ = Integer.bitCount(j);
                if (countJ > turnedOn) continue;
                if (countI + countJ == turnedOn)
                    res.add(j < 10 ? (i + ":0" + j) : (i + ":" + j));
            }
        }
        return res;
    }

//     一年前刚学算法那会用回溯做的 真牛逼啊 C++
//    vector<string> res;
//    int nums[10]={1,2,4,8,1,2,4,8,16,32};
//    vector<string> readBinaryWatch(int num) {
//        backtrack(num,0,0,0);
//        return res;
//    }
//    void backtrack(int num,int start,int hour,int minute){
//        if(num==0){
//            if(hour>11||minute>59)
//                return;
//            res.push_back(to_string(hour)+":"+(minute<10?"0"+to_string(minute):to_string(minute)));
//            return;
//        }
//        for(int i=start; i<10; i++){
//            if(i<4)
//                hour+=nums[i];
//            else minute+=nums[i];
//            backtrack(num-1,i+1,hour,minute);
//            if(i<4)
//                hour-=nums[i];
//            else minute-=nums[i];
//        }
//    }

    public static void main(String[] args) {
        System.out.println(readBinaryWatch(1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
