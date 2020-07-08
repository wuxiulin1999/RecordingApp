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
import dao.OutaccountDAO;
import model.Tb_inaccount;
import model.Tb_outaccount;

public class NewExpense extends AppCompatActivity {
    EditText txtOutMoney,txtOutTime,txtOutType,txtOutGiver,txtOutDue_time;
    Button btnOutSave,btnOutCancel;
    protected static final int DATE_DIALOG_ID=0;//创建日期对话框常量
    private int mYear;
    private int mMonth;
    private int mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        txtOutMoney=(EditText)findViewById(R.id.txtOutMoney);
        txtOutTime=(EditText)findViewById(R.id.txtOutTime);
        txtOutType=(EditText)findViewById(R.id.txtOutType);
        txtOutGiver=(EditText)findViewById(R.id.txtOutGiver);
        txtOutDue_time=(EditText)findViewById(R.id.txtOutDue_time);
        btnOutSave=(Button)findViewById(R.id.btnOutSave);
        btnOutCancel=(Button)findViewById(R.id.btnOutCancel);

        txtOutTime.setOnClickListener(new View.OnClickListener() {
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

        btnOutSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strInMoney=txtOutMoney.getText().toString();
                if(!strInMoney.isEmpty()){
                    Toast.makeText(NewExpense.this,"Adding Successful!",Toast.LENGTH_SHORT).show();
                    OutaccountDAO outaccountDAO=new OutaccountDAO(NewExpense.this);
                    Tb_outaccount tb_outaccount=new Tb_outaccount(outaccountDAO.getMaxId()+1,Double.parseDouble(strInMoney),
                            txtOutTime.getText().toString(),txtOutType.getText().toString(),txtOutGiver.getText().toString(),
                            txtOutDue_time.getText().toString());
                    outaccountDAO.add(tb_outaccount);
                    Intent intent=new Intent(NewExpense.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(NewExpense.this,"Please Enter the money!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnOutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOutMoney.setText("");
                txtOutTime.setText("");
                txtOutType.setText("");
                txtOutGiver.setText("");
                txtOutDue_time.setText("");
            }
        });
    }
    private void updateDisplay(){
        txtOutTime.setText(new StringBuffer().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
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
