package Java_SPOJ;
import java.lang.StringBuilder;
import java.io.DataInputStream;
import java.io.IOException;

// Định nghĩa lớp QUEUEEZ
class QUEUEEZ {

    // Lớp con Reader để đọc dữ liệu đầu vào
    static final class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream dis;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        // Khởi tạo Reader với dữ liệu đầu vào từ System.in
        public Reader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        // Bỏ qua n dòng ký tự '\n' trong dữ liệu đầu vào
        public void omitLines(int n) throws IOException {
            while (n > 0) {
                if (read() == '\n')
                    n--;
            }
        }

        // Đọc một số nguyên từ dữ liệu đầu vào
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        // Đọc một số nguyên dương dài từ dữ liệu đầu vào
        public long nextPositiveLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            return ret;
        }

        // Đọc một số nguyên dương từ dữ liệu đầu vào
        public int nextPositiveInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            return ret;
        }

        // Đọc một từ từ dữ liệu đầu vào
        public String nextWord() throws IOException {
            byte[] buf = new byte[10];
            int cnt = 0;
            byte c = read();

            while (c <= ' ')
                c = read();

            buf[cnt++] = c;
            while ((c = read()) != -1) {
                if (c <= ' ') {
                    break;
                }

                buf[cnt++] = c;
            }
            return new String(buf, 0, cnt);
        }

        // Đọc một byte từ dữ liệu đầu vào
        public byte nextByte() throws IOException {
            byte c;
            while ((c = read()) <= ' ') ;

            return c;
        }

        // Đọc dữ liệu vào bộ đệm
        private void fillBuffer() throws IOException {
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        // Đọc một byte từ bộ đệm hoặc từ dữ liệu đầu vào
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        // Đóng DataInputStream nếu cần thiết
        public void close() throws IOException {
            if (dis == null)
                return;
            dis.close();
        }
    }

    // Hàm main
    public static void main(String[] args) throws IOException {
        Reader qt = new Reader();
        StringBuilder sb = new StringBuilder();
        final int MAX_SIZE = 1000000;
        int T = qt.nextPositiveInt();
        int front = 0, back = 0;
        byte type_of_operation;
        int[] st = new int[MAX_SIZE];

        // Thực hiện các phép toán trên Queue
        while (T-- > 0) {
            type_of_operation = qt.nextByte();

            if (type_of_operation == '1') { // Thêm một phần tử vào Queue
                st[back++] = qt.nextInt();
            } else if (type_of_operation == '2') { // Xóa phần tử đầu tiên khỏi Queue
                if (back > front) {
                    front++;
                } else {
                    back = 0;
                    front = 0;
                }
            } else { // Lấy phần tử đầu tiên khỏi Queue
                if (back > front) {
                    sb.append(st[front]);
                    sb.append('\n');
                } else {
                    sb.append("Empty!\n");
                }
            }
        }

        // In ra kết quả
        System.out.print(sb.toString());
    }
}