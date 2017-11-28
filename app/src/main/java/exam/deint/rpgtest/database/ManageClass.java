package exam.deint.rpgtest.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import exam.deint.rpgtest.pojos.Class;
import exam.deint.rpgtest.pojos.ClassForSpinner;

public class ManageClass {
    private static ManageClass instance;

    public static ManageClass getInstance() {
        if (instance == null) {
            instance = new ManageClass();
        }
        return instance;
    }

    public List<Class> selectAllClass() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(
                DatabaseContract.ClassTable.TABLE_NAME, DatabaseContract.ClassTable.ALL_COLUMNS, null, null, null, null, null
        );

        ArrayList<Class> tmpList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Class pojoClass = new Class(
                        cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)
                );
                tmpList.add(pojoClass);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return tmpList;
    }

    public List<ClassForSpinner> selectSpinnerClasses() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(
                DatabaseContract.ClassTable.TABLE_NAME, new String[]{DatabaseContract.ClassTable.COLUMN_ID,
                        DatabaseContract.ClassTable.COLUMN_NAME}, null, null, null, null, null
        );

        ArrayList<ClassForSpinner> tmpList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ClassForSpinner pojoClass = new ClassForSpinner(
                        cursor.getInt(0), cursor.getString(1)
                );
                tmpList.add(pojoClass);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return tmpList;
    }

    public long insertClass(Class pojoClass) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.ClassTable.COLUMN_NAME, pojoClass.getCl_name());
        values.put(DatabaseContract.ClassTable.COLUMN_WEAPON, pojoClass.getCl_weapon());
        values.put(DatabaseContract.ClassTable.COLUMN_ROLE, pojoClass.getCl_role());
        long result = sqLiteDatabase.insert(DatabaseContract.ClassTable.TABLE_NAME, null, values);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int updateClass(Class pojoClass) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.ClassTable.COLUMN_NAME, pojoClass.getCl_name());
        values.put(DatabaseContract.ClassTable.COLUMN_WEAPON, pojoClass.getCl_weapon());
        values.put(DatabaseContract.ClassTable.COLUMN_ROLE, pojoClass.getCl_role());
        String[] where = new String[]{String.valueOf(pojoClass.getCl_id())};
        int result = sqLiteDatabase.update(DatabaseContract.ClassTable.TABLE_NAME, values, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int deleteClass(Class pojoClass) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        int result = sqLiteDatabase.delete(DatabaseContract.ClassTable.TABLE_NAME, "_id = ?", new String[]{String.valueOf(pojoClass.getCl_id())});
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }
}
