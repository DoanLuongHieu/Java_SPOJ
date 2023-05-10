package Java_SPOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class ADAQUEUE {
    static class FastReader {
        static boolean isfile = false;
        static BufferedReader br;
        static StringTokenizer st;
        public FastReader() {
            if (isfile) {
                try {
                    br = new BufferedReader(new FileReader("test.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {br = new BufferedReader(new InputStreamReader(System.in));}
        }
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            if (st != null && !st.hasMoreTokens()) {
                return 0;
            }
            return Integer.parseInt(next());
        }
    }
    static ArrayDeque<Integer> queue;
    static boolean isReversed;
    public static void main(String[] args) {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        FastReader fr = new FastReader();
        int commands = fr.nextInt();
        queue = new ArrayDeque<>();

        for (int i = 0; i < commands; i++) {
            resolve(fr.next(), fr.nextInt(), bw);
        }
    }

    static void resolve(String command, int number, BufferedWriter bw) {
        switch (command) {
            case "back":
                back(bw);
                break;

            case "front":
                front(bw);
                break;

            case "reverse":
                reverse();
                break;

            case "push_back":
                push_back(number);
                break;

            case "toFront":
                toFront(number);
                break;

            default:
                System.out.println("bad cmd lmao");
                break;
        }
    }

    static void toFront(int num) {
        if (isReversed) {
            queue.addLast(num);
        } else {
            queue.addFirst(num);
        }
    }

    static void push_back(int num) {
        if (isReversed) {
            queue.addFirst(num);
        } else {
            queue.addLast(num);
        }
    }

    static void back(BufferedWriter bw) {
        try {
            if (queue.isEmpty()) {
                bw.write("No job for Ada?");
            } else if (isReversed) {
                bw.write((queue.removeFirst().toString()));
            } else
                bw.write(queue.removeLast().toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void front(BufferedWriter bw) {
        try {
            if (queue.isEmpty()) {
                bw.write("No job for Ada?");
            } else if (isReversed) {
                bw.write((queue.removeLast().toString()));
            } else
                bw.write(queue.removeFirst().toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void reverse() {
        isReversed = !isReversed;
    }
}
