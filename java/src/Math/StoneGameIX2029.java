package Math;
//Alice and Bob continue their games with stones. There is a row of n stones, an
//d each stone has an associated value. You are given an integer array stones, whe
//re stones[i] is the value of the ith stone. 
//
// Alice and Bob take turns, with Alice starting first. On each turn, the player
// may remove any stone from stones. The player who removes a stone loses if the s
//um of the values of all removed stones is divisible by 3. Bob will win automatic
//ally if there are no remaining stones (even if it is Alice's turn). 
//
// Assuming both players play optimally, return true if Alice wins and false if 
//Bob wins. 
//
// 
// Example 1: 
//
// 
//Input: stones = [2,1]
//Output: true
//Explanation: The game will be played as follows:
//- Turn 1: Alice can remove either stone.
//- Turn 2: Bob removes the remaining stone. 
//The sum of the removed stones is 1 + 2 = 3 and is divisible by 3. Therefore, B
//ob loses and Alice wins the game.
// 
//
// Example 2: 
//
// 
//Input: stones = [2]
//Output: false
//Explanation: Alice will remove the only stone, and the sum of the values on th
//e removed stones is 2. 
//Since all the stones are removed and the sum of values is not divisible by 3, 
//Bob wins the game.
// 
//
// Example 3: 
//
// 
//Input: stones = [5,1,2,4,3]
//Output: false
//Explanation: Bob will always win. One possible way for Bob to win is shown bel
//ow:
//- Turn 1: Alice can remove the second stone with value 1. Sum of removed stone
//s = 1.
//- Turn 2: Bob removes the fifth stone with value 3. Sum of removed stones = 1 
//+ 3 = 4.
//- Turn 3: Alices removes the fourth stone with value 4. Sum of removed stones 
//= 1 + 3 + 4 = 8.
//- Turn 4: Bob removes the third stone with value 2. Sum of removed stones = 1 
//+ 3 + 4 + 2 = 10.
//- Turn 5: Alice removes the first stone with value 5. Sum of removed stones = 
//1 + 3 + 4 + 2 + 5 = 15.
//Alice loses the game because the sum of the removed stones (15) is divisible b
//y 3. Bob wins the game.
// 
//
// 
// Constraints: 
//
// 
// 1 <= stones.length <= 105 
// 1 <= stones[i] <= 104 
// 
// Related Topics 贪心 数组 数学 计数 博弈 
// 👍 108 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class StoneGameIX2029 {
    public boolean stoneGameIX(int[] stones) {
        // 将所有数转化为0/3 1 2 因此1和2可以看成是对立数
        if(stones.length == 1)
            return false;
        int[] cnt = new int[3];
        for(int n : stones)
            cnt[n % 3]++;
        // 3 可以两两抵消
        if(cnt[0] % 2 == 0)
            // 先手先取少的一方 只要1和2的个数都不为0 就可以让后手必抓到对立数
            return cnt[1] != 0 && cnt[2] != 0;
        int r = Math.max(cnt[1], cnt[2]);
        int l = Math.min(cnt[1], cnt[2]);
        // 存在一个多余3时 若先手先取少的一方 必输
        // 先手取多的一方 多者比少者多3及以上时 是先手赢
        return r - l >= 3;
    }

    public static void main(String[] args) {
        StoneGameIX2029 stoneGameIX2029 = new StoneGameIX2029();
        int[] stones = new int[]{3, 3, 3};
        System.out.println(stoneGameIX2029.stoneGameIX(stones));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
