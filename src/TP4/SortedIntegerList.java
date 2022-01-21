package TP4;

import java.util.LinkedList;
import java.util.ListIterator;


public class SortedIntegerList {
    protected LinkedList<Integer> list = new LinkedList<>();
    private boolean multiArray;

    public SortedIntegerList(boolean multiArray) {
        this.multiArray = multiArray;
    }

    public void add(int a) {
        ListIterator iter = list.listIterator();

        while (iter.hasNext()) {
            int tmp = (int) iter.next();

            if (a == tmp) {
                if (multiArray) {
                    iter.add(a);
                }
                return;
            }

            if (a < tmp) {
                iter.previous();
                iter.add(a);
                return;
            }
        }
        iter.add(a);
    }

    public SortedIntegerList getOdd() {
        SortedIntegerList list2 = new SortedIntegerList(this.multiArray);
        ListIterator iter = list.listIterator();
        while (iter.hasNext()) {
            int tmp = iter.nextIndex();
            if (tmp % 2 != 0) {
                list2.add(list.get(tmp));
            }
            iter.next();
        }
        return list2;
    }

    public void remove(int a) {
        ListIterator iter = list.listIterator();
        while (iter.hasNext()) {
            int tmp = (int) iter.next();
            if (tmp == a) {
                iter.remove();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortedIntegerList that = (SortedIntegerList) o;
        ListIterator iter1 = list.listIterator();
        ListIterator iter2 = that.list.listIterator();

        if (this.multiArray != that.multiArray || this.list.size() != that.list.size()) return false;

        for (int i = 0; i < list.size(); i++) {
            if (iter1.next() != iter2.next()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            str.append(list.get(i) + " ");
        }
        return str.toString();
    }
}
