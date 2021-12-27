package Array;
//There are n persons on a social media website. You are given an integer array
//ages where ages[i] is the age of the ith person. 
//
// A Person x will not send a friend request to a person y (x != y) if any of th
//e following conditions is true: 
//
// 
// age[y] <= 0.5 * age[x] + 7 
// age[y] > age[x] 
// age[y] > 100 && age[x] < 100 
// 
//
// Otherwise, x will send a friend request to y. 
//
// Note that if x sends a request to y, y will not necessarily send a request to
// x. Also, a person will not send a friend request to themself. 
//
// Return the total number of friend requests made. 
//
// 
// Example 1: 
//
// 
//Input: ages = [16,16]
//Output: 2
//Explanation: 2 people friend request each other.
// 
//
// Example 2: 
//
// 
//Input: ages = [16,17,18]
//Output: 2
//Explanation: Friend requests are made 17 -> 16, 18 -> 17.
// 
//
// Example 3: 
//
// 
//Input: ages = [20,30,100,110,120]
//Output: 3
//Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
// 
//
// 
// Constraints: 
//
// 
// n == ages.length 
// 1 <= n <= 2 * 104 
// 1 <= ages[i] <= 120 
// 
// Related Topics 数组 双指针 二分查找 排序 
// 👍 89 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class FriendsOfAppropriateAges825 {
    public int numFriendRequests(int[] ages) {
        int len = ages.length;
        Integer[] a = new Integer[len];
        for(int i = 0; i < len; i++)
            a[i] = ages[i];
        Arrays.sort(a, (n1, n2) -> n2 - n1);
        System.out.println(Arrays.toString(a));
        int cnt = 0;
        // 这里j可以只维护一个
        for(int i = 0; i <= len - 1; i++) {
            for(int j = i + 1; j < len; j++) {
                if (a[j] <= (a[i] >> 1) + 7)
                    break;
                else cnt++;
            }
            for(int j = i - 1; j >= 0 && a[j].equals(a[i]) && a[i] > 14; j--)
                cnt++;
        }
        return cnt;
    }

    public int numFriendRequests2(int[] ages) {
        int len = ages.length;
        Arrays.sort(ages);
        int cnt = 0;
        int l = 0;
        for(int i = 0; i < len; i++) {
            int age = ages[i];
            if(age <= 14) continue;
            while(ages[l] <= age / 2 + 7) l++;
            int r = i + 1;
            while(r < len && ages[r] == age) r++;
            cnt += r - l - 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        FriendsOfAppropriateAges825 friendsOfAppropriateAges825 = new FriendsOfAppropriateAges825();
        int[] ages = new int[]{81,106,11,66,83,113,51,62,47,42,85,94,78,96,51,14,3,111,57,66,8,113,27,61,21,55,87,15,20,23,14,105,38,85,2,108,103,46,44,27,79,108,106,86,113,24,39,8,7,97};
        System.out.println(friendsOfAppropriateAges825.numFriendRequests2(ages));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
