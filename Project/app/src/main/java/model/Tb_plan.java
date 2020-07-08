package model;

public class Tb_plan {
    private int _id;
    private String time;
    private  String flag;
    public Tb_plan(){
        super();
    }
    public Tb_plan(int id,String time,String flag){
        super();
        this._id=id;
        this.time=time;
        this.flag=flag;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlan() {
        return flag;
    }

    public void setPlan(String flag) {
        this.flag = flag;
    }
}
