package Array;//You are playing a game involving a circular array of non-zero integers nums. E
//ach nums[i] denotes the number of indices forward/backward you must move if you 
//are located at index i: 
//
// 
// If nums[i] is positive, move nums[i] steps forward, and 
// If nums[i] is negative, move nums[i] steps backward. 
// 
//
// Since the array is circular, you may assume that moving forward from the last
// element puts you on the first element, and moving backwards from the first elem
//ent puts you on the last element. 
//
// A cycle in the array consists of a sequence of indices seq of length k where:
// 
//
// 
// Following the movement rules above results in the repeating index sequence se
//q[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ... 
// Every nums[seq[j]] is either all positive or all negative. 
// k > 1 
// 
//
// Return true if there is a cycle in nums, or false otherwise. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,-1,1,2,2]
//Output: true
//Explanation:
//There is a cycle from index 0 -> 2 -> 3 -> 0 -> ...
//The cycle's length is 3.
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,2]
//Output: false
//Explanation:
//The sequence from index 1 -> 1 -> 1 -> ... is not a cycle because the sequence
//'s length is 1.
//By definition the sequence's length must be strictly greater than 1 to be a cy
//cle.
// 
//
// Example 3: 
//
// 
//Input: nums = [-2,1,-1,-2,-2]
//Output: false
//Explanation:
//The sequence from index 1 -> 2 -> 1 -> ... is not a cycle because nums[1] is p
//ositive, but nums[2] is negative.
//Every nums[seq[j]] must be either all positive or all negative.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 5000 
// -1000 <= nums[i] <= 1000 
// nums[i] != 0 
// 
//
// 
// Follow up: Could you solve it in O(n) time complexity and O(1) extra space co
//mplexity? 
// Related Topics 数组 哈希表 双指针 
// 👍 97 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class CircularArrayLoop457 {
    // Wrong Answer
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        for(int i = len - 1; i >= 0; i--) {
            int cur = nums[i];
            int next = cur + i;
            if(cur <= 0 || (next < len && nums[next] <= 0) || cur >= len) continue;
            if(next >= len) dp[i] = cur;    // 如果目标序列中两个元素都超边界会挂
            else dp[i] = cur + dp[next];
            if(dp[i] % len == 0) return true;
        }
        for(int i = 0; i <= len - 1; i++) {
            int cur = nums[i];
            int next = cur + i;
            if(cur >= 0 || (next >= 0 && nums[next] >= 0) || cur <= -len) continue;
            if(next <= -1) dp[i] = cur; // 与上同
            else dp[i] = cur + dp[next];
            if(dp[i] % len == 0) return true;
        }

        return false;
    }


    // 搬运工
    public boolean circularArrayLoop2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(nums, i);
            // 判断非零且方向相同
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                int tmp = add;
                add = next(nums, add);
                nums[tmp] = 0;
            }
        }
        return false;
    }

    public int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n; // 保证返回值在 [0,n) 中
    }

}
//leetcode submit region end(Prohibit modification and deletion)
