package Java_SPOJ;
// Import các thư viện cần thiết
import java.io.*;
import java.util.*;
class STOPCITY {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader(){
            din=new DataInputStream(System.in);
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }
        public String readLine() throws IOException{ // Hàm readLine(), đọc dòng từ input
            byte[] buf=new byte[67108864]; // line length
            int cnt=0,c;
            while((c=read())!=-1){
                if(c=='\n')break;
                buf[cnt++]=(byte)c;
            }
            return new String(buf,0,cnt);
        }
        public int nextInt() throws IOException{ // Hàm nextInt(), đọc số nguyên từ input
            int ret=0;byte c=read();
            while(c<=' ')c=read();
            boolean neg=(c=='-');
            if(neg)c=read();
            do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
            if(neg)return -ret;
            return ret;
        }
        private void fillBuffer() throws IOException{ // Hàm fillBuffer(), đọc dữ liệu vào buffer
            bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
            if(bytesRead==-1)buffer[0]=-1;
        }
        private byte read() throws IOException{ // Hàm read(), đọc byte từ input
            if(bufferPointer==bytesRead)fillBuffer();
            return buffer[bufferPointer++];
        }
        public void close() throws IOException{ // Hàm đóng đầu vào
            din.close();
        }
    }
    public static void main(String[] args) throws IOException { // Hàm main
        Reader in = new Reader(); // Tạo đối tượng in để đọc dữ liệu
        // Tạo đối tượng pw để ghi dữ liệu
        PrintWriter pw = new PrintWriter(System.out);
        // Đọc số lượng đỉnh của đồ thị
        int V = in.nextInt();
        // Tạo mảng danh sách các đỉnh kề
        ArrayList<Integer>[] v = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            v[i] = new ArrayList<>();
        }
        // Tạo mảng kraj để lưu trạng thái của đỉnh
        boolean[] kraj = new boolean[V + 1];
        // Tạo danh sách các đỉnh kết thúc
        ArrayList<Integer> vk = new ArrayList<>();
        // Tạo mảng pot để lưu trạng thái của đỉnh
        int[] pot = new int[V + 1];
        // Tạo mảng vis1 để lưu trạng thái của đỉnh
        int[] vis1 = new int[V + 1];
        // Tạo mảng vis2 để lưu trạng thái của đỉnh
        int[] vis2 = new int[V + 1];
        // Tạo Stack s để lưu các đỉnh
        Stack<Integer> s = new Stack<>();
        // Khai báo biến a, b
        int a, b;
        // Đọc dữ liệu cho đến khi nhập a = -1 và b = -1
        while (true) {
            a = in.nextInt();
            b = in.nextInt();
            if (a == -1 && b == -1)
                break;
            if (a == b)
                continue;
            v[a].add(b);
            v[b].add(a);
        }
        // Đọc 2 đỉnh x, y
        int x, y;
        x = in.nextInt();
        y = in.nextInt();
        // Gán trạng thái của đỉnh y = 1
        vis1[y] = 1;
        // Thêm đỉnh x vào Stack s
        s.push(x);
        // Khai báo biến broj
        int broj = 0;
        // Thực hiện vòng lặp cho đến khi Stack s rỗng
        while (!s.empty()) {
            broj++;
            // Lấy đỉnh g ra khỏi Stack s
            int g = s.pop();
            // Gọi hàm bfs để tìm đỉnh kết thúc
            bfs(g, 1, broj, v, vis1, vis2, pot, s);
        }
        // Duyệt mảng vis1 để tìm đỉnh kết thúc
        for (int i = 0; i < V; i++) {
            if (vis1[i]==1)
                vk.add(i);
        }
        // Duyệt mảng vis1 để tìm đỉnh kết thúc
        Collections.sort(vk);
        // Duyệt danh sách đỉnh kết thúc và ghi ra màn hình
        for (int i = 0; i < vk.size(); i++) {
            if (i > 0)
                pw.print(" ");
            pw.print(vk.get(i));
        }
        // Xuống dòng và ghi dữ liệu ra màn hình
        pw.println();
        pw.flush();
    }
    // Hàm bfs để tìm đỉnh kết thúc
    static void bfs(int x, int b, int zaf, ArrayList<Integer>[] v, int[] vis1, int[] vis2, int[] pot, Stack<Integer> s) {
        // Tạo Queue qi để lưu các đỉnh
        Queue<Integer> qi = new LinkedList<>();
        // Thêm đỉnh x vào Queue qi
        qi.add(x);
        // Gán pot[x] = -1
        pot[x] = -1;
        // Gán vis2[x] = zaf
        vis2[x] = zaf;
        // Tạo danh sách pat để lưu các đỉnh
        ArrayList<Integer> pat = new ArrayList<>();
        // Thực hiện vòng lặp cho đến khi Queue qi rỗng
        while (!qi.isEmpty()) {
            // Lấy đỉnh topi ra khỏi Queue qi
            int topi = qi.poll();
            // Nếu vis1[topi] = b và topi != x
            if (vis1[topi] == b && topi != x) {
                // Gán y = topi
                int y = topi;
                // Thực hiện vòng lặp cho đến khi y = x
                while (y != x) {
                    // Thêm đỉnh pot[y] vào danh sách pat
                    pat.add(pot[y]);
                    // Gán vis1[pot[y]] = b
                    vis1[pot[y]] = b;
                    // Gán y = pot[y]
                    y = pot[y];
                }
                // Kết thúc vòng lặp
                break;
            }
            // Duyệt danh sách các đỉnh kề của đỉnh topi
            for (int i = 0; i < v[topi].size(); i++) {
                // Nếu vis2[v[topi].get(i)] != zaf và (vis1[v[topi].get(i)] != b hoặc vis1[topi] != b)
                if (vis2[v[topi].get(i)] != zaf && (vis1[v[topi].get(i)] != b || vis1[topi] != b)) {
                    // Gán vis2[v[topi].get(i)] = zaf
                    vis2[v[topi].get(i)] = zaf;
                    // Thêm đỉnh v[topi].get(i) vào Queue qi
                    qi.add(v[topi].get(i));
                    // Gán pot[v[topi].get(i)] = topi
                    pot[v[topi].get(i)] = topi;
                }
            }
        }
        for (Integer integer : pat) { // Duyệt danh sách pat
            s.push(integer); // Thêm các đỉnh vào Stack s
        }
    }
}