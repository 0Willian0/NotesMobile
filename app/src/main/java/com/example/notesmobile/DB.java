package com.example.notesmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private SQLiteDatabase dataBase = null;
    public static final String BankName =  "BankNotes";
    public static final int Version =  1;

    private static DB instance;

    public DB(Context context) {
        super(context,BankName,null,Version);
        dataBase = getWritableDatabase();
    }

    public static DB getInstance(Context context) {
        if(instance == null)
            instance = new DB(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Notes.createTable);
        dataBase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Notes.DropTable);
        onCreate( sqLiteDatabase);

    }

    public void createNewTask(Notes notes) {
        ContentValues values = this.contentValuesTask(notes);
        notes.id = (int) getWritableDatabase().insert(Notes.tableName, null, values);


    }

    private ContentValues contentValuesTask(Notes notes) {
        ContentValues values = new ContentValues();
        values.put(notes.titleColumn, notes.getTitle());
        values.put(notes.descriptionColumn, notes.getDescription());
        return values;
    }

}
