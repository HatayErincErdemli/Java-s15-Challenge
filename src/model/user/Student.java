package model.user;

public class Student extends User {
    private String studentNumber;
    private String department;
    private static final int BOOK_LIMIT = 5;

    public Student(int id, String name, String studentNumber, String department) {
        super(id, name);
        this.studentNumber = studentNumber;
        this.department = department;
    }

    @Override
    public boolean canBorrow() {
        return getBorrowedBooks().size() < BOOK_LIMIT;
    }

    // Getters and Setters
    public String getStudentNumber() {
        return studentNumber;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("Öğrenci[ID=%d, İsim='%s', Öğrenci No='%s', Bölüm='%s']",
                getId(), getName(), studentNumber, department);
    }
}