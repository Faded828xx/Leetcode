package 面试;

import java.util.*;

/**
 * @author faded828x
 * @date 2022/9/4
 */
public class e {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Map<Integer, Integer> map = new HashMap<>();
//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
//        for(int i = 0; i < n; i++) {
//            int a = scanner.nextInt();
//            map.put(a % k, map.getOrDefault(a % k, 0) + 1);
//        }
//        int res = 0;
//        for(int v : map.values()) res = Math.max(res, v);
//        System.out.println(res);

//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
//        int t = scanner.nextInt();
//        StringBuilder sb = new StringBuilder();
//        if(k < t + 1 || n < 2 * k - t - 1) System.out.println(-1);
//        else {
//            sb.append("1".repeat(t + 1));
//            sb.append("01".repeat(k - t - 1));
//            sb.append("0".repeat(n - 2 * k + t + 1));
//        }
//        System.out.println(sb);

//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
//        int x = scanner.nextInt();
//        List<Integer> list = new ArrayList<>(n);
//        for(int i = 0; i < n; i++)
//            list.add(scanner.nextInt());
//        PriorityQueue<Integer> heap = new PriorityQueue<>(n, (o1, o2) -> o2 - o1);
//        heap.addAll(list);
//        for(int i = 0; i < k; i++) {
//            int max = heap.peek();
//            heap.remove();
//            heap.add(max - x);
//        }
//        System.out.println(heap.peek());

//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        int k = scanner.nextInt();
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < n; i++)
//            sb.append(scanner.nextInt());
//        int idx = k % (2 * sb.length());
//        sb.append(new StringBuilder(sb).reverse());
//        System.out.println(sb.charAt(idx == 0 ? sb.length() - 1 : idx - 1));


        int n = scanner.nextInt();
        int res = 0;
        int num0 = 0;
        int num1 = 0;
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int cur = scanner.nextInt();
            int diff1 = cur > 0 ? Math.abs(cur - 7) : Math.abs(cur - -7);
            int diff2 = diff > 0 ? Math.abs(diff - 7) : Math.abs(diff - -7);
            if(diff2 > diff1) diff = cur;
            if(cur < 0) {
                res += (-1 - cur);
                num1++;
            }
            else if(cur == 0) {num0++;res++;}
            else res += (cur - 1);
        }
        if(diff < -7) res -= (-7 - diff);
        else if(diff > 7) res -= (diff - 7);
        else if(diff < 0) res += (8 + diff * 2);
        else res += (8 - 2 * diff);
        if(num0 == 0) {
            if(num1 % 2 == 1) res += 2;
        }
        System.out.println(res);

    }
}
