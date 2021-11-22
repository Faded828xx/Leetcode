package Contest.Weekly_Contest;

import java.util.Arrays;

/**
 * @author faded828x
 * @date 2021/11/21
 */
public class Contest268 {

    // AC
    public int maxDistance(int[] colors) {
        int res = 0;
        int l = colors.length;
        for(int i = 1; i < l; i++) {
            for(int j = 0; j < i; j++) {
                if(colors[i] != colors[j]) {
                    res = Math.max(res, i - j);
                    break;
                }
            }
        }
        return res;
    }

    // AC
    public int wateringPlants(int[] plants, int capacity) {
        int l = plants.length;
        int idx = 0;
        int res = 0;
        int cur = capacity;
        while(idx != l) {
            if(cur >= plants[idx]) {
                res++;
                cur -= plants[idx];
                idx++;
                continue;
            }
            res += (idx * 2);
            cur = capacity;
        }
        return res;
    }

    String convert(long n, int k) {
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            int m = (int) (n % k);
            sb.insert(0, m);
            n = n / k;
        }
        return sb.toString();
    }
    boolean check(String s) {
        int l = s.length();
        for(int i = 0; i < l / 2; i++) {
            if(s.charAt(i) != s.charAt(l - i - 1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Contest268 contest268 = new Contest268();
        long[] a7 = new long[30];
        int j = 0;
        for(long i = 1; i < Long.MAX_VALUE; i++) {
            if(contest268.check(String.valueOf(i)) && contest268.check(contest268.convert(i, 7))) {
                a7[j] = i;
                j++;
                if(j == 30)
                    break;
                System.out.println(i);
            }
        }
        System.out.println(Arrays.toString(a7));
    }

    // 跑到十二点零几分才跑完 没打上 气死啊 应该是方法没写对
    {
        long[][] arr = new long[10][30];
        arr[2] = new long[]{1, 3, 5, 7, 9, 33, 99, 313, 585, 717, 7447, 9009, 15351, 32223, 39993, 53235, 53835, 73737,
                585585, 1758571, 1934391, 1979791, 3129213, 5071705, 5259525, 5841485, 13500531, 719848917, 910373019, 939474939};
        arr[3] = new long[]{1, 2, 4, 8, 121, 151, 212, 242, 484, 656, 757, 29092, 48884, 74647, 75457, 76267, 92929, 93739,
                848848, 1521251, 2985892, 4022204, 4219124, 4251524, 4287824, 5737375, 7875787, 7949497, 27711772, 83155138};
        arr[4] = new long[]{1 ,2 ,3 ,5 ,55 ,373 ,393 ,666 ,787 ,939 ,7997 ,53235 ,55255 ,55655 ,57675 ,506605 ,1801081 ,2215122 ,3826283,
                3866683, 5051505 ,5226225 ,5259525 ,5297925 ,5614165 ,5679765 ,53822835 ,623010326 ,954656459,51717171715L};
        arr[5] = new long[]{1,2,3,4,6,88,252,282,626,676,1221,15751,18881,10088001,10400401,27711772,30322303,47633674,65977956,
                808656808,831333138,831868138,836131638,836181638,2512882152L,2596886952L,2893553982L,6761551676L, 12114741121L, 12185058121L};
        arr[6] = new long[]{1, 2, 3, 4, 5, 7, 55, 111, 141, 191, 343, 434, 777, 868, 1441, 7667, 7777, 22022, 39893, 74647,
                168861, 808808, 909909, 1867681, 3097903, 4232324, 4265624, 4298924, 4516154, 4565654};
        arr[7] = new long[]{1 ,2 ,3 ,4 ,5 ,6 ,8 ,121 ,171 ,242 ,292 ,16561 ,65656 ,2137312 ,4602064 ,6597956 ,6958596 ,9470749 ,61255216 ,230474032,
                466828664 ,485494584 ,638828836 ,657494756 ,858474858 ,25699499652L, 40130703104L, 45862226854L,61454945416L, 64454545446L};
        arr[8] = new long[]{1, 2, 3, 4, 5, 6, 7, 9, 121, 292, 333, 373, 414, 585, 3663, 8778, 13131, 13331, 26462, 26662,
                30103, 30303, 207702, 628826, 660066, 1496941, 1935391, 1970791, 4198914, 55366355};
        arr[9] = new long[]{1, 2, 3, 4, 5, 6, 7, 8, 191, 282, 373, 464, 555, 646, 656, 6886, 25752, 27472, 42324, 50605,
                626626, 1540451, 1713171, 1721271, 1828281, 1877781, 1885881, 2401042, 2434342, 2442442};
//        int n, k;
//        long res = 0;
//        for(int i = 0; i < n; i++)
//            res += arr[k][i];
//        return res;
    }

}
