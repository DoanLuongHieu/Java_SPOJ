package Java_SPOJ;
// Import các thư viện cần thiết
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// Khởi tạo class BDOI16A
class BDOI16A {
    // Phương thức chính của chương trình
    public static void main(String[] args) throws IOException {
        // Tạo đối tượng reader từ class Reader
        Reader reader = new Reader();
        // Tạo đối tượng out từ lớp PrintWriter, ghi dữ liệu ra System.out
        PrintWriter out = new PrintWriter(System.out);
        // Tạo một đối tượng StringBuilder để lưu trữ kết quả
        StringBuilder s = new StringBuilder();
        // Đọc số lượng test từ input
        int numOfTest = reader.nextInt();
        // Khai báo biến numOfOperations để lưu trữ số lượng operations
        int numOfOperations;
        // Khai báo biến count, ban đầu bằng 0, sử dụng để đếm số lượng test
        int count = 0;
        // Khai báo biến x, y, code để lưu trữ các tham số đầu vào
        String x, y, code;
        // Khai báo biến offset, ban đầu bằng 200025
        final int offset = 200025;
        // Lặp qua từng test
        while (count++ < numOfTest) {
            // Khai báo biến high, ban đầu bằng offset
            int high = offset;
            // Khai báo biến low, ban đầu bằng offset
            int low = offset;
            // Thêm vào kết quả với string "Case " và số thứ tự của test
            s.append("Case ").append(count).append(":\n");
            // Khai báo mảng data có kích thước 400125
            String[] data = new String[400125];
            // Tạo đối tượng position từ lớp HashMap, dùng để lưu trữ vị trí của mỗi phần tử
            Map<String, Integer> position = new HashMap<>();
            // Đọc số lượng operations từ input
            numOfOperations = reader.nextInt();
            // Lặp qua từng operation
            while (numOfOperations-- > 0) {
                // Tạo đối tượng token từ lớp StringTokenizer để tách chuỗi đọc được từ reader.readLine()
                StringTokenizer token = new StringTokenizer(reader.readLine());
                // Đọc code từ token
                code = token.nextToken();
                // Lựa chọn thực hiện tùy theo code
                switch (code) {
                    case "1": // Nếu code là 1
                        x = token.nextToken(); // Đọc x từ token
                        y = token.nextToken(); // Đọc y từ token
                        if (x.equals("B")) { // Nếu x bằng B
                            // Thêm phần tử y vào position tại vị trí high
                            position.put(y, high);
                            // Lưu trữ y tại vị trí high trong mảng data
                            data[high] = y;
                            // Tăng high lên 1
                            high++;
                        } else {
                            // Giảm low xuống 1
                            low--;
                            // Thêm phần tử y vào position tại vị trí low
                            position.put(y, low);
                            // Lưu trữ y tại vị trí low trong mảng data
                            data[low] = y;
                        }
                        break;
                    case "2": // Nếu code là 2
                        // Đọc x từ token
                        x = token.nextToken();
                        // Nếu x bằng B
                        if (x.equals("B"))
                            // Giảm high xuống 1
                            high--;
                        // Ngược lại
                        else
                        // Tăng low lên 1
                            low++;
                        break;
                    case "3": // Nếu code là 3
                        // Đọc x từ token
                        x = token.nextToken();
                        // Đọc y từ token
                        y = token.nextToken();
                        // Chuyển y từ dạng string sang dạng int
                        int finalY = Integer.parseInt(y);
                        // Nếu x bằng D
                        if (x.equals("D"))
                            // Thêm vào kết quả data tại vị trí low + finalY - 1
                            s.append(data[low + finalY - 1]).append("\n");
                        // Ngược lại
                        else
                            // Thêm vào kết quả position tại vị trí y trừ low + 1
                            s.append(position.get(y) - low + 1).append("\n");
                        break;
                }
            }
        }

        // Ghi kết quả ra đầu ra
        out.print(s);
        // Đóng đối tượng out
        out.close();
        // Đóng đối tượng reader
        reader.close();
    }

    static class Reader { // Lớp Reader dùng để đọc đầu vào từ một file hoặc từ bàn phím
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException { // Đọc một số nguyên từ đầu vào
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        public String readLine() throws IOException { // Đọc một đòng từ đầu vào
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        final private int BUFFER_SIZE = 1 << 16;

        private void fillBuffer() throws IOException { // Đọc các byte từ đầu vào và đưa vào buffer
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException { // Đọc một byte từ buffer và đưa vào đầu ra
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException { // Đóng đầu vào
            din.close();
        }
    }
}
