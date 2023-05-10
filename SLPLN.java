package Java_SPOJ;
import java.io.*; //import thư viện java.io để sử dụng các lớp xử lý đọc/ghi dữ liệu vào/ra.

class SLPLN { //định nghĩa class SLPLN

    static class Reader { //định nghĩa lớp con Reader để đọc dữ liệu đầu vào từ bàn phím
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() { //constructor của lớp Reader
            din = new DataInputStream(System.in); //khởi tạo đối tượng DataInputStream với input stream là System.in (bàn phím)
            buffer = new byte[BUFFER_SIZE]; //khởi tạo bộ đệm buffer với kích thước là 2^16 byte (64KB)
            bufferPointer = bytesRead = 0; //khởi tạo vị trí con trỏ và số byte đã đọc được ban đầu bằng 0
        }

        public String readLine() throws IOException { //phương thức readLine để đọc 1 dòng dữ liệu từ bàn phím
            byte[] buf = new byte[64]; //khởi tạo một mảng byte với độ dài tối đa là 64 byte
            int cnt = 0, c;
            while ((c = read()) != -1) { //đọc từng ký tự từ bàn phím, đến khi gặp ký tự kết thúc dòng (-1) thì thoát vòng lặp
                if (c == ' ') break; //nếu gặp ký tự trắng thì thoát vòng lặp
                buf[cnt++] = (byte) c; //lưu ký tự vào mảng buf
            }
            return new String(buf, 0, cnt); //trả về chuỗi String được tạo từ mảng buf từ vị trí 0 đến cnt
        }

        public int nextInt() throws IOException { //phương thức nextInt để đọc 1 số nguyên từ bàn phím
            int ret = 0;
            byte c = read(); //đọc ký tự đầu tiên từ bàn phím
            while (c <= ' ') c = read(); //bỏ qua các ký tự trắng, đọc tiếp cho đến khi gặp ký tự không phải khoảng trắng
            boolean neg = (c == '-'); //kiểm tra xem số có âm hay không
            if (neg) c = read(); //nếu có dấu trừ thì đọc ký tự tiếp theo
            do {
                ret = ret * 10 + c - '0'; //tính giá trị số nguyên từ
            } while ((c = read()) >= '0' && c <= '9'); //đọc các ký tự tiếp theo cho đến khi gặp ký tự không phải số
            if (neg) return -ret; //nếu số âm thì trả về giá trị âm
            return ret; //nếu số dương thì trả về giá trị dương
        }

        private void fillBuffer() throws IOException { //phương thức fillBuffer để đọc dữ liệu từ bàn phím vào bộ đệm buffer
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); //đọc dữ liệu từ bàn phím vào bộ đệm buffer
            if (bytesRead == -1) buffer[0] = -1; //nếu không đọc được dữ liệu thì gán giá trị -1 cho buffer[0]
        }

        private byte read() throws IOException { //phương thức read để đọc 1 byte từ bàn phím
            if (bufferPointer == bytesRead) fillBuffer(); //nếu đã đọc hết bộ đệm buffer thì đọc thêm dữ liệu từ bàn phím vào bộ đệm
            return buffer[bufferPointer++]; //trả về byte đọc được và tăng con trỏ bộ đệm lên 1
        }

        public void close() throws IOException { //phương thức close để đóng luồng đọc dữ liệu
            din.close(); //đóng đối tượng DataInputStream
        }
    }

    public static void main(String[] args) throws IOException { //hàm main để chạy chương trình
        Reader reader = new Reader(); //khởi tạo đối tượng reader để đọc dữ liệu từ bàn phím
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); //khởi tạo đối tượng BufferedWriter để ghi dữ liệu ra màn hình
        StringBuilder s = new StringBuilder(); //khởi tạo đối tượng StringBuilder để lưu kết quả tìm được
        int t = reader.nextInt(); //đọc số lượng trường hợp cần xử lý từ bàn phím
        int[] n = new int[t]; //khởi tạo mảng n để lưu các giá trị n cần tìm số lớn nhất khi lập phương
        for (int i = 0; i < t; i++) { //vòng lặp để đọc tất cả các giá trị n cần tìm
            n[i] = reader.nextInt(); //đọc giá trị n từ bàn phím và lưu vào mảng n
        }
        for (int i = 0; i < t; i++) { //vòng lặp để tìm số lớn nhất mà khi lập phương nó nhỏ hơn hoặc bằng giá trị n tương ứng
            s.append(largestCube(n[i])).append("\n"); //tìm số lớn nhất và lưu kết quả vào đối tượng StringBuilder s
        }
        bw.write(s.toString()); //ghi kết quả vào đối tượng BufferedWriter bw
        bw.flush(); //đẩy các dữ liệu chưa được ghi ra màn hình
        bw.close(); //đóng đối tượng BufferedWriter
    }

    static int largestCube(int n) { //phương thức largestCube để tìm số lớn nhất mà khi lập phương nó nhỏ hơn hoặc bằng giá trị n được cung cấp
        int i = 1; //khởi tạo biến i bằng 1
        while (i * i * i <= n) { //vòng lặp để tìm số lớn nhất
            i++; //tăng biến i lên 1
        }
        return i - 1; //trả về giá trị lớn nhất tìm được trừ đi 1
    }
}
