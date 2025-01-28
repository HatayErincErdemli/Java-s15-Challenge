package main;

import model.book.*;
import model.user.*;
import service.Library;
import service.BorrowService;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static int nextBookId = 1;
    private static int nextAuthorId = 1;
    private static int nextCategoryId = 1;

    public static void main(String[] args) {
        Library library = new Library();
        BorrowService borrowService = new BorrowService();
        Scanner scanner = new Scanner(System.in);

        // Örnek veriler ekle
        addSampleData(library);

        while (true) {
            showMenu();
            try {
                System.out.print("Seçiminiz: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 10) {
                    System.out.println("Program sonlandırılıyor...");
                    break;
                }

                processChoice(choice, scanner, library, borrowService);
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz!");
            } catch (Exception e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n=== KÜTÜPHANE SİSTEMİ ===");
        System.out.println("1. Yeni Kitap Ekle");
        System.out.println("2. Kitap Ara");
        System.out.println("3. Kitap Güncelle");
        System.out.println("4. Kitap Sil");
        System.out.println("5. Kategoriye Göre Kitapları Listele");
        System.out.println("6. Yazara Göre Kitapları Listele");
        System.out.println("7. Kitap Ödünç Al");
        System.out.println("8. Kitap İade Et");
        System.out.println("9. Tüm Kitapları Listele");
        System.out.println("10. Çıkış");
    }

    private static void processChoice(int choice, Scanner scanner, Library library, BorrowService borrowService) {
        switch (choice) {
            case 1:
                addNewBook(scanner, library);
                break;
            case 2:
                searchBook(scanner, library);
                break;
            case 3:
                updateBook(scanner, library);
                break;
            case 4:
                deleteBook(scanner, library);
                break;
            case 5:
                listBooksByCategory(scanner, library);
                break;
            case 6:
                listBooksByAuthor(scanner, library);
                break;
            case 7:
                borrowBook(scanner, library, borrowService);
                break;
            case 8:
                returnBook(scanner, library, borrowService);
                break;
            case 9:
                library.listAllBooks();
                break;
            default:
                System.out.println("Geçersiz seçim!");
        }
    }

    private static void addNewBook(Scanner scanner, Library library) {
        System.out.print("Kitap Başlığı: ");
        String title = scanner.nextLine();

        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();

        System.out.print("Kategori Adı: ");
        String categoryName = scanner.nextLine();

        Author author = library.findOrCreateAuthor(nextAuthorId, authorName);
        if (author.getId() == nextAuthorId) {
            nextAuthorId++; // Sadece yeni yazar oluşturulduğunda ID'yi artır
        }

        Category category = library.findOrCreateCategory(nextCategoryId++, categoryName, "");
        if (category.getId() == nextCategoryId) {
            nextCategoryId++; // Sadece yeni kategori oluşturulduğunda ID'yi artır
        }

        Book book = new Book(nextBookId++, title, author, category);
        library.addBook(book);
        System.out.println("Kitap eklendi.");
    }

    private static void searchBook(Scanner scanner, Library library) {
        System.out.println("1. ID ile ara");
        System.out.println("2. İsim ile ara");
        System.out.println("3. Yazar ile ara");
        System.out.print("Seçiminiz: ");

        int searchChoice = Integer.parseInt(scanner.nextLine());

        switch (searchChoice) {
            case 1:
                System.out.print("Kitap ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                Book book = library.findBookById(id);
                if (book != null) {
                    System.out.println(book);
                } else {
                    System.out.println("Kitap bulunamadı.");
                }
                break;
            case 2:
                System.out.print("Kitap Adı: ");
                String title = scanner.nextLine();
                List<Book> booksByTitle = library.searchBooksByTitle(title);
                if (!booksByTitle.isEmpty()) {
                    for (Book b : booksByTitle) {
                        System.out.println(b);
                    }
                } else {
                    System.out.println("Kitap bulunamadı.");
                }
                break;
            case 3:
                System.out.print("Yazar Adı: ");
                String authorName = scanner.nextLine();
                List<Book> booksByAuthor = library.searchBooksByAuthor(authorName);
                if (!booksByAuthor.isEmpty()) {
                    for (Book b : booksByAuthor) {
                        System.out.println(b);
                    }
                } else {
                    System.out.println("Kitap bulunamadı.");
                }
                break;
            default:
                System.out.println("Geçersiz seçim!");
        }
    }

    private static void updateBook(Scanner scanner, Library library) {
        System.out.print("Güncellenecek Kitap ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        System.out.print("Yeni Başlık: ");
        String newTitle = scanner.nextLine();

        System.out.print("Yeni Yazar Adı: ");
        String newAuthorName = scanner.nextLine();

        System.out.print("Yeni Kategori Adı: ");
        String newCategoryName = scanner.nextLine();

        Author newAuthor = new Author(nextAuthorId++, newAuthorName);
        Category newCategory = new Category(nextCategoryId++, newCategoryName, "");

        library.updateBook(bookId, newTitle, newAuthor, newCategory);
        System.out.println("Kitap güncellendi.");
    }

    private static void deleteBook(Scanner scanner, Library library) {
        System.out.print("Silinecek Kitap ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        if (library.removeBook(bookId)) {
            System.out.println("Kitap silindi.");
        } else {
            System.out.println("Kitap silinemedi.");
        }
    }

    private static void listBooksByCategory(Scanner scanner, Library library) {
        System.out.print("Kategori Adı: ");
        String categoryName = scanner.nextLine();

        List<Book> books = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getCategory().getName().equalsIgnoreCase(categoryName)) {
                books.add(book);
            }
        }

        if (!books.isEmpty()) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println("Bu kategoride kitap bulunamadı.");
        }
    }

    private static void listBooksByAuthor(Scanner scanner, Library library) {
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();

        List<Book> books = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                books.add(book);
            }
        }

        if (!books.isEmpty()) {
            for (Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        }
    }

    private static void borrowBook(Scanner scanner, Library library, BorrowService borrowService) {
        System.out.print("Kullanıcı ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.print("Kitap ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        double fee = borrowService.borrowBook(library, userId, bookId);
        if (fee > 0) {
            System.out.printf("Kitap ödünç alındı. Ücret: %.2f TL%n", fee);
        }
    }

    private static void returnBook(Scanner scanner, Library library, BorrowService borrowService) {
        System.out.print("Kullanıcı ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.print("Kitap ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        double refund = borrowService.returnBook(library, userId, bookId);
        if (refund > 0) {
            System.out.printf("Kitap iade edildi. İade edilen ücret: %.2f TL%n", refund);
        }
    }

    private static void addSampleData(Library library) {
        library.addUser(new Student(1, "Ali", "12345", "Bilgisayar"));
        library.addUser(new Teacher(2, "Ayşe", "Fen", "A1"));

        Author author = new Author(nextAuthorId++, "George Orwell");
        Category category = new Category(nextCategoryId++, "Roman", "");
        library.addBook(new Book(nextBookId++, "1984", author, category));
    }
}