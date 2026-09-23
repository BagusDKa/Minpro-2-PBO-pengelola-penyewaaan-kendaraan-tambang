public class Excavator extends KendaraanTambang {
 
    private double kapasitasBucket;
 
    public Excavator(String id, String namaKendaraan, double hargaSewaPerHari, double kapasitasBucket) {
        super(id, namaKendaraan, hargaSewaPerHari);
        setKapasitasBucket(kapasitasBucket);
    }
 
    public double getKapasitasBucket() {
        return kapasitasBucket;
    }
 
    public void setKapasitasBucket(double kapasitasBucket) {
        if (kapasitasBucket <= 0) {
            throw new IllegalArgumentException("Kapasitas bucket harus lebih dari 0.");
        }
        this.kapasitasBucket = kapasitasBucket;
    }
 
    @Override
    public String getJenis() {
        return "Excavator";
    }
 
    @Override
    protected String getDetail() {
        return String.format("Bucket %.1f m3", kapasitasBucket);
    }
}
