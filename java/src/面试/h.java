package 面试;

/**
 * @author faded828x
 * @date 2022/9/13
 */
public class h {
    public static void main(String[] args) {
        System.out.println(fun(5,7,32,4));
    }


    public static int fun(int a, int n, int m, int x) {
        int[] u = new int[n + 1];
        u[1] = a;
        int[] tmp = new int[n - 3];
        tmp[0] = 1; tmp[1] = 2;
        for(int i = 2; i < tmp.length; i++)
            tmp[i] = tmp[i - 1] + tmp[i - 2];
        int p = tmp[tmp.length - 2] + 1;
        int q = tmp[tmp.length - 1] - 1;
        if((m - p * a) % q != 0) return -1;
        int xx = (m - p * a) / q;
        return (tmp[x - 4] + 1) * a + (tmp[x - 3] - 1) * xx;
    }


    /*
    * a x a+x a+2x 2a+3x 3a+5x
    *   x x a+x
    * */
}
