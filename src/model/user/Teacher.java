package model.user;

public class Teacher extends User {
    private String faculty;
    private String officeNumber;
    private static final int BOOK_LIMIT = 10;

    public Teacher(int id, String name, String faculty, String officeNumber) {
        super(id, name);
        this.faculty = faculty;
        this.officeNumber = officeNumber;
    }

    @Override
    public boolean canBorrow() {
        return getBorrowedBooks().size() < BOOK_LIMIT;
    }

    // Getters and Setters
    public String getFaculty() {
        return faculty;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    @Override
    public String toString() {
        return String.format("Öğretmen[ID=%d, İsim='%s', Fakülte='%s', Ofis='%s']",
                getId(), getName(), faculty, officeNumber);
    }
}