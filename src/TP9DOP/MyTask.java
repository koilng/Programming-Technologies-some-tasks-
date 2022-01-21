package TP9DOP;

import java.util.ArrayList;

public class MyTask {
    int start;
    int end;
    protected ArrayList<Integer> answers;
    boolean isDone;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
        answers = new ArrayList<>();
        isDone = false;
    }
}
