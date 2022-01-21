package TP8DOP;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Backpack {
    int maxWeight;
    int bestPrice;
    CopyOnWriteArrayList<Item> copyOnWriteArrayList;
    ArrayList<Item> bestItems;
    Thread[] thread;

    public Backpack(int maxWeight) {
        this.maxWeight = maxWeight;
        this.copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        this.bestItems = new ArrayList<>();
    }

    public void makeAllSets(ArrayList<Item> list, int threadNum) {

        this.thread = new Thread[threadNum];
        int opsize = (int) Math.pow(2, list.size());
        int remain = opsize % thread.length;
        int step = opsize / thread.length;
        int begin = 1;
        int end = begin + step + ((remain > 0) ? 1 : 0);

        for (int i = 0; i < thread.length; i++) {

            System.out.println("begin: " + begin);
            System.out.println("end: " + end);

            remain--;

            this.thread[i] = new Thread(new BackpackThread(list, begin, end));

            begin += step + ((remain >= 0) ? 1 : 0);
            end = begin + step + ((remain > 0) ? 1 : 0);
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
    }

    public void checkSet(CopyOnWriteArrayList<Item> items) {
        if (calcWeight(items) <= maxWeight & calcPrice(items) > bestPrice) {
            bestItems.clear();
            bestPrice = calcPrice(items);
            bestItems.addAll(items);
        }
    }

    public int calcPrice(CopyOnWriteArrayList<Item> items) {
        int setPrice = 0;
        for (Item elem : items) {
            setPrice += elem.price;
        }
        return setPrice;
    }

    public int calcWeight(CopyOnWriteArrayList<Item> items) {
        int setWeight = 0;
        for (Item elem : items) {
            setWeight += elem.weight;
        }
        return setWeight;
    }

    public class BackpackThread extends Thread {
        int begin;
        int end;
        ArrayList<Item> itemsTemp;

        public BackpackThread(ArrayList<Item> items, int begin, int end) {
            this.begin = begin;
            this.end = end;
            itemsTemp = items;
        }

        public void run() {

            for (int i = begin; i < end; i++) {
                for (int j = 0; j < itemsTemp.size(); j++) {
                    if (BigInteger.valueOf(i).testBit(j)) {
                        copyOnWriteArrayList.add(itemsTemp.get(j));
                    }
                }
                checkSet(copyOnWriteArrayList);
                copyOnWriteArrayList.clear();
            }
        }
    }
}

/*while(maxWeight > 0) {
            priceEfficiency = 0;
            elementIndex = 0;

            for (Item elem: list) {
                if ((double) elem.price/ elem.weight > priceEfficiency) {
                    priceEfficiency = (double) elem.price / elem.weight;
                    elementIndex = list.indexOf(elem);
                }
            }

            if (maxWeight < list.get(elementIndex).weight) {
                list.remove(elementIndex);
            } else {
                backpack.add(list.get(elementIndex));
                maxWeight -= list.get(elementIndex).weight;
            }
        }*/