package pl.edu.wszib.book.rent.gui;

import pl.edu.wszib.book.rent.core.Authenticator;
import pl.edu.wszib.book.rent.database.BookDAO;
import pl.edu.wszib.book.rent.model.Book;
import pl.edu.wszib.book.rent.model.Thebook;
import pl.edu.wszib.book.rent.model.User;

//import pl.edu.wszib.car.rent.model.*;

import java.util.List;
import java.util.Scanner;

public class GUI {
    private final Scanner scanner = new Scanner(System.in);
    final Authenticator authenticator = Authenticator.getInstance();
    final BookDAO bookDB = BookDAO.getInstance();
    private static final GUI instance = new GUI();

    private GUI() {
    }

    public String showMenu() {
        System.out.println("1. List books");
        System.out.println("2. Rent books");
        System.out.println("3. Exit");
        if(this.authenticator.getLoggedUser() != null &&
                this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
            System.out.println("4. Add book");
        }
        System.out.println("5. Search books");
        System.out.println("6. List loaned books");
        return scanner.nextLine();
    }

    public void listThebook() {
        for(Book book : this.bookDB.getBooks()) {
            System.out.println(book);
        }
    }

    public String readIsbn() {
        System.out.println("ISBN:");
        return this.scanner.nextLine();
    }

    public void showRentResult(boolean result) {
        if(result) {
            System.out.println("Rent successful");
        } else {
            System.out.println("ISBN does not exist or book is already rent");
        }
    }

    public User readLoginAndPassword() {
        User user = new User();
        System.out.println("Login:");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Password:");
        user.setPassword(this.scanner.nextLine());
        return user;
    }

    public Book readNewBookData() {
        System.out.println("Title:");
        String title = this.scanner.nextLine();
        System.out.println("Author:");
        String author = this.scanner.nextLine();
        System.out.println("ISBN:");
        int isbn = Integer.parseInt(this.scanner.nextLine());


                return new Thebook(title, author, isbn);


    }

    public void searchBooks() {
        System.out.println("Enter a part of the book title, author or ISBN:");
        String query = scanner.nextLine();
        List<Book> result = this.bookDB.getSearchBooks(query);
        if(result.isEmpty()) {
            System.out.println("No books found");
        } else {
            System.out.println("Search results:");
            for(Book book : result) {
                System.out.println(book);
            }
        }
    }

    public void listRentedBooks() {
        List<Book> rentedBooks = this.bookDB.getRentedBooks();
        if(rentedBooks.isEmpty()) {
            System.out.println("No books currently rented");
        } else {
            System.out.println("Rented books:");
            for(Book book : rentedBooks) {
                System.out.println(book);
            }
        }
    }

    public static GUI getInstance() {
        return instance;
    }
}
