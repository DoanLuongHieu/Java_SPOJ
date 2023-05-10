package Java_SPOJ;
import java.io.*;
import java.util.*;
class SOCNETC {
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
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public String readLine() throws IOException {
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
        public int nextInt() throws IOException {
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
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
        public void close() throws IOException {
            din.close();
        }
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] parent = new int[n + 1];
        int[] size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int numOfQueries = reader.nextInt();
        while (numOfQueries-- > 0) {
            String q = reader.readLine();
            StringTokenizer str = new StringTokenizer(q);
            String query = str.nextToken();
            int x = Integer.parseInt(str.nextToken());
            if (query.equals("S")) {
                bw.write(size[findParent(parent, x)] + "\n");
            } else {
                int y = Integer.parseInt(str.nextToken());
                int parentX = findParent(parent, x);
                int parentY = findParent(parent, y);
                if (query.equals("A")) {
                    if (parentX != parentY) {
                        if (size[parentX] + size[parentY] <= m) {
                            union(parent, size, parentX, parentY);
                        }
                    }
                } else {
                    if (parentX == parentY) {
                        bw.write("Yes\n");
                    } else {
                        bw.write("No\n");
                    }
                }
            }
        }
        bw.flush();
    }
    private static void union(int[] parent, int[] size, int parentX, int parentY) {
        if (size[parentX] > size[parentY]) {
            parent[parentY] = parentX;
            size[parentX] += size[parentY];
        } else {
            parent[parentX] = parentY;
            size[parentY] += size[parentX];
        }
    }
    private static int findParent(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent, parent[x]);
    }
}