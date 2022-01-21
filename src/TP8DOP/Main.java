package TP8DOP;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("Ветки", 1, 50));
        items.add(new Item("Камни", 1, 50));
        items.add(new Item("Ткань", 2, 200));
        items.add(new Item("Топливо", 3, 450));
        items.add(new Item("Сосиски", 2, 250));
        items.add(new Item("Спички", 1, 100));

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        long startTime = System.currentTimeMillis();
        Backpack backpack = new Backpack(6);
        backpack.makeAllSets(items, 1);
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + time / 1000 + " sec");
        System.out.println(backpack.bestItems);
        System.out.println(backpack.bestPrice);
    }
}
