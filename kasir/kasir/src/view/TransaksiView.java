package view;

import model.Transaksi;
import service.TransaksiService;

import java.util.List;
import java.util.Scanner;

public class TransaksiView {

    private TransaksiService service;
    private Scanner scanner;

    public TransaksiView(TransaksiService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    // ------------------------------------------------------------------ //
    //  ENTRY POINT
    // ------------------------------------------------------------------ //
    public void jalankan() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║     SISTEM KASIR - TOKO MAJU     ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean running = true;
        while (running) {
            tampilMenu();
            int pilihan = inputInt("Pilih menu: ");
            switch (pilihan) {
                case 1  -> tampilDaftarBarang();
                case 2  -> tambahBarang();
                case 3  -> tampilKeranjang();
                case 4  -> prosesBayar();
                case 5  -> batalTransaksi();
                case 0  -> { System.out.println("\nTerima kasih! Sampai jumpa 👋"); running = false; }
                default -> System.out.println("⚠️  Pilihan tidak valid.");
            }
        }
    }

    // ------------------------------------------------------------------ //
    //  MENU
    // ------------------------------------------------------------------ //
    private void tampilMenu() {
        System.out.println("\n┌─────────────────────────────┐");
        System.out.println("│           MENU UTAMA        │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│  1. Lihat Daftar Barang     │");
        System.out.println("│  2. Tambah Barang ke Keranjang │");
        System.out.println("│  3. Lihat Keranjang         │");
        System.out.println("│  4. Bayar                   │");
        System.out.println("│  5. Batalkan Transaksi      │");
        System.out.println("│  0. Keluar                  │");
        System.out.println("└─────────────────────────────┘");
    }

    // ------------------------------------------------------------------ //
    //  TAMPIL DAFTAR BARANG
    // ------------------------------------------------------------------ //
    private void tampilDaftarBarang() {
        System.out.println("\n=== DAFTAR BARANG TERSEDIA ===");
        service.getDaftarBarang().forEach(System.out::println);
    }

    // ------------------------------------------------------------------ //
    //  TAMBAH BARANG
    // ------------------------------------------------------------------ //
    private void tambahBarang() {
        tampilDaftarBarang();
        System.out.print("\nMasukkan kode barang : ");
        String kode   = scanner.nextLine().trim();
        int    jumlah = inputInt("Masukkan jumlah     : ");

        String hasil = service.tambahKeKeranjang(kode, jumlah);
        System.out.println(hasil);
    }

    // ------------------------------------------------------------------ //
    //  TAMPIL KERANJANG
    // ------------------------------------------------------------------ //
    private void tampilKeranjang() {
        List<Transaksi> keranjang = service.getKeranjang();
        if (keranjang.isEmpty()) {
            System.out.println("\n🛒 Keranjang masih kosong.");
            return;
        }

        System.out.println("\n=== ISI KERANJANG ===");
        System.out.println("─".repeat(45));
        int no = 1;
        for (Transaksi t : keranjang) {
            System.out.printf("%2d. %s%n", no++, t);
        }
        System.out.println("─".repeat(45));
        System.out.printf("    %-24s Rp%,.0f%n", "TOTAL", service.hitungTotal());
    }

    // ------------------------------------------------------------------ //
    //  PROSES BAYAR
    // ------------------------------------------------------------------ //
    private void prosesBayar() {
        if (service.getKeranjang().isEmpty()) {
            System.out.println("⚠️  Keranjang kosong, tidak ada yang dibayar.");
            return;
        }

        tampilKeranjang();
        double bayar = inputDouble("\nMasukkan uang bayar: Rp");
        double kembalian = service.prosesPembayaran(bayar);

        if (kembalian < 0) {
            System.out.printf("❌ Uang tidak cukup! Kurang Rp%,.0f%n",
                    service.hitungTotal() - bayar);
            return;
        }

        cetakStruk(bayar, kembalian);
        service.resetKeranjang();
    }

    // ------------------------------------------------------------------ //
    //  STRUK
    // ------------------------------------------------------------------ //
    private void cetakStruk(double bayar, double kembalian) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║            STRUK BELANJA         ║");
        System.out.println("║         Toko Maju - Kasir        ║");
        System.out.println("╠══════════════════════════════════╣");
        for (Transaksi t : service.getKeranjang()) {
            System.out.println("║  " + t);
        }
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf ("║  %-16s  Rp%,10.0f  ║%n", "Total",     service.hitungTotal());
        System.out.printf ("║  %-16s  Rp%,10.0f  ║%n", "Bayar",     bayar);
        System.out.printf ("║  %-16s  Rp%,10.0f  ║%n", "Kembalian", kembalian);
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║     Terima kasih telah berbelanja ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    // ------------------------------------------------------------------ //
    //  BATAL TRANSAKSI
    // ------------------------------------------------------------------ //
    private void batalTransaksi() {
        if (service.getKeranjang().isEmpty()) {
            System.out.println("⚠️  Tidak ada transaksi yang aktif.");
            return;
        }
        System.out.print("Yakin ingin membatalkan transaksi? (y/n): ");
        String jawab = scanner.nextLine().trim();
        if (jawab.equalsIgnoreCase("y")) {
            service.batalTransaksi();
            System.out.println("✅ Transaksi dibatalkan. Stok telah dikembalikan.");
        } else {
            System.out.println("Pembatalan dibatalkan.");
        }
    }

    // ------------------------------------------------------------------ //
    //  HELPER INPUT
    // ------------------------------------------------------------------ //
    private int inputInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("⚠️  Masukkan angka yang valid."); }
        }
    }

    private double inputDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("⚠️  Masukkan angka yang valid."); }
        }
    }
}
