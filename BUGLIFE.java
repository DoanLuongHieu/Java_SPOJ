package Java_SPOJ;
// Import các gói thư viện IO và Arrays của Java
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

// Khai báo lớp BUGLIFE
class BUGLIFE {
    // Khai báo đối tượng parserIn và writer của lớp Parser và PrintWriter
    Parser parserIn = new Parser(System.in);
    PrintWriter writer = new PrintWriter(System.out);
    // Khai báo và khởi tạo các hằng số SCENARIO_NUMBER, SUSPICIOUS và NO_SUSPICIOUS
    private static final String SCENARIO_NUMBER = "Scenario #%d:\n";
    private static final String SUSPICIOUS = "Suspicious bugs found!";
    private static final String NO_SUSPICIOUS = "No suspicious bugs found!";
    // Hàm main, chạy chương trình
    public static void main(String[] args) throws IOException {
        new BUGLIFE().run();
    }

    // Hàm run(), đọc dữ liệu và giải quyết bài toán
    void run() throws IOException {
        int testcases = parserIn.nextInt(); // Đọc số lượng bộ test
        for (int i = 1; i <= testcases; i++) // Duyệt qua từng bộ test
            solve(i); // Gọi hàm giải quyết bài toán cho từng bộ test
        writer.flush(); // In dữ liệu ra output
    }

    // Khai báo và khởi tạo các biến alphaBugs, betaBugs, graph và sexType
    private static int alphaBugs, betaBugs;
    private static boolean[][] graph = new boolean[2001][2001];
    private static byte[] sexType = new byte[2001];

    // Hàm solve(), giải quyết bài toán cho từng bộ test
    void solve(int testcase) throws IOException {
        constructGraph(); // Đọc dữ liệu và xây dựng đồ thị
        writer.printf(SCENARIO_NUMBER, testcase); // In ra số thứ tự bộ test
        boolean status = true;
        for (int i = 1; status && i <= alphaBugs; i++) { // Duyệt qua các đỉnh của đồ thị
            if (sexType[i] == 0) { // Nếu chưa được đánh dấu là giới tính nào
                sexType[i] = 1; // Đánh dấu giới tính là 1
                status = dfs(i); // Thực hiện DFS để kiểm tra tính liên thông của đồ thị
            }
        }
        if (status) // Nếu đồ thị là liên thông
            writer.println(NO_SUSPICIOUS); // In ra không tìm thấy bug nghi ngờ
        else // Nếu đồ thị không liên thông
            writer.println(SUSPICIOUS); // In ra tìm thấy bug nghi ngờ
    }

    // Hàm constructGraph(), xây dựng đồ thị
    void constructGraph() throws IOException {
        alphaBugs = parserIn.nextInt(); // Đọc số lượng đỉnh alphaBugs
        betaBugs = parserIn.nextInt(); // Đọc số lượng cạnh betaBugs
        for (int i = 1; i <= alphaBugs; i++)
            // Khởi tạo mảng graph và sexType
            Arrays.fill(graph[i], 1, alphaBugs + 1, false);
        Arrays.fill(sexType, 1, alphaBugs + 1, (byte) 0);
        int u, v;
        for (int j = 0; j < betaBugs; j++) { // Duyệt qua các cạnh của đồ thị
            u = parserIn.nextInt(); // Đọc đỉnh u
            v = parserIn.nextInt(); // Đọc đỉnh v
            graph[u][v] = graph[v][u] = true; // Đánh dấu cạnh (u, v) và (v, u) có kết nối
        }
    }
    // Hàm dfs(), duyệt đồ thị sử dụng thuật toán DFS
    boolean dfs(int u) {
        for (int v = 1; v <= alphaBugs; v++) { // Duyệt qua tất cả các đỉnh v của đồ thị
            if (!graph[u][v]) // Nếu không có kết nối giữa đỉnh u và v
                continue;
            if (sexType[v] == 0) { // Nếu đỉnh v chưa được đánh dấu giới tính
                sexType[v] = (byte) -sexType[u]; // Đánh dấu giới tính đối nghịch với giới tính của đỉnh u
                if (!dfs(v)) // Thực hiện DFS cho đỉnh v
                    return false; // Nếu đồ thị không liên thông, trả về false
            } else if (sexType[v] + sexType[u] != 0) { // Nếu hai đỉnh có cùng giới tính
                return false; // Trả về false
            }
        }
        return true; // Trả về true nếu đồ thị liên thông
    }
    static class Parser { // Khai báo lớp Parser, đọc dữ liệu từ input
        private final int BUFFER_SIZE = 33554432;
        private InputStream stream;
        private byte[] byteBuffer;
        private int bufPosition;
        private int numOfBytesRead;
        public Parser(InputStream in) {
            stream = in;
            byteBuffer = new byte[BUFFER_SIZE];
            bufPosition = numOfBytesRead = 0;
        }
        public int nextInt() throws IOException { // Hàm nextInt(), đọc số nguyên từ input
            int result = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            while (c >= '0' && c <= '9') {
                result = result * 10 + c - '0';
                c = read();
            }
            if (neg)
                return -result;
            return result;
        }
        public String readLine() throws IOException { // Hàm readLine(), đọc dòng từ input
            StringBuilder line = new StringBuilder();
            char c;
            while ((c = (char) (read())) != '\n') {
                line.append(c);
            }
            return line.toString();
        }
        public byte read() throws IOException { // Hàm read(), đọc byte từ input
            if (bufPosition == numOfBytesRead)
                fillBuffer();
            return byteBuffer[bufPosition++];
        }
        private void fillBuffer() throws IOException { // Hàm fillBuffer(), đọc dữ liệu vào buffer
            numOfBytesRead = stream.read(byteBuffer, bufPosition = 0, BUFFER_SIZE);
            if (numOfBytesRead == -1)
                byteBuffer[0] = -1;
        }
    }
}