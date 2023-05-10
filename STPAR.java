package Java_SPOJ;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
class STPAR {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int index, i, a;
        Stack<Integer> s;
        for(;;)
        {
            int n = parseInt(f.readLine());
            if(n == 0) break;
            st = new StringTokenizer(f.readLine());
            index = 1;
            s = new Stack<>();
            for(i = 0; i < n; i++)
            {
                a = parseInt(st.nextToken());
                if(a == index) {index++; continue; }
                while(s.size() > 0 && s.peek() == index)
                { s.pop(); index++; }
                if(s.size() > 0 && s.peek() < a) break;
                s.push(a);
            }
            if(i == n) System.out.println("yes");
            else System.out.println("no");
            System.out.flush();
        }
        System.exit(0);
    }
}