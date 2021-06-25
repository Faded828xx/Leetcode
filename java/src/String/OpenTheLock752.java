package String;
//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
// 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。 
//
// 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。 
//
// 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。 
//
// 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。 
//
// 
//
// 示例 1: 
//
// 
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
// 
//
// 示例 2: 
//
// 
//输入: deadends = ["8888"], target = "0009"
//输出：1
//解释：
//把最后一位反向旋转一次即可 "0000" -> "0009"。
// 
//
// 示例 3: 
//
// 
//输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], targ
//et = "8888"
//输出：-1
//解释：
//无法旋转到目标数字且不被锁定。
// 
//
// 示例 4: 
//
// 
//输入: deadends = ["0000"], target = "8888"
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= deadends.length <= 500 
// deadends[i].length == 4 
// target.length == 4 
// target 不在 deadends 之中 
// target 和 deadends[i] 仅由若干位数字组成 
// 
// Related Topics 广度优先搜索 数组 哈希表 字符串 
// 👍 294 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class OpenTheLock752 {
    // 本来想归到BFS分类的 懒得开分组了
    public static int openLock(String[] deadends, String target) {
        int tar = Integer.parseInt(target);
        if (tar == 0) return 0; // target为0 无需变化
        Set<Integer> dead = new HashSet<>();
        Set<Integer> visited = new HashSet<>(); // 记忆化 防止死循环
        for (String str : deadends) {   // 感觉用整型好处理点
            dead.add(Integer.parseInt(str));
        }
        if (dead.contains(0))   // 0直接锁死
            return -1;
        Deque<Integer> queue = new ArrayDeque<>();  // BFS
        queue.add(0);
        visited.add(0);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                int cur = queue.remove();
                for (int j = 0; j < 8; j++) {
                    int next = change(cur, j);
                    if (next == tar) return res;
                    if (dead.contains(next) || visited.contains(next))
                        continue;
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
        return -1;
    }

    // 9->0 和 0->9需退位
    public static int change(int src, int index) {
        int[] method = new int[]{1, -1, 10, -10, 100, -100, 1000, -1000};
        int res = src + method[index];
        if (src % 10 == 9 && index == 0) res -= 10;
        else if (src % 10 == 0 && index == 1) res += 10;
        else if ((src / 10) % 10 == 9 && index == 2) res -= 100;
        else if ((src / 10) % 10 == 0 && index == 3) res += 100;
        else if ((src / 100) % 10 == 9 && index == 4) res -= 1000;
        else if ((src / 100) % 10 == 0 && index == 5) res += 1000;
        else if ((src / 1000) % 10 == 9 && index == 6) res -= 10000;
        else if ((src / 1000) % 10 == 0 && index == 7) res += 10000;
        return res;
    }

    public static void main(String[] args) {
        String[] deadends = new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target = "8888";
        System.out.println(openLock(deadends, target));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
