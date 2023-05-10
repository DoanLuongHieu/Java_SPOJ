package Java_SPOJ;
import java.io.*;
import java.util.*;
class SOCNETC {
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
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public String readLine() throws IOException { // Đọc một dòng từ đầu vào
            byte[] buf = new byte[16]; // line length
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
    // Hàm main
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader(); // Tạo một đối tượng Reader
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // Tạo một đối tượng BufferedWriter để ghi đầu ra
        String[] nm = reader.readLine().split(" "); // Đọc và tách chuỗi đầu vào thành mảng các chuỗi con
        int n = Integer.parseInt(nm[0]); // Lấy giá trị n từ mảng nm
        int m = Integer.parseInt(nm[1]); // Lấy giá trị m từ mảng nm
        int[] parent = new int[n + 1]; // Mảng parent dùng để lưu trữ thông tin về các đỉnh cha trong một cây
        int[] size = new int[n + 1]; // Mảng size dùng để lưu trữ kích thước của từng cây
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // Khởi tạo giá trị ban đầu cho mảng parent
            size[i] = 1; // Khởi tạo giá trị ban đầu cho mảng size
        }
        int numOfQueries = reader.nextInt(); // Đọc số lượng truy vấn từ đầu vào
        // Vòng lặp xử lý từng truy vấn
        while (numOfQueries-- > 0) {
            String q = reader.readLine(); // Đọc một dòng truy vấn từ đầu vào
            StringTokenizer str = new StringTokenizer(q); // Tách chuỗi truy vấn thành một mảng các token
            String query = str.nextToken(); // Lấy token đầu tiên làm loại truy vấn
            int x = Integer.parseInt(str.nextToken()); // Lấy token thứ hai làm giá trị x
            if (query.equals("S")) { // Nếu truy vấn là S (size)
                bw.write(size[findParent(parent, x)] + "\n"); // Ghi kích thước của cây có đỉnh x
            } else {
                int y = Integer.parseInt(str.nextToken()); // Lấy token thứ ba làm giá trị y
                int parentX = findParent(parent, x); // Tìm đỉnh cha của đỉnh x
                int parentY = findParent(parent, y); // Tìm đỉnh cha của đỉnh y
                if (query.equals("A")) { // Nếu truy vấn là A (add)
                    if (parentX != parentY) { // Nếu x và y không cùng thuộc một cây
                        if (size[parentX] + size[parentY] <= m) { // Nếu tổng kích thước của hai cây nhỏ hơn hoặc bằng m
                            union(parent, size, parentX, parentY); // Hợp nhất hai cây
                        }
                    }
                } else { // Nếu truy vấn là Q (query)
                    if (parentX == parentY) { // Nếu x và y cùng thuộc một cây
                        bw.write("Yes\n");
                    } else {
                        bw.write("No\n");
                    }
                }
            }
        }
        bw.flush(); // Đẩy dữ liệu đầu ra
    }
    // Hàm hợp nhất hai cây
    private static void union(int[] parent, int[] size, int parentX, int parentY) {
        if (size[parentX] > size[parentY]) {
            parent[parentY] = parentX; // Đặt cha của cây y là x
            size[parentX] += size[parentY]; // Cập nhật kích thước của cây x
        } else {
            parent[parentX] = parentY; // Đặt cha của cây x là y
            size[parentY] += size[parentX]; // Cập nhật kích thước của cây y
        }
    }
    // Hàm tìm đỉnh cha của một đỉnh
    private static int findParent(int[] parent, int x) {
        if (parent[x] == x) { // Nếu x là đỉnh cha của nó
            return x; // Trả về x
        }
        return parent[x] = findParent(parent, parent[x]); // Tìm đỉnh cha của x
    }
}