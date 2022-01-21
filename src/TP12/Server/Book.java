package TP12.Server;

public class Book {
    private String name;
    private String takenBy = "noone";

    public Book(String bookName) {
        this.name = bookName;
    }

    public Book(String bookName, String takenBy) {
        this.name = bookName;
        this.takenBy = takenBy;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book: " + name + " | " + "Taken by: " + takenBy + "\n";
    }
}
