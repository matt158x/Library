package pl.edu.wszib.car.rent.model;

public class Thebook extends Book {

    public Thebook(String title, String author, int isbn) {
        super(title, author, isbn);
    }

    public Thebook(String title, String author, int isbn, boolean rent) {
        super(title, author, isbn, rent);
    }

    public Thebook() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
