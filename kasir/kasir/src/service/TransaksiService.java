package service;

import model.Barang;
import model.Transaksi;

import java.util.ArrayList;
import java.util.List;

public class TransaksiService {

    // Daftar barang yang tersedia di toko
    private List<Barang> daftarBarang = new ArrayList<>();

    // Keranjang belanja sesi ini
    private List<Transaksi> keranjang = new ArrayList<>();

    public TransaksiService() {
        // Seed data barang
        daftarBarang.add(new Barang("B001", "Indomie Goreng",    3_500,  100));
        daftarBarang.add(new Barang("B002", "Aqua 600ml",        4_000,  50));
        daftarBarang.add(new Barang("B003", "Roti Tawar Sari",  12_000,  30));
        daftarBarang.add(new Barang("B004", "Susu Ultra 250ml",  5_500,  60));
        daftarBarang.add(new Barang("B005", "Teh Botol 350ml",   4_500,  80));
    }

    /** Kembalikan semua barang yang tersedia. */
    public List<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    /**
     * Cari barang berdasarkan kode.
     * @return Barang jika ditemukan, null jika tidak.
     */
    public Barang cariBarang(String kode) {
        for (Barang b : daftarBarang) {
            if (b.getKode().equalsIgnoreCase(kode)) return b;
        }
        return null;
    }

    /**
     * Tambahkan item ke keranjang belanja.
     * @return pesan hasil (sukses / error)
     */
    public String tambahKeKeranjang(String kode, int jumlah) {
        Barang barang = cariBarang(kode);
        if (barang == null)             return "❌ Kode barang tidak ditemukan.";
        if (jumlah <= 0)                return "❌ Jumlah harus lebih dari 0.";
        if (jumlah > barang.getStok())  return "❌ Stok tidak cukup. Stok tersedia: " + barang.getStok();

        // Kurangi stok sementara
        barang.setStok(barang.getStok() - jumlah);
        keranjang.add(new Transaksi(barang, jumlah));
        return "✅ " + barang.getNama() + " x" + jumlah + " ditambahkan ke keranjang.";
    }

    /** Kembalikan isi keranjang. */
    public List<Transaksi> getKeranjang() {
        return keranjang;
    }

    /** Hitung total belanja. */
    public double hitungTotal() {
        return keranjang.stream().mapToDouble(Transaksi::getSubtotal).sum();
    }

    /**
     * Proses pembayaran.
     * @return kembalian, atau -1 jika uang tidak cukup.
     */
    public double prosesPembayaran(double uangBayar) {
        double total = hitungTotal();
        if (uangBayar < total) return -1;
        return uangBayar - total;
    }

    /** Reset keranjang untuk transaksi baru. */
    public void resetKeranjang() {
        keranjang.clear();
    }

    /** Batalkan seluruh transaksi & kembalikan stok. */
    public void batalTransaksi() {
        for (Transaksi t : keranjang) {
            Barang b = t.getBarang();
            b.setStok(b.getStok() + t.getJumlah());
        }
        keranjang.clear();
    }
}
