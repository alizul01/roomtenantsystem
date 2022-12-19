package Koneksi;

import java.sql.*;

public class Koneksi {
    public Koneksi() throws SQLException {
        Statement state = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rtsdb", "root", "");
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
