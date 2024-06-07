import java.util.ArrayList;
import java.util.List;

public class LMS {
    private static List<Book> library = new ArrayList<>();

    public static void addBook(String title, String author) {
        Book book = new Book(title, author);
        library.add(book);
    }

    public static void removeBook(String title, String author) {
        Book bookToRemove = null;
        for (Book book : library) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            library.remove(bookToRemove);
        }
    }

    public static List<Book> getLibrary() {
        return library;
    }

    private static class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }
    }
}