package exam.deint.rpgtest.database;

import android.provider.BaseColumns;

class DatabaseContract {
    static class ClassTable implements BaseColumns {
        static final String TABLE_NAME = "class";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_NAME = "cl_name";
        static final String COLUMN_WEAPON = "cl_weapon";
        static final String COLUMN_ROLE = "cl_role";

        static final String SQL_CREATION = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL,"
                        + " %s TEXT NOT NULL)",
                TABLE_NAME, COLUMN_ID, COLUMN_NAME,
                COLUMN_WEAPON, COLUMN_ROLE
        );

        static final String SQL_DELETION = String.format(
                "DROP TABLE IF EXISTS %s",
                TABLE_NAME
        );

        static final String[] ALL_COLUMNS = new String[]{
                COLUMN_ID, COLUMN_NAME, COLUMN_WEAPON, COLUMN_ROLE
        };
    }

    static class AdventurerTable implements BaseColumns {
        static final String TABLE_NAME = "adventurer";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_NAME = "ad_name";
        static final String COLUMN_RACE = "ad_race";
        static final String COLUMN_ALIGNMENT = "ad_alignment";
        static final String COLUMN_CLASS = "ad_class";
        static final String COLUMN_STR = "ad_str";
        static final String COLUMN_DEX = "ad_dex";
        static final String COLUMN_INT = "ad_int";

        static final String REFERENCE_CLASS = String.format(
                "REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                ClassTable.TABLE_NAME, ClassTable.COLUMN_ID
        );

        static final String SQL_CREATION = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL,"
                        + " %s TEXT NOT NULL, %s INTEGER NOT NULL %s, %s INTEGER NOT NULL,"
                        + " %s INTEGER NOT NULL, %s INTEGER NOT NULL)",
                TABLE_NAME, COLUMN_ID, COLUMN_NAME,
                COLUMN_RACE, COLUMN_ALIGNMENT, COLUMN_CLASS,
                REFERENCE_CLASS, COLUMN_STR, COLUMN_DEX,
                COLUMN_INT
        );

        static final String SQL_DELETION = String.format(
                "DROP TABLE IF EXISTS %s",
                TABLE_NAME
        );

        static final String[] ALL_COLUMNS = new String[]{
                COLUMN_ID, COLUMN_NAME, COLUMN_RACE, COLUMN_ALIGNMENT,
                COLUMN_CLASS, COLUMN_STR, COLUMN_DEX, COLUMN_INT
        };

        static final String JOINCLASS = String.format("%s ad INNER JOIN %s cl ON ad.%s = cl.%s",
                AdventurerTable.TABLE_NAME, ClassTable.TABLE_NAME, AdventurerTable.COLUMN_CLASS, ClassTable.COLUMN_ID);

        static final String[] JOINCOLUMNS = new String[]{
                "ad." + COLUMN_ID, COLUMN_NAME, COLUMN_RACE,
                "cl." + ClassTable.COLUMN_NAME, COLUMN_STR, COLUMN_DEX, COLUMN_INT
        };
    }
}
