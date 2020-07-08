package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION=1;//定义数据库版本号
    private static final String DBNAME="account.db";//定义数据库名
    public DBOpenHelper(Context context){
        super(context,DBNAME,null, VERSION);//重写基类的构造函数
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_inaccount (_id integer primary key,money double,time varchar(10),"+
                "type varchar(30),handler varchar(100),note varchar(200))");
        db.execSQL("create table tb_outaccount (_id integer primary key,money double,time varchar(10),"+
                "type varchar(30),giver varchar(100),due_time varchar(10))");
        db.execSQL("create table tb_plan(_id integer primary key,time varchar(10),flag varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
