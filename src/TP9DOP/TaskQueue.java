package TP9DOP;

import java.util.LinkedList;

public class TaskQueue {
    LinkedList<MyTask> tasks;
    Thread[] threads;

    public TaskQueue() {
        tasks = new LinkedList<>();
    }

    void push(MyTask task) {
        synchronized (tasks) {
            tasks.addLast(task);
        }
    }

    void start(int threadNumber) throws InterruptedException {
        threads = new Thread[threadNumber];
        int k = 0;
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread();
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }

    void showResult() {

    }

    MyTask pop() {
        MyTask t;
        synchronized (tasks) {
            t = tasks.getFirst();
            tasks.removeFirst();
            if (t.isDone) {
                return null;
            }
            return t;
        }
    }

    public class MyThread extends Thread {
        public void run() {
            MyTask t;
            for (int k = 0; k < tasks.size(); k++) {
                if ((t = pop()) != null) {
                    for (int i = t.start; i < t.end; i++) {
                        boolean isPrime = true;
                        for (int j = 2; j < i; j++) {
                            if (i <= 1) {
                                break;
                            }
                            if (i % j == 0) {
                                isPrime = false;
                                break;
                            }
                        }
                        if (isPrime && i > 1) {
                            t.answers.add(i);
                            System.out.println(i);
                        }
                    }
                    t.isDone = true;
                    System.out.println(Thread.currentThread().getName());
                    push(t);
                }
            }
        }
    }
}
