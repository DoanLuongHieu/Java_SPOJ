package Java_SPOJ;
import java.util.Arrays;
import java.util.Scanner;
class ACMNEW1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int SchoolsCount = sc.nextInt();
        int[][] WeightList = new int[SchoolsCount][];
        int[] MaxWeight = new int[SchoolsCount];

        for (int i = 0; i < WeightList.length; i++) {
            int StudentsCount = sc.nextInt();
            WeightList[i] = new int[StudentsCount];
            for (int j = 0; j < WeightList[i].length; j++) {
                WeightList[i][j] = sc.nextInt();
            }
            MaxWeight[i] = sc.nextInt();
        }

        for (int i = 0; i < MaxWeight.length; i++) {
            System.out.println(maxTeamWeight(WeightList[i], MaxWeight[i]));
        }
    }

    private static int maxTeamWeight(int[] weight, int limit) {
        Arrays.sort(weight);
        reverseArr(weight);

        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight.length; j++) {
                for (int k = j + 1; k < weight.length; k++) {
                    int curr_weight = weight[i] + weight[j] + weight[k];
                    if (curr_weight <= limit) {
                        return curr_weight;
                    }
                }
            }
        }

        return 0;
    }

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