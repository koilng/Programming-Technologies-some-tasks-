package TP4;

import java.util.Collections;
import java.util.LinkedList;

public class MyList {
    public static void main(String[] args) {
        SortedIntegerList linkedList = new SortedIntegerList(true);
        SortedIntegerList linkedList1 = new SortedIntegerList(true);


        for (int i = 0; i < 100; i++) {
            linkedList.add(i);
        }

        /*for (int i = 0; i < linkedList.list.size(); i++) {
            System.out.println(linkedList.list.get(i));
        }*/

        //System.out.println(linkedList.equals(linkedList1));

        System.out.println(linkedList.getOdd());
    }
}
