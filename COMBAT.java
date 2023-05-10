package Java_SPOJ;
import java.io.*;
class COMBAT {
    static int[][][] bit;
    static int gapX, gapY, gapZ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder s = new StringBuilder();
        String[] line = br.readLine().split(" ");
        int numTests = Integer.parseInt(line[0]);
        while (numTests-- > 0) {
            line = br.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int z1 = Integer.parseInt(line[2]);
            int x2 = Integer.parseInt(line[3]);
            int y2 = Integer.parseInt(line[4]);
            int z2 = Integer.parseInt(line[5]);
            gapX = x2 - x1 + 1;
            gapY = y2 - y1 + 1;
            gapZ = z2 - z1 + 1;
            bit = new int[gapX + 1][gapY + 1][gapZ + 1];
            int n = Integer.parseInt(br.readLine());
            while (n-- > 0) {
                line = br.readLine().split(" ");
                char q = line[0].charAt(0);
                int x3 = Integer.parseInt(line[1]) - x1 + 1;
                int y3 = Integer.parseInt(line[2]) - y1 + 1;
                int z3 = Integer.parseInt(line[3]) - z1 + 1;
                if (q == 85) update(x3, y3, z3, Integer.parseInt(line[4]));
                else {
                    if (get(x3, y3, z3)) s.append("Friend").append("\n");
                    else s.append("Enemy").append("\n");
                }
            }
        }
        System.out.print(s);
    }

    static void update(int x, int y, int z, int r) {
        int xa = Math.max(x - r, 1);
        int xb = x + r + 1;
        int ya = Math.max(y - r, 1);
        int yb = y + r + 1;
        int za = Math.max(z - r, 1);
        int zb = z + r + 1;
        update(xb, yb, zb);
        update(xa, ya, za);
        update(xb, ya, za);
        update(xa, yb, za);
        update(xa, ya, zb);
        update(xb, yb, za);
        update(xb, ya, zb);
        update(xa, yb, zb);
    }

    static void update(int x, int y, int z) {
        for (int i = x; i <= gapX; i += i & -i)
            for (int j = y; j <= gapY; j += j & -j) for (int k = z; k <= gapZ; k += k & -k) bit[i][j][k] ^= 1;
    }

    static boolean get(int x, int y, int z) {
        int res = 0;
        for (int i = x; i > 0; i -= i & -i)
            for (int j = y; j > 0; j -= j & -j) for (int k = z; k > 0; k -= k & -k) res ^= bit[i][j][k];
        return res != 0;
    }
}