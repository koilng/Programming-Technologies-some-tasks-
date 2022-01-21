package TP5;

public class Matrix56 {
    public static void main(String[] args) {
       /* SparseMatrix sprsM = new SparseMatrix(5, 5);
        SparseMatrix sprsM1 = new SparseMatrix(4, 2);
        UsualMatrix uslM = new UsualMatrix(2, 4);
        */


        TreeMapMatrix treeM = new TreeMapMatrix(2, 4);
        treeM.setElement(0, 1, 5);
        treeM.setElement(0, 2, 7);
        treeM.setElement(1, 0, 8);
        treeM.setElement(1, 0, 9);
        System.out.println(treeM);


    }
}
