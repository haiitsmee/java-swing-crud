# 💻 Java Swing CRUD - Aplikasi POS Sederhana

Aplikasi desktop CRUD untuk pengelolaan penjualan.  
Dibuat menggunakan **Java Swing + MySQL**.

## 📂 Fitur Utama
- Master Barang, Pelanggan, Pesanan
- Transaksi & Nota
- Laporan Penjualan
- Integrasi JasperReports

## 🚀 Cara Menjalankan

### 1. Clone Repo
```bash
git clone https://github.com/haiitsmee/java-swing-crud.git
```

### 2. Setup Database
- Buka folder database/ di repo ini
  → File yang dibutuhkan: java_swing_crud.sql
- Buka phpMyAdmin (atau MySQL Workbench, sesuai yang Anda gunakan)
- Buat database baru dengan nama: java_swing_crud
- Klik Import, lalu pilih file java_swing_crud.sql yang tadi diunduh.
  * Jika Anda memakai username/password yang berbeda dari root/kosong, silakan sesuaikan file Koneksi.java.

### 3. Cek Koneksi
Edit file src/koneksi/Koneksi.java
Pastikan koneksi database di file `Koneksi.java` sudah sesuai:

```java
   String url = "jdbc:mysql://localhost:3306/java_swing_crud";
   String user = "root";
   String pass = "";
```
### 4. Jalankan Aplikasi

Buka di NetBeans → Run → menuUtama.java
