package pl.edu.wszib.book.rent.model;

import java.time.LocalDate;

public class Thebook extends Book {

    public Thebook(String title, String author, int isbn) {
        super(title, author, isbn);
    }

    public Thebook(String title, String author, int isbn, boolean rent, String user) {
        super(title, author, isbn, rent, user);
    }

    public Thebook(String title, String author, int isbn, boolean rent, String user, LocalDate date) {
        super(title, author, isbn, rent,user,date);
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
