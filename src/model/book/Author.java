package model.book;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String name;
    private List<Book> books;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return name;
    }
}