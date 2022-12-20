package app;

import Koneksi.Koneksi;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Tambah extends JDialog {
    private JPanel contentTambah;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfRuangan;
    private JTextField tfLokasi;

    public Tambah() {
        setContentPane(contentTambah);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onCancel();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    onCancel();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // call onCancel() on ESCAPE
        contentTambah.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onCancel();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String queryCreateNewRuangan = "INSERT INTO ruangan (nama_ruangan, lokasi_ruangan, status) VALUES ('" + tfRuangan.getText() + "', '" + tfLokasi.getText() + "', '0')";
        try {
            Koneksi koneksi = new Koneksi();
            koneksi.state.executeUpdate(queryCreateNewRuangan);
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan");
        }
        dispose();
    }

    private void onCancel() throws SQLException {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) throws SQLException {
        Koneksi koneksi = new Koneksi();
        Tambah dialog = new Tambah();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
