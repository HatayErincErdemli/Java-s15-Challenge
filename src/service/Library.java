package service;

import model.book.Author;
import model.book.Book;
import model.book.Category;
import model.user.User;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<User> users;
    private Map<User, List<Book>> borrowRecords;
    private List<Author> authors = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.borrowRecords = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null && book.isAvailable()) {
            books.remove(book);
            return true;
        }
        return false;
    }

    public void updateBook(int bookId, String newTitle, Author newAuthor, Category newCategory) {
        Book book = findBookById(bookId);
        if (book != null && book.isAvailable()) {
            book.updateDetails(newTitle, newAuthor, newCategory);
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().getId() == category.getId()) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByAuthor(Author author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getId() == author.getId()) {
                result.add(book);
            }
        }
        return result;
    }

    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap bulunmamaktadır.");
            return;
        }
        System.out.println("\n=== Kitap Listesi ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<User, List<Book>> getBorrowRecords() {
        return borrowRecords;
    }

    public Author findOrCreateAuthor(int nextAuthorId, String authorName) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(authorName)) {
                return author;
            }
        }
        Author newAuthor = new Author(nextAuthorId, authorName);
        authors.add(newAuthor);
        return newAuthor;
    }

    public Category findOrCreateCategory(int nextCategoryId, String categoryName, String description) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        Category newCategory = new Category(nextCategoryId, categoryName, description);
        categories.add(newCategory);
        return newCategory;
    }
}