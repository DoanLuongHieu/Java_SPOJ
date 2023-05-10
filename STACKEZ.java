package Java_SPOJ;
import java.lang.StringBuilder; // import thư viện StringBuilder để xây dựng chuỗi
import java.io.DataInputStream; // import thư viện DataInputStream để đọc dữ liệu vào chương trình
import java.io.IOException; // import thư viện IOException để xử lý ngoại lệ
import java.util.Stack; // import thư viện Stack để thực hiện các thao tác trên ngăn xếp

class STACKEZ{
    static class Reader { // định nghĩa lớp Reader để đọc dữ liệu vào chương trình
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader() { // khởi tạo đối tượng Reader và buffer
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public String readLine() throws IOException { // đọc dữ liệu đầu vào từ bàn phím theo từng dòng
            byte[] buf = new byte[64]; // độ dài của mỗi dòng là 64 ký tự
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == ' ') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException { // đọc số nguyên đầu vào từ bàn phím
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
        private void fillBuffer() throws IOException { // đọc dữ liệu vào buffer
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }
        private byte read() throws IOException { // đọc từng byte từ buffer
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
        public void close() throws IOException { // đóng đối tượng Reader và giải phóng tài nguyên
            din.close();
        }
    }
    public static void main(String[] args) throws IOException{ // hàm main của chương trình
        Reader r = new Reader(); // khởi tạo đối tượng Reader
        StringBuilder sb = new StringBuilder(); // khởi tạo đối tượng StringBuilder để xây dựng chuỗi kết quả
        Stack<Integer> st = new Stack<>(); // khởi tạo đối tượng Stack để thực hiện các thao
        int no_of_operations = r.nextInt(); // đọc số lượng thao tác cần thực hiện từ bàn phím
        int t;
        int n;
        while (no_of_operations-- > 0){ // lặp qua các thao tác cần thực hiện
            t = r.nextInt(); // đọc loại thao tác cần thực hiện từ bàn phím
            if (t == 1){ // nếu là thao tác push
                n = r.nextInt(); // đọc giá trị cần đẩy vào Stack từ bàn phím
                st.push(n); // đẩy giá trị vào Stack
            } else if (t == 2){ // nếu là thao tác pop
                if (!st.empty()){ // kiểm tra xem Stack có rỗng hay không
                    st.pop(); // nếu không rỗng thì rút phần tử trên cùng ra khỏi Stack
                }
            } else { // nếu là thao tác peek
                if (st.empty()){ // kiểm tra xem Stack có rỗng hay không
                    sb.append("Empty!\n"); // nếu rỗng thì thêm chuỗi "Empty!" vào chuỗi kết quả
                } else {
                    sb.append(st.peek()); // nếu không rỗng thì truy xuất phần tử trên cùng của Stack và thêm vào chuỗi kết quả
                    sb.append('\n'); // thêm ký tự xuống dòng vào chuỗi kết quả
                }
            }
        }
        System.out.print(sb.toString()); // in ra chuỗi kết quả
    }
}
