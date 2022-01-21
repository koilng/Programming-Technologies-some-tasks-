package TP5;

import TP3.MatrixException;

import java.util.TreeMap;

public class TreeMapMatrix {
    protected int row;
    protected int column;

    TreeMap<Element, Integer> treeMap;


    public TreeMapMatrix(int row, int column) {
        treeMap = new TreeMap<>();

        if (row < 0 | column < 0) {
            throw new MatrixException("Matrix row <= 0");
        }
        this.row = row;
        this.column = column;
    }

    protected static class Element implements Comparable<Element> {
        int row;
        int column;

        public Element(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public int compareTo(Element o) {
            if (this.row > o.row | this.row == o.row & this.column > o.column) {
                return 1;
            }
            if (this.row < o.row | this.row == o.row & this.column < o.column) {
                return -1;
            }
            return 0;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getElement(int row, int column) {
        Integer value = this.treeMap.get(new Element(row, column));
        return value == null ? 0 : value;
    }

    public void setElement(int row, int column, int value) {
        if (value == 0) {
            this.treeMap.remove(new Element(row, column));
        } else {
            this.treeMap.put(new Element(row, column), value);
        }
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
}
