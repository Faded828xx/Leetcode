package Contest.Weekly_Contest;

import LinkedList.ListNode;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/11/14
 */

// T1 -> T3 -> T4 -> T2
public class Contest267 {

    // AC
    public int timeRequiredToBuy(int[] tickets, int k) {
        int l = tickets.length;
        int res = 0;
        for(int i = 0; i < k; i++) {
            res += Math.min(tickets[i], tickets[k]);
        }
        for(int i = k + 1; i < l; i++) {
            res += Math.min(tickets[i], tickets[k] - 1);
        }
        return res + tickets[k];
    }

    // AC
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int cur = 2;
        int cnt = 0;
        ListNode node = head;
        while(node.next != null) {
            ListNode nod = node;
            List<Integer> l = new ArrayList<>();
            while(cnt < cur && node.next != null) {
                l.add(node.next.val);
                node = node.next;
                cnt++;
            }
            while(cnt > 0) {
                nod.next.val = l.remove(l.size() - 1);
                cnt--;
                nod = nod.next;
            }
            cur++;
            while(node.next != null) {
                node = node.next;
            }
            cur++;
        }
        return head;
    }



    // AC
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();
        int cols = l / rows;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < cols) {
            for(int j = 0; j < rows; j++) {
                int idx = j * cols + j + i;
                if(idx < l)
                    sb.append(encodedText.charAt(idx));
            }
            i++;
        }
        for(i = sb.length() - 1; i >= 0; i--) {
            if(sb.charAt(i) != ' ')
                break;
        }
        return sb.substring(0, i + 1);
    }



    // 大意了 应该用并查集？
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        Map<Integer, Set<Integer>> rest = new HashMap<>(n);
        Map<Integer, Set<Integer>> fri = new HashMap<>();
        for(int i = 0; i < n; i++) {
            rest.put(i, new HashSet<>());
            fri.put(i, new HashSet<>());
        }
        for(int[] restrict : restrictions) {
            int a = restrict[0];
            int b = restrict[1];
            Set<Integer> sa = rest.get(a);
            Set<Integer> sb = rest.get(b);
            sa.add(b);
            sb.add(a);
            rest.put(a, sa);
            rest.put(b, sb);
        }
        int idx = 0;
        boolean[] res = new boolean[requests.length];
        for(int[] req : requests) {
            int a = req[0];
            int b = req[1];
            if(rest.get(a).contains(b)) {
                idx++;
                continue;
            }
            boolean fail = false;
            for(int f : fri.get(b)) {
                if(rest.get(a).contains(f)) {
                    fail = true;
                    break;
                }
            }
            if(fail) {
                idx++;
                continue;
            }
            for(int f : fri.get(a)) {
                if(rest.get(b).contains(f)) {
                    fail = true;
                    break;
                }
            }
            res[idx++] = !fail;
            if(!fail) {
                fri.get(a).add(b);
                fri.get(b).add(a);
            }
        }
        return res;
    }
}
