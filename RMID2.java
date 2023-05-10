package Java_SPOJ;

import java.io.*;
import java.util.*;
class RMID2 {
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
                if (c == ' ') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException { // Đọc một số nguyên từ đầu vào
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

        private void fillBuffer() throws IOException { // Đọc các byte từ đầu vào và đưa vào buffer
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException { // Đọc một byte từ buffer và đưa vào đầu ra
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException { // Đóng đầu vào
            din.close();
        }
    }

    // Hàm main của class RMID2
    public static void main(String[] args) throws IOException {
        // Tạo đối tượng reader từ lớp Reader
        Reader reader = new Reader();
        // Tạo đối tượng bw dùng để ghi đầu ra
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // Tạo đối tượng s dùng để lưu kết quả
        StringBuilder s = new StringBuilder();
        // Đọc số lượng Test case
        int Test = reader.nextInt();
        // Duyệt qua từng Test case
        for (int k = 0; k < Test; k++) {
            // Tạo hai hàng đợi ưu tiên ql và qr
            PriorityQueue<Integer> ql = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> qr = new PriorityQueue<>();
            // Vòng lặp để thực hiện các thao tác
            while (true) {
                int n = reader.nextInt();
                // Nếu n > 0, thêm n vào hàng đợi
                if (n > 0) {
                    int m;
                    // Tìm giá trị lớn nhất trong ql, nếu ql rỗng thì m = 999999
                    if (ql.size() > 0)
                        m = ql.peek();
                    else
                        m = 999999;
                    // Nếu n > m, thêm n vào qr, ngược lại thêm vào ql
                    if (n > m)
                        qr.add(n);
                    else
                        ql.add(n);
                    // Nếu số lượng phần tử trong ql < số lượng phần tử trong qr, chuyển phần tử nhỏ nhất từ qr vào ql
                    if (ql.size() < qr.size()) {
                        m = qr.peek();
                        qr.poll();
                        ql.add(m);
                    }// Nếu số lượng phần tử trong ql > số lượng phần tử trong qr + 1, chuyển phần tử lớn nhất từ ql vào qr
                    else if (ql.size() > qr.size() + 1) {
                        m = ql.peek();
                        ql.poll();
                        qr.add(m);
                    }
                } // Nếu n = -1, in ra giá trị lớn nhất trong ql và loại bỏ nó khỏi ql
                else if (n == -1) {
                    if (ql.size() > 0) {
                        int m = ql.peek();
                        s.append(m).append("\n");
                        ql.poll();
                        // Nếu số lượng phần tử trong ql = số lượng phần tử trong qr - 1, chuyển phần tử nhỏ nhất từ qr vào ql
                        if (ql.size() == qr.size() - 1) {
                            m = qr.peek();
                            qr.poll();
                            ql.add(m);
                        }
                    }
                }
                // Nếu n = 0, kết thúc vòng lặp
                else
                    break;
            }
        }
        // Ghi kết quả ra đầu ra
        bw.write(s.toString());
        bw.flush();
        bw.close();
    }
}