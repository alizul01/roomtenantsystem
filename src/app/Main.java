package app;

import Koneksi.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main extends JDialog {
    private JPanel contentPane;
    private JTextField tfNamaRuangan;
    private JTextField tfNamaPeminjam;
    private JTextField tfNoHp;
    private JButton deleteButton;
    private JButton updateButton;
    private JTable tableData;
    private JComboBox tfStatus;
    private JButton createRuanganButton;
    private JTextField tfTanggalPinjam;
    private JTextField tfSelesaiPinjam;
    private JComboBox<String> tfChooseRuangan;

    public Main() throws SQLException {
        setContentPane(contentPane);
        setModal(true);

        Koneksi koneksi = new Koneksi();
        koneksi.state = koneksi.conn.createStatement();
        String query = "SELECT * FROM ruangan";
        ResultSet result = koneksi.state.executeQuery(query);
        while (result.next()) {
            tfChooseRuangan.addItem(result.getString("nama_ruangan"));
        }

        String[] tableHead = {"Nama Ruangan", "Nama Peminjam", "No Hp", "Status", "Tanggal Pinjam", "Selesai Pinjam"};
        DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);
        tableData.setModel(tableModel);

        String query2 = "SELECT * FROM peminjaman";
        ResultSet result2 = koneksi.state.executeQuery(query2);
        while (result2.next()) {
            String namaRuangan = result2.getString("nama_ruangan");
            String namaPeminjam = result2.getString("nama_peminjam");
            String noHp = result2.getString("no_hp");
            String tanggalPinjam = result2.getString("tanggal_awal_pinjam");
            String selesaiPinjam = result2.getString("tanggal_akhir_pinjam");
            String[] data = {namaRuangan, namaPeminjam, noHp, tanggalPinjam, selesaiPinjam};
            tableModel.addRow(data);
        }

        tableData.getColumnModel().getColumn(0).setPreferredWidth(100);
        createRuanganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tambah tambah = new Tambah();
                tambah.pack();
                tambah.setVisible(true);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) throws SQLException {
        Main dialog = new Main();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
