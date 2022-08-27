package StackAndHeap;
//Given n non-negative integers representing an elevation map where the width of
// each bar is 1, compute how much water it can trap after raining. 
//
// 
// Example 1: 
//
// 
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [
//0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are
// being trapped.
// 
//
// Example 2: 
//
// 
//Input: height = [4,2,0,3,2,5]
//Output: 9
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 1 <= n <= 2 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 2811 👎 0


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class TrappingRainWater42 {
    public int trap(int[] height) {
        int len = height.length;
        if(len < 3) return 0;
        Deque<Integer> stack1 = new ArrayDeque<>();
//        Deque<Integer> stack2 = new ArrayDeque<>();
        int[] nextGN1 = new int[len];   // 右方的nextGreaterNumber
        int[] nextGN2 = new int[len];   // 左方的nextGreaterNumber
        Arrays.fill(nextGN2, -1);
        for(int i = len - 1; i >= 0; i--) {
            while(!stack1.isEmpty()) {
                int top = stack1.peek();
                if(height[i] <= height[top]) {
                    stack1.push(i);
                    nextGN1[i] = top;
                    break;
                }
                stack1.pop();
            }
            if(stack1.isEmpty())
                stack1.push(i);
        }
        int cur = 0;
        int cnt = 0;
        while(cur < len) {
            if(nextGN1[cur] == 0)
                cur++;
            else {
                int next = nextGN1[cur];
                for(int j = cur + 1; j < next; j++)
                    cnt += height[cur] - height[j];
                cur = next;
            }
        }
        cur = len - 1;
        while(cur >= 0) {
            if(nextGN2[cur] == -1)
                cur--;
            else {
                int next = nextGN2[cur];
                for(int j = cur - 1; j > next; j--)
                    cnt += height[cur] - height[j];
                cur = next;
            }
        }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
