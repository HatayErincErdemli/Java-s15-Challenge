package main;

import model.*;
import service.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Farklı kullanıcı türlerini ekle
        library.addUser(new Student(1, "Ali"));
        library.addUser(new Teacher(2, "Ayşe"));

        // Kitapları ekle
        library.addBook(new Book(1001, "Java Programming", "Author A", "Programming"));
        library.addBook(new Book(1002, "Clean Code", "Author B", "Programming"));

        // Kullanıcı girişlerini yakalamak için Scanner
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n== KÜTÜPHANE OTOMASYONU ==");
            System.out.println("1- Kitap Ekle");
            System.out.println("2- Kitap Sil");
            System.out.println("3- Kitap Ödünç Al");
            System.out.println("4- Kitap İade Et");
            System.out.println("5- Çıkış");
            System.out.print("Seçiminizi Yapın: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Kitap Başlığı: ");
                    String title = scanner.nextLine();
                    library.addBook(new Book(1, title, "Unknown", "General"));
                    System.out.println("Kitap başarıyla eklendi.");
                }
                case 3 -> {
                    System.out.print("Kullanıcı ID'si: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Kitap ID'si: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    try {
                        library.borrowBook(userId, bookId);
                        System.out.println("Kitap başarıyla ödünç alındı.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Çıkış yapılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }
}