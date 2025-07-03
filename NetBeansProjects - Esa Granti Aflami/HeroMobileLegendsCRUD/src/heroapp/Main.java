package heroapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

class MainFrame extends JFrame {
    private final HeroDatabase db = new HeroDatabase();
    private JTextField txtId, txtNama;
    private JComboBox<String> cmbKategori, cmbGender;
    private JTable tblHero;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("Hero Mobile Legends");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // === PANEL FORM (CENTERED) ===
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250)); // Margin kiri-kanan tengah

        txtId = new JTextField(15);
        txtNama = new JTextField(15);
        cmbKategori = new JComboBox<>(new String[]{"MAGE", "ASSASIN", "FIGHTER", "TANK", "MARKSMAN", "SUPPORT"});
        cmbGender = new JComboBox<>(new String[]{"MALE", "FEMALE"});

        formPanel.add(labelField("ID Hero:", txtId));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(labelField("Nama Hero:", txtNama));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(labelField("Kategori:", cmbKategori));
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(labelField("Gender:", cmbGender));
        formPanel.add(Box.createVerticalStrut(20));

        // === TOMBOL CRUD ===
        JPanel tombolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnTambah = new JButton("➕ Tambah");
        JButton btnUpdate = new JButton("✏️ Update");
        JButton btnHapus = new JButton("🗑️ Hapus");
        JButton btnReset = new JButton("🔄 Reset");

        tombolPanel.add(btnTambah);
        tombolPanel.add(btnUpdate);
        tombolPanel.add(btnHapus);
        tombolPanel.add(btnReset);
        formPanel.add(tombolPanel);

        // === TABEL DI BAWAH ===
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Kategori", "Gender"}, 0);
        tblHero = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblHero);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Hero"));

        // === ADD KE FRAME ===
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // === EVENT HANDLER ===
        btnTambah.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnHapus.addActionListener(e -> hapusData());
        btnReset.addActionListener(e -> clearForm());

        tblHero.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tblHero.getSelectedRow();
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
                cmbKategori.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                cmbGender.setSelectedItem(tableModel.getValueAt(row, 3).toString());
            }
        });

        loadTable();
    }

    private JPanel labelField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        for (Hero hero : db.ambilSemuaHero()) {
            tableModel.addRow(new Object[]{
                hero.getIdHero(), hero.getNamaHero(), hero.getKategori(), hero.getGender()
            });
        }
    }

    private void tambahData() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nama = txtNama.getText();
            String kategori = cmbKategori.getSelectedItem().toString();
            String gender = cmbGender.getSelectedItem().toString();

            Hero hero = new Hero(id, nama, kategori, gender);
            db.tambahHero(hero);
            loadTable();
            clearForm();
        } catch (Exception e) {
            showError("Tambah gagal:\n" + e.getMessage());
        }
    }

    private void updateData() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nama = txtNama.getText();
            String kategori = cmbKategori.getSelectedItem().toString();
            String gender = cmbGender.getSelectedItem().toString();

            Hero hero = new Hero(id, nama, kategori, gender);
            db.updateHero(hero);
            loadTable();
            clearForm();
        } catch (Exception e) {
            showError("Update gagal:\n" + e.getMessage());
        }
    }

    private void hapusData() {
        try {
            int id = Integer.parseInt(txtId.getText());
            db.hapusHero(id);
            loadTable();
            clearForm();
        } catch (Exception e) {
            showError("Hapus gagal:\n" + e.getMessage());
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        cmbKategori.setSelectedIndex(0);
        cmbGender.setSelectedIndex(0);
        tblHero.clearSelection();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
