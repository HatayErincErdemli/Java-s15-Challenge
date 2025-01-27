package model;

public class Teacher extends User {
    private static final int BOOK_LIMIT = 7; // Öğretmen için limit

    // Constructor
    public Teacher(int id, String name) {
        super(id, name); // User sınıfının constructor'ını çağır
    }

    @Override
    public boolean canBorrow() {
        return getBorrowedBooks().size() < BOOK_LIMIT;
    }
}