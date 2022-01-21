package TP7;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Settings {

    HashMap<String, Integer> hashMap;
    BufferedWriter bW = null;
    BufferedReader bR = null;

    public Settings() {
        hashMap = new HashMap<>();
    }

    public void put(String key, int value) {
        this.hashMap.put(key, value);
    }

    public void delete(String key) {
        this.hashMap.remove(key);
    }

    public int get(String key) {
        return this.hashMap.get(key);
    }

    public void saveToTextFile(String filename) {
        try {
            File file = new File(filename);
            bW = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, Integer> entry : this.hashMap.entrySet()) {
                bW.write(entry.getKey() + " = " + entry.getValue());
                bW.newLine();
            }
            bW.close();
        } catch (IOException e) {
            System.out.println("saveToText error");
        }
    }

    public void loadFromTextFile(String filename) {
        try {
            bR = new BufferedReader(new FileReader(filename));
            String line;
            this.hashMap = new HashMap<>();
            while ((line = bR.readLine()) != null) {
                if (line.contains("=")) {
                    String[] strings = line.split(" = ");
                    this.hashMap.put(strings[0], Integer.parseInt(strings[1]));
                }
            }
            bR.close();
        } catch (IOException e) {
            System.out.println("loadFromText error");
        }
    }

    public void saveToBinaryFile(String filename) {
        try {
            File file = new File(filename);
            String line;
            DataOutputStream x = new DataOutputStream(new FileOutputStream(file));
            for (Map.Entry<String, Integer> entry : this.hashMap.entrySet()) {
                line = entry.getKey() + " = " + entry.getValue() + "\n";
                byte[] byteArray = line.getBytes(StandardCharsets.UTF_8);
                for (byte b : byteArray) {
                    x.write(b);
                }
            }
            x.close();
        } catch (IOException e) {
            System.out.println("saveToText error");
        }
    }

    public void loadFromBinaryFile(String filename) throws IOException {
        try {
            this.hashMap = new HashMap<>();
            DataInputStream x = new DataInputStream(new FileInputStream(filename));
            StringBuilder stringBuilder = new StringBuilder();
            while (x.available() > 0) {
                char k = (char) x.read();
                stringBuilder.append(k);
            }
            String[] strings = stringBuilder.toString().split("\n");
            for (String string : strings) {
                String[] stringToParse = string.split(" = ");
                this.hashMap.put(stringToParse[0], Integer.parseInt(stringToParse[1]));
            }
            x.close();
        } catch (EOFException e) {
            System.out.println("loadFromBinary error");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : this.hashMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        if (hashMap.toString().equals(settings.hashMap.toString())) {
            return Objects.equals(hashMap, settings.hashMap);
        }
        return false;
    }
}