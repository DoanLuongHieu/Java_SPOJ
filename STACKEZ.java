package Java_SPOJ;
import java.lang.StringBuilder;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;
class STACKEZ{
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
    public static void main(String[] args) throws IOException{
        Reader r = new Reader();
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        int no_of_operations = r.nextInt();
        int t;
        int n;
        while (no_of_operations-- > 0){
            t = r.nextInt();
            if (t == 1){
                n = r.nextInt();
                st.push(n);
            } else if (t == 2){
                if (!st.empty()){
                    st.pop();
                }
            } else {
                if (st.empty()){
                    sb.append("Empty!\n");
                } else {
                    sb.append(st.peek());
                    sb.append('\n');
                }
            }
        }
        System.out.print(sb.toString());
    }
}