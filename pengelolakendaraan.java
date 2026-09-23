import java.utilArrayList;
 
public class PengelolaKendaraan {
 
    private ArrayList<KendaraanTambang> daftarKendaraan;
 
    public PengelolaKendaraan() {
        this.daftarKendaraan = new ArrayList<>();
        isiDataAwal(); // dummy data agar fitur read langsung menampilkan data
    }
 
    private void isiDataAwal() {
        daftarKendaraan.add(new DumpTruck("DT-001", "Komatsu HD785", 3500000, 91.0));
        daftarKendaraan.add(new DumpTruck("DT-002", "Caterpillar 777", 3200000, 100.0));
        daftarKendaraan.add(new Excavator("EX-001", "Komatsu PC200", 2500000, 1.2));
    }
 
    public boolean tambahKendaraan(KendaraanTambang kendaraan) {
        if (cariIndexById(kendaraan.getId()) != -1) {
            return false; // ID sudah dipakai
        }
        daftarKendaraan.add(kendaraan);
        return true;
    }
 
    public void tampilkanSemuaKendaraan() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return;
        }
        String garis = "=".repeat(100);
        System.out.println(garis);
        System.out.printf("%-8s | %-12s | %-20s | %-24s | %s%n",
                "ID", "Jenis", "Nama Kendaraan", "Harga Sewa/Hari", "Detail");
        System.out.println("-".repeat(100));
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.println(daftarKendaraan.get(i));
        }
        System.out.println(garis);
    }
 
    public int cariIndexById(String id) {
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            if (daftarKendaraan.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
 
    public boolean updateKendaraan(String id, String namaBaru, double hargaBaru) {
        int index = cariIndexById(id);
        if (index == -1) {
            return false;
        }
        daftarKendaraan.get(index).setNamaKendaraan(namaBaru);
        daftarKendaraan.get(index).setHargaSewaPerHari(hargaBaru);
        return true;
    }
 
    public boolean hapusKendaraan(String id) {
        int index = cariIndexById(id);
        if (index == -1) {
            return false;
        }
        daftarKendaraan.remove(index);
        return true;
    }
 
    public int getJumlahData() {
        return daftarKendaraan.size();
    }
}
