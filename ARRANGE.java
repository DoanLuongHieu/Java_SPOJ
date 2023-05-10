package Java_SPOJ;

import java.io.*;
import java.util.Arrays;

class ARRANGE {
    // Lớp Reader dùng để đọc đầu vào từ một file hoặc từ bàn phím
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

        // Đọc một dòng từ đầu vào
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

        // Đọc một số nguyên từ đầu vào
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

        // Đọc các byte từ đầu vào và đưa vào buffer
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        // Đọc một byte từ buffer và đưa vào đầu ra
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        // Đóng đầu vào
        public void close() throws IOException {
            din.close();
        }
    }

    // Hàm main sẽ đọc đầu vào và gọi hàm arrangeAmps để sắp xếp các bộ khuếch đại
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

    // Hàm arrangeAmps sẽ sắp xếp các bộ khuếch đại theo một cách nhất định
    static void arrangeAmps(int[] amps, BufferedWriter bw) {
        // Lấy các bộ khuếch đại có giá trị quan trọng (khác 1)
        int[] sig_amps = getSignificantAmps(amps);
        int ones = amps.length - sig_amps.length;

        // Nếu có đúng 2 bộ khuếch đại quan trọng và tổng của chúng là 5 thì sắp xếp lại thành 2 và 3
        if (sig_amps.length == 2 && (sig_amps[0] + sig_amps[1] == 5)) {
            sig_amps[0] = 2;
            sig_amps[1] = 3;
        } else {
            // Ngược lại, sắp xếp các bộ khuếch đại quan trọng theo thứ tự giảm dần
            sorting(sig_amps, 0, sig_amps.length - 1);
        }

        // In ra các bộ khuếch đại có giá trị bằng 1
        for (int i = 0; i < ones; i++) {
            try {
                bw.write("1 ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // In ra các bộ khuếch đại quan trọng đã sắp xếp
        printArr(sig_amps, bw);
    }

    // Hàm getSignificantAmps lấy các bộ khuếch đại có giá trị quan trọng (khác 1)
    static int[] getSignificantAmps(int[] amps) {
        int[] result = new int[amps.length];
        int result_idx = 0;
        for (int amp : amps) {
            if (amp != 1) {
                result[result_idx] = amp;
                result_idx++;
            }
        }
        // Trả về một mảng chứa các bộ khuếch đại quan trọng
        return Arrays.copyOfRange(result, 0, result_idx);
    }

    // Hàm printArr in ra một mảng
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

    // Hàm partition dùng trong thuật toán quicksort để phân chia mảng thành 2 phần
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

    // Hàm sorting sắp xếp mảng bằng thuật toán quicksort
    static void sorting(int[] arr, int left, int right) {
        if(left < right)
        {
            int q = partition(arr, left, right);
            sorting(arr, left, q);
            sorting(arr, q + 1, right);
        }
    }
}