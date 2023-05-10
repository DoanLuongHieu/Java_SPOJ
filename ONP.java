package Java_SPOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class ONP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //đọc input từ bàn phím
        String s = br.readLine(); //đọc số lượng biểu thức cần xử lý
        int T = Integer.parseInt(s);
        while (T > 0) { //vòng lặp để xử lý các biểu thức theo yêu cầu
            T--;
            s = br.readLine(); //đọc biểu thức từ bàn phím
            Stack<Character> stack = new Stack<Character>(); //tạo một stack để lưu trữ toán tử
            StringBuilder op = new StringBuilder(); //tạo một StringBuilder để lưu trữ các phần tử trong biểu thức kết quả
            for (int i = 0; i < s.length(); i++) { //duyệt từng phần tử trong biểu thức
                char current = s.charAt(i);
                if (current >= 'a' && current <= 'z') { //nếu là chữ cái thì thêm vào biểu thức kết quả
                    op.append(current);
                    continue;
                } else if (current == '(') { //nếu là dấu mở ngoặc thì đẩy vào stack
                    stack.push(current);
                    continue;
                } else if (current == ')') { //nếu là dấu đóng ngoặc thì lấy toán tử trong stack và thêm vào biểu thức kết quả cho đến khi gặp dấu mở ngoặc
                    while (true) {
                        char temp = stack.pop();
                        if (temp == '(') {
                            break;
                        } else {
                            op.append(temp);
                        }
                    }
                    continue;
                } else { //nếu là toán tử
                    while (true) {
                        if (stack.isEmpty()) { //nếu stack rỗng thì đẩy toán tử vào stack
                            stack.push(current);
                            break;
                        }
                        if (!currentPrecedenceGreaterThanTop(current, stack.peek())) { //nếu độ ưu tiên của toán tử hiện tại nhỏ hơn hoặc bằng độ ưu tiên của toán tử trên cùng của stack thì lấy toán tử đó ra và thêm vào biểu thức kết quả
                            op.append(stack.pop());
                        } else { //ngược lại, đẩy toán tử vào stack
                            stack.push(current);
                            break;
                        }
                    }
                    continue;
                }
            }
            while (!stack.empty()) { //sau khi duyệt hết biểu thức, lấy các toán tử còn lại trong stack và thêm vào biểu thức kết quả
                op.append(stack.pop());
            }
            System.out.println(op.toString()); //in ra biểu thức kết quả
        }
    }

    private static boolean currentPrecedenceGreaterThanTop(char current, Character peek) { //hàm kiểm tra độ ưu tiên của toán tử hiện tại có lớn hơn toán tử trên cùng của stack hay không
        String prec = "(+-*/^)";
        int cur = prec.indexOf(current);
        int top = prec.indexOf(peek);
        return (cur > top);
    }
}