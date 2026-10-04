/**
 * Mempresentasikan data buku dalam sistem perpustakaan
 * Kelas Book menyimpan informasi ID buku, judul, dan jumlah stok yang tersedia
 * 
*/

public class Book {
    /** ID unik untuk mengidentifikasi buku. */ 
    private String BookId;
    /** Judul Buku. */ 
    private String Title;
    /** Jumlah stok buku yang tersedia. */ 
    private int Stock;

    /**
     * Membuat objek Book baru dengan informasi awal.
     * 
     * @param bookId ID unik untuk buku
     * @param title Judul lengkap buku
     * @param stock Jumlah stok awal buku
     */ 
    public Book (String bookId, String title, int stock) 
    {
        this.BookId = bookId;
        this.Title = title;
        this.Stock = stock;
    }

    /**
     * Mengambil ID buku.
     * 
     * @return String berisi ID buku
     */
    public String getBookId() {
        return BookId;
    }

    /**
     * Mengambil judul buku.
     * 
     * @return String berisi judul buku
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Mengambil jumlah stok buku yang tersisa.
     * 
     * @return int jumlah stok buku
     */
    public int getStock() {
        return Stock;
    }

    /**
     * Mengurangi jumlah stok buku sebanyak 1 jika stok masih tersedia (> 0).
     * 
     * @return {@code true} jika stok berhasil dikurangi, {@code false} jika stok sudah habis (0 atau kurang)
     */
    public boolean decreaseStock() 
    {
        if(getStock() > 0) {
            this.Stock--;
            return true;       
        } else {
            return false;
        }
    }

    /**
     * Menambahkan jumlah stok buku sebanyak 1 unit.
     */
    public void increaseStock()
    {
        this.Stock++;
    }

    /**
     * Mengembalikan teks (String) dari objek Book.
     * 
     * @return String berisi informasi format ID, Title, dan Stock buku
     */
    @Override
    public String toString() {
        return "{" +
            " BookId='" + getBookId() + "'" +
            ", Title='" + getTitle() + "'" +
            ", Stock='" + getStock() + "'" +
            "}";
    }

}
