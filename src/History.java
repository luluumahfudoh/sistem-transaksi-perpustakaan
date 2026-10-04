import java.util.ArrayList;
import java.util.List;

/**
 * Memproses dan menyimpan riwayat seluruh transaksi yang terjadi di perpustakaan.
 * Kelas History mengelola daftar objek {@link Transaction} untuk keperuan pencatatan dan pelaporan.
*/ 
public class History {
    /** List yang menyimpan seluruh catatan transaksi. */ 
    private List<Transaction> log;

    /** 
     * Membuat objek History baru dnegan inisialisasi daftar transaksi kosong.
    */ 
    public History() {
        this.log = new ArrayList<>();
    }

    /** 
     * Menambahkan catata transaksi baru ke dalam riwayat log.
     * 
     * @param transaction Objek {@link Transaction} yang akan disimpan ke dalam riwayat.
    */ 
    public void addRecord(Transaction transaction) {
        log.add(transaction);
    }

    /** 
     * Menampilkan seluruh daftar riwayat transaksi ke konsol.
     * Jika belum ada transaksi, akan menampilkan pesan informasi bahwa log masih kosong.
    */ 
    public void printHistory() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        if (log.isEmpty()) {
            System.out.println("Belum ada transaksi yang tercatat.");
            return;
        }
        for (Transaction t : log) {
            System.out.println(t);
        }
    }

    /** 
     * Mengambil daftar seluruh objek transaksi yang tersimpan di dalam log.
     * 
     * @return {@link List} berisi objek {@link Transaction}
    */ 
    public List<Transaction> getLog() {
        return log;
    }

}
