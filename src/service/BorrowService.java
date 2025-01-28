package service;

import model.book.Book;
import model.user.User;
import java.util.ArrayList;

public class BorrowService {
    private static final double RENTAL_FEE = 10.0; // TL

    public double borrowBook(Library library, int userId, int bookId) {
        User user = library.findUserById(userId);
        if (user == null) {
            System.out.println("Kullanıcı bulunamadı!");
            return 0;
        }

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Kitap bulunamadı!");
            return 0;
        }

        try {
            user.borrowBook(book);
            if (!library.getBorrowRecords().containsKey(user)) {
                library.getBorrowRecords().put(user, new ArrayList<>());
            }
            library.getBorrowRecords().get(user).add(book);
            return RENTAL_FEE;
        } catch (RuntimeException e) {
            System.out.println("Hata: " + e.getMessage());
            return 0;
        }
    }

    public double returnBook(Library library, int userId, int bookId) {
        User user = library.findUserById(userId);
        if (user == null) {
            System.out.println("Kullanıcı bulunamadı!");
            return 0;
        }

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Kitap bulunamadı!");
            return 0;
        }

        try {
            user.returnBook(book);
            library.getBorrowRecords().get(user).remove(book);
            return RENTAL_FEE;
        } catch (RuntimeException e) {
            System.out.println("Hata: " + e.getMessage());
            return 0;
        }
    }
}