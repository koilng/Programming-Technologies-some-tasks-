package TP11.Client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClientRunner {
    public static void main(String[] args) {

        try (DatagramSocket clientSocket = new DatagramSocket()) {

            UDPClient.setClientSocket(clientSocket);
            UDPClient.setIP(InetAddress.getByName(args[0]));
            UDPClient.setPort(Integer.parseInt(args[1]));

            Thread sender = new Thread(UDPClient::sender);
            Thread receiver = new Thread(UDPClient::receiver);

            sender.start();
            receiver.start();

            sender.join();
            receiver.join();

        } catch (InterruptedException | SocketException | UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
