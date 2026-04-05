
/*
 * Team Assignment 2 - Data Structures and Algorithm Analysis (COSC6025036)
 * Session 8 - Group 6
 *
 * Anggota Kelompok:
 * 1. DWILALA ASYIROTUL AMINIYAH  - 2902692762
 * 2. DAROJATUL ULIYAH             - 2902716182
 * 3. JASON CHRISTOFER HANDIKA     - 2902689566
 * 4. ASMA' GHAITSANI FUAIZAH      - 2902737703
 * 5. DENDY RAMDHAN FAUZY          - 2902724966
 *
 * Kelas: TBHA - LEC
 */

import java.util.Scanner;

// Class Lagu merepresentasikan satu objek lagu dalam playlist
class Lagu {
    String judul;
    String artis;
    double durasi; // durasi dalam menit

    // Constructor untuk inisialisasi objek Lagu
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Method untuk menampilkan informasi lagu
    public void tampilkanInfo() {
        System.out.printf("  Judul  : %s%n", judul);
        System.out.printf("  Artis  : %s%n", artis);
        System.out.printf("  Durasi : %.2f menit%n", durasi);
    }
}

// Class utama yang mengelola playlist menggunakan array statis
public class PlaylistArray {

    // Array statis dengan kapasitas maksimum 10 lagu
    static Lagu[] playlist = new Lagu[10];

    // Variabel untuk melacak jumlah lagu yang tersimpan saat ini
    static int jumlahLagu = 0;

    // =====================================================
    // TRAVERSAL - Menampilkan seluruh lagu dalam playlist
    // Kompleksitas: O(n), karena mengiterasi seluruh elemen
    // =====================================================
    static void tampilkanSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("\nPlaylist kosong. Belum ada lagu yang ditambahkan.");
            return;
        }

        System.out.println("\n===== DAFTAR LAGU DALAM PLAYLIST =====");
        // Iterasi dari indeks 0 sampai jumlahLagu - 1
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println("\nLagu ke-" + (i + 1) + ":");
            playlist[i].tampilkanInfo();
        }
        System.out.println("\nTotal lagu: " + jumlahLagu + "/10");
    }

    // =====================================================
    // INSERTION - Menambahkan lagu baru ke dalam playlist
    // Kompleksitas: O(1), karena lagu ditambahkan di akhir array
    // =====================================================
    static void tambahLagu(Scanner scanner) {
        // Cek apakah playlist sudah penuh (kapasitas maksimum 10)
        if (jumlahLagu >= 10) {
            System.out.println("\nPlaylist sudah penuh! Maksimum 10 lagu.");
            return;
        }

        System.out.println("\n--- Tambah Lagu Baru ---");
        System.out.print("Masukkan judul lagu : ");
        String judul = scanner.nextLine();

        System.out.print("Masukkan nama artis : ");
        String artis = scanner.nextLine();

        System.out.print("Masukkan durasi (menit) : ");
        double durasi = scanner.nextDouble();
        scanner.nextLine(); // membersihkan buffer newline

        // Sisipkan lagu baru di posisi terakhir yang tersedia
        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;

        System.out.println("Lagu \"" + judul + "\" berhasil ditambahkan ke playlist!");
    }

    // =====================================================
    // DELETION - Menghapus lagu berdasarkan judul
    // Kompleksitas: O(n), karena perlu mencari lagu lalu
    //               menggeser elemen agar array tetap rapat
    // =====================================================
    static void hapusLagu(Scanner scanner) {
        if (jumlahLagu == 0) {
            System.out.println("\nPlaylist kosong. Tidak ada lagu untuk dihapus.");
            return;
        }

        System.out.println("\n--- Hapus Lagu ---");
        System.out.print("Masukkan judul lagu yang ingin dihapus: ");
        String judul = scanner.nextLine();

        int indeks = -1;

        // Cari posisi lagu yang ingin dihapus dengan linear search
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].judul.equalsIgnoreCase(judul)) {
                indeks = i;
                break;
            }
        }

        // Jika lagu tidak ditemukan
        if (indeks == -1) {
            System.out.println("Lagu \"" + judul + "\" tidak ditemukan dalam playlist.");
            return;
        }

        System.out.println("Lagu \"" + playlist[indeks].judul + "\" berhasil dihapus.");

        // Geser semua elemen setelah indeks ke kiri agar array tetap rapat
        for (int i = indeks; i < jumlahLagu - 1; i++) {
            playlist[i] = playlist[i + 1];
        }

        // Kosongkan elemen terakhir dan kurangi counter
        playlist[jumlahLagu - 1] = null;
        jumlahLagu--;
    }

    // =====================================================
    // SEARCHING - Mencari lagu berdasarkan judul (Linear Search)
    // Kompleksitas: O(n), karena dalam kasus terburuk harus
    //               memeriksa seluruh elemen array
    // =====================================================
    static void cariLagu(Scanner scanner) {
        if (jumlahLagu == 0) {
            System.out.println("\nPlaylist kosong. Tidak ada lagu untuk dicari.");
            return;
        }

        System.out.println("\n--- Cari Lagu ---");
        System.out.print("Masukkan judul lagu yang dicari: ");
        String judul = scanner.nextLine();

        // Linear search: periksa satu per satu dari awal array
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].judul.equalsIgnoreCase(judul)) {
                System.out.println("\nLagu ditemukan pada posisi ke-" + (i + 1) + ":");
                playlist[i].tampilkanInfo();
                return;
            }
        }

        // Jika loop selesai tanpa menemukan lagu
        System.out.println("Lagu \"" + judul + "\" tidak ditemukan dalam playlist.");
    }

    // Method utama dengan menu interaktif
// Method utama dengan menu interaktif
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;

        // Tambahkan beberapa data awal sebagai contoh
        playlist[0] = new Lagu("Bohemian Rhapsody", "Queen", 5.55);
        playlist[1] = new Lagu("Shape of You", "Ed Sheeran", 3.53);
        playlist[2] = new Lagu("Blinding Lights", "The Weeknd", 3.20);
        jumlahLagu = 3;

        System.out.println("Selamat datang di Sistem Manajemen Playlist Musik!");

        // Loop menu utama
        do {
            System.out.println("\n=== MENU PLAYLIST MUSIK ===");
            System.out.println("1. Tampilkan semua lagu");
            System.out.println("2. Tambah lagu baru");
            System.out.println("3. Hapus lagu berdasarkan judul");
            System.out.println("4. Cari lagu berdasarkan judul");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            // CEK 1: Pastikan ada input angka yang tersedia
            if (!scanner.hasNextInt()) {
                System.out.println("\nInput terputus. Menghentikan program.");
                break; // Keluar dari loop jika tidak ada input lagi
            }

            pilihan = scanner.nextInt();

            // CEK 2: Bersihkan buffer hanya jika ada baris selanjutnya
            if (scanner.hasNextLine()) {
                scanner.nextLine(); 
            }

            // Jalankan operasi sesuai pilihan pengguna
            switch (pilihan) {
                case 1:
                    tampilkanSemuaLagu();    // Traversal
                    break;
                case 2:
                    tambahLagu(scanner);     // Insertion
                    break;
                case 3:
                    hapusLagu(scanner);      // Deletion
                    break;
                case 4:
                    cariLagu(scanner);       // Searching
                    break;
                case 5:
                    System.out.println("\nTerima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan pilih 1-5.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}