package Java_SPOJ;
import java.util.*; // để sử dụng HashMap
import java.io.*;   // để sử dụng DataInputStream, BufferedWriter, OutputStreamWriter

class MINSTOCK {
    static class Reader { // Lớp Reader dùng để đọc đầu vào từ một file hoặc từ bàn phím
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException { // Đọc một dòng từ đầu vào
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException { // Đọc một số nguyên từ đầu vào
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }
        private void fillBuffer() throws IOException { // Đọc các byte từ đầu vào và đưa vào buffer
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }
        private byte read() throws IOException { // Đọc một byte từ buffer và đưa vào đầu ra
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
        public void close() throws IOException { // Đóng đầu vào
            din.close();
        }
    }
    public static void main(String[] args) throws java.lang.Exception {
        Reader reader = new Reader(); // Khởi tạo đối tượng Reader
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // Khởi tạo đối tượng BufferedWriter để xuất kết quả
        StringBuilder sb = new StringBuilder(); //Khởi tạo đối tượng StringBuilder để lưu kết quả
        int n = reader.nextInt(); // Đọc số lượng truy vấn từ input
        HashMap<String, Integer> map = new HashMap<>(); // Khởi tạo HashMap để lưu các sản phẩm
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" "); // Đọc một dòng từ input và chia nó thành mảng các chuỗi theo dấu cách
            if (s[0].equals("1")) { // Nếu truy vấn là loại 1 (thêm sản phẩm)
                map.put(s[1], Integer.parseInt(s[2])); // Thêm sản phẩm với giá vào HashMap
            } else if (s[0].equals("2")) { // Nếu truy vấn là loại 2 (cập nhật giá sản phẩm)
                map.put(s[1], Integer.parseInt(s[2])); // Cập nhật giá sản phẩm trong HashMap
            } else { // Nếu truy vấn là loại 3 (tìm sản phẩm giá thấp nhất)
                int min = Integer.MAX_VALUE; // Khởi tạo giá nhỏ nhất là giá lớn nhất
                String name = ""; // Khởi tạo tên sản phẩm giá thấp nhất là chuỗi rỗng
                for (String key : map.keySet()) { // Duyệt tất cả các key trong HashMap
                    if (map.get(key) < min) {
                        // Nếu giá sản phẩm của key này nhỏ hơn giá thấp nhất hiện tại
                        min = map.get(key); // Cập nhật giá thấp nhất
                        name = key; // Cập nhật tên sản phẩm giá thấp nhất
                    }
                }
                // Thêm tên sản phẩm giá thấp nhất và số truy vấn tìm được vào StringBuilder
                sb.append(name).append(" ").append(i + 1).append("\n");
                // Xóa sản phẩm giá thấp nhất khỏi HashMap
                map.remove(name);
            }
        }
        bw.write(sb.toString()); // Ghi kết quả từ StringBuilder vào BufferedWriter
        bw.flush(); // Đẩy dữ liệu từ buffer xuống OutputStream
        bw.close(); // Đóng BufferedWriter
    }
}
