package TP3;

class SquareMatrix extends Matrix {

    public SquareMatrix(int i) {
        super(i, i);

        if (i < 0) {
            throw new MatrixException("SquareMatrix i <= 0");
        }

        for (i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
        }
    }

    public Matrix sum(Matrix matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {

                matrix.setElement(i, j, this.matrix[i][j] + matrix.getElement(i, j));
                System.out.println(matrix.getElement(i, j));
            }
        }
        return matrix;
    }
}
