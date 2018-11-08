package com.dicoding.paul.dictionary.BahasaToEnglish;

import android.provider.BaseColumns;

public class BahasaDatabaseContract {
    static String TABLE_NAME = "table_bahasa";

    static final class BahasaColumns implements BaseColumns {
        static String KATA = "kata";
        static String DEFINISI = "definisi";
    }
}
