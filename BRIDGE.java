package Java_SPOJ;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
class BRIDGE {
    static class Point implements Comparator<Point> {
        int x, y;

        Point() {
        }

        @Override
        public int compare(Point o1, Point o2) {
            if (o1.y == o2.y)
                return o1.x - o2.x;
            return o1.y - o2.y;
        }
    }

    public static void main(String[] args) throws IOException {
        AmitScan sc = new AmitScan();
        AmitPrint pr = new AmitPrint();
        int t = sc.si();
        while (t-- > 0) {
            int n = sc.si();
            Point[] a = new Point[n];
            for (int i = 0; i < n; i++) {
                a[i] = new Point();
                a[i].x = sc.si();
            }
            for (int i = 0; i < n; i++)
                a[i].y = sc.si();
            // Sort according to y coordinates
            Arrays.sort(a, new Point());

            // LIS on x coordinates
            int[] memo = new int[n];
            for (int i = 0; i < n; i++)
                memo[i] = 1;
            int ans = 1;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (a[j].x <= a[i].x) {
                        int temp = 1 + memo[j];
                        memo[i] = Math.max(temp, memo[i]);
                    }
                }
                ans = Math.max(ans, memo[i]);
            }
            pr.pl(ans);
        }
        pr.close();
    }

    static class AmitScan {
        private byte[] buf = new byte[131072]; // Buffer of Bytes
        private int index;
        private InputStream in;
        private int total;

        AmitScan() {
            in = System.in;
        }

        private int scan() throws IOException // Scan method used to scan buf
        {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        int si() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) // Removing starting whitespaces
                n = scan();
            int neg = 1;
            if (n == '-') // If Negative Sign encounters
            {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else
                    throw new InputMismatchException();
            }
            return neg * integer;
        }

        private boolean isWhiteSpace(int n) {
            return n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1;
        }
    }

    static class AmitPrint {
        private final BufferedWriter bw;

        AmitPrint() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        private void p(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        void pl(Object object) throws IOException {
            p(object);
            bw.append("\n");
        }

        void close() throws IOException {
            bw.close();
        }
    }
}