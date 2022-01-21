package TP7;

import java.io.IOException;

public class SettingsMain {
    public static void main(String[] args) throws IOException {
        Settings set1 = new Settings();
        Settings set2 = new Settings();

        set1.loadFromTextFile("kekich.bin");
        set1.loadFromBinaryFile("kekichBinary");
        set2.put("djujun", 12);
        set2.put("age", 50);
        set2.put("height", 180);
        set2.put("keke", 159);
        System.out.println(set1.equals(set2));
    }
}
