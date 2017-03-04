package exam.deint.rpgtest.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import exam.deint.rpgtest.RPGTestApplication;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rpgtest.db";
    private static final int DATABASE_VERSION = 1;

    private static volatile DatabaseHelper instance;
    private SQLiteDatabase database;

    public synchronized static DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    private DatabaseHelper() {
        super(RPGTestApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(DatabaseContract.ClassTable.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.AdventurerTable.SQL_CREATION);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(DatabaseContract.AdventurerTable.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.ClassTable.SQL_DELETION);
            onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }

    SQLiteDatabase openDatabase() {
        database = getWritableDatabase();
        return database;
    }

    void closeDatabase() {
        database.close();
    }
}
