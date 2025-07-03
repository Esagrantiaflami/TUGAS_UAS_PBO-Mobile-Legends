package heroapp;

public class Hero {
    private int idHero;            // Sesuai kolom: id_hero (INT)
    private String namaHero;       // Sesuai kolom: nama_hero (VARCHAR)
    private String kategori;       // Sesuai kolom: kategori (ENUM)
    private String gender;         // Sesuai kolom: gender (ENUM)

    public Hero(int idHero, String namaHero, String kategori, String gender) {
        this.idHero = idHero;
        this.namaHero = namaHero;
        this.kategori = kategori;
        this.gender = gender;
    }

    public int getIdHero() {
        return idHero;
    }

    public void setIdHero(int idHero) {
        this.idHero = idHero;
    }

    public String getNamaHero() {
        return namaHero;
    }

    public void setNamaHero(String namaHero) {
        this.namaHero = namaHero;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "idHero=" + idHero +
                ", namaHero='" + namaHero + '\'' +
                ", kategori='" + kategori + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
