package model;

public class Tb_inaccount {
    private int _id;
    private double money;
    private String time;
    private String type;
    private String handler;
    private String note;
    public Tb_inaccount(){
        super();
    }
    public Tb_inaccount(int id,double money,String time,String type, String handler,String note){
        super();
        this._id=id;
        this.money=money;
        this.time=time;
        this.type=type;
        this.handler=handler;
        this.note=note;
    }
    public void setId(int id){
        this._id=id;
    }
    public void setMoney(double money){
        this.money=money;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setHandler(String handler) {
        this.handler = handler;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getId(){
        return _id;
    }
    public double getMoney(){
        return money;
    }
    public String getTime(){
        return time;
    }
    public String getType(){
        return type;
    }
    public  String getHandler(){
        return  handler;
    }
    public String getNote(){
        return  note;
    }
}
