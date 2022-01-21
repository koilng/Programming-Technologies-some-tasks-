package TP12.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TCPServerRunner {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(/*args[0]*/"9876"));
        List<Client> clientArrayList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        Set<String> namesList = new HashSet<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("TP12\\Dop\\Books.txt"));
        String bookLine;
        while ((bookLine = bufferedReader.readLine()) != null) {
            String[] line = bookLine.split(":");
            if (line.length == 1) {
                bookList.add(new Book(line[0]));
            }
            if (line.length == 2) {
                bookList.add(new Book(line[0], line[1]));
            }
        }

        try {
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                Client client = new Client(clientSocket);
                clientArrayList.add(client);
                Thread clientThread = new Thread(
                        new ClientHandler(client, clientArrayList, namesList, bookList));
                clientThread.start();
            }
        } catch (IOException ignored) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
            FileWriter fileWriter = new FileWriter("TP12\\Dop\\Books.txt");
            for (Book book : bookList) {
                fileWriter.write(book.getName() + ":" + book.getTakenBy() + "\n");
            }
            fileWriter.close();
        }
        System.out.println("end");
    }
}