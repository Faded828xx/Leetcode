package Contest.Biweekly_Contest;

public class Contest55 {
    // AC
    public static boolean canBeIncreasing(int[] nums) {
        int len = nums.length;
        int split = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1])
                continue;
            split = i;
            break;
        }
        if (split == 0) return true;
        int tmp = split + 1;
        if (tmp == len) return true;
        if ((split > 1 && nums[split] > nums[split - 2]) || split == 1) {
            for (int i = split + 1; i < len; i++) {
                if (nums[i] > nums[i - 1])
                    continue;
                split = i;
                break;
            }
            if (split == tmp - 1) return true;
        }
        split = -1;
        if (nums[tmp] <= nums[tmp - 2]) return false;
        for (int i = tmp + 1; i < len; i++) {
            if (nums[i] > nums[i - 1])
                continue;
            split = i;
            break;
        }
        if (split == -1) return true;
        return false;
    }

    // AC
    public static String removeOccurrences(String s, String part) {
        int index = s.indexOf(part);
        int lenS = s.length();
        int lenP = part.length();
        while (index != -1) {
            s = s.substring(0, index) + s.substring(index + lenP, lenS);
            index = s.indexOf(part);
            lenS = s.length();
        }
        return s;
    }

    // 看不懂
    public static long maxAlternatingSum(int[] nums) {
        int len = nums.length;
        int[] sum = new int[len];

        return 0;
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{13,205,553,527,790,238};
//        System.out.println(canBeIncreasing(nums));
        String s = "daabcbaabcbc";
        String part = "abc";
        System.out.println(removeOccurrences(s, part));
    }
}
