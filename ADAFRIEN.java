package Java_SPOJ;
import java.io.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Arrays;
class ADAFRIEN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        HashMap<String, Long> map = new HashMap<>();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            map.put(name, map.getOrDefault(name, 0L) + Long.parseLong(st.nextToken()));
        }
        long[] values = new long[map.size()];
        int i = 0;
        for (long value : map.values()) {
            values[i++] = value;
        }
        Arrays.sort(values);
        long sum = 0;
        k = Math.min(k, values.length);
        for (int j = values.length - 1; j >= values.length - k; j--) {
            sum += values[j];
        }
        out.println(sum);
        out.close();
        br.close();
    }
}