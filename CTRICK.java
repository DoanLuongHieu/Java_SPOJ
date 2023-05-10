package Java_SPOJ;
import java.io.*;
import java.util.*;

class CTRICK {
    InputStream obj;
    PrintWriter out;
    String check = "";

    // Hàm giải quyết bài toán
    void solution() {
        int t = inti(); // số lượng bộ test
        for (int tt = 0; tt < t; tt++) {
            int n = inti(); // số lượng phần tử của mảng
            int arr[] = new int[n+1]; // mảng kết quả
            int[] bit = createTree(n); // tạo cây Fenwick (Binary Indexed Tree) với n phần tử
            int M = n, L = 1;

            // Thực hiện các phép biến đổi theo yêu cầu của bài toán và lưu kết quả vào mảng arr
            for (int i = 1; i <= n; i++) {
                L = (L + i) % M;
                L = (L == 0) ? M : L;
                M--;
                int u = binarySearch(bit,L);
                arr[u] = i;
                bit = updateBinaryIndexedTree(bit, -1, u);
            }
            // In kết quả của bộ test hiện tại
            for(int i=1;i<=n;i++){
                out.print(arr[i]+" ");
            }
            out.println();
        }
    }

    // Hàm tìm kiếm nhị phân trong cây Fenwick để lấy vị trí thích hợp cho phần tử tiếp theo
    int binarySearch(int[] bit,int L) {
        int low = 1, high = bit.length - 1;
        int mid;
        while (high >= low) {
            mid = (low + high) / 2;
            if (getSum(bit, mid) >=L) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Hàm lấy vị trí của phần tử cha của một phần tử trong cây Fenwick
    int getParent(int index) {
        return index - (index & -index);
    }

    // Hàm lấy vị trí của phần tử con của một phần tử trong cây Fenwick
    int getNext(int index) {
        return index + (index & -index);
    }

    // Hàm cập nhật giá trị của một phần tử trong cây Fenwick
    int[] updateBinaryIndexedTree(int bit[], int value, int index) {
        while (index < bit.length) {
            bit[index] += value;
            index = getNext(index);
        }
        return bit;
    }
    // Hàm tính tổng giá trị của các phần tử trong đoạn từ 1 đến index trong cây Fenwick
    long getSum(int bit[], int index) {
        long sum = 0;
        while (index > 0) {
            sum += bit[index];
            index = getParent(index);
        }
        return sum;
    }

    // Hàm tạo cây Fenwick với n phần tử và khởi tạo giá trị ban đầu cho mỗi phần tử là 1
    int[] createTree(int n) {
        int bit[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bit = updateBinaryIndexedTree(bit, 1, i);
        }
        return bit;
    }

    // Hàm main, đọc dữ liệu từ input và gọi hàm giải quyết bài toán
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CTRICK().ace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    // Hàm ace, khởi tạo các biến đầu vào, gọi hàm giải quyết bài toán và in kết quả
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
        out.flush();
        out.close();
    }

    // Các hàm đọc dữ liệu đầu vào từ input
    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) {
            throw new InputMismatchException();
        }
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) {
            return -1;
        }
        return inbuffer[ptrbuffer++];
    }
    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }
    String stri() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b)))
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }
    int inti() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    int[][] ar2D(int n, int m) {
        int ark[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ark[i][j] = inti();
            }
        }
        return ark;
    }
    long loni() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    float fl() {
        return Float.parseFloat(stri());
    }
    double dou() {
        return Double.parseDouble(stri());
    }
    char chi() {
        return (char) skip();
    }
    int[] arri(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inti();
        }
        return a;
    }
    long[] arrl(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = loni();
        }
        return a;
    }
    String[] stra(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = stri();
        }
        return a;
    }
    private static void pa(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
    // Hàm pow, tính a^n mod m
    public static long pow(long a, long n, long mod) {
        long ret = 1;
        int x = 63 - Long.numberOfLeadingZeros(n);
        for (; x >= 0; x--) {
            ret = ret * ret % mod;
            if (n << 63 - x < 0) {
                ret = ret * a % mod;
            }
        }
        return ret;
    }
    // Hàm tính ước số chung lớn nhất của 2 số a và b
    int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
    // Hàm tính bội số chung nhỏ nhất của 2 số a và b
    long lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}