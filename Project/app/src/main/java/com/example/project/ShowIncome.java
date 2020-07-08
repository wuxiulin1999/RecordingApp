package com.example.project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.InaccountDAO;
import model.Tb_inaccount;

public class ShowIncome extends AppCompatActivity {
    EditText txtShInMoney,txtShInTime,txtShInType,txtShInHandler,txtShInNote;
    Button btnInChange,btnInDelete;
    String strid;
    InaccountDAO inaccountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_income);

        inaccountDAO=new InaccountDAO(ShowIncome.this);
        txtShInMoney=(EditText)findViewById(R.id.txtShInMoney);
        txtShInTime=(EditText)findViewById(R.id.txtShInTime);
        txtShInType=(EditText)findViewById(R.id.txtShInType);
        txtShInHandler=(EditText)findViewById(R.id.txtShInHandler);
        txtShInNote=(EditText)findViewById(R.id.txtShInNote);
        btnInChange=(Button)findViewById(R.id.btnInChange);
        btnInDelete=(Button)findViewById(R.id.btnInDelete);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        strid=bundle.getString("id");

        final Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
        txtShInMoney.setText(String.valueOf(tb_inaccount.getMoney()));
        txtShInTime.setText(tb_inaccount.getTime());
        txtShInType.setText(tb_inaccount.getType());
        txtShInHandler.setText(tb_inaccount.getHandler());
        txtShInNote.setText(tb_inaccount.getNote());

        btnInChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_inaccount tb_inaccount1=new Tb_inaccount();
                tb_inaccount1.setId(Integer.parseInt(strid));
                tb_inaccount1.setMoney(Double.parseDouble(txtShInMoney.getText().toString()));
                tb_inaccount1.setTime(txtShInTime.getText().toString());
                tb_inaccount1.setType(txtShInType.getText().toString());
                tb_inaccount1.setHandler(txtShInHandler.getText().toString());
                tb_inaccount1.setNote(txtShInNote.getText().toString());
                inaccountDAO.update(tb_inaccount1);
                Intent intent1=new Intent(ShowIncome.this,MainActivity.class);
                startActivity(intent1);
                Toast.makeText(ShowIncome.this,"Change successful!",Toast.LENGTH_SHORT).show();
            }
        });

        btnInDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert=new AlertDialog.Builder(ShowIncome.this).create();
                alert.setMessage("Do you want to delete it?");
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inaccountDAO.delete(Integer.parseInt(strid));
                        Intent intent1=new Intent(ShowIncome.this,MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(ShowIncome.this,"Delete successful!",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });


    }
}
