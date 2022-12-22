package Main;

import Koneksi.Koneksi;
import app.Tambah;
import com.formdev.flatlaf.FlatIntelliJLaf;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RoomTenantSystem {
    private JPanel MainPanel;
    private JButton tambahRuanganBaruButton;
    private JButton hapusRuanganButton;
    private JTextField tfTujuanPeminjaman;
    private JTextField tfNamaPeminjam;
    private JTextField tfNoHp;
    private JComboBox<String> cbRuangan;
    private JTextField tfTanggalAwal;
    private JTextField tfTanggalAkhir;
    private JComboBox cbStatus;
    private JButton saveButton;
    private JTable table1;
    private JTextField tfIdSearchBar;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton showPeminjam;
    private JButton showRuangan;

    public Koneksi koneksi;

    public void tableLoad() throws SQLException {
        koneksi = new Koneksi();
        try {
            String sql = "SELECT * FROM peminjaman";
            ResultSet rs = koneksi.state.executeQuery(sql);
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("RoomTenantSystem");
        frame.setContentPane(new RoomTenantSystem().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public RoomTenantSystem() throws SQLException {
        tableLoad();
        try {
            koneksi = new Koneksi();
            koneksi.state = koneksi.conn.createStatement();
            ResultSet rs = koneksi.state.executeQuery("SELECT * FROM ruangan");
            while (rs.next()) {
                cbRuangan.addItem(rs.getString("nama_ruangan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tujuanPeminjaman, namaPeminjam, noHp, ruangan, tanggalAwal, tanggalAkhir, status;
                tujuanPeminjaman = tfTujuanPeminjaman.getText();
                namaPeminjam = tfNamaPeminjam.getText();
                noHp = tfNoHp.getText();
                ruangan = Objects.requireNonNull(cbRuangan.getSelectedItem()).toString();
                tanggalAwal = tfTanggalAwal.getText();
                tanggalAkhir = tfTanggalAkhir.getText();
                status = Objects.requireNonNull(cbStatus.getSelectedItem()).toString();

                if (status.equalsIgnoreCase("dipinjam")) {
                    status = "1";
                } else {
                    status = "0";
                }

                try {
                    koneksi = new Koneksi();
                    koneksi.state = koneksi.conn.createStatement();
                    String query = "INSERT INTO peminjaman (tujuan_peminjaman, nama_peminjam, no_hp, nama_ruangan, tanggal_awal_pinjam, tanggal_akhir_pinjam) VALUES ('" + tujuanPeminjaman + "', '" + namaPeminjam + "', '" + noHp + "', '" + ruangan + "', '" + tanggalAwal + "', '" + tanggalAkhir + "')";
                    String updateRuanganStatus = "UPDATE ruangan SET status = '" + status + "' WHERE nama_ruangan = '" + ruangan + "'";
                    koneksi.state.executeUpdate(query);
                    koneksi.state.executeUpdate(updateRuanganStatus);
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
//                reset input
                    tfTujuanPeminjaman.setText("");
                    tfNamaPeminjam.setText("");
                    tfNoHp.setText("");
                    cbRuangan.setSelectedIndex(0);
                    tfTanggalAwal.setText("");
                    tfTanggalAkhir.setText("");
                    cbStatus.setSelectedIndex(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal ditambahkan");
                    throw new RuntimeException(ex);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfIdSearchBar.getText();
                String tujuanPeminjaman, namaPeminjam, noHp, ruangan, tanggalAwal, tanggalAkhir, status;
                tujuanPeminjaman = tfTujuanPeminjaman.getText();
                namaPeminjam = tfNamaPeminjam.getText();
                noHp = tfNoHp.getText();
                ruangan = Objects.requireNonNull(cbRuangan.getSelectedItem()).toString();
                tanggalAwal = tfTanggalAwal.getText();
                tanggalAkhir = tfTanggalAkhir.getText();
                status = Objects.requireNonNull(cbStatus.getSelectedItem()).toString();

                if (status.equalsIgnoreCase("dipinjam")) {
                    status = "1";
                } else {
                    status = "0";
                }

                try {
                    koneksi = new Koneksi();
                    koneksi.state = koneksi.conn.createStatement();
                    String query = "UPDATE peminjaman SET tujuan_peminjaman = '" + tujuanPeminjaman + "', nama_peminjam = '" + namaPeminjam + "', no_hp = '" + noHp + "', nama_ruangan = '" + ruangan + "', tanggal_awal_pinjam = '" + tanggalAwal + "', tanggal_akhir_pinjam = '" + tanggalAkhir + "' WHERE id = '" + id + "'";
                    String updateRuanganStatus = "UPDATE ruangan SET status = '" + status + "' WHERE nama_ruangan = '" + ruangan + "'";
                    koneksi.state.executeUpdate(query);
                    koneksi.state.executeUpdate(updateRuanganStatus);
                    JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == 1) {
                    return;
                }
                String id = tfIdSearchBar.getText();
                try {
                    koneksi = new Koneksi();
                    koneksi.state = koneksi.conn.createStatement();
                    String query = "DELETE FROM peminjaman WHERE id = '" + id + "'";
                    koneksi.state.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        showRuangan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    koneksi = new Koneksi();
                    koneksi.state = koneksi.conn.createStatement();
                    String query = "SELECT * FROM ruangan";
                    ResultSet rs = koneksi.state.executeQuery(query);
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        showPeminjam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    koneksi = new Koneksi();
                    koneksi.state = koneksi.conn.createStatement();
                    String query = "SELECT * FROM peminjaman";
                    ResultSet rs = koneksi.state.executeQuery(query);
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfIdSearchBar.getText();
                try {
                    koneksi = new Koneksi();
                    koneksi.state = koneksi.conn.createStatement();
                    String query = "SELECT * FROM peminjaman WHERE id = '" + id + "'";
                    ResultSet rs = koneksi.state.executeQuery(query);
                    String nama_ruangan = "";
//                    get status from table ruangan, get other data from table peminjaman
                    while (rs.next()) {
                        nama_ruangan = rs.getString("nama_ruangan");
                        tfTujuanPeminjaman.setText(rs.getString("tujuan_peminjaman"));
                        tfNamaPeminjam.setText(rs.getString("nama_peminjam"));
                        tfNoHp.setText(rs.getString("no_hp"));
                        tfTanggalAwal.setText(rs.getString("tanggal_awal_pinjam"));
                        tfTanggalAkhir.setText(rs.getString("tanggal_akhir_pinjam"));
                    }
                    String query2 = "SELECT * FROM ruangan WHERE nama_ruangan = '" + nama_ruangan + "'";
                    ResultSet rs2 = koneksi.state.executeQuery(query2);
                    while (rs2.next()) {
                        String status = rs2.getString("status");
                        if (status.equalsIgnoreCase("1")) {
                            cbStatus.setSelectedIndex(0);
                        } else {
                            cbStatus.setSelectedIndex(1);
                        }
                    }


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

//                fill the text field

            }
        });
        tambahRuanganBaruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tambah ruanganBaru = new Tambah();
                ruanganBaru.setVisible(true);
            }
        });
    }
}