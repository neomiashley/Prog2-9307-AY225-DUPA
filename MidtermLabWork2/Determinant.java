package MidtermLabWork2;

public class Determinant {
    public static void main(String[] args) {
        int[][] matrix = {
            {2, -1, 3},
            {1, 4, -2},
            {3, 2, 1}
        };
        
        int det = matrix[0][0] * (matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1])
                - matrix[0][1] * (matrix[1][0]*matrix[2][2] - matrix[1][2]*matrix[2][0])
                + matrix[0][2] * (matrix[1][0]*matrix[2][1] - matrix[1][1]*matrix[2][0]);
        System.out.println(det);
    }
}
