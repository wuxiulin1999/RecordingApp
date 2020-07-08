package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dao.InaccountDAO;
import model.Tb_inaccount;

public class MyIncome extends AppCompatActivity {
    ListView lvinfoin;
    String strType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_income);
        lvinfoin=(ListView)findViewById(R.id.lvmoduleinfoin);
        String[] strInfos=null;
        InaccountDAO inaccountinfo=new InaccountDAO(MyIncome.this);
        List<Tb_inaccount> listinfoins=inaccountinfo.getScrollData(0,(int)inaccountinfo.getCount());
        strInfos=new String[listinfoins.size()];
        int m=0;
        for(Tb_inaccount tb_inaccount:listinfoins){
            strInfos[m]=tb_inaccount.getId()+"|";
            strInfos[m]=strInfos[m]+tb_inaccount.getType()+" "+tb_inaccount.getMoney()+" ";
            strInfos[m]=strInfos[m]+tb_inaccount.getTime();
            m++;
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
        lvinfoin.setAdapter(arrayAdapter);

        lvinfoin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo=String.valueOf(((TextView)view).getText());
                String strid=strInfo.substring(0,strInfo.indexOf("|"));
                Bundle bundle=new Bundle();
                bundle.putString("id",strid);
                Intent intent=new Intent();
                intent.putExtras(bundle);
                intent.setClass(MyIncome.this,ShowIncome.class);
                startActivity(intent);
            }
        });
    }
}
