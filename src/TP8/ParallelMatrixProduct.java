package TP8;

public class ParallelMatrixProduct {
    Thread[] thread;
    UsualMatrix firstMatrix;
    UsualMatrix secondMatrix;
    UsualMatrix resultMatrix;

    public ParallelMatrixProduct(int t) {
        thread = new Thread[t];
    }

    public UsualMatrix product(UsualMatrix matrix1, UsualMatrix matrix2) {
        this.firstMatrix = matrix1;
        this.secondMatrix = matrix2;
        resultMatrix = new UsualMatrix(matrix1.getRow(), matrix2.getColumn());
        int rowRemain = matrix1.getRow() % thread.length; //0
        int rowToThread = matrix1.getRow() / thread.length; //2

        int left = 0;
        int right = left + rowToThread + ((rowRemain > 0) ? 1 : 0);

        for (int i = 0; i < thread.length; i++) {

            System.out.println("begin: " + left);
            System.out.println("end: " + right);

            rowRemain--;

            thread[i] = new Thread(new MyThread(left, right));

            left += rowToThread + ((rowRemain >= 0) ? 1 : 0);
            right = left + rowToThread + ((rowRemain > 0) ? 1 : 0);

            /*if (i == 0) {
                thread[i] = new Thread(new BackpackThread(i*rowToThread, i*rowToThread+rowToThread));
            }
            if (i > 0) {
                thread[i] = new Thread(new BackpackThread(i*rowToThread, i*rowToThread+rowToThread));
            }
            if (i == thread.length - 1) {
                thread[i] = new Thread(new BackpackThread(i*rowToThread, i*rowToThread+rowToThread));
            }*/

        }
        try {
            for (Thread t : thread) {
                t.start();
            }
            for (Thread t : thread) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("interr exc");
        }
        return resultMatrix;
    }

    public class MyThread extends Thread {
        int begin;
        int end;

        public MyThread(int begin, int end) {
            this.begin = begin;
            this.end = end;

        }

        public void run() {
            for (int i = this.begin; i < this.end; i++) {
                for (int j = 0; j < secondMatrix.getColumn(); j++) {
                    for (int s = 0; s < firstMatrix.getColumn(); s++) {
                        resultMatrix.setElement(i, j, resultMatrix.getElement(i, j) + firstMatrix.getElement(i, s) * secondMatrix.getElement(s, j));
                    }
                }
            }
        }
    }
}
