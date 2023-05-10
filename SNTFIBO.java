package Java_SPOJ;
import java.io.*;
class SNTFIBO {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int[] p = new int[t];
        for (int i = 0; i < t; i++) {
            p[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < t; i++) {
            bw.write(isFibonacciPrime(p[i]) + " ");
        }
        bw.flush();
    }

    static String isFibonacciPrime(int p) {
        if (isPrime(p) && isFibonacci(p)) {
            return "YES";
        }
        return "NO";
    }

    static boolean isPrime(int p) {
        if (p == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(p); i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isFibonacci(int p) {
        int a = 0;
        int b = 1;
        int c = 0;
        while (c < p) {
            c = a + b;
            a = b;
            b = c;
        }
        return c == p;
    }
}