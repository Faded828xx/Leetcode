package Contest.Weekly_Contest;

public class Contest252 {

    // AC
    public boolean isThree(int n) {
        if(n == 1) return false;
        int a = (int) Math.sqrt(n);
        if(n != a * a) return false;
        for(int i = 2; i < a; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

}
