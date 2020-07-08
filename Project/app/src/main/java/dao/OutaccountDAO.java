package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_outaccount;

public class OutaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public OutaccountDAO(Context context) {
        helper = new DBOpenHelper(context);
    }
    public void add(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_outaccount (_id,money,time,type,giver,due_time) values (?,?,?,?,?,?)", new
                Object[]{
                tb_outaccount.getId(), tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(),
                tb_outaccount.getGiver(), tb_outaccount.getDue_time()
        });
    }
    public void update(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();
        db.execSQL("update tb_outaccount set money=?,time=?,type=?,giver=?,due_time=? where _id=?",
                new Object[]{
                        tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getGiver(), tb_outaccount.getDue_time(),
                        tb_outaccount.getId()
                });
    }
    public Tb_outaccount find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,money,time,type,giver,due_time from tb_outaccount where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("giver")),
                    cursor.getString(cursor.getColumnIndex("due_time")));
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
            db.execSQL("delete from tb_outaccount where _id in (" + sb + ")", (Object[]) ids);
        }
    }
    public List<Tb_outaccount> getScrollData(int start, int count) {
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_outaccount limit ?,?", new String[]{String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext()) {
            tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("giver")),
                    cursor.getString(cursor.getColumnIndex("due_time"))));
        }
        return tb_outaccount;
    }
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(_id) from tb_outaccount",null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);//返回总记录数
        }
        return 0;
    }
    public int getMaxId(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select max(_id) from tb_outaccount",null);
        while (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
