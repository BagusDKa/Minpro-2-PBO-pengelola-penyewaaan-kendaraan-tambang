import java.util.Scanner
 
public class Main {
 
    private static Scanner scanner = new Scanner(System.in);
    private static PengelolaKendaraan pengelola = new PengelolaKendaraan();
 
    public static void main(String[] args) {
        int pilihan;
 
        do {
            tampilkanMenu();
            pilihan = bacaPilihanMenu();
 
            switch (pilihan) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    pengelola.tampilkanSemuaKendaraan();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    hapusData();
                    break;
                case 5:
                    System.out.println("Terima kasih, program selesai.");
                    break;
            }
        } while (pilihan != 5);
    }
 
    private static void tampilkanMenu() {
        System.out.println();
        System.out.println("=== SISTEM PENYEWAAN KENDARAAN TAMBANG ===");
        System.out.println("1. Tambah Data Kendaraan");
        System.out.println("2. Tampilkan Semua Kendaraan");
        System.out.println("3. Update Data Kendaraan");
        System.out.println("4. Hapus Data Kendaraan");
        System.out.println("5. Keluar");
    }
 
    private static int bacaPilihanMenu() {
        return bacaIntRentang("Pilih menu (1-5): ", 1, 5);
    }
 
    private static void tambahData() {
        System.out.println("\n--- Tambah Data Kendaraan ---");
        System.out.println("Jenis kendaraan:");
        System.out.println("1. Dump Truck");
        System.out.println("2. Excavator");
        int jenis = bacaIntRentang("Pilih jenis (1-2): ", 1, 2);
 
        String id = bacaStringTidakKosong("ID Kendaraan       : ");
        if (pengelola.cariIndexById(id) != -1) {
            System.out.println("Gagal: ID sudah dipakai.");
            return;
        }
        String nama = bacaStringTidakKosong("Nama Kendaraan    : ");
        double harga = bacaDoublePositif("Harga Sewa / Hari : ");
 
        KendaraanTambang kendaraan;
        if (jenis == 1) {
            double kapasitas = bacaDoublePositif("Kapasitas (ton)   : ");
            kendaraan = new DumpTruck(id, nama, harga, kapasitas);
        } else {
            double kapasitas = bacaDoublePositif("Kapasitas bucket (m3): ");
            kendaraan = new Excavator(id, nama, harga, kapasitas);
        }
 
        if (pengelola.tambahKendaraan(kendaraan)) {
            System.out.println("Data berhasil ditambahkan.");
        } else {
            System.out.println("Gagal: ID sudah dipakai.");
        }
    }
 
    private static void updateData() {
        System.out.println("\n--- Update Data Kendaraan ---");
        if (pengelola.getJumlahData() == 0) {
            System.out.println("Belum ada data kendaraan.");
            return;
        }
        pengelola.tampilkanSemuaKendaraan();
        String id = bacaStringTidakKosong("Masukkan ID yang akan diupdate: ");
        if (pengelola.cariIndexById(id) == -1) {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
            return;
        }
        String namaBaru = bacaStringTidakKosong("Nama baru          : ");
        double hargaBaru = bacaDoublePositif("Harga sewa/hari baru: ");
 
        if (pengelola.updateKendaraan(id, namaBaru, hargaBaru)) {
            System.out.println("Data berhasil diupdate.");
        } else {
            System.out.println("Gagal mengupdate data.");
        }
    }
 
    private static void hapusData() {
        System.out.println("\n--- Hapus Data Kendaraan ---");
        if (pengelola.getJumlahData() == 0) {
            System.out.println("Belum ada data kendaraan.");
            return;
        }
        pengelola.tampilkanSemuaKendaraan();
        String id = bacaStringTidakKosong("Masukkan ID yang akan dihapus: ");
        if (pengelola.hapusKendaraan(id)) {
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
        }
    }
 
    private static String bacaStringTidakKosong(String pesan) {
        String input;
        while (true) {
            System.out.print(pesan);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Input tidak boleh kosong. Silakan ulangi.");
        }
        return input;
    }
 
    private static double bacaDoublePositif(String pesan) {
        double nilai = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            try {
                nilai = Double.parseDouble(input);
                if (nilai > 0) {
                    valid = true;
                } else {
                    System.out.println("Nilai harus lebih dari 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
        return nilai;
    }
 
    private static int bacaIntRentang(String pesan, int min, int max) {
        int nilai = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            try {
                nilai = Integer.parseInt(input);
                if (nilai >= min && nilai <= max) {
                    valid = true;
                } else {
                    System.out.println("Pilihan harus antara " + min + " sampai " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat.");
            }
        }
        return nilai;
    }
}
