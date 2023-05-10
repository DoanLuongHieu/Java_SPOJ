package Java_SPOJ;

import java.util.*;
import java.io.*;

class CONGRAPH {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader(){
            din=new DataInputStream(System.in);
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }
        public Reader(String file_name) throws IOException{
            din=new DataInputStream(new FileInputStream(file_name));
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }
        public String readLine() throws IOException{ // Hàm readLine(), đọc dòng từ input
            byte[] buf=new byte[65536]; // line length
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
        public long nextLong() throws IOException{ // Hàm đọc một số nguyên lớn, trả về kiểu long
            long ret=0;byte c=read();
            while(c<=' ')c=read();
            boolean neg=(c=='-');
            if(neg)c=read();
            do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
            if(neg)return -ret;
            return ret;
        }
        public double nextDouble() throws IOException{ // Hàm đọc một số thực, trả về kiểu double
            double ret=0,div=1;byte c=read();
            while(c<=' ')c=read();
            boolean neg=(c=='-');
            if(neg)c = read();
            do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
            if(c=='.')while((c=read())>='0'&&c<='9')
                ret+=(c-'0')/(div*=10);
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
            if(din==null) return;
            din.close();
        }
    }
    // Hàm main để đọc vào đồ thị và tính toán số thành phần liên thông.
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader(); // Khởi tạo đối tượng Reader để đọc dữ liệu đầu vào.
        int n = sc.nextInt(); // Đọc vào số lượng đỉnh của đồ thị.
        int m = sc.nextInt(); // Đọc vào số lượng cạnh của đồ thị.
        int[] parent = new int[n]; // Mảng parent chứa thông tin về cha của mỗi đỉnh trong cây
        int[] rank = new int[n]; // Mảng rank chứa thông tin về độ sâu của mỗi đỉnh trong cây.

        // Khởi tạo mỗi đỉnh là một cây riêng biệt.
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int edges = 0;
        // Đọc vào mỗi cạnh của đồ thị và thực hiện nối hai đỉnh của cạnh lại với nhau.
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt(); // Đọc vào đỉnh đầu tiên của cạnh.
            int b = sc.nextInt(); // Đọc vào đỉnh thứ hai của cạnh.

            // Tìm cha của hai đỉnh đó trong cây.
            int p1 = findParent(a, parent);
            int p2 = findParent(b, parent);

            // Nếu cha của hai đỉnh đó không giống nhau thì nối chúng lại với nhau.
            if(p1 != p2) {
                union(p1, p2, parent, rank);
            } else {
                edges++;
            }
        }

        // Đếm số thành phần liên thông của đồ thị.
        int components = 0;
        for(int i = 0; i < n; i++) {
            if(parent[i] == i) {
                components++;
            }
        }

        // In ra kết quả.
        System.out.println(components - 1);
    }

    // Hàm tìm cha của một đỉnh trong cây.
    public static int findParent(int a, int[] parent) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = findParent(parent[a], parent);
    }

    // Hàm nối hai cây lại với nhau.
    public static void union(int a, int b, int[] parent, int[] rank) {
        if(rank[a] < rank[b]) {
            parent[a] = b;
        } else if(rank[a] > rank[b]) {
            parent[b] = a;
        } else {
            parent[a] = b;
            rank[b]++;
        }
    }
}
