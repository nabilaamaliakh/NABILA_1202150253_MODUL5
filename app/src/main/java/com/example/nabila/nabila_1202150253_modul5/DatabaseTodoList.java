package com.example.nabila.nabila_1202150253_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseTodoList extends SQLiteOpenHelper{
    Context context;
    SQLiteDatabase db;

    public static final String nama_db = "listtodo.db";
    public static final String nama_tabel = "daftar";
    public static final String kolom_1 = "todo";
    public static final String kolom_2 = "description";
    public static final String kolom_3 = "priority";

    public DatabaseTodoList(Context context){
        super(context, nama_db, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+nama_tabel);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(ListData list){
        ContentValues values = new ContentValues();
        values.put(kolom_1, list.getTodo());
        values.put(kolom_2, list.getDesc());
        values.put(kolom_3, list.getPrio());
        long hasil = db.insert(nama_tabel, null, values);
        if (hasil == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean remove(String ToDo){
        return db.delete(nama_tabel, kolom_1+"=\""+ToDo+"\"", null)>0;
    }

    public void readdata(ArrayList<ListData> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+nama_tabel, null);
        while (cursor.moveToNext()){
            daftar.add(new ListData(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
