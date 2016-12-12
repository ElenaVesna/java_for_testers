package eb.vesna.sandbox;

/**
 * Created by Elena_Bogomolova on 12.12.2016.
 */
public class Primes {
    public static boolean isPrime(int n) {
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m; i++) {
            if (n % i == 0) {
                /*System.out.println("число не простое"); */
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFast(int n) {
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m; i++) {
            if (n % i == 0) {
                /*System.out.println("число не простое"); */
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime1(long n) {
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeWhile(int n) {
        int i = 2;
        while (i < n && n % i == 0) {
            i++;
        }
        return i == n;
    }
}
