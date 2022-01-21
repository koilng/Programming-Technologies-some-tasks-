package TP5;

import TP3.MatrixException;
import TP8.UsualMatrix;

class SquareMatirx extends UsualMatrix {

    public SquareMatirx(int i) {
        super(i, i);

        if (i < 0) {
            throw new MatrixException("SquareMatrix i <= 0");
        }

        for (i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
        }
    }

    public UsualMatrix sum(UsualMatrix matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {

                matrix.setElement(i, j, this.matrix[i][j] + matrix.getElement(i, j));
                System.out.println(matrix.getElement(i, j));
            }
        }
        return matrix;
    }
}
