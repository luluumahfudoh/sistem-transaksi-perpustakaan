/**
 * Mempresentasikan data member dalam sistem perpustakaan.
 * Kelas Member mengelola informasi member, batas maksimal peminjaman, serta jumlah buku yang sedang dipinjam oleh anggota
 */ 

public class Member {
    /** ID unik untuk mengidentifikasi member. */ 
    private String MemberId;
    /** Nama lengkap member. */ 
    private String Name;
    /** Batas maksimal jumlah buku yang dapat dipinjam sekaligus oleh member (3 buku). */ 
    private static final int maxLimit=3;
    /** Jumlah buku yang saat ini sedang dipinjam oleh member. */ 
    private int BorrowedCount;

    /** 
     * Membuat objek Member baru dengan jumlah peminjaman awal nol.
     * 
     * @param memId ID unik member
     * @param name Nama lengkap member
    */ 
    public Member (String memId, String name)
    {
        this.MemberId = memId;
        this.Name = name;
        this.BorrowedCount = 0;
    }

    /** 
     * Mengambil ID member.
     * 
     * @return String berisi ID member
    */ 
    public String getMemberId()
    {
        return MemberId;
    }

    /** 
     * Mengambil nama member.
     * 
     * @return String berisi nama member
    */ 
    public String getName()
    {
        return Name;
    }

    /** 
     * Mengambil jumlah buku yang saat ini sedang dipinjam oleh member.
     * 
     * @return int jumlah buku yang dipinjam
    */ 
    public int getBorrowedCount()
    {
        return BorrowedCount;
    }

    /** 
     * Mengambil batas maksimal peminjaman buku yang diizinkan sistem.
     * 
     * @return int batas maksimal peminjaman
    */ 
    public static final int getMaxLimit() {
        return maxLimit;
    }

    /** 
     * Memeriksa apakah member masih diperbolehkan meminjam buku.
     * 
     * @return {@code true} jika jumlah peminjaman kurang dari batas maksimal (maxLimit), {@code false} jika jumlah peminjaman sudah mencapai atau melebihi batas
    */ 
    public boolean canBorrow()
    {
        if(getBorrowedCount() < maxLimit)
        {
            return true;
        } else {
            return false;
        }
    }

    /** 
     * Menambahkan jumlah buku yang dipinjam sebanyak 1 unit
     * hanya jika member masih memililki kuota peminjama ({@code canBorrow()}).
    */ 
    public void incrementBorrowed() 
    {
        if(canBorrow()) {
            this.BorrowedCount++;
        }
    }

    /** 
     * Mengurangi jumlah buku yang dipinjam sebanyak 1 unit (saat pengembalian buku), 
     * hanya jika jumlah buku yang dipinjam saat ini lebih besar dari 0
    */ 
    public void decrementBorrowed() 
    {
        if(this.BorrowedCount > 0) {
            this.BorrowedCount--;
        }
    }

    /** 
     * Mengembalikan teks (String) dari objek Member.    (non-Javadoc)
     * 
     * @return String berisi infrmasi format MemberId, Name, maxLimit, dan BorrowedCount
    */ 
    @Override
    public String toString() {
        return "{" +
            " MemberId='" + getMemberId() + "'" +
            ", Name='" + getName() + "'" +
            ", maxLimit='" + getMaxLimit() + "'" +
            ", BorrowedCount='" + getBorrowedCount() + "'" +
            "}";
    }
    
}
