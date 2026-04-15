package model;

public class Transaksi {
    private Barang barang;
    private int jumlah;
    private double subtotal;

    public Transaksi(Barang barang, int jumlah) {
        this.barang   = barang;
        this.jumlah   = jumlah;
        this.subtotal = barang.getHarga() * jumlah;
    }

    // Getters
    public Barang getBarang()   { return barang; }
    public int getJumlah()      { return jumlah; }
    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return String.format("%-20s x%-3d  Rp%,.0f",
                barang.getNama(), jumlah, subtotal);
    }
}
