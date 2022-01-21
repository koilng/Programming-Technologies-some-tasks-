package TP8;

import TP3.MatrixException;
import TP5.IMatrix;

public class UsualMatrix implements IMatrix {

    protected int[][] matrix;
    protected final int row;
    protected final int column;

    public UsualMatrix(int i, int j) {
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

    public void fill(int[][] matrix) {
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                matrix[i][j] = (int) (Math.random() * 10);
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

    public UsualMatrix sum(UsualMatrix matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                this.matrix[i][j] = this.matrix[i][j] + matrix.getElement(i, j);
            }
        }
        return matrix;
    }

    /*public UsualMatrix product(IMatrix matrix) {
        UsualMatrix tmp = new UsualMatrix(this.getRow(), matrix.getColumn());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                tmp.setElement(i, j, 0);
                for (int s = 0; s < this.getColumn(); s++) {
                    tmp.setElement(i, j, tmp.getElement(i,j) + this.matrix[i][s] * matrix.getElement(s, j));
                }
            }
        }
        return tmp;
    }*/

    public UsualMatrix product(IMatrix b) {

        UsualMatrix res = new UsualMatrix(this.getRow(), b.getColumn());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < b.getColumn(); j++) {
                res.setElement(i, j, 0);
                for (int k = 0; k < this.getColumn(); k++) {
                    res.setElement(i, j, res.getElement(i, j) + this.getElement(i, k) * b.getElement(k, j));
                }
            }
        }
        return res;
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

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UsualMatrix)) {
            return false;
        }

        UsualMatrix cur = (UsualMatrix) obj;
        if (this.getColumn() != cur.getColumn() || this.getRow() != cur.getRow())
            return false;

        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                if (cur.getElement(i, j) != this.getElement(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

}


