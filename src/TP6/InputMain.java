package TP6;

import java.io.*;
import java.util.Scanner;

public class InputMain {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String format = scanner.nextLine();

        FormattedInput ioTest = new FormattedInput();


        ioTest.sscanf(format, "kek lol 228");
        //System.out.println(ioTest);
    }
}

