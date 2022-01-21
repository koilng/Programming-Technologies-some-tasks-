package TP11.Server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServerRunner {
    public static void main(String[] args) {

        try (DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(args[0]))) {

            UDPServer.setServerSocket(serverSocket);

            Thread sender = new Thread(UDPServer::sender);
            Thread receiver = new Thread(UDPServer::receiver);

            sender.start();
            receiver.start();

            sender.join();
            receiver.join();

        } catch (SocketException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
