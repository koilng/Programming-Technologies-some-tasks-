package TP6;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EncodingConverter {
    public static void main(String[] args) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream
                ("C:\\Users\\grits\\Desktop\\input.txt"), StandardCharsets.UTF_8);
        Writer writer = new OutputStreamWriter(new FileOutputStream
                ("C:\\Users\\grits\\Desktop\\output.txt"), "cp1251");
        int c = 0;
        while ((c = reader.read()) >= 0) {
            writer.write(c);
        }
        reader.close();
        writer.close();
    }
}