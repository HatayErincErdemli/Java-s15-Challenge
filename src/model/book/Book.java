package model.book;

public class Book {
    private int id;
    private String title;
    private Author author;
    private Category category;
    private boolean isAvailable;

    public Book(int id, String title, Author author, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
        author.addBook(this);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void updateDetails(String title, Author author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Kitap[ID=" + id + ", Başlık='" + title + "', Yazar='" +
                author.getName() + "', Kategori='" + category.getName() +
                "', Mevcut=" + isAvailable + "]";
    }
}