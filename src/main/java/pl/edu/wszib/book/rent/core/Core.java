package pl.edu.wszib.book.rent.core;

import pl.edu.wszib.book.rent.database.BookDAO;

import pl.edu.wszib.book.rent.gui.GUI;
import pl.edu.wszib.book.rent.model.User;

public class Core {
    final BookDAO bookDB = BookDAO.getInstance();
    final Authenticator authenticator = Authenticator.getInstance();
    final GUI gui = GUI.getInstance();
    private static final Core instance = new Core();

    private Core() {

    }
    public void start() {
        boolean isRunning = false;
        int counter = 0;

        while(!isRunning && counter < 3) {
            this.authenticator.authenticate(this.gui.readLoginAndPassword());
            isRunning = this.authenticator.getLoggedUser() != null;
            if(!isRunning) {
                System.out.println("Not authorized !!");
            }
            counter++;
        }

        while(isRunning) {
            switch(this.gui.showMenu()) {
                case "1":
                    this.gui.listThebook();
                    break;
                case "2":
                    this.gui.showRentResult(this.bookDB.rentBook(this.gui.readIsbn()));
                    break;
                case "3":
                    isRunning = false;
                    break;
                case "4":
                    if(this.authenticator.getLoggedUser() != null &&
                            this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN) {
                        this.bookDB.addBook(this.gui.readNewBookData());
                        break;
                    }
                case "5":
                    this.gui.searchBooks();
                    break;

                case "6":
                    this.gui.listRentedBooks();
                    break;
                default:
                    System.out.println("Wrong choose !!");
                    break;
            }
        }
    }

    public static Core getInstance() {
        return instance;
    }
}
