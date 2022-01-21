package TP12.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientRunner {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
            TCPClient.setSocket(clientSocket);
            TCPClient.setIn(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            TCPClient.setOut(new PrintWriter(clientSocket.getOutputStream(), true));
            TCPClient.setUserInput(new BufferedReader(new InputStreamReader(System.in)));

            Thread sender = new Thread(TCPClient::sender);
            Thread receiver = new Thread(TCPClient::receiver);

            sender.start();
            receiver.start();

            sender.join();
            receiver.join();

        } catch (IOException | InterruptedException e) {
            System.out.println("error");
        }
    }
}
