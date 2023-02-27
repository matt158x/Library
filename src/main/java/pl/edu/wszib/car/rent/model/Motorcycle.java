/*package pl.edu.wszib.car.rent.model;

public class Motorcycle extends Book {
    private boolean cart;

    public Motorcycle(String title, String author, int isbn, double price, boolean cart) {
        super(title, author, isbn, price);
        this.cart = cart;
    }

    public Motorcycle(String title, String author, int isbn, double price, boolean rent, boolean cart) {
        super(title, author, isbn, price, rent);
        this.cart = cart;
    }

    public Motorcycle() {
    }

    public boolean isCart() {
        return cart;
    }

    public void setCart(boolean cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append(" Wozek: ")
                .append(this.isCart() ? "Tak" : "Nie")
                .toString();
    }
}
*/