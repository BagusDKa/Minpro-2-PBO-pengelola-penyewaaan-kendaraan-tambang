/**
 * Superclass abstrak untuk semua kendaraan tambang.
 * Menerapkan encapsulation: field private, diakses lewat getter dan setter
 * yang sudah dilengkapi validasi.
 */
public abstract class KendaraanTambang {
 
    // private: hanya bisa diakses dari dalam class ini
    private String id;
    private String namaKendaraan;
    private double hargaSewaPerHari;
 
    // public: bisa dipanggil dari class mana saja
    public KendaraanTambang(String id, String namaKendaraan, double hargaSewaPerHari) {
        setId(id);
        setNamaKendaraan(namaKendaraan);
        setHargaSewaPerHari(hargaSewaPerHari);
    }
 
    // ===== Getter =====
    public String getId() {
        return id;
    }
 
    public String getNamaKendaraan() {
        return namaKendaraan;
    }
 
    public double getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }
 
    // ===== Setter (dengan validasi) =====
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID tidak boleh kosong.");
        }
        this.id = id.trim();
    }
 
    public void setNamaKendaraan(String namaKendaraan) {
        if (namaKendaraan == null || namaKendaraan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kendaraan tidak boleh kosong.");
        }
        this.namaKendaraan = namaKendaraan.trim();
    }
 
    public void setHargaSewaPerHari(double hargaSewaPerHari) {
        if (hargaSewaPerHari <= 0) {
            throw new IllegalArgumentException("Harga sewa harus lebih dari 0.");
        }
        this.hargaSewaPerHari = hargaSewaPerHari;
    }
 
    // Method abstrak: wajib di-override oleh subclass
    public abstract String getJenis();
 
    // protected: hanya bisa diakses oleh class ini, subclass, dan package yang sama
    protected abstract String getDetail();
 
    @Override
    public String toString() {
        String harga = String.format("Rp %,.2f / hari", hargaSewaPerHari);
        return String.format("%-8s | %-12s | %-20s | %-24s | %s",
                id, getJenis(), namaKendaraan, harga, getDetail());
    }
}
