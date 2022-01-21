package TP3;

import java.util.Arrays;

public class Matrica {
    public static void main(String[] args) {
        try {
            SquareMatrix matrix3 = new SquareMatrix(0);
            Matrix matrix2 = new Matrix(2, 4);
            Matrix matrix1 = new Matrix(4, 2);
            matrix2.setElement(0, 0, 1);
            matrix1.setElement(0, 0, 1);

            System.out.println(matrix1.product(matrix2));


        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }


        //System.out.println(matrix3.sum(matrix2));
    }
}


