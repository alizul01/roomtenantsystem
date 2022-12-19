package test;

// Nama file : TabelPenjualanNotebook3.java
// Penggunaan table column model untuk menghapus dan memindahkan kolom

// Mengimpor kelas
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

// Deklarasi kelas
public class TabelPenjualanNotebook3 extends JFrame {

    // Membuat nama kolom tabel
    private String[] kolomTabel =
            {"Tipe", "Warna", "Sistem Operasi", "Jumlah Terjual", "Harga Satuan"};

    // Membuat data tabel
    private Object[][] dataTabel = {
            {"Satellite L630-1031U", "Black", new Boolean(false), new Integer(3),
                    new Integer(6300000)},
            {"Satellite L645-1043XB", "Brown", new Boolean(true), new Integer(7),
                    new Integer (7500000)},
            {"Qosmio X500-D832", "Black", new Boolean(true), new Integer(4),
                    new Integer(14000000)},
            {"Portege T210-1014UR", "Red", new Boolean(true), new Integer(6),
                    new Integer(6800000)},
            {"Libretto W100-1002U", "Black", new Boolean(true), new Integer(1),
                    new Integer(12000000)}
    };

    // Membuat table model
    private DefaultTableModel tModel = new DefaultTableModel(dataTabel,
            kolomTabel);

    // Membuat tabel
    private JTable tabel = new JTable(tModel);

    // Membuat obyek check box
    private JCheckBox grsVertikal = new JCheckBox(
            "Tampilkan garis vertikal", true);
    private JCheckBox grsHorisontal = new JCheckBox(
            "Tampilkan garis Horisontal", true);

    // Membuat obyek button (tombol)
    private JButton hapus = new JButton("Hapus kolom");
    private JButton pindah = new JButton("Pindahkan kolom ke posisi kedua");

    // Konstruktor tanpa argumen
    public TabelPenjualanNotebook3() {

        // Menginisialisasi tabel
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.setColumnSelectionAllowed(true);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Mengelompokkan obyek check box
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(grsVertikal);
        panel1.add(grsHorisontal);

        // Mengelompokkan obyek tombol
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panel2.add(hapus);
        panel2.add(pindah);

        getContentPane().add(panel1, BorderLayout.PAGE_START);
        getContentPane().add(new JScrollPane(tabel), BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.PAGE_END);

        // Penanganan event check box
        grsVertikal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tabel.setShowVerticalLines(grsVertikal.isSelected());
            }
        });

        grsHorisontal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tabel.setShowHorizontalLines(grsHorisontal.isSelected());
            }
        });

        // Penanganan event button
        hapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (tabel.getSelectedColumn() >= 0) {
                    TableColumnModel modelKolom = tabel.getColumnModel();
                    TableColumn kolomDihapus =
                            modelKolom.getColumn(tabel.getSelectedColumn());
                    modelKolom.removeColumn(kolomDihapus);
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "Pilih kolom yang akan dihapus!", "Hapus Kolom",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pindah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (tabel.getSelectedColumn() >= 0) {
                    int indekLama = tabel.getSelectedColumn();
                    int indekBaru = 1;
                    TableColumnModel modelKolom = tabel.getColumnModel();
                    modelKolom.moveColumn(indekLama, indekBaru);
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "Pilih kolom yang akan dipindah!", "Pindah Kolom",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Metoda main
    public static void main(String[] args) {
        TabelPenjualanNotebook3 frame = new TabelPenjualanNotebook3();
        frame.setTitle("Kelas TabelPenjualanNotebook3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(440, 200);
        frame.setVisible(true);
    }
}
