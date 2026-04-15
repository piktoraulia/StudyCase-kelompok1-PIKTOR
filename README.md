# README --- Sistem Kasir OOP Java

## Deskripsi Proyek

Program ini adalah Sistem Kasir Sederhana berbasis Java OOP untuk
melakukan transaksi penjualan barang di toko.

Konsep OOP yang digunakan: - Class dan Object\
- Encapsulation\
- Separation of Responsibility\
- List Collection\
- MVC Sederhana (Model--Service--View)

Struktur Program:

Main.java\
model/ ├── Barang.java\
└── Transaksi.java\
service/ └── TransaksiService.java\
view/ └── TransaksiView.java

------------------------------------------------------------------------

# Penjelasan Class

## 1. Class Main

File: Main.java

Fungsi:\
Class utama untuk menjalankan program.

Method: public static void main(String\[\] args)\
Menjalankan program dengan membuat object TransaksiService dan
TransaksiView.

------------------------------------------------------------------------

## 2. Class Barang

File: model/Barang.java

Fungsi:\
Menyimpan data barang.

Atribut: - kode : String\
- nama : String\
- harga : double\
- stok : int

Method: - Barang(String kode, String nama, double harga, int stok) →
Constructor\
- getKode() → Mengambil kode barang\
- getNama() → Mengambil nama barang\
- getHarga() → Mengambil harga barang\
- getStok() → Mengambil stok barang\
- setStok(int stok) → Mengubah stok\
- toString() → Menampilkan data barang

------------------------------------------------------------------------

## 3. Class Transaksi

File: model/Transaksi.java

Fungsi:\
Menyimpan data transaksi.

Atribut: - barang : Barang\
- jumlah : int\
- subtotal : double

Method: - Transaksi(Barang barang, int jumlah) → Constructor\
- getBarang()\
- getJumlah()\
- getSubtotal()\
- toString()

------------------------------------------------------------------------

## 4. Class TransaksiService

File: service/TransaksiService.java

Fungsi:\
Mengelola proses transaksi.

Atribut: - List`<Barang>`{=html} daftarBarang\
- List`<Transaksi>`{=html} keranjang

Method: - TransaksiService() → Mengisi data awal\
- getDaftarBarang() → Mengambil daftar barang\
- cariBarang(String kode) → Mencari barang\
- tambahKeKeranjang(String kode, int jumlah)\
- getKeranjang()\
- hitungTotal()\
- prosesPembayaran(double uangBayar)\
- resetKeranjang()\
- batalTransaksi()

------------------------------------------------------------------------

## 5. Class TransaksiView

File: view/TransaksiView.java

Fungsi:\
Menangani tampilan menu dan input user.

Atribut: - TransaksiService service\
- Scanner scanner

Method: - jalankan()\
- tampilMenu()\
- tampilDaftarBarang()\
- tambahBarang()\
- tampilKeranjang()\
- prosesBayar()\
- cetakStruk(double bayar, double kembalian)\
- batalTransaksi()\
- inputInt(String prompt)\
- inputDouble(String prompt)

------------------------------------------------------------------------

# Cara Menjalankan Program

Compile: javac Main.java

Jalankan: java Main

------------------------------------------------------------------------

# Kesimpulan

Program ini merupakan implementasi Sistem Kasir berbasis Java OOP dengan
pemisahan Model, Service, dan View sehingga kode lebih rapi dan mudah
dikembangkan.
