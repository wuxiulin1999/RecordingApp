package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_inaccount;

public class InaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public InaccountDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    public void add(Tb_inaccount tb_inaccount) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_inaccount (_id,money,time,type,handler,note) values (?,?,?,?,?,?)", new
                Object[]{
                tb_inaccount.getId(), tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(),
                tb_inaccount.getHandler(), tb_inaccount.getNote()
        });
    }

    public void update(Tb_inaccount tb_inaccount) {
        db = helper.getWritableDatabase();
        db.execSQL("update tb_inaccount set money=?,time=?,type=?,handler=?,note=? where _id=?",
                new Object[]{
                        tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(), tb_inaccount.getHandler(), tb_inaccount.getNote(),
                        tb_inaccount.getId()
                });
    }

    public Tb_inaccount find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,money,time,type,handler,note from tb_inaccount where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("note")));
        }
        return null;
    }

    public void delete(Integer... ids) {
        if (ids.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {//遍历要删除id的集合
                sb.append('?').append(',');//删除条件
            }
            sb.deleteCharAt(sb.length() - 1);
            db = helper.getWritableDatabase();
            db.execSQL("delete from tb_inaccount where _id in (" + sb + ")", (Object[]) ids);
        }
    }

    public List<Tb_inaccount> getScrollData(int start, int count) {
        List<Tb_inaccount> tb_inaccount = new ArrayList<Tb_inaccount>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?,?", new String[]{String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext()) {
            tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("note"))));
        }
        return tb_inaccount;
    }
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(_id) from tb_inaccount",null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);//返回总记录数
        }
        return 0;
    }
    public int getMaxId(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select max(_id) from tb_inaccount",null);
        while (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
