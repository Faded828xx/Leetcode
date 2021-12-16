package Math;
//You are given an array points, an integer angle, and your location, where loca
//tion = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on
// the X-Y plane. 
//
// Initially, you are facing directly east from your position. You cannot move f
//rom your position, but you can rotate. In other words, posx and posy cannot be c
//hanged. Your field of view in degrees is represented by angle, determining how w
//ide you can see from any given view direction. Let d be the amount in degrees th
//at you rotate counterclockwise. Then, your field of view is the inclusive range 
//of angles [d - angle/2, d + angle/2]. 
//
// 
// Your browser does not support the video tag or this video format. 
// 
//
// You can see some set of points if, for each point, the angle formed by the po
//int, your position, and the immediate east direction from your position is in yo
//ur field of view. 
//
// There can be multiple points at one coordinate. There may be points at your l
//ocation, and you can always see these points regardless of your rotation. Points
// do not obstruct your vision to other points. 
//
// Return the maximum number of points you can see. 
//
// 
// Example 1: 
//
// 
//Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
//Output: 3
//Explanation: The shaded region represents your field of view. All points can b
//e made visible in your field of view, including [3,3] even though [2,2] is in fr
//ont and in the same line of sight.
// 
//
// Example 2: 
//
// 
//Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
//Output: 4
//Explanation: All points can be made visible in your field of view, including t
//he one at your location.
// 
//
// Example 3: 
//
// 
//Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
//Output: 1
//Explanation: You can only see one of the two points, as shown above.
// 
//
// 
// Constraints: 
//
// 
// 1 <= points.length <= 105 
// points[i].length == 2 
// location.length == 2 
// 0 <= angle < 360 
// 0 <= posx, posy, xi, yi <= 100 
// 
// Related Topics 几何 数组 数学 排序 滑动窗口 
// 👍 41 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumNumberOfVisiblePoints1610 {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int cnt = 0;    // points即location点数
        int x = location.get(0);
        int y = location.get(1);
        // 每个点和location的斜率转度数
        List<Double> degrees = new ArrayList<>(points.size());
        for(List<Integer> point : points) {
            int xx = point.get(0);
            int yy = point.get(1);
            if(xx == x && yy == y) {
                cnt++;
                continue;
            }
            double slope = (double)(yy - y) / (xx - x);
            double degree = Math.toDegrees(Math.atan(slope));

            if(xx > x && yy > y) {  // 第一象限
            } else if(xx < x && yy > y) {       // 二
                degree += 180;
            } else if(xx < x && yy < y) {   // 三
                degree += 180;
            } else if(xx > x && yy < y) {   // 四
                degree += 360;
            } else if(xx == x) {    // 同垂直
                if(yy > y) degree = 90;
                else degree = 270;
            } else {    // 同水平
                if(xx > x) degree = 0;
                else degree = 180;
            }
            // System.out.println(point + "-" + degree);
            degrees.add(degree);
        }
        Collections.sort(degrees);
        // System.out.println("degrees:" + degrees);
        // System.out.println("cnt:" + cnt);
        int res = 0;
        // 滑动窗口
        for(int i = 0; i < degrees.size(); i++) {
            double d = degrees.get(i);
            if(d <= angle) {
                res++;
                degrees.add(d + 360);   // 循环数组 360为模
                continue;
            }
            for(int j = i - res; j >= 0; j--) {
                if(degrees.get(j) >= d - angle)
                    res++;
                else break;
            }
        }
        return cnt + res;
    }

    public static void main(String[] args) {
        // 这个list写的好丑
        MaximumNumberOfVisiblePoints1610 maximumNumberOfVisiblePoints1610 = new MaximumNumberOfVisiblePoints1610();
        List<List<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>(){{add(1);add(1);}});
        points.add(new ArrayList<>(){{add(2);add(2);}});
        points.add(new ArrayList<>(){{add(3);add(3);}});
        points.add(new ArrayList<>(){{add(4);add(4);}});
        points.add(new ArrayList<>(){{add(1);add(2);}});
        points.add(new ArrayList<>(){{add(2);add(1);}});
        int angle = 0;
        List<Integer> location = new ArrayList<>(){{add(1);add(1);}};
        System.out.println(maximumNumberOfVisiblePoints1610.visiblePoints(points, angle, location));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
