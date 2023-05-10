package Java_SPOJ;
// Import các thư viện cần thiết để ghi và đọc dữ liệu từ đầu vào/đầu ra
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
// Tạo lớp BRIDGE
class BRIDGE {
    // Tạo lớp Point, kế thừa từ interface Comparator
    static class Point implements Comparator<Point> {
        // 2 thuộc tính x, y là tọa độ của 1 điểm
        int x, y;
        Point() {
        }
        // Phương thức so sánh 2 điểm dựa trên tọa độ y. Nếu tọa độ y bằng nhau thì so sánh dựa trên tọa độ x
        @Override
        public int compare(Point o1, Point o2) {
            if (o1.y == o2.y)
                return o1.x - o2.x;
            return o1.y - o2.y;
        }
    }

    public static void main(String[] args) throws IOException {
        // Tạo đối tượng sc từ lớp AmitScan
        AmitScan sc = new AmitScan();
        // Tạo đối tượng pr từ lớp AmitPrint
        AmitPrint pr = new AmitPrint();
        // Đọc số lượng test case từ đầu vào
        int t = sc.si();
        // Vòng lặp duyệt qua từng test case
        while (t-- > 0) {
            // Đọc số lượng điểm từ đầu vào
            int n = sc.si();
            // Tạo mảng a chứa các điểm
            Point[] a = new Point[n];
            for (int i = 0; i < n; i++) {
                a[i] = new Point();
                // Đọc tọa độ x của điểm thứ i
                a[i].x = sc.si();
            }
            for (int i = 0; i < n; i++)
                // Đọc tọa độ y của điểm thứ i
                a[i].y = sc.si();
            // Sắp xếp mảng a dựa trên tọa độ y (sử dụng phương thức compare của lớp Point)
            Arrays.sort(a, new Point());

            // Tìm Longest Increasing Subsequence (LIS) trên tọa độ x
            int[] memo = new int[n];
            // Khởi tạo từng phần tử của mảng memo bằng 1
            for (int i = 0; i < n; i++)
                memo[i] = 1;
            // Biến ans lưu giá trị LIS tối đa
            int ans = 1;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    // Nếu tọa độ x của điểm j nhỏ hơn hoặc bằng tọa độ x của điểm i
                    if (a[j].x <= a[i].x) {
                        // Cập nhật giá trị LIS tạm thời tại điểm i
                        int temp = 1 + memo[j];
                        memo[i] = Math.max(temp, memo[i]);
                    }
                }
                // Cập nhật giá trị LIS tối đa
                ans = Math.max(ans, memo[i]);
            }
            // In giá trị LIS tối đa ra đầu ra
            pr.pl(ans);
        }
        // Đóng đầu ra
        pr.close();
    }
    // Tạo lớp AmitScan để đọc dữ liệu từ đầu vào
    static class AmitScan {
        private byte[] buf = new byte[131072]; // Buffer of Bytes
        private int index;
        private InputStream in;
        private int total;

        AmitScan() {
            in = System.in;
        }

        private int scan() throws IOException // Phương thức scan để quét buffer
        {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        int si() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) // Xóa các khoảng trắng đầu
                n = scan();
            int neg = 1;
            if (n == '-') // Nếu gặp dấu trừ
            {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        private boolean isWhiteSpace(int n) {
            return n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1;
        }
    }
// Tạo lớp AmitPrint để ghi dữ liệu ra đầu ra
    static class AmitPrint {
        private final BufferedWriter bw;
        AmitPrint() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        private void p(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }
        void pl(Object object) throws IOException {
            p(object);
            bw.append("\n");
        }
        void close() throws IOException {
            bw.close();
        }
    }
}