package service;

import model.Book;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Library {
    private List<Book> books; // Kitap listesi
    private List<User> users; // Kullanıcı listesi
    private Map<User, List<Book>> borrowRecords; // Ödünç alınan kitap kaydı

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.borrowRecords = new HashMap<>();
    }

    // Kitap ekleme
    public void addBook(Book book) {
        books.add(book);
    }

    // Kitap silme
    public boolean removeBook(int bookId) {
        return books.removeIf(book -> book.getId() == bookId);
    }

    // Yeni kullanıcı ekleme
    public void addUser(User user) {
        users.add(user);
    }

    // Kullanıcıyı ID'ye göre bulma
    public User findUserById(int userId) {
        return users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
    }

    // Kitap ID'ye göre bulma
    public Book findBookById(int bookId) {
        return books.stream().filter(b -> b.getId() == bookId).findFirst().orElse(null);
    }

    // Kitap ödünç alma
    public void borrowBook(int user, int bookId) {
        Book book = findBookById(bookId);
        if (user == null || book == null) {
            System.out.println("Kullanıcı veya kitap bulunamadı.");
            return;
        }
        user.borrowBook(book);
        borrowRecords.computeIfAbsent(user, k -> new ArrayList<>()).add(book);
    }

    // Kitap iade etme
    public void returnBook(User user, int bookId) {
        Book book = findBookById(bookId);
        if (user == null || book == null) {
            System.out.println("Kullanıcı veya kitap bulunamadı.");
            return;
        }
        user.returnBook(book);
        borrowRecords.getOrDefault(user, new ArrayList<>()).remove(book);
    }

    // Kategoriye göre kitap listeleme
    public List<Book> listBooksByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }
}