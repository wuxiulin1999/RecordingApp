package com.example.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.OutaccountDAO;
import model.Tb_outaccount;

public class ShowExpense extends AppCompatActivity {
    EditText txtShOutMoney,txtShOutTime,txtShOutType,txtShOutGiver,txtShOutDue_time;
    Button btnOutChange,btnOutDelete;
    String strid;
    OutaccountDAO outaccountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);

        outaccountDAO=new OutaccountDAO(ShowExpense.this);
        txtShOutMoney=(EditText)findViewById(R.id.txtShOutMoney);
        txtShOutTime=(EditText)findViewById(R.id.txtShOutTime);
        txtShOutType=(EditText)findViewById(R.id.txtShOutType);
        txtShOutGiver=(EditText)findViewById(R.id.txtShOutGiver);
        txtShOutDue_time=(EditText)findViewById(R.id.txtShOutDue_time);
        btnOutChange=(Button)findViewById(R.id.btnOutChange);
        btnOutDelete=(Button)findViewById(R.id.btnOutDelete);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        strid=bundle.getString("id");

        final Tb_outaccount tb_outaccount = outaccountDAO.find(Integer.parseInt(strid));
        txtShOutMoney.setText(String.valueOf(tb_outaccount.getMoney()));
        txtShOutTime.setText(tb_outaccount.getTime());
        txtShOutType.setText(tb_outaccount.getType());
        txtShOutGiver.setText(tb_outaccount.getGiver());
        txtShOutDue_time.setText(tb_outaccount.getDue_time());

        btnOutChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_outaccount tb_outaccount1=new Tb_outaccount();
                tb_outaccount1.setId(Integer.parseInt(strid));
                tb_outaccount1.setMoney(Double.parseDouble(txtShOutMoney.getText().toString()));
                tb_outaccount1.setTime(txtShOutTime.getText().toString());
                tb_outaccount1.setType(txtShOutType.getText().toString());
                tb_outaccount1.setGiver(txtShOutGiver.getText().toString());
                tb_outaccount1.setDue_time(txtShOutDue_time.getText().toString());
                outaccountDAO.update(tb_outaccount1);
                Intent intent1=new Intent(ShowExpense.this,MainActivity.class);
                startActivity(intent1);
                Toast.makeText(ShowExpense.this,"Change successful!",Toast.LENGTH_SHORT).show();
            }
        });

        btnOutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert=new AlertDialog.Builder(ShowExpense.this).create();
                alert.setMessage("Do you want to delete it?");
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        outaccountDAO.delete(Integer.parseInt(strid));
                        Intent intent1=new Intent(ShowExpense.this,MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(ShowExpense.this,"Delete successful!",Toast.LENGTH_SHORT).show();
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
