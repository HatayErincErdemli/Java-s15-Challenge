package model;

public class Student extends User {
    private static final int BOOK_LIMIT = 5; // Öğrenci için limit

    // Constructor
    public Student(int id, String name) {
        super(id, name); // User sınıfının constructor'ını çağır
    }

    @Override
    public boolean canBorrow() {
        return getBorrowedBooks().size() < BOOK_LIMIT;
    }
}