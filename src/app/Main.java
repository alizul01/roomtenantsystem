package app;

import Koneksi.Koneksi;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.time.chrono.JapaneseDate;


public class Main {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField3;

    public Main() {

    }

    public static void main(String[] args) throws SQLException {
        Koneksi koneksi = new Koneksi();
        

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}
