package Java_SPOJ;
// Import thư viện Input/Output của Java
import java.io.*;

// Khai báo class AKVQLD03
class AKVQLD03 {
    static class Reader { // Khai báo class Reader để đọc input từ người dùng hoặc file
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
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

        public String readLine() throws IOException { // Hàm đọc một dòng input, trả về kiểu String
            byte[] buf = new byte[2048]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException { // Hàm đọc một số nguyên, trả về kiểu int
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public long nextLong() throws IOException { // Hàm đọc một số nguyên lớn, trả về kiểu long
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public double nextDouble() throws IOException { // Hàm đọc một số thực, trả về kiểu double
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.') while ((c = read()) >= '0' && c <= '9')
                ret += (c - '0') / (div *= 10);
            if (neg) return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException { // Hàm đọc vào buffer
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException { // Hàm đọc từ buffer
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException { // Hàm đóng đầu vào
            if (din == null) return;
            din.close();
        }
    }

    // Khai báo biến static của lớp AKVQLD03
    private static Reader reader;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] input;
    static int N;

    // Hàm cập nhật giá trị tại vị trí i trong input
    static void update(int i, int v) {
        while (i <= N) {
            input[i] += v;
            i += i & (-i);
        }
    }

    // Hàm tính tổng các giá trị trong input từ đầu đến vị trí i
    static long getSum(int i) {
        long sum = 0;
        while (i > 0) {
            sum += input[i];
            i -= i & (-i);
        }
        return sum;
    }

    // Hàm main của lớp AKVQLD03
    public static void main(String[] args) throws IOException {
        // Khởi tạo đối tượng reader để đọc input
        reader = new Reader();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Đọc giá trị N và Q từ input
        N = reader.nextInt();
        int Q = reader.nextInt();

        // Khởi tạo mảng input với kích thước N+1
        input = new int[N + 1];

        // Vòng lặp thực hiện các truy vấn và cập nhật giá trị của input
        while (Q > 0) {
            String line = reader.readLine();
            String[] words = line.split(" ");
            if (line.charAt(0) == 'a') {
                int fans = Integer.parseInt(words[1]);
                int pos = Integer.parseInt(words[2]);
                update(fans, pos);
            } else {
                int pos1 = Integer.parseInt(words[1]);
                int pos2 = Integer.parseInt(words[2]);
                System.out.println(getSum(pos2) - getSum(pos1 - 1));
            }
            Q--;
        }

        // Đóng BufferedWriter và in xuống dòng mới
        bw.write("\n");
        bw.flush();
    }
}