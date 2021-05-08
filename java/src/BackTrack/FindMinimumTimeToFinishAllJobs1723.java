package BackTrack;
//给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
//
// 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
//设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。 
//
// 返回分配方案中尽可能 最小 的 最大工作时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：jobs = [3,2,3], k = 3
//输出：3
//解释：给每位工人分配一项工作，最大工作时间是 3 。
// 
//
// 示例 2： 
//
// 
//输入：jobs = [1,2,4,7,8], k = 2
//输出：11
//解释：按下述方式分配工作：
//1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
//2 号工人：4、7（工作时间 = 4 + 7 = 11）
//最大工作时间是 11 。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= jobs.length <= 12 
// 1 <= jobs[i] <= 107 
// 
// Related Topics 递归 回溯算法 
// 👍 121 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class FindMinimumTimeToFinishAllJobs1723 {

    public static int res = Integer.MAX_VALUE;

    public static int minimumTimeRequired(int[] jobs, int k) {
        backtrack(jobs, 0, new int[k], 0);
        return res;
    }

    /**
     * @param jobs 工作量
     * @param jobIndex 当前待分配工作索引
     * @param jobTime  员工工作分配
     * @param curMax   当前分配情况的res值
     */
    public static void backtrack(int[] jobs, int jobIndex, int[] jobTime, int curMax) {
        if (jobIndex == jobs.length) {
            res = Math.min(res, curMax);
            return;
        }
        // 无序分配 因此多个员工分配量都为0时 任选其一即可
        boolean flag = true;
        for (int i = 0; i < jobTime.length; i++) {
            if (jobTime[i] == 0) {
                if (!flag) return;
                flag = false;
            }
            jobTime[i] += jobs[jobIndex];
            if (jobTime[i] <= res) {
                backtrack(jobs, jobIndex + 1, jobTime, Math.max(curMax, jobTime[i]));
            }
            jobTime[i] -= jobs[jobIndex];
        }
    }

    public static void main(String[] args) {
        int[] jobs = new int[]{1, 2, 4, 7, 8};
        System.out.println(minimumTimeRequired(jobs, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
