// Đây là một lớp Java đại diện cho một danh sách dữ liệu (queue) với các chức năng thêm vào đầu, cuối, đảo ngược, lấy đầu, cuối, và giải quyết các lệnh từ một tập lệnh cho trước.
package Java_SPOJ;

// Import các thư viện cần thiết
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class ADAQUEUE { // Tạo lớp ADAQUEUE
    static class FastReader { // Tạo lớp con FastReader để đọc dữ liệu từ tập tin hoặc từ bàn phím
        static boolean isfile = false; // Mặc định không đọc từ tập tin
        static BufferedReader br; // Đối tượng để đọc dữ liệu
        static StringTokenizer st; // Đối tượng để phân tách dữ liệu
        public FastReader() { // Hàm khởi tạo FastReader
            if (isfile) { // Nếu đọc từ tập tin
                try {
                    br = new BufferedReader(new FileReader("test.txt"));
                } catch (FileNotFoundException e) { // Nếu không tìm thấy tập tin
                    e.printStackTrace();
                }
            } else { // Nếu đọc từ bàn phím
                br = new BufferedReader(new InputStreamReader(System.in));
            }
        }
        String next() { // Hàm đọc một từ trong dòng
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() { // Hàm đọc một số nguyên trong dòng
            if (st != null && !st.hasMoreTokens()) {
                return 0;
            }
            return Integer.parseInt(next());
        }
    }
    static ArrayDeque<Integer> queue; // Tạo một danh sách dữ liệu (queue) với kiểu ArrayDeque
    static boolean isReversed; // Tạo biến isReversed để lưu trạng thái đảo ngược của danh sách
    public static void main(String[] args) { // Hàm main để thực thi chương trình
        // Tạo đối tượng BufferedWriter để ghi dữ liệu ra màn hình
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Tạo đối tượng FastReader để đọc dữ liệu
        FastReader fr = new FastReader();

        // Đọc số lượng lệnh từ bàn phím hoặc tập tin
        int commands = fr.nextInt();

        // Khởi tạo danh sách dữ liệu với kiểu ArrayDeque
        queue = new ArrayDeque<>();

        // Duyệt qua từng lệnh
        for (int i = 0; i < commands; i++) {
            // Gọi hàm resolve để giải quyết lệnh
            resolve(fr.next(), fr.nextInt(), bw);
        }
    }

    static void resolve(String command, int number, BufferedWriter bw) { // Hàm resolve để giải quyết lệnh
        // Sử dụng switch-case để phân biệt các lệnh
        switch (command) {
            case "back": // Lệnh lấy phần tử cuối của danh sách
                back(bw);
                break;

            case "front": // Lệnh lấy phần tử đầu của danh sách
                front(bw);
                break;

            case "reverse": // Lệnh đảo ngược danh sách
                reverse();
                break;

            case "push_back": // Lệnh thêm phần tử vào cuối danh sách
                push_back(number);
                break;

            case "toFront": // Lệnh thêm phần tử vào đầu danh sách
                toFront(number);
                break;

            default: // Trường hợp lệnh không hợp lệ
                System.out.println("ERROR gì cũng được :v");
                break;
        }
    }

    static void toFront(int num) { // Hàm toFront để thêm phần tử vào đầu danh sách
        // Nếu danh sách đang đảo ngược, thì thêm phần tử vào cuối danh sách
        if (isReversed) {
            queue.addLast(num);
        } else { // Ngược lại, thêm phần tử vào đầu danh sách
            queue.addFirst(num);
        }
    }

    static void push_back(int num) { // Hàm push_back để thêm phần tử vào cuối danh sách
            // Nếu danh sách đang đảo ngược, thì thêm phần tử vào đầu danh sách
            if (isReversed) {
                queue.addFirst(num);
            } else { // Ngược lại, thêm phần tử vào cuối danh sách
                queue.addLast(num);
            }
        }

    static void back(BufferedWriter bw) { // Hàm back để lấy phần tử cuối của danh sách
        try {
            // Nếu danh sách trống, in ra màn hình "No job for Ada?"
            if (queue.isEmpty()) {
                bw.write("No job for Ada?");
                // Nếu danh sách đang đảo ngược, lấy phần tử đầu của danh sách
            } else if (isReversed) {
                bw.write((queue.removeFirst().toString()));
                // Ngược lại, lấy phần tử cuối của danh sách
            } else
                bw.write(queue.removeLast().toString());
            // Kết thúc dòng và gỡ bỏ dữ liệu đã ghi ra màn hình
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void front(BufferedWriter bw) { // Hàm front để lấy phần tử đầu của danh sách
        try {
            // Nếu danh sách trống, in ra màn hình "No job for Ada?"
            if (queue.isEmpty()) {
                bw.write("No job for Ada?");
                // Nếu danh sách đang đảo ngược, lấy phần tử cuối của danh sách
            } else if (isReversed) {
                bw.write((queue.removeLast().toString()));
                // Ngược lại, lấy phần tử đầu của danh sách
            } else
                bw.write(queue.removeFirst().toString());
            // Kết thúc dòng và gỡ bỏ dữ liệu đã ghi ra màn hình
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void reverse() { // Hàm reverse để đảo ngược danh sách
        // Đảo trạng thái của biến isReversed
        isReversed = !isReversed;
    }
}
