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
        public String readLine() throws IOException{
            byte[] buf=new byte[65536]; // line length
            int cnt=0,c;
            while((c=read())!=-1){
                if(c=='\n')break;
                buf[cnt++]=(byte)c;
            }
            return new String(buf,0,cnt);
        }
        public int nextInt() throws IOException{
            int ret=0;byte c=read();
            while(c<=' ')c=read();
            boolean neg=(c=='-');
            if(neg)c=read();
            do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
            if(neg)return -ret;
            return ret;
        }
        public long nextLong() throws IOException{
            long ret=0;byte c=read();
            while(c<=' ')c=read();
            boolean neg=(c=='-');
            if(neg)c=read();
            do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
            if(neg)return -ret;
            return ret;
        }
        public double nextDouble() throws IOException{
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
        private void fillBuffer() throws IOException{
            bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
            if(bytesRead==-1)buffer[0]=-1;
        }
        private byte read() throws IOException{
            if(bufferPointer==bytesRead)fillBuffer();
            return buffer[bufferPointer++];
        }
        public void close() throws IOException{
            if(din==null) return;
            din.close();
        }
    }
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        int edges = 0;
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p1 = findParent(a, parent);
            int p2 = findParent(b, parent);
            if(p1 != p2) {
                union(p1, p2, parent, rank);
            } else {
                edges++;
            }
        }
        int components = 0;
        for(int i = 0; i < n; i++) {
            if(parent[i] == i) {
                components++;
            }
        }
        System.out.println(components - 1);
    }
    public static int findParent(int a, int[] parent) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = findParent(parent[a], parent);
    }
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
