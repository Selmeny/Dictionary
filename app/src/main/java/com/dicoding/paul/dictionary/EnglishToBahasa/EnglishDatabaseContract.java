package com.dicoding.paul.dictionary.EnglishToBahasa;

import android.provider.BaseColumns;

public class EnglishDatabaseContract {
    static String TABLE_NAME = "table_english";

    static final class EnglishColumns implements BaseColumns {
        static String WORD = "word";
        static String DEFINITION = "definition";
    }
}
