package Java_SPOJ;

import java.io.*;
import java.util.*;
class RMID2 {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == ' ') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
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

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder s = new StringBuilder();
        int Test = reader.nextInt();
        for (int k = 0; k < Test; k++) {
            PriorityQueue<Integer> ql = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> qr = new PriorityQueue<>();
            while (true) {
                int n = reader.nextInt();
                if (n > 0) {
                    int m;
                    if (ql.size() > 0)
                        m = ql.peek();
                    else
                        m = 999999;
                    if (n > m)
                        qr.add(n);
                    else
                        ql.add(n);
                    if (ql.size() < qr.size()) {
                        m = qr.peek();
                        qr.poll();
                        ql.add(m);
                    } else if (ql.size() > qr.size() + 1) {
                        m = ql.peek();
                        ql.poll();
                        qr.add(m);
                    }
                } else if (n == -1) {
                    if (ql.size() > 0) {
                        int m = ql.peek();
                        s.append(m).append("\n");
                        ql.poll();
                        if (ql.size() == qr.size() - 1) {
                            m = qr.peek();
                            qr.poll();
                            ql.add(m);
                        }
                    }
                } else
                    break;
            }
        }
        bw.write(s.toString());
        bw.flush();
        bw.close();
    }
}