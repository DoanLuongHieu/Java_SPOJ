package Java_SPOJ;
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
        public String readLine() throws IOException{
            byte[] buf=new byte[67108864]; // line length
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
        private void fillBuffer() throws IOException{
            bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
            if(bytesRead==-1)buffer[0]=-1;
        }
        private byte read() throws IOException{
            if(bufferPointer==bytesRead)fillBuffer();
            return buffer[bufferPointer++];
        }
        public void close() throws IOException{
            din.close();
        }
    }
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter pw = new PrintWriter(System.out);
        int V = in.nextInt();
        ArrayList<Integer>[] v = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            v[i] = new ArrayList<>();
        }
        boolean[] kraj = new boolean[V + 1];
        ArrayList<Integer> vk = new ArrayList<>();
        int[] pot = new int[V + 1];
        int[] vis1 = new int[V + 1];
        int[] vis2 = new int[V + 1];
        Stack<Integer> s = new Stack<>();
        int a, b;
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
        int x, y;
        x = in.nextInt();
        y = in.nextInt();
        vis1[y] = 1;
        s.push(x);
        int broj = 0;
        while (!s.empty()) {
            broj++;
            int g = s.pop();
            bfs(g, 1, broj, v, vis1, vis2, pot, s);
        }
        for (int i = 0; i < V; i++) {
            if (vis1[i]==1)
                vk.add(i);
        }
        Collections.sort(vk);
        for (int i = 0; i < vk.size(); i++) {
            if (i > 0)
                pw.print(" ");
            pw.print(vk.get(i));
        }
        pw.println();
        pw.flush();
    }
    static void bfs(int x, int b, int zaf, ArrayList<Integer>[] v, int[] vis1, int[] vis2, int[] pot, Stack<Integer> s) {
        Queue<Integer> qi = new LinkedList<>();
        qi.add(x);
        pot[x] = -1;
        vis2[x] = zaf;
        ArrayList<Integer> pat = new ArrayList<>();
        while (!qi.isEmpty()) {
            int topi = qi.poll();
            if (vis1[topi] == b && topi != x) {
                int y = topi;
                while (y != x) {
                    pat.add(pot[y]);
                    vis1[pot[y]] = b;
                    y = pot[y];
                }
                break;
            }
            for (int i = 0; i < v[topi].size(); i++) {
                if (vis2[v[topi].get(i)] != zaf && (vis1[v[topi].get(i)] != b || vis1[topi] != b)) {
                    vis2[v[topi].get(i)] = zaf;
                    qi.add(v[topi].get(i));
                    pot[v[topi].get(i)] = topi;
                }
            }
        }
        for (Integer integer : pat) {
            s.push(integer);
        }
    }
}