package TP9DOP;

public class TaskMain {
    public static void main(String[] args) throws InterruptedException {
        MyTask interval1 = new MyTask(0, 5);
        MyTask interval2 = new MyTask(5, 10);
        MyTask interval3 = new MyTask(10, 15);
        MyTask interval4 = new MyTask(15, 20);
        TaskQueue taskQueue = new TaskQueue();
        taskQueue.push(interval1);
        taskQueue.push(interval2);
        taskQueue.push(interval3);
        taskQueue.push(interval4);


        long startTime = System.currentTimeMillis();
        taskQueue.start(4);
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + time / 1000 + " sec");
    }
}
