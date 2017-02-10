package shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseSQL implements Base {

    private Connection conn;

    public BaseSQL() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/candy_shop?" +
                    "user=root&password=password&autoReconnect=true&useSSL=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sweet> getSweets() {

        List<Sweet> sweets = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM sweet");

            while (rs.next()) {
                Sweet sweet = new Sweet();
                sweet.setName(rs.getString("name"));
                sweet.setPrice(rs.getInt("price"));
                sweet.setInStock(rs.getInt("inStock"));
                sweets.add(sweet);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }
            }
        }

        return sweets;
    }
}
