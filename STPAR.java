package Java_SPOJ;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

class STPAR {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); // đọc dữ liệu nhập từ bàn phím
        StringTokenizer st; // tách các từ trong chuỗi thành các token
        int index, i, a;
        Stack<Integer> s; // khởi tạo một stack để lưu các số
        for(;;) // vòng lặp chính
        {
            int n = parseInt(f.readLine()); // đọc số nguyên n từ bàn phím
            if(n == 0) break; // nếu n = 0 thì thoát khỏi vòng lặp
            st = new StringTokenizer(f.readLine()); // tách các số trong chuỗi thành các token
            index = 1; // khởi tạo biến index = 1
            s = new Stack<>(); // khởi tạo stack rỗng
            for(i = 0; i < n; i++) // vòng lặp để duyệt các số trong chuỗi
            {
                a = parseInt(st.nextToken()); // đọc số nguyên từ chuỗi
                if(a == index) {index++; continue; } // nếu số đó bằng index thì tăng index và tiếp tục vòng lặp
                while(s.size() > 0 && s.peek() == index)
                { s.pop(); index++; } // nếu stack không rỗng và phần tử đầu tiên của stack bằng index thì lấy phần tử đó ra khỏi stack và tăng index
                if(s.size() > 0 && s.peek() < a) break; // nếu stack không rỗng và phần tử đầu tiên của stack nhỏ hơn số hiện tại thì thoát vòng lặp
                s.push(a); // đưa số hiện tại vào stack
            }
            if(i == n) System.out.println("yes"); // nếu i = n (đã duyệt hết các số) thì in ra "yes"
            else System.out.println("no"); // nếu không in ra "no"
            System.out.flush(); // xóa bộ đệm và đẩy dữ liệu xuống thiết bị ngoại vi
        }
        System.exit(0); // thoát chương trình
    }
}