package model;

public class Tb_outaccount {
    private int _id;
    private double money;
    private String time;
    private String type;
    private String giver;
    private String due_time;
    public Tb_outaccount(){
        super();
    }
    public Tb_outaccount(int id,double money,String time,String type,String giver,String due_time){
        super();
        this._id=id;
        this.money=money;
        this.time=time;
        this.type=type;
        this.giver=giver;
        this.due_time=due_time;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGiver() {
        return giver;
    }

    public void setGiver(String giver) {
        this.giver = giver;
    }

    public String getDue_time() {
        return due_time;
    }

    public void setDue_time(String due_time) {
        this.due_time = due_time;
    }
}
