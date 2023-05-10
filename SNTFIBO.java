package Java_SPOJ;
import java.io.*;
class SNTFIBO {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine()); //Đọc số lượng phần tử trong mảng từ bàn phím
        int[] p = new int[t]; //Tạo mảng p với kích thước là t
        for (int i = 0; i < t; i++) {
            p[i] = Integer.parseInt(br.readLine()); //Đọc các phần tử của mảng từ bàn phím và gán vào mảng p
        }
        for (int i = 0; i < t; i++) { //Lặp qua từng phần tử trong mảng
            bw.write(isFibonacciPrime(p[i]) + " "); //Kiểm tra xem phần tử i có phải số nguyên tố và số trong dãy Fibonacci không
        }
        bw.flush(); //Xuất kết quả
    }

    static String isFibonacciPrime(int p) { //Hàm kiểm tra xem phần tử có phải số nguyên tố và số trong dãy Fibonacci không
        if (isPrime(p) && isFibonacci(p)) { //Nếu phần tử p là số nguyên tố và số trong dãy Fibonacci thì trả về YES
            return "YES";
        }
        return "NO"; //Ngược lại trả về NO
    }

    static boolean isPrime(int p) { //Hàm kiểm tra xem một số có phải là số nguyên tố hay không
        if (p == 1) {
            return false; //Nếu p = 1 thì không phải số nguyên tố
        }
        for (int i = 2; i <= Math.sqrt(p); i++) {
            if (p % i == 0) {
                return false; //Nếu p chia hết cho i thì không phải số nguyên tố
            }
        }
        return true; //Ngược lại là số nguyên tố
    }

    static boolean isFibonacci(int p) { //Hàm kiểm tra xem một số có phải số trong dãy Fibonacci hay không
        int a = 0;
        int b = 1;
        int c = 0;
        while (c < p) {
            c = a + b;
            a = b;
            b = c;
        }
        return c == p; //Nếu số p nằm trong dãy Fibonacci thì c = p, ngược lại thì c < p
    }
}
