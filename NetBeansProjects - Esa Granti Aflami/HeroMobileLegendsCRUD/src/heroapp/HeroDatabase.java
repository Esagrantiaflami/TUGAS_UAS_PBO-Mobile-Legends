package heroapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDatabase {
    // Ganti dengan nama database baru kamu
    private static final String URL = "jdbc:mysql://localhost:3306/db_mobile_legends";
    private static final String USER = "root";
    private static final String PASS = "";

    // Koneksi ke database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Tambah data hero
    public void tambahHero(Hero hero) throws SQLException {
        String sql = "INSERT INTO tm_hero (id_hero, nama_hero, kategori, gender) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hero.getIdHero());
            stmt.setString(2, hero.getNamaHero());
            stmt.setString(3, hero.getKategori());
            stmt.setString(4, hero.getGender());
            stmt.executeUpdate();
        }
    }

    // Ambil semua data hero
    public List<Hero> ambilSemuaHero() {
        List<Hero> daftar = new ArrayList<>();
        String sql = "SELECT * FROM tm_hero";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hero hero = new Hero(
                    rs.getInt("id_hero"),
                    rs.getString("nama_hero"),
                    rs.getString("kategori"),
                    rs.getString("gender")
                );
                daftar.add(hero);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil data: " + e.getMessage());
        }

        return daftar;
    }

    // Update data hero
    public void updateHero(Hero hero) throws SQLException {
        String sql = "UPDATE tm_hero SET nama_hero = ?, kategori = ?, gender = ? WHERE id_hero = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hero.getNamaHero());
            stmt.setString(2, hero.getKategori());
            stmt.setString(3, hero.getGender());
            stmt.setInt(4, hero.getIdHero());
            stmt.executeUpdate();
        }
    }

    // Hapus hero berdasarkan ID
    public void hapusHero(int id) throws SQLException {
        String sql = "DELETE FROM tm_hero WHERE id_hero = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
