package TP11.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.stream.Collectors;

public class UDPServer {
    private static DatagramSocket serverSocket;
    private static InetAddress clientIP;
    private static int clientPort;
    private static String name = "Server";

    public static void setServerSocket(DatagramSocket serverSocket) {
        UDPServer.serverSocket = serverSocket;
    }

    public static void sender() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (!serverSocket.isClosed()) {
                String message = reader.readLine().trim();

                String[] tokens = message.split(" ");
                switch (tokens.length) {
                    case (1):
                        if (tokens[0].equals("@exit")) {
                            serverSocket.close();
                        } else {
                            message = name + ": " + message;
                            serverSocket.send(new DatagramPacket(message.getBytes(), message.length(), clientIP, clientPort));
                        }
                        break;

                    case (2):
                        if (tokens[0].equals("@name")) {
                            name = tokens[1];
                        } else {
                            message = name + ": " + message;
                            serverSocket.send(new DatagramPacket(message.getBytes(), message.length(), clientIP, clientPort));
                        }
                        break;

                    default:
                        message = name + ": " + message;
                        serverSocket.send(new DatagramPacket(message.getBytes(), message.length(), clientIP, clientPort));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void receiver() {
        try {
            while (!serverSocket.isClosed()) {
                DatagramPacket receivePacket = new DatagramPacket(new byte[100], 100);
                serverSocket.receive(receivePacket);

                clientIP = receivePacket.getAddress();
                clientPort = receivePacket.getPort();

                String message = new String(receivePacket.getData());
                String newMessage = message.chars().
                        filter(ch -> Character.isLetterOrDigit(ch) | Character.isSpaceChar(ch) | ch == ':').
                        mapToObj(ch -> "" + (char) ch).collect(Collectors.joining());

                System.out.println(newMessage);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
