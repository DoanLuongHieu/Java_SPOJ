package Java_SPOJ;
// Import thư viện java.io
import java.io.*;

// Tạo class COMBAT
class COMBAT {
    // Tạo mảng 3 chiều bit
    static int[][][] bit;
    // Tạo biến gapX, gapY, gapZ
    static int gapX, gapY, gapZ;

    // Hàm main chính
    public static void main(String[] args) throws IOException {
        // Tạo một BufferedReader để đọc dữ liệu từ bàn phím
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Tạo một StringBuilder để lưu kết quả
        StringBuilder s = new StringBuilder();
        // Đọc dòng đầu tiên và cắt thành mảng line
        String[] line = br.readLine().split(" ");
        // Lấy số lượng bộ test từ line[0]
        int numTests = Integer.parseInt(line[0]);
        // Duyệt qua từng bộ test
        while (numTests-- > 0) {
            // Đọc dòng tiếp theo và cắt thành mảng line
            line = br.readLine().split(" ");
            // Lấy giá trị tọa độ x1, y1, z1
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int z1 = Integer.parseInt(line[2]);
            // Lấy giá trị tọa độ x2, y2, z2
            int x2 = Integer.parseInt(line[3]);
            int y2 = Integer.parseInt(line[4]);
            int z2 = Integer.parseInt(line[5]);
            // Tính gapX, gapY, gapZ
            gapX = x2 - x1 + 1;
            gapY = y2 - y1 + 1;
            gapZ = z2 - z1 + 1;
            // Khởi tạo mảng bit với kích thước gapX + 1, gapY + 1, gapZ + 1
            bit = new int[gapX + 1][gapY + 1][gapZ + 1];
            // Đọc dòng tiếp theo và lấy số lượng lệnh
            int n = Integer.parseInt(br.readLine());
            // Duyệt qua từng lệnh
            while (n-- > 0) {
                // Đọc dòng tiếp theo và cắt thành mảng line
                line = br.readLine().split(" ");
                // Lấy ký tự đầu tiên của line[0]
                char q = line[0].charAt(0);
                // Lấy giá trị tọa độ x3, y3, z3 sau khi trừ đi tọa độ x1, y1, z1
                int x3 = Integer.parseInt(line[1]) - x1 + 1;
                int y3 = Integer.parseInt(line[2]) - y1 + 1;
                int z3 = Integer.parseInt(line[3]) - z1 + 1;
                // Nếu lệnh là 85 (U), gọi hàm update
                if (q == 85) update(x3, y3, z3, Integer.parseInt(line[4]));
                // Nếu lệnh là Q, gọi hàm get và thêm kết quả vào s
                else {
                    if (get(x3, y3, z3)) s.append("Friend").append("\n");
                    else s.append("Enemy").append("\n");
                }
            }
        }
        // In kết quả ra màn hình
        System.out.print(s);
    }

    // Hàm update
    static void update(int x, int y, int z, int r) {
        // Tính giá trị xa, xb, ya, yb, za, zb
        int xa = Math.max(x - r, 1);
        int xb = x + r + 1;
        int ya = Math.max(y - r, 1);
        int yb = y + r + 1;
        int za = Math.max(z - r, 1);
        int zb = z + r + 1;
        // Gọi hàm update 8 lần
        update(xb, yb, zb);
        update(xa, ya, za);
        update(xb, ya, za);
        update(xa, yb, za);
        update(xa, ya, zb);
        update(xb, yb, za);
        update(xb, ya, zb);
        update(xa, yb, zb);
    }

    // Hàm update
    static void update(int x, int y, int z) {
        // Duyệt qua mảng bit và update giá trị
        for (int i = x; i <= gapX; i += i & -i)
            for (int j = y; j <= gapY; j += j & -j) for (int k = z; k <= gapZ; k += k & -k) bit[i][j][k] ^= 1;
    }

    // Hàm get
    static boolean get(int x, int y, int z) {
        int res = 0;
        // Duyệt qua mảng bit và tính tổng
        for (int i = x; i > 0; i -= i & -i)
            for (int j = y; j > 0; j -= j & -j)
                for (int k = z; k > 0; k -= k & -k)
                    res ^= bit[i][j][k];
        // Trả về kết quả
        return res != 0;
    }
}