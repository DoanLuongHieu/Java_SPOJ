package Java_SPOJ;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
class SNTGT {
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
    static int[] factorial = {0, 1, 2, 6, 24, 120, 720, 5040, 40320};

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder s = new StringBuilder();
        int TestNumber = reader.nextInt();
        int [] TestCases = new int [TestNumber];

        for (int i = 0; i < TestCases.length; i++) {
            TestCases[i] = reader.nextInt();
        }

        for (int testCase : TestCases) {
            checkFactorialPrime(testCase);
        }
        bw.write(s.toString());
        bw.flush();
        bw.close();
    }

    private static void checkFactorialPrime(int n) {
        if (isFactorialPrime(n)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean isFactorialPrime(int n) {
        return isPrime(n) && isNearFactorial(n);
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        } else if (n == 2) {
            return true;
        } else if (n % 2 == 0) {
            return false;
        }

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNearFactorial(int n) {
        for (int j : factorial) {
            int diff = j - n;
            if (Math.abs(diff) == 1) {
                return true;
            }
            if (diff > 0) {
                return false;
            }
        }
        return false;
    }
}