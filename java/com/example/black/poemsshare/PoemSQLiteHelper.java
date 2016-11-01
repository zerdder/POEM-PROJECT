package com.example.black.poemsshare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Black on 2016/10/31.
 */
public class PoemSQLiteHelper extends SQLiteOpenHelper{

    public static final String CREATE_USER = "create table USER ("
            + "Account text primary key,"
            + "Password text,"
            + "Name text)";
            String CREATE_COMMENT = "create table COMMENT ("
            + "Number int primary key autoincrement,"
            + "Commrnt0 text,"
            + "Poem-Number int,"
            + "User-Account text,"
            + "Like-Amoun int)";
    private Context mContext;

    public PoemSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_COMMENT);
        Toast.makeText(mContext,"数据库已创建。",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
