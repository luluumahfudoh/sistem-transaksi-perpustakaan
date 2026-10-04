import java.time.LocalDate;

/** Mempresentasikan transaksi peminjaman dan pengembalian buku di perpustakaan.
 * Kelas Transaction mengelola interaksi antara {@link Member} dan {@link Book},
 * mencatat status transaksi, serta tanggal peminjaman dan pengembalian.
 */

public class Transaction {
    /** ID unik transaksi. */ 
    private String transactionId;
    /** Member yang melakukan transaksi peminjaman/pengembalian.  */ 
    private Member member;
    /** Buku yang dipinjam atau dikembalikan. */ 
    private Book book;
    /** Status keberhasilan pemrosesan transaksi peminjaman */ 
    private boolean status;
    /** Status pengembalian buku ({@code true} jika buku sudah dikembalikan). */ 
    private boolean isReturned;
    /** Tanggal buku mulai dipinjam. */ 
    private LocalDate borrowedDate;
    /** Tanggal buku dikembalikan oleh anggota. */ 
    private LocalDate returnedDate;

    /**
     * Membuat objek Transaction baru untuk peminjama buku.
     * @param transactionId ID unik transaksi
     * @param member Objek {@link Member} yang meminjam buku
     * @param book Objek {@link Book} yang dipinjam
     * @param borrowedDate Tanggal dimulainya peminjaman
    */ 
    public Transaction(String transactionId, Member member, Book book, LocalDate borrowedDate) {
        this.transactionId = transactionId;
        this.member = member;
        this.book = book;
        this.isReturned = false;
        this.borrowedDate = borrowedDate;
    }

    /**
     * Mengambil ID transaksi.
     * 
     * @return String berisi ID transaksi
    */ 
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Mengambil data member yang telibat dalam transaksi.
     * 
     * @return Objek {@link Member}
    */ 
    public Member getMember() {
        return member;
    }

    /**
     * Mengambil data buku yang terlibat dalam transaksi.
     * 
     * @return Objek {@link Book}
    */ 
    public Book getBook() {
        return book;
    }

    /**
     * Memeriksa apakah buku pada transaksi ini sudah dikembalikan.
     * 
     * @return {@code true} jika buku telah dikembalikan, {@code false} jika belum
    */ 
    public boolean isReturned() {
        return isReturned;
    }

    /**
     * Mengambil tanggal peminjaman buku.
     * 
     * @return {@link LocalDate} tanggal peminjaman
    */ 
    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    /**
     * Memproses alur peminjakan buku. 
     * Melakuka pengecekan kuota peminjaman anggota dan ketersediaan stok buku.
     * Jika valid, stok buku akan dikurnagi dan kuota peminjaman member akan bertambah.
     * 
     * @return {@code true} jika proses peminjaman berhasil, {@code false} jika member melebihi batas peminjaman atau stok buku habis
    */ 
    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    /**
     * Memproses alur peminjaman buku.
     * 
     * @return {@code true} jika peminjaman berhasil, {@code false} jika gagal
    */ 
    public boolean processBorrow() {
        if (!member.canBorrow()) {
            System.out.println("GAGAL [" + transactionId + "]: " + member.getName()
                    + " sudah mencapai batas maksimal peminjaman ("
                    + member.getBorrowedCount() + "/" + Member.getMaxLimit() + ").");
            this.status = false;
            return false;
        }

        boolean stockReduced = book.decreaseStock();
        if (!stockReduced) {
            System.out.println("GAGAL [" + transactionId + "]: Stok buku \""
                    + book.getTitle() + "\" habis.");
            this.status = false;
            return false;
        }

        member.incrementBorrowed();
        isReturned = false;
        System.out.println("SUKSES [" + transactionId + "]: " + member.getName()
                + " berhasil meminjam \"" + book.getTitle() + "\". Sisa s tok: "
                + book.getStock() + ", Total dipinjam anggota: "
                + member.getBorrowedCount() + "/" + Member.getMaxLimit());
        this.status = true;
        return true;
    }

    /**
     * Memproses alur pengembalian buku.
     * Menambahkan kembali stok buku, mengurangi jumlah pinjaman member,
     * serta mencatat tangal pengembalian saat ini.
     * 
     * @return {@code true} jika proses pengembalian berhasil, {@code false} jika buku sudah dikembalikan sebelumnya
    */ 
    public boolean processReturn() {
        if (isReturned) {
            System.out.println("GAGAL [" + transactionId + "]: Buku \""
                    + book.getTitle() + "\" sudah pernah dikembalikan sebelumnya.");
            return false;
        }

        book.increaseStock();
        member.decrementBorrowed();
        this.returnedDate = LocalDate.now();
        isReturned = true;

        System.out.println("SUKSES [" + transactionId + "]: " + member.getName()
                + " berhasil mengembalikan \"" + book.getTitle() + "\". Stok sekarang: "
                + book.getStock() + ", Total dipinjam anggota: "
                + member.getBorrowedCount() + "/" + Member.getMaxLimit());
        return true;
    }

    // public boolean processBorrow() {
    //     if (book.getStock() > 0 && member.canBorrow()) {
    //         book.decreaseStock();
    //         member.incrementBorrowed();
    //         this.status = true;
    //         return true;
    //     }
    //     this.status = false;
    //     return false;
    // }

    // public boolean processReturn() {
    //     if (!isReturned) {
    //         book.increaseStock();
    //         member.decrementBorrowed();
    //         isReturned = true;
    //         this.status = true;
    //         return true;
    //     }
    //     this.status = false;
    //     return false;
    // }

    /**
     * Mengembalikan ringkasan teks dari transaksi.
     * 
     * @return String berisi rincian transaksi termasuk ID, tanggal, member, buku, status, dan tanggal kembali
    */ 
    @Override 
    public String toString() {
        return "Transaksi " + transactionId + " | Tanggal Pinjam: " + borrowedDate + " | Anggota: " + member.getName() + " | Buku: " + book.getTitle() + " | Status: " + (status ? "Berhasil" : "Gagal") + " | Tanggal Kembali: " + (returnedDate != null ? returnedDate : "-");
    }
}