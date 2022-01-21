package TP12.Server;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ClientHandler implements Runnable {
    private Client client;
    private List<Client> list;
    private List<Book> bookList;
    private Set<String> names;

    ClientHandler(Client client, List<Client> list, Set<String> names, List<Book> bookList) {
        this.client = client;
        this.list = list;
        this.names = names;
        this.bookList = bookList;
    }

    @Override
    public void run() {
        try {
            String message;
            String name = client.getIn().readLine();

            while (names.contains(name)) {
                client.send("Name already exists");
                name = client.getIn().readLine();
            }
            client.setName(name);
            names.add(client.getName());

            while ((message = client.getIn().readLine()) != null) {

                if (message.startsWith("@take ")) {
                    for (Book book : bookList) {
                        if (book.getName().equals(message.substring(6))) {
                            if (book.getTakenBy().equals("noone")) {
                                book.setTakenBy(client.getName());
                            } else {
                                client.send("Book is already taken by: " + book.getTakenBy());
                            }
                        } else {
                            client.send("Book with such name doesn't exists");
                        }
                    }
                } else if (message.startsWith("@return ")) {
                    for (Book book : bookList) {
                        if (book.getName().equals(message.substring(8))) {
                            if (book.getTakenBy().equals(client.getName())) {
                                book.setTakenBy("noone");
                            } else {
                                client.send("You can't return book that was taken by another client");
                            }
                        } else {
                            client.send("Book with such name doesn't exists");
                        }
                    }
                } else if (message.startsWith("@name ")) {
                    if (names.contains(message.substring(6))) {
                        client.send("Name already exists");
                    } else {
                        client.setName(message.substring(6));
                    }
                } else if (message.startsWith("@list")) {
                    System.out.println(bookList);
                } else {
                    for (Client anotherClient : list) {
                        if (!this.client.equals(anotherClient)) {
                            anotherClient.send(client.getName() + ": " + message);
                        }
                    }
                }
            }
            list.remove(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


