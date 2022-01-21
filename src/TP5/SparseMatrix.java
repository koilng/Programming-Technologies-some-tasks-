package TP5;

import TP3.MatrixException;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;

public class SparseMatrix implements IMatrix {
    protected int row;
    protected int column;

    LinkedList<Element> sprsMatrix = new LinkedList();

    public SparseMatrix(int row, int column) {
        if (row < 0 | column < 0) {
            throw new MatrixException("Matrix row <= 0");
        }
        this.row = row;
        this.column = column;
    }

    protected static class Element {
        int row;
        int column;
        int value;

        public Element(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return row == element.row && column == element.column && value == element.value;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int getElement(int row, int column) {
        ListIterator<Element> iter = this.sprsMatrix.listIterator();

        while (iter.hasNext()) {
            Element tmp = iter.next();
            if (tmp.row == row & tmp.column == column) {
                return tmp.value;
            }
        }
        return 0;
    }


    @Override
    public void setElement(int row, int column, int value) {
        ListIterator<Element> iter = this.sprsMatrix.listIterator();

        while (iter.hasNext()) {
            Element tmp = iter.next();
            if (tmp.row == row & tmp.column == column) {
                if (value == 0) {
                    iter.remove();
                    return;
                }
                iter.set(new Element(row, column, value));
                return;
            }
        }

        if (value == 0) {
            return;
        }

        sprsMatrix.add(new Element(row, column, value));
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                result.append("[" + this.getElement(i, j) + "]");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparseMatrix that = (SparseMatrix) o;

        ListIterator iter1 = sprsMatrix.listIterator();
        ListIterator iter2 = that.sprsMatrix.listIterator();

        if (sprsMatrix.size() != that.sprsMatrix.size()) {
            return false;
        }

        while (iter1.hasNext()) {
            Element tmp = (Element) iter1.next();
            Element tmp2 = (Element) iter2.next();
            if (!tmp.equals(tmp2)) {
                return false;
            }

        }

        return true;
    }

    public SparseMatrix product(IMatrix matrix) throws MatrixException {
        SparseMatrix tmp = new SparseMatrix(this.getRow(), matrix.getColumn());

        if (this.getRow() != matrix.getColumn() || this.getColumn() != matrix.getRow()) {
            throw new MatrixException("Different sizes");
        }

        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < matrix.getColumn(); j++) {
                tmp.setElement(i, j, 0);
                for (int s = 0; s < this.getColumn(); s++) {
                    tmp.setElement(i, j, tmp.getElement(i, j) + this.getElement(i, s) * matrix.getElement(s, j));
                }
            }
        }
        return tmp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, sprsMatrix);
    }
}