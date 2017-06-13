package com.m520it.jdmall.cons;

/**
 * @author 杨飞
 * @time 2016/9/3  10:35
 * @desc ${TODD}
 */
public class DbCons {
    public static final String DB_NAME="jdmall.db";
    public static final int  DB_VERSION= 1;

    public static final String USER_TABLE="user";
    public static final String COLUMN_USERNAME="name";
    public static final String COLUMN_PASSWORD="pwd";
    //create table contactinfo(id integer primary key autoincrement,username varchar(15),phone text);
    public static final String CREATE_USER_TABLE_SQL="create table user(id integer primary key autoincrement,name varchar(15),pwd varchar(40))";
}
