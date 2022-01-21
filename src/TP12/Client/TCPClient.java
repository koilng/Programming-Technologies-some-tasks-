package TP12.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader userInput;

    public static void setOut(PrintWriter out) {
        TCPClient.out = out;
    }

    public static void setIn(BufferedReader in) {
        TCPClient.in = in;
    }

    public static void setUserInput(BufferedReader userInput) {
        TCPClient.userInput = userInput;
    }

    public static void setSocket(Socket socket) {
        TCPClient.socket = socket;
    }

    public static void sender() {
        try {
            while (!socket.isClosed()) {
                String fromUser = userInput.readLine();
                if (fromUser != null) {
                    if (fromUser.equals("@exit")) {
                        socket.close();
                        break;
                    } else {
                        out.println(fromUser);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client closed");
        }
    }

    public static void receiver() {
        try {
            while (!socket.isClosed()) {
                String fromServer = in.readLine();

                System.out.println(fromServer);
            }
        } catch (IOException e) {
            System.out.println("Client closed");
        }
    }
}
