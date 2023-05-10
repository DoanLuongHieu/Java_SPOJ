package Java_SPOJ;

import java.io.*;
import java.util.Arrays;
class ARRANGE {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public String readLine() throws IOException {
            byte[] buf = new byte[16]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
        public void close() throws IOException {
            din.close();
        }
    }

    public static void main(String[] args) throws IOException{
        Reader re = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_cases = re.nextInt();
        int amp_count;
        int[] test;
        for (int i = 0; i < test_cases; i++) {
            amp_count = re.nextInt();
            test = new int[amp_count];

            for (int j = 0; j < test.length; j++) {
                test[j] = re.nextInt();
            }
            arrangeAmps(test, bw);
        }
    }
    static void arrangeAmps(int[] amps, BufferedWriter bw) {
        int[] sig_amps = getSignificantAmps(amps);
        int ones = amps.length - sig_amps.length;

        if (sig_amps.length == 2 && (sig_amps[0] + sig_amps[1] == 5)) {
            sig_amps[0] = 2;
            sig_amps[1] = 3;
        } else {
            sorting(sig_amps, 0, sig_amps.length - 1);
        }

        for (int i = 0; i < ones; i++) {
            try {
                bw.write("1 ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printArr(sig_amps, bw);
    }
    static int[] getSignificantAmps(int[] amps) {
        int[] result = new int[amps.length];
        int result_idx = 0;
        for (int amp : amps) {
            if (amp != 1) {
                result[result_idx] = amp;
                result_idx++;
            }
        }
        return Arrays.copyOfRange(result, 0, result_idx);
    }
    static void printArr(int[] arr, BufferedWriter bw) {
        try {
            for (int j : arr) {
                bw.write(j + " ");
            }
            bw.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        for(int j = left + 1; j <= right; j++){
            if (arr[j] > pivot){
                i = i + 1;
                int temp = arr[i];
                arr[i]= arr[j];
                arr[j]= temp;
            }
        }
        int temp = arr[i];
        arr[i] = arr[left];
        arr[left] = temp;

        return i;

    }
    static void sorting(int[] arr, int left, int right) {
        if(left < right)
        {
            int q = partition(arr, left, right);
            sorting(arr, left, q);
            sorting(arr, q + 1, right);
        }
    }
}