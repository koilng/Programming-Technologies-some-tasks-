package TP1;

public class TaskHelp {
    private int n = 0;

    public void inc() {
        n++;
    }

    public void dec() {
        n--;
    }

    public void add() {
        n += n;
    }

    public void substract() {
        n -= n;
    }

    @Override
    public String toString() {
        return "TaskHelp{" +
                "n=" + n +
                '}';
    }
}
