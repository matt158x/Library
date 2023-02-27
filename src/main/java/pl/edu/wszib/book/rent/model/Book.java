package pl.edu.wszib.book.rent.model;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean rent;



    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.rent = false;
    }

    public Book(String title, String author, int isbn, boolean rent) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.rent = rent;
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
                .toString();
    }

}
