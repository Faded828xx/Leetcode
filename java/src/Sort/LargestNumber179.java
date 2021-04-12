package Sort;
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 
//输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 排序 
// 👍 551 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class LargestNumber179 {
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strArr = new String[len];
        for(int i=0; i<len; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strArr,(a,b)-> (b+a).compareTo(a+b));
        if(strArr[0].equals("0"))
            return "0";
        StringBuilder sb = new StringBuilder();
        for(String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
