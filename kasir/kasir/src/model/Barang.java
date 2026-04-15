package model;

public class Barang {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Barang(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getters
    public String getKode()  { return kode; }
    public String getNama()  { return nama; }
    public double getHarga() { return harga; }
    public int getStok()     { return stok; }

    // Setter stok untuk update setelah transaksi
    public void setStok(int stok) { this.stok = stok; }

    @Override
    public String toString() {
        return String.format("[%s] %-20s Rp%,.0f  (Stok: %d)", kode, nama, harga, stok);
    }
}
