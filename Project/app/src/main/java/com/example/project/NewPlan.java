package com.example.project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import dao.InaccountDAO;
import dao.PlanDAO;
import model.Tb_inaccount;
import model.Tb_plan;

public class NewPlan extends AppCompatActivity {
    EditText plan_time,textFlag;
    Button btnPlanSave,btnPlanCancel;
    protected static final int DATE_DIALOG_ID=0;//创建日期对话框常量
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);

        plan_time=(EditText)findViewById(R.id.plan_time);
        textFlag=(EditText)findViewById(R.id.textFlag);
        btnPlanSave=findViewById(R.id.btnPlanSave);
        btnPlanCancel=findViewById(R.id.btnPlanCancel);

        plan_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c=Calendar.getInstance();//获取当前日期
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        btnPlanSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPlan=textFlag.getText().toString();
                if(!strPlan.isEmpty()){
                    Toast.makeText(NewPlan.this,"Adding Successful!",Toast.LENGTH_SHORT).show();
                    PlanDAO planDAO=new PlanDAO(NewPlan.this);
                    Tb_plan tb_plan=new Tb_plan(planDAO.getMaxId()+1,plan_time.getText().toString(),textFlag.getText().toString());
                    planDAO.add(tb_plan);
                    Intent intent=new Intent(NewPlan.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(NewPlan.this,"Please Enter the plan!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPlanCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               plan_time.setText("");
               textFlag.setText("");
            }
        });
    }
    private void updateDisplay(){
        plan_time.setText(new StringBuffer().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch(id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,mYear,mMonth,mMonth);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear=i;
            mMonth=i1;
            mDay=i2;
            updateDisplay();
        }
    };
}
