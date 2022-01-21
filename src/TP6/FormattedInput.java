package TP6;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class FormattedInput {
    private static ArrayList<Object> arrList;
    private static String temp;

    public FormattedInput() {
        arrList = new ArrayList<>();
    }

    public static Object[] scanf(String format) throws IOException {

        Scanner input;
        Scanner scanner = new Scanner(System.in);

        if (temp == null) {
            System.out.println("Введите строку");
            temp = scanner.nextLine();
            scanner.close();
        }

        input = new Scanner(temp);

        StringTokenizer st = new StringTokenizer(temp);
        while (st.hasMoreTokens()) {
            arrList.add(st.nextToken());
        }

        Object[] formatValue = format.split(" ");


        for (int i = 0; i < formatValue.length; i++) {
            if (formatValue.length != arrList.size()) {
                System.out.println("Не равное количество форматов и строк");
                break;
            }
            if (!formatValue[i].toString().equals("%d") & !formatValue[i].toString().equals("%f") &
                    !formatValue[i].toString().equals("%s") & !formatValue[i].toString().equals("%c")) {
                System.out.println("Неправильный ввод формата");
                break;
            }
            switch (formatValue[i].toString()) {
                case "%d":
                    try {
                        input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Значение не int");
                        return null;
                    }
                    break;
                case "%f":
                    try {
                        input.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Значение не double");
                        return null;
                    }
                    break;
                case "%c":
                    if (input.next().length() == 1) {
                        break;
                    }
                    System.out.println("Значение не char");
                    return null;
                default:
                    if (input.next().length() != 0) {
                        break;
                    }
                    System.out.println("Значение не string");
                    return null;
            }
        }
        return arrList.toArray();
    }


    public static Object[] sscanf(String format, String in) throws IOException {
        temp = in;
        return scanf(format);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object s : arrList) {
            result.append(s).append(" ");
        }
        return result.toString();
    }
}

