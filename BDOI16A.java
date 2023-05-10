package Java_SPOJ;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
class BDOI16A {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder s = new StringBuilder();
        int numOfTest = reader.nextInt();
        int numOfOperations;
        int count = 0;
        String x, y, code;
        final int offset = 200025;
        while (count++ < numOfTest) {
            int high = offset;
            int low = offset;
            s.append("Case ").append(count).append(":\n");
            String[] data = new String[400125];
            Map<String, Integer> position = new HashMap<>();
            numOfOperations = reader.nextInt();
            while (numOfOperations-- > 0) {
                StringTokenizer token = new StringTokenizer(reader.readLine());
                code = token.nextToken();
                switch (code) {
                    case "1":
                        x = token.nextToken();
                        y = token.nextToken();
                        if (x.equals("B")) {
                            position.put(y, high);
                            data[high] = y;
                            high++;
                        } else {
                            low--;
                            position.put(y, low);
                            data[low] = y;
                        }
                        break;
                    case "2":
                        x = token.nextToken();
                        if (x.equals("B")) high--;
                        else low++;
                        break;
                    case "3":
                        x = token.nextToken();
                        y = token.nextToken();
                        int finalY = Integer.parseInt(y);
                        if (x.equals("D")) s.append(data[low + finalY - 1]).append("\n");
                        else s.append(position.get(y) - low + 1).append("\n");
                        break;
                }
            }
        }

        out.print(s);
        out.close();
        reader.close();
    }

    static class Reader {
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
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

        public String readLine() throws IOException {
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

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }
}
