package Java_SPOJ;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
class BUGLIFE {
    Parser parserIn = new Parser(System.in);
    PrintWriter writer = new PrintWriter(System.out);
    private static final String SCENARIO_NUMBER = "Scenario #%d:\n";
    private static final String SUSPICIOUS = "Suspicious bugs found!";
    private static final String NO_SUSPICIOUS = "No suspicious bugs found!";

    public static void main(String[] args) throws IOException {
        new BUGLIFE().run();
    }
    void run() throws IOException {
        int testcases = parserIn.nextInt();
        for (int i = 1; i <= testcases; i++)
            solve(i);
        writer.flush();
    }
    private static int alphaBugs, betaBugs;
    private static boolean[][] graph = new boolean[2001][2001];
    private static byte[] sexType = new byte[2001];
    void solve(int testcase) throws IOException {
        constructGraph();
        writer.printf(SCENARIO_NUMBER, testcase);
        boolean status = true;
        for (int i = 1; status && i <= alphaBugs; i++) {
            if (sexType[i] == 0) {
                sexType[i] = 1;
                status = dfs(i);
            }
        }
        if (status)
            writer.println(NO_SUSPICIOUS);
        else
            writer.println(SUSPICIOUS);
    }
    void constructGraph() throws IOException {
        alphaBugs = parserIn.nextInt();
        betaBugs = parserIn.nextInt();
        for (int i = 1; i <= alphaBugs; i++)
            Arrays.fill(graph[i], 1, alphaBugs + 1, false);
        Arrays.fill(sexType, 1, alphaBugs + 1, (byte) 0);
        int u, v;
        for (int j = 0; j < betaBugs; j++) {
            u = parserIn.nextInt();
            v = parserIn.nextInt();
            graph[u][v] = graph[v][u] = true;
        }
    }
    boolean dfs(int u) {
        for (int v = 1; v <= alphaBugs; v++) {
            if (!graph[u][v])
                continue;
            if (sexType[v] == 0) {
                sexType[v] = (byte) -sexType[u];
                if (!dfs(v))
                    return false;
            } else if (sexType[v] + sexType[u] != 0) {
                return false;
            }
        }
        return true;
    }
    static class Parser {
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
        public int nextInt() throws IOException {
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
        public String readLine() throws IOException {
            StringBuilder line = new StringBuilder();
            char c;
            while ((c = (char) (read())) != '\n') {
                line.append(c);
            }
            return line.toString();
        }
        public byte read() throws IOException {
            if (bufPosition == numOfBytesRead)
                fillBuffer();
            return byteBuffer[bufPosition++];
        }
        private void fillBuffer() throws IOException {
            numOfBytesRead = stream.read(byteBuffer, bufPosition = 0, BUFFER_SIZE);
            if (numOfBytesRead == -1)
                byteBuffer[0] = -1;
        }
    }
}