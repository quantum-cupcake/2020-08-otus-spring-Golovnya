package ye.golovnya.otusbookstore.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.service.BookService;

import java.util.Collection;
import java.util.stream.Collectors;

@ShellComponent
public class BookCrudCommands {

    private final BookService bookService;

    public BookCrudCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Register book", key = {"r", "register"})
    public String registerBook(String title, long authorId, long genreId) {
        bookService.createBook(title, authorId, genreId);
        return String.format("Зарегистрирована книга \"%s\"!", title);
    }

    @ShellMethod(value = "List all books", key = {"l", "list"})
    public String listBooks() {
        var books = bookService.getAllBooks();
        return booksToString(books);
    }

    @ShellMethod(value = "Find by title or id", key = {"f", "find"})
    public String getBook(String input, boolean byId) {
        if (byId) {
            var id = Long.parseLong(input);
            return String.format("Найдена книга: %s", bookService.getBook(id));
        }
        var books = bookService.getBooksByTitle(input);
        return booksToString(books);
    }

    @ShellMethod(value = "Update book", key = {"u", "update"})
    public String updateBook(Long bookId, String title, Long authorId, Long genreId) {
        bookService.updateBook(bookId, title, authorId, genreId);
        return String.format("Обновлены данные книги %d \"%s\"!", bookId, title);
    }

    @ShellMethod(value = "Delete by id", key = {"d", "delete"})
    public String deleteBook(@ShellOption Long bookId) {
        Book book = bookService.getBook(bookId);
        bookService.deleteBook(bookId);
        return String.format("Удалена книга %s", book);
    }

    private String booksToString(Collection<Book> books) {
        if (books.isEmpty()) {
            return "Книг не найдено!";
        } else {
            var output = books.stream().map(Book::toString).collect(Collectors.joining(System.lineSeparator()));
            return String.format("Найдены книги: %n%s", output);
        }
    }
}
