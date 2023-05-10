package Java_SPOJ;

//Import thư viện để sử dụng các hàm trong đó
import java.util.Arrays;
import java.util.Scanner;

//Khai báo class SLEXSORT
class SLEXSORT {
    //Hàm main
    public static void main(String[] args) {

        //Tạo đối tượng Scanner để nhận đầu vào từ bàn phím
        Scanner in = new Scanner(System.in);

        //Nhập số lượng trường hợp cần xử lý
        int t = in.nextInt();

        //Vòng lặp for để xử lý từng trường hợp
        for (int i = 0; i < t; i++) {

            //Nhập chuỗi A
            String A = in.next();

            //Nhập số lượng từ cần sắp xếp
            int n = in.nextInt();

            //Khởi tạo mảng chứa các từ cần sắp xếp
            String[] words = new String[n];

            //Vòng lặp để nhập từng từ vào mảng
            for (int j = 0; j < n; j++) {
                words[j] = in.next();
            }

            //Sử dụng hàm sort của thư viện Arrays để sắp xếp các từ trong mảng words theo thứ tự được định nghĩa bằng lambda expression
            Arrays.sort(words, (x, y) -> {
                int p = x.length();
                int q = y.length();
                int j = 0, z;
                while (j < p && j < q) {
                    z = A.indexOf(x.charAt(j)) - A.indexOf(y.charAt(j++));
                    if (z != 0) return z;
                }
                return p - q;
            });

            //Vòng lặp for-each để in ra các từ đã sắp xếp
            for (String word : words) {
                System.out.println(word);
            }

            //In ra một dòng trống để ngăn cách giữa các trường hợp
            System.out.println();
        }
    }
}