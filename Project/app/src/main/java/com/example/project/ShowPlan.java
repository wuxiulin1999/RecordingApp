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

import java.nio.charset.MalformedInputException;

import dao.PlanDAO;
import model.Tb_plan;

public class ShowPlan extends AppCompatActivity {
    EditText Shplan_time,ShtextFlag;
    Button btnChangeplan,btnDeletePlan;
    String strid;
    PlanDAO plandao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plan);

        plandao=new PlanDAO(ShowPlan.this);
        Shplan_time=(EditText)findViewById(R.id.Shplan_time);
        ShtextFlag=(EditText)findViewById(R.id.ShtextFlag);
        btnChangeplan=findViewById(R.id.btnPlanChange);
        btnDeletePlan=findViewById(R.id.btnPlanDelete);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        strid=bundle.getString("id");

        final Tb_plan tb_plan=plandao.find(Integer.parseInt(strid));
        Shplan_time.setText(tb_plan.getTime());
        ShtextFlag.setText(tb_plan.getPlan());

        btnChangeplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_plan tb_plan1=new Tb_plan();
                tb_plan1.setId(Integer.parseInt(strid));
                tb_plan1.setTime(Shplan_time.getText().toString());
                tb_plan1.setPlan(ShtextFlag.getText().toString());
                plandao.update(tb_plan1);
                Intent intent1=new Intent(ShowPlan.this, MainActivity.class);
                startActivity(intent1);
                Toast.makeText(ShowPlan.this,"Change successful!",Toast.LENGTH_SHORT).show();
            }
        });

        btnDeletePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert=new AlertDialog.Builder(ShowPlan.this).create();
                alert.setMessage("Do you want to delete it?");
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        plandao.delete(Integer.parseInt(strid));
                        Intent intent1=new Intent(ShowPlan.this,MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(ShowPlan.this,"Delete successful!",Toast.LENGTH_SHORT).show();
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
