package TP12.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class Client {

    private String name;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    Client(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        out.println(message);
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedReader getIn() {
        return in;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(out, client.out) && Objects.equals(in, client.in) && Objects.equals(socket, client.socket);
    }
}
