package com.m520it.jdmall.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.m520it.jdmall.cons.DbCons;

/**
 * @author 杨飞
 * @time 2016/9/3  10:33
 * @desc ${TODD}
 */
public class DbOpenHelper extends SQLiteOpenHelper{

    public DbOpenHelper(Context context) {
        super(context, DbCons.DB_NAME, null, DbCons.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表
        db.execSQL(DbCons.CREATE_USER_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
