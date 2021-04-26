package Array;
//传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。 
//
// 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。 
//
// 
//
// 示例 1： 
//
// 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。 
// 
//
// 示例 2： 
//
// 输入：weights = [3,2,2,4,1,4], D = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
// 
//
// 示例 3： 
//
// 输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= D <= weights.length <= 50000 
// 1 <= weights[i] <= 500 
// 
// Related Topics 数组 二分查找 
// 👍 298 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class CapacityToShipPackagesWithinDDays1011 {
    // 最近写的力扣代码 很多都是看了题解的一点思路 再自己写的 所以大部分有待优化 也算是留个纪念 以后看看现在写的蹩脚代码
    // 以后写的代码仍然很蹩脚也说不定 谁知道呢
    // 二分法 先粗略估计载重的范围 再用二分法对其逼近
    public static int shipWithinDays(int[] weights, int D) {
        int max = 0;
        int sum = 0;
        for(int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }
        int left = Math.max(max, sum/D);    // 左边界为weights中最大值或每天装载的均值
        int right = sum;    // 右边界为weights之和
        while(left<=right) {
            int mid = left + (right - left) / 2;
            if(isValid(weights, D, mid)) {
                right = mid-1;  // 不对mid加一减一会出现死循环
            } else left = mid+1;
        }
        return isValid(weights, D, left) ? left : left + 1; // 因为二分的时候会对mid加一减一 但可能mid是答案 因此加一层判定
    }

    // 当前载重load是否满足条件 load比weights中任意元素都大
    public static boolean isValid(int[] weights, int D, int load) {
        int index = 0;
        while(index<weights.length) {
            if(D==0) return false;
            int curWeight = 0;
            while(curWeight<=load) {
                curWeight += weights[index];
                index++;
                if(index==weights.length && curWeight<=load)
                    return true;
            }
            D--;
            index--;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(isValid(weights, 5, 14));
        System.out.println(shipWithinDays(weights, 5));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
