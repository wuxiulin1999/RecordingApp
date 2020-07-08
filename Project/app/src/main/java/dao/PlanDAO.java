package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tb_plan;

public class PlanDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public PlanDAO(Context context) {
        helper = new DBOpenHelper(context);
    }
    public void add(Tb_plan tb_plan) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into tb_plan (_id,time,flag) values (?,?,?)", new
                Object[]{
               tb_plan.getId(),tb_plan.getTime(),tb_plan.getPlan()
        });
    }
    public void update(Tb_plan tb_plan) {
        db = helper.getWritableDatabase();
        db.execSQL("update tb_plan set time=?,flag=? where _id=?",
                new Object[]{
                       tb_plan.getTime(),tb_plan.getPlan(),tb_plan.getId()
                });
    }
    public Tb_plan find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,time,flag from tb_plan where _id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            return new Tb_plan(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("flag")));
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
            db.execSQL("delete from tb_plan where _id in (" + sb + ")", (Object[]) ids);
        }
    }
    public List<Tb_plan> getScrollData(int start, int count) {
        List<Tb_plan> tb_plan = new ArrayList<Tb_plan>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_plan limit ?,?", new String[]{String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext()) {
            tb_plan.add(new Tb_plan(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("flag"))));
        }
        return tb_plan;
    }
    public long getCount(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(_id) from tb_plan",null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);//返回总记录数
        }
        return 0;
    }
    public int getMaxId(){
        db=helper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select max(_id) from tb_plan",null);
        while (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
