package TP5;

public interface IMatrix {
    public String toString();

    public int getElement(int row, int column);

    public void setElement(int row, int column, int value);

    public int getRow();

    public int getColumn();
}
