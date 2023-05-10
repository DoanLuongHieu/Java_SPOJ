package Java_SPOJ;
// Import các thư viện cần thiết
import java.io.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Arrays;

// Tạo class ADAFRIEN
class ADAFRIEN {
    // Hàm chính của chương trình
    public static void main(String[] args) throws IOException {
        // Tạo đối tượng BufferedReader để đọc dữ liệu từ input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Tạo đối tượng PrintWriter để in dữ liệu ra output
        PrintWriter out = new PrintWriter(System.out);

        // Đọc dòng đầu tiên từ input và tách thành các token bằng StringTokenizer
        StringTokenizer st = new StringTokenizer(br.readLine());
        // Chuyển token đầu tiên thành số nguyên và gán cho biến q
        int q = Integer.parseInt(st.nextToken());
        // Chuyển token thứ hai thành số nguyên và gán cho biến k
        int k = Integer.parseInt(st.nextToken());

        // Tạo HashMap để lưu tên và số liệu liên quan
        HashMap<String, Long> map = new HashMap<>();
        // Duyệt qua tất cả các dòng tiếp theo (q dòng)
        for (int i = 0; i < q; i++) {
            // Đọc dòng tiếp theo và tách thành các token
            st = new StringTokenizer(br.readLine());
            // Lấy token đầu tiên làm tên
            String name = st.nextToken();
            // Cập nhật giá trị cho tên trong HashMap
            map.put(name, map.getOrDefault(name, 0L) + Long.parseLong(st.nextToken()));
        }

        // Tạo mảng values để lưu giá trị của tất cả các entry trong HashMap
        long[] values = new long[map.size()];
        int i = 0;
        // Duyệt qua tất cả các entry trong HashMap và lưu giá trị của chúng vào mảng values
        for (long value : map.values()) {
            values[i++] = value;
        }
        // Sắp xếp mảng values tăng dần
        Arrays.sort(values);
        // Tạo biến sum để lưu tổng các giá trị lớn nhất
        long sum = 0;
        // Lấy k nhỏ hơn hoặc bằng độ dài của mảng values
        k = Math.min(k, values.length);
        // Duyệt qua k phần tử cuối cùng của mảng values và cộng dồn vào biến sum
        for (int j = values.length - 1; j >= values.length - k; j--) {
            sum += values[j];
        }
        // In kết quả ra output
        out.println(sum);
        // Đóng đối tượng PrintWriter
        out.close();
        // Đóng đối tượng BufferedReader
        br.close();
    }
}