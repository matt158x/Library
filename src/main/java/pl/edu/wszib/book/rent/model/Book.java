package pl.edu.wszib.book.rent.model;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean rent;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;


    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }


    public Book(String title, String author, int isbn, boolean rent, String user) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.rent = rent;
        this.user = user;
    }

    public Book(String title, String author, int isbn, boolean rent, String user, LocalDate date) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.rent = rent;
        this.user = user;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getTitle())
                .append(" ")
                .append(this.getAuthor())
                .append(" ISBN: ")
                .append(this.getIsbn())
                .append(" Dostepny: ")
                .append(this.isRent() ? "Nie" : "Tak")
                .append( " Data oddania: ")
                .append( this.getDate())
                .append(" Username: ")
                .append(this.getUser())
                .toString();
    }

}
