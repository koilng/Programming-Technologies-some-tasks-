package TP3;

import java.lang.Throwable;

class Matrix {

    protected int[][] matrix;
    protected final int row;
    protected final int column;

    public Matrix(int i, int j) {
        if (i <= 0 | j <= 0) {
            throw new MatrixException("Matrix i <= 0");
        }

        this.row = i;
        this.column = j;

        matrix = new int[i][j];
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                result.append("[" + matrix[i][j] + "]");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public Matrix sum(Matrix matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                this.matrix[i][j] = this.matrix[i][j] + matrix.getElement(i, j);
            }
        }
        return matrix;
    }

    public Matrix product(Matrix matrix) {
        Matrix tmp = new Matrix(this.getColumn(), matrix.getRow());
        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                tmp.setElement(i, j, 0);
                for (int s = 0; s < matrix.getRow(); s++) {
                    tmp.setElement(i, j, tmp.getElement(i, j) + this.matrix[i][s] * matrix.getElement(s, j));
                }
            }
        }
        return tmp;
    }

    public int getElement(int row, int column) {
        return this.matrix[row][column];
    }

    public void setElement(int row, int column, int value) {
        this.matrix[row][column] = value;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}


