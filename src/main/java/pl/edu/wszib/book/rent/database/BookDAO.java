package pl.edu.wszib.book.rent.database;
import pl.edu.wszib.book.rent.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final BookDAO instance = new BookDAO();

    private final Connection connection;

    private BookDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookrent",
                    "root",
                    "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getBooks() {
        ArrayList<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int isbn = rs.getInt("isbn");
                boolean rent = rs.getBoolean("rent");
                Book book = new Book(title,author,isbn,rent);
                result.add(book);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Book> getSearchBooks(String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int isbn = rs.getInt("isbn");
                boolean rent = rs.getBoolean("rent");
                Book book = new Book(title, author, isbn, rent);
                result.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



    public boolean rentBook(String isbn) {
        try {
            String sql = "SELECT * FROM tbook WHERE isbn = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                boolean rent = rs.getBoolean("rent");
                if (!rent) {
                    String updateSql = "UPDATE tbook SET rent = ?, rent_end = ? WHERE id = ?";
                    int bookId = rs.getInt("id");

                    // calculate rent_end date
                    LocalDate rentEnd = LocalDate.now().plusWeeks(2);

                    PreparedStatement updatePs = this.connection.prepareStatement(updateSql);
                    updatePs.setBoolean(1, true);
                    updatePs.setDate(2, Date.valueOf(rentEnd));
                    updatePs.setInt(3, bookId);

                    updatePs.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addBook(Book book) {


        try {
            String sql = "INSERT INTO tbook " +
                    "(title, author, isbn, rent) VALUES (?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getIsbn());
            ps.setBoolean(4, book.isRent());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Book> getRentedBooks() {
        ArrayList<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook WHERE rent = true";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int isbn = rs.getInt("isbn");
                boolean rent = rs.getBoolean("rent");
                Book book = new Book(title, author, isbn, rent);
                result.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static BookDAO getInstance() {
        return instance;
    }
}
