# TUGAS_UAS_PBO MOBILE LEGENDS

Aplikasi ini dibuat sebagai tugas akhir mata kuliah **Pemrograman Berorientasi Objek (PBO)**.  
Aplikasi ini berbasis **Java Swing** dan terhubung dengan **database MySQL**, digunakan untuk mengelola data hero dari game **Mobile Legends**.

---

## 🧩 Fitur Aplikasi
- Menambahkan data hero
- Menampilkan semua data dalam tabel
- Mengedit data hero yang telah disimpan
- Menghapus data hero
- Reset form input
- Tampilan GUI rapi (versi **centered layout**)

---

## 💾 Struktur Database
- **Database**: `db_mobile_legends`
- **Tabel**: `tm_hero`
- **Kolom**:
  - `id_hero` (INT, Primary Key)
  - `nama_hero` (VARCHAR)
  - `kategori` (ENUM: MAGE, ASSASIN, FIGHTER, TANK, MARKSMAN, SUPPORT)
  - `gender` (ENUM: MALE, FEMALE)

---

## ⚙️ Teknologi
- Java (JDK 8+)
- Java Swing (GUI)
- MySQL (XAMPP/phpMyAdmin)
- JDBC (Java Database Connectivity)
- NetBeans IDE

---

## 📷 Tampilan Aplikasi
Form input dan tombol CRUD ditampilkan di bagian atas secara **terpusat**, sementara **tabel data berada di bawah**.  
Tampilan ini dirancang agar ringkas, mudah digunakan, dan cocok untuk keperluan presentasi UAS.

---

## 👨‍💻 Developer
**Nama**: Esa Granti Aflami  
**NIM**: SSI202303416  

---

> 📝 Aplikasi ini dikembangkan sebagai bagian dari pembelajaran OOP (Object-Oriented Programming) dan implementasi GUI & database di Java.
