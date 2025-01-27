package model;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String id;
    private String name;
    private List<Book> borrowedBooks;

    // Constructor
    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Kitap ödünç al (ortak metot)
    public void borrowBook(Book book) {
        if (canBorrow() && book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
        } else {
            throw new RuntimeException("Kitap ödünç alınamaz. Ya mevcut değil ya da limit doldu.");
        }
    }

    // Kitap iade et (ortak metot)
    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
        }
    }

    // Ödünç alınabilme yeteneği (alt sınıflarda özelleştirilecek)
    public abstract boolean canBorrow(); // Her alt sınıf kendi limitini tanımlayacak
}