package TP11.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.stream.Collectors;

public class UDPClient {
    private static DatagramSocket clientSocket;
    private static InetAddress IP;
    private static int port;
    private static String name = "Client";

    public static void setIP(InetAddress IP) {
        UDPClient.IP = IP;
    }

    public static void setPort(int port) {
        UDPClient.port = port;
    }

    public static void setClientSocket(DatagramSocket clientSocket) {
        UDPClient.clientSocket = clientSocket;
    }

    public static void sender() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            clientSocket.send(new DatagramPacket("@connected".getBytes(), "@connected".length(), IP, port));

            while (!clientSocket.isClosed()) {
                String message = reader.readLine().trim();

                String[] tokens = message.split(" ");
                switch (tokens.length) {
                    case (1):
                        if (tokens[0].equals("@exit")) {
                            clientSocket.close();
                        } else {
                            message = name + ": " + message;
                            clientSocket.send(new DatagramPacket(message.getBytes(), message.length(), IP, port));
                        }
                        break;

                    case (2):
                        if (tokens[0].equals("@name")) {
                            name = tokens[1];
                        } else {
                            message = name + ": " + message;
                            clientSocket.send(new DatagramPacket(message.getBytes(), message.length(), IP, port));
                        }
                        break;

                    default:
                        message = name + ": " + message;
                        clientSocket.send(new DatagramPacket(message.getBytes(), message.length(), IP, port));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void receiver() {
        try {
            while (!clientSocket.isClosed()) {
                //разбор строки + в сервере

                DatagramPacket receivedPacket = new DatagramPacket(new byte[100], 100);
                clientSocket.receive(receivedPacket);

                String message = new String(receivedPacket.getData());
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
