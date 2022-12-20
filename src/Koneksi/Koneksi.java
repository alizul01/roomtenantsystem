package Koneksi;

import javax.swing.*;
import java.sql.*;

public class Koneksi {
    public Statement state = null;
    public Connection conn = null;
    public Koneksi() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rtsdb", "root", "");
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
}
