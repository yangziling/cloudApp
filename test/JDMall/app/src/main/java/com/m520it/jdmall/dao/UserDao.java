package com.m520it.jdmall.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.m520it.jdmall.bean.UserBnean;
import com.m520it.jdmall.cons.DbCons;
import com.m520it.jdmall.help.DbOpenHelper;

/**
 * @author 杨飞
 * @time 2016/9/3  10:47
 * @desc ${TODD}
 */
public class UserDao {
    private DbOpenHelper mHelper;

    public UserDao(Context context) {
        mHelper = new DbOpenHelper(context);
    }
    //保存用户的账号和密码
    public boolean  saveUser(String name, String pwd) {//这里只要保存一个最新的用户名和密码
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbCons.COLUMN_USERNAME, name);
        values.put(DbCons.COLUMN_PASSWORD, pwd);
        //nullColumnHack 指定插入的某一列不能为null
        long insertedId = db.insert(DbCons.USER_TABLE, null, values);

        return insertedId!=-1;
    }


    //删除用户的账户和密码
    public boolean cleanUserTable(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int deleteRows = db.delete(DbCons.USER_TABLE, null, null);
        return deleteRows >0;
    }
    //回显用户名称和密码
    //获取当前登录用户的信息
    public UserBnean queryUserTable() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbCons.USER_TABLE, new String[]{
                DbCons.COLUMN_USERNAME, DbCons.COLUMN_PASSWORD}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            String pwd = cursor.getString(1);
            return new UserBnean(name, pwd);
        }
        return null;
    }
}
