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
import model.Tb_inaccount;

public class NewIncome extends AppCompatActivity {
    EditText txtInMoney,txtInTime,txtInType,txtInHandler,txtInNote;
    Button btnInSave,btnInCancel;
    protected static final int DATE_DIALOG_ID=0;//创建日期对话框常量
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);

        txtInMoney=(EditText)findViewById(R.id.txtInMoney);
        txtInTime=(EditText)findViewById(R.id.txtInTime);
        txtInType=(EditText)findViewById(R.id.txtInType);
        txtInHandler=(EditText)findViewById(R.id.txtInHandler);
        txtInNote=(EditText)findViewById(R.id.txtInNote);
        btnInSave=(Button)findViewById(R.id.btnInSave);
        btnInCancel=(Button)findViewById(R.id.btnInCancel);

        txtInTime.setOnClickListener(new View.OnClickListener() {
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

        btnInSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strInMoney=txtInMoney.getText().toString();
                if(!strInMoney.isEmpty()){
                    Toast.makeText(NewIncome.this,"Adding Successful!",Toast.LENGTH_SHORT).show();
                    InaccountDAO inaccountDAO=new InaccountDAO(NewIncome.this);
                    Tb_inaccount tb_inaccount=new Tb_inaccount(inaccountDAO.getMaxId()+1,Double.parseDouble(strInMoney),
                            txtInTime.getText().toString(),txtInType.getText().toString(),txtInHandler.getText().toString(),
                            txtInNote.getText().toString());
                    inaccountDAO.add(tb_inaccount);
                    Intent intent=new Intent(NewIncome.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(NewIncome.this,"Please Enter the money!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInMoney.setText("");
                txtInTime.setText("");
                txtInType.setText("");
                txtInHandler.setText("");
                txtInNote.setText("");
            }
        });
    }

    private void updateDisplay(){
        txtInTime.setText(new StringBuffer().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
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
