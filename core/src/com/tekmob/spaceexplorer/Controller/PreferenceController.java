package com.tekmob.spaceexplorer.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Muhammad Fajar on 23/12/2014.
 * Kelas ini berperan sebagai controller dari shared preferences yang digunakan dalam aplikasi
 * Versi 1.1
 * Perubahan : penambahan komentar untuk mempermudah penggunaan
 *
 */
public class PreferenceController {
    /**
     * Ada empat preference yang digunakan
     */
    private Preferences statistic;
    private Preferences achievement;
    private Preferences encyclopedia;
    private Preferences firstTime;

    final public static String STATISTIC = "Statistic";
    final public static String ACHIEVEMENT = "Achievement";
    final public static String ENCYCLOPEDIA = "Encyclopedia";
    final public static String ITEM = "item";

    public static final String PLAYER_ID = "player_id";
    public static final String DISTANCE = "longest_distance";
    public static final String SCORE = "highest_score";
    public static final String MISSILE = "most_missile";
    public static final String SHIELD = "most_shield";
    public static final String OBJECT = "object_discovered";
    public static final String GAMES = "total_games";
    public static final String MILESTONE = "longest_milestone";

    /**
     * Konstruktor untuk membuat instance PreferenceController
     * Pemanggilannya akan berimbas ke pembuatan preference (jika belum ada)
     */
    public PreferenceController() {
        statistic = Gdx.app.getPreferences(STATISTIC);
        achievement = Gdx.app.getPreferences(ACHIEVEMENT);
        encyclopedia = Gdx.app.getPreferences(ENCYCLOPEDIA);
        firstTime = Gdx.app.getPreferences("firstRun");
        // cek agar nilai shared preference tidak tertimpa setiap kali aplikasi dijalankan
        if (firstTime.getBoolean("first", true)) {
            initStatistic();
            initAchievement();
            initEncyclopedia();
            // mark it as non-first run anymore
            firstTime.putBoolean("first", false);
            firstTime.flush();
            Gdx.app.log("MESSAGE","Data inisialized for the first time.");
        } else {
            Gdx.app.log("MESSAGE","Data loaded from previous.");
        }

    }

    /**
     * Lakukan inisialisasi untuk preference Statistic. Hanya berlaku ketika aplikasi baru saja diinstal.
     * Semua artibut pada statistik akan di-set ke nilai default-nya.
     */
    private void initStatistic() {
        statistic.putInteger(PLAYER_ID, 1);
        statistic.putInteger(DISTANCE, 0);
        statistic.putInteger(SCORE, 0);
        statistic.putInteger(MISSILE, 0);
        statistic.putInteger(SHIELD, 0);
        statistic.putInteger(OBJECT, 0);
        statistic.putInteger(GAMES, 0);
        statistic.putString(MILESTONE, "Earth");
        statistic.flush();
    }

    /**
     * Lakukan inisialisasi untuk preference Achievement. Hanya berlaku ketika aplikasi baru saja diinstal.
     * Achievement di sini mengacu pada entri ensiklopedia. Untuk keadaan pertama, hanya Earth yang terbuka.
     * Yang lainnya akan terbuka setelah pemain mencapai milestone tertentu.
     */
    private void initAchievement() {
        // Earth is already unlocked at first
        achievement.putBoolean(ITEM + "0", true);
        for (int i = 1; i < 10; i++) {
            // set others as locked
            achievement.putBoolean(ITEM + i, false);
        }
        achievement.flush();
    }

    /**
     * Lakukan inisialisasi untuk preference Encyclopedia. Hanya berlaku ketika aplikasi baru saja diinstal.
     * Semua artibut pada ensiklopedia berasal dari file encyclopedia.txt. Format penyimpanannya adalah :
     * (key - data). Contohnya : (item0 - Planet;Bumi;0;Lorem ipsum blablabla). Nantinya, akan ada method tambahan
     * untuk mengambil nama, jarak dari Bumi (dalam AU), dan deskripsinya.
     */
    private void initEncyclopedia() {
        FileHandle file = Gdx.files.internal("encyclopedia.txt");
        String isi = file.readString();
        Gdx.app.log("MESSAGE", isi); // for debug only, check whether file is loaded properly

        String [] temp = isi.split("--");
        for (int i = 0; i < temp.length; i++) {
            // letakkan item0, item1, item2, dst ke preference beserta data terkait
            encyclopedia.putString(ITEM + i, temp[i]);
        }
        encyclopedia.flush();
    }

    /**
     * Method yang berfungsi untuk menambahkan data String ke preference Statistic.
     * Contoh penggunaan : putData("Statistic", "longest_milestone", "Kuiper Belt")
     * @param prefName nama preference yang dituju
     * @param key nama key dari data yang akan dimasukkan
     * @param data data yang akan dimasukkan
     */
    public void putData(String prefName, String key, String data) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            statistic.putString(key, data);
            statistic.flush();
        }
    }

    /**
     * Method yang berfungsi untuk menambahkan data int ke preference Statistic.
     * Contoh penggunaan : putData("Statistic", "highest_score", 45)
     * @param prefName nama preference yang dituju
     * @param key nama key dari data yang akan dimasukkan
     * @param data data yang akan dimasukkan
     */
    public void putData(String prefName, String key, int data) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            statistic.putInteger(key, data);
            statistic.flush();
        }
    }

    /**
     * Mengembalikan data String dari satu preference tertentu.
     * @param prefName nama preference tempat beradanya data yang dimaksud
     * @param key nama key dari data yang akan diambil
     * @return data String yang diambil dari preference tertentu, atau
     *          kembalikan "Data not found" apabila data tidak ditemukan
     */
    public String getString(String prefName, String key) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            return statistic.getString(key, "Data not found.");
        }
        return null;
    }

    /**
     * Mengembalikan data int dari satu preference tertentu.
     * @param prefName nama preference tempat beradanya data yang dimaksud
     * @param key nama key dari data yang akan diambil
     * @return data int yang diambil dari preference dan key tertentu, atau
     *          kembalikan angka 0 apabila data tidak ditemukan
     */
    public int getInteger(String prefName, String key) {
        if (prefName.equalsIgnoreCase(STATISTIC)) {
            return statistic.getInteger(key, 0);
        }
        return 0;
    }

    /**
     * Set suatu item pada ensiklopedia menjadi terbuka untuk dibaca.
     * @param item item mana yang akan di-unlock
     */
    public void unlockAchievement(String item) {
        achievement.putBoolean(item, true);
        achievement.flush();
    }

    /**
     * Mengembalikan status satu item tertentu dari preference Achievement. Berguna untuk menentukan
     * apakah suatu entri pada ensiklopedia sudah dapat dinikmati oleh pengguna atau tidak
     * @param item item yang akan dicek status lock-nya
     * @return hasil pengecekan item terkait, di mana jika true maka item tersebut akan unlocked
     *          di ensiklopedia
     */
    public boolean getAchievementStatus(String item) {
        return achievement.getBoolean(item);
    }

    /**
     * Mengembalikan informasi lengkap dari satu item ensiklopedia
     * @param itemName nama item yang akan diambil informasinya
     * @return informasi item ensiklopedia, dalam bentuk array of String
     */
    public String [] getInfoForEncyclopedia(String itemName) {
        String temp = encyclopedia.getString(itemName);
        String [] content = temp.split(";");
        return content;
    }

    /**
     * Mengembalikan tipe dari suatu item pada ensiklopedia
     * Contohnya : getTypeOfEncyclopedia("item0"), maka return value-nya adalah "Planet"
     * @param itemName nama item yang akan diambil informasinya
     * @return informasi tipe item (planet, bulan, atau lainnya)
     */
    public String getTypeOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return temp[0];
    }

    /**
     * Mengembalikan nama dari suatu item pada ensiklopedia
     * Contohnya : getNameOfEncyclopedia("item0"), maka return value-nya adalah "Earth"
     * @param itemName nama item yang akan diambil informasinya
     * @return informasi nama item
     */
    public String getNameOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return temp[1];
    }

    /**
     * Mengembalikan jarak dari suatu item pada ensiklopedia terhadap Bumi
     * Contohnya : getDistanceOfEncyclopedia("item0"), maka return value-nya adalah 0
     * @param itemName nama item yang akan diambil informasinya
     * @return informasi jarak item dari Bumi (dalam AU - Astronomical Unit)
     */
    public double getDistanceOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return Double.parseDouble(temp[2]);
    }

    /**
     * Mengembalikan deskripsi dari suatu item pada ensiklopedia
     * Contohnya : getDescOfEncyclopedia("item0"), maka return value-nya adalah "Lorem ipsum blablabla"
     * @param itemName nama item yang akan diambil informasinya
     * @return informasi tipe item (deskripsi panjangnya)
     */
    public String getDescOfEncyclopedia(String itemName) {
        String [] temp = getInfoForEncyclopedia(itemName);
        return temp[3];
    }
}
