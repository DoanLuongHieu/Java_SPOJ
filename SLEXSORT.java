package Java_SPOJ;
import java.util.Arrays;
import java.util.Scanner;
class S{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            String A = s.next();
            int n = s.nextInt();
            String[] w = new String[n];
            for (int j = 0; j < n; j++) {
                w[j] = s.next();
            }
            Arrays.sort(w,(x,y) -> {
                int p = x.length();
                int q = y.length();
                int j = 0, z;
                while (j < p && j < q) {
                    z = A.indexOf(x.charAt(j)) - A.indexOf(y.charAt(j++));
                    if (z != 0) return z;
                }return p - q;
            });
            for (String z : w) {
                System.out.println(z);
            }
            System.out.println();
        }
    }
}
// Bắt đầu từ đây, vì vấn đề này cần độ dài của mã nguồn luôn là nhỏ nhất (theo yêu cầu của vấn đề trong website https://www.spoj.com/problems/SLEXSORT/)
// Vì lẽ đó, đoạn code sẽ càng ngày càng ngắn, và để không phải viết lại, tôi sẽ chỉ giải thích các dòng lệnh trong vấn đề này ở commit đầu tiên (Add SLEXSORT.java)
// Từ các commit sau của vấn đề này, sẽ chỉ có các đoạn mã (với độ dài ngày càng ngắn).
