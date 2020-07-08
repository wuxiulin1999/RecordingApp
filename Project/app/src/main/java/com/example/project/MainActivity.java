package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gvInfo;
    String[] titles=new String[]{"New Income","New Expense","New Plan","Show Income","Show Expense","Show Plan","Help","Exit"};
    int[] images=new int[]{R.drawable.income,R.drawable.expense,R.drawable.plan,R.drawable.sincome,R.drawable.sexpense,R.drawable.splan,
    R.drawable.help,R.drawable.exit};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvInfo=(GridView)findViewById(R.id.gvInfo);
        PictureAdapter adapter=new PictureAdapter(titles,images,this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=null;
                switch (position){
                    case 0:
                        intent=new Intent(MainActivity.this,NewIncome.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(MainActivity.this, NewExpense.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(MainActivity.this,NewPlan.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent=new Intent(MainActivity.this,MyIncome.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(MainActivity.this,MyExpense.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent=new Intent(MainActivity.this,MyPlan.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent=new Intent(MainActivity.this,Help.class);
                        startActivity(intent);
                        break;
                    case 7:
                        AlertDialog alert=new AlertDialog.Builder(MainActivity.this).create();
                        alert.setTitle("Exit");
                        alert.setMessage("Do you want to exit?");
                        alert.setButton(DialogInterface.BUTTON_POSITIVE, "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                       alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               finish();
                           }
                       });
                       alert.show();
                }
            }
        });
    }
}
