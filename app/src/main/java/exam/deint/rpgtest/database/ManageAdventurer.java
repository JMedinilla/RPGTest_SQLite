package exam.deint.rpgtest.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import exam.deint.rpgtest.pojos.AdapterPojo_Adventurer;
import exam.deint.rpgtest.pojos.DialogAdventurer;
import exam.deint.rpgtest.pojos.Pojo_Adventurer;
import exam.deint.rpgtest.pojos.Pojo_Class;

public class ManageAdventurer {
    private static ManageAdventurer instance;

    public static ManageAdventurer getInstance() {
        if (instance == null) {
            instance = new ManageAdventurer();
        }
        return instance;
    }

    public List<AdapterPojo_Adventurer> selectAllAdventurer() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.AdventurerTable.JOINCLASS,
                DatabaseContract.AdventurerTable.JOINCOLUMNS, null, null, null, null, null);

        ArrayList<AdapterPojo_Adventurer> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                AdapterPojo_Adventurer adventurer = new AdapterPojo_Adventurer(
                        cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getInt(4), cursor.getInt(5), cursor.getInt(6)
                );
                arrayList.add(adventurer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return arrayList;
    }

    public Pojo_Adventurer selectAdventurer(int id) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.AdventurerTable.TABLE_NAME,
                DatabaseContract.AdventurerTable.ALL_COLUMNS, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);

        Pojo_Adventurer pojo_adventurer = null;
        if (cursor.moveToFirst()) {
            pojo_adventurer = new Pojo_Adventurer(
                    cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getInt(5),
                    cursor.getInt(6), cursor.getInt(7)
            );
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return pojo_adventurer;
    }

    public long insertAdventurer(Pojo_Adventurer pojoAdventurer) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.AdventurerTable.COLUMN_NAME, pojoAdventurer.getAd_name());
        values.put(DatabaseContract.AdventurerTable.COLUMN_RACE, pojoAdventurer.getAd_race());
        values.put(DatabaseContract.AdventurerTable.COLUMN_ALIGNMENT, pojoAdventurer.getAd_alignment());
        values.put(DatabaseContract.AdventurerTable.COLUMN_CLASS, pojoAdventurer.getAd_class());
        values.put(DatabaseContract.AdventurerTable.COLUMN_STR, pojoAdventurer.getAd_str());
        values.put(DatabaseContract.AdventurerTable.COLUMN_DEX, pojoAdventurer.getAd_dex());
        values.put(DatabaseContract.AdventurerTable.COLUMN_INT, pojoAdventurer.getAd_int());
        long result = sqLiteDatabase.insert(DatabaseContract.AdventurerTable.TABLE_NAME, null, values);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int updateAdventurer(Pojo_Adventurer pojoAdventurer) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.AdventurerTable.COLUMN_NAME, pojoAdventurer.getAd_name());
        values.put(DatabaseContract.AdventurerTable.COLUMN_RACE, pojoAdventurer.getAd_race());
        values.put(DatabaseContract.AdventurerTable.COLUMN_ALIGNMENT, pojoAdventurer.getAd_alignment());
        values.put(DatabaseContract.AdventurerTable.COLUMN_CLASS, pojoAdventurer.getAd_class());
        values.put(DatabaseContract.AdventurerTable.COLUMN_STR, pojoAdventurer.getAd_str());
        values.put(DatabaseContract.AdventurerTable.COLUMN_DEX, pojoAdventurer.getAd_dex());
        values.put(DatabaseContract.AdventurerTable.COLUMN_INT, pojoAdventurer.getAd_int());
        int result = sqLiteDatabase.update(DatabaseContract.AdventurerTable.TABLE_NAME, values, "_id = ?", new String[]{String.valueOf(pojoAdventurer.getAd_id())});
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int deleteAdventurer(int id) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        int result = sqLiteDatabase.delete(DatabaseContract.AdventurerTable.TABLE_NAME, "_id = ?", new String[]{String.valueOf(id)});
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public DialogAdventurer selectAdventurerClass(int id) {
        DialogAdventurer dialogAdventurer = null;
        Pojo_Adventurer pojoAdventurer = null;
        Pojo_Class pojoClass = null;

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor curAdv = sqLiteDatabase.query(DatabaseContract.AdventurerTable.TABLE_NAME,
                DatabaseContract.AdventurerTable.ALL_COLUMNS, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (curAdv.moveToFirst()) {
            pojoAdventurer = new Pojo_Adventurer(
                    curAdv.getInt(0), curAdv.getString(1), curAdv.getString(2),
                    curAdv.getString(3), curAdv.getInt(4), curAdv.getInt(5),
                    curAdv.getInt(6), curAdv.getInt(7)
            );
        }
        curAdv.close();
        if (pojoAdventurer != null) {
            Cursor curCla = sqLiteDatabase.query(DatabaseContract.ClassTable.TABLE_NAME,
                    DatabaseContract.ClassTable.ALL_COLUMNS, "_id = ?", new String[]{String.valueOf(pojoAdventurer.getAd_class())}, null, null, null);
            if (curCla.moveToFirst()) {
                pojoClass = new Pojo_Class(
                        curCla.getInt(0), curCla.getString(1),
                        curCla.getString(2), curCla.getString(3)
                );
            }
            curCla.close();
        }

        if (pojoClass != null) {
            dialogAdventurer = new DialogAdventurer(
                    pojoAdventurer.getAd_id(), pojoAdventurer.getAd_name(), pojoAdventurer.getAd_race(),
                    pojoAdventurer.getAd_alignment(), pojoClass.getCl_name(), pojoClass.getCl_weapon(),
                    pojoClass.getCl_role(), pojoAdventurer.getAd_str(), pojoAdventurer.getAd_dex(),
                    pojoAdventurer.getAd_int()
            );
        }

        DatabaseHelper.getInstance().closeDatabase();
        return dialogAdventurer;
    }
}
