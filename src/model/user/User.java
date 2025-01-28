package model.user;

import model.book.Book;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected int id;
    protected String name;
    protected List<Book> borrowedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public abstract boolean canBorrow();

    public void borrowBook(Book book) {
        if (!canBorrow()) {
            throw new RuntimeException("Kitap limiti doldu!");
        }
        if (!book.isAvailable()) {
            throw new RuntimeException("Kitap mevcut değil!");
        }
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
        } else {
            throw new RuntimeException("Bu kitap bu kullanıcıda değil!");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "Kullanıcı[ID=" + id + ", İsim='" + name + "']";
    }
}