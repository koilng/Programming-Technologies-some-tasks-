package TP8;

public class ParallelMain {


    public static void main(String[] args) {
        /*ParallelMatrixProduct newThread = new ParallelMatrixProduct(4);
        UsualMatrix firstMatrix = new UsualMatrix(5,3);
        UsualMatrix secondMatrix = new UsualMatrix(3,5);
        firstMatrix.fill(firstMatrix.matrix);
        secondMatrix.fill(secondMatrix.matrix);
        System.out.println(firstMatrix);
        System.out.println(secondMatrix);

        newThread.product(firstMatrix, secondMatrix);

        System.out.println(newThread.resultMatrix);
        System.out.println(firstMatrix.product(secondMatrix));*/

        UsualMatrix a = new UsualMatrix(1000, 1000);
        UsualMatrix b = new UsualMatrix(1000, 1000);
        UsualMatrix c;

        a.setElement(0, 0, 100);
        b.setElement(0, 0, 125);

        long m = System.currentTimeMillis();
        c = a.product(b);

        System.out.print("Usual product: ");
        System.out.println((System.currentTimeMillis() - m) / 1000.0 + "s");


        ParallelMatrixProduct thread = new ParallelMatrixProduct(7);
        m = System.currentTimeMillis();
        a = thread.product(a, b);

        System.out.print("Thread product: ");
        System.out.println((System.currentTimeMillis() - m) / 1000.0 + "s");


        System.out.println(c.equals(a));

    }
}
