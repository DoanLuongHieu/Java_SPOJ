package Java_SPOJ;
import java.util.Arrays;
import java.util.Scanner;
class ACMNEW1 {
    public static void main(String[] args) {
        // Tạo đối tượng Scanner để đọc dữ liệu từ bàn phím
        Scanner sc = new Scanner(System.in);
        int SchoolsCount = sc.nextInt();
        // Tạo một mảng 2 chiều chứa danh sách các trọng lượng của học sinh ở từng trường học
        int[][] WeightList = new int[SchoolsCount][];
        // Tạo một mảng chứa trọng lượng tối đa của đội học sinh của từng trường
        int[] MaxWeight = new int[SchoolsCount];

        // Duyệt qua danh sách các trường học và đọc dữ liệu từ bàn phím
        for (int i = 0; i < WeightList.length; i++) {
            // Đọc số lượng học sinh ở trường hiện tại
            int StudentsCount = sc.nextInt();
            // Tạo một mảng chứa danh sách trọng lượng của học sinh ở trường hiện tại
            WeightList[i] = new int[StudentsCount];
            // Đọc trọng lượng của từng học sinh và lưu vào mảng trọng lượng của trường hiện tại
            for (int j = 0; j < WeightList[i].length; j++) {
                WeightList[i][j] = sc.nextInt();
            }
            // Đọc trọng lượng tối đa của đội học sinh ở trường hiện tại và lưu vào mảng trọng lượng tối đa của từng trường
            MaxWeight[i] = sc.nextInt();
        }

        // Duyệt qua danh sách các trường học và tính toán trọng lượng lớn nhất mà mỗi đội học sinh có thể mang
        for (int i = 0; i < MaxWeight.length; i++) {
            System.out.println(maxTeamWeight(WeightList[i], MaxWeight[i]));
        }
    }
    // Phương thức tính toán trọng lượng lớn nhất mà một đội học sinh có thể mang
    private static int maxTeamWeight(int[] weight, int limit) {
        Arrays.sort(weight);
        reverseArr(weight);

        // Duyệt qua tất cả các cặp học sinh và tính toán trọng lượng của đội học sinh
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight.length; j++) {
                for (int k = j + 1; k < weight.length; k++) {
                    int curr_weight = weight[i] + weight[j] + weight[k];
                    // Nếu trọng lượng của đội học sinh nhỏ hơn hoặc bằng trọng lượng tối đa, trả về trọng lượng đó
                    if (curr_weight <= limit) {
                        return curr_weight;
                    }
                }
            }
        }

        // Nếu không có đội học sinh nào có trọng lượng nhỏ hơn hoặc bằng trọng lượng tối đa, trả về 0
        return 0;
    }

    // Phương thức đảo ngược mảng
    private static void reverseArr(int[] array)
    {
        int n = array.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = array[i];
            array[i] = array[n - i - 1];
            array[n - i - 1] = temp;
        }
    }
}