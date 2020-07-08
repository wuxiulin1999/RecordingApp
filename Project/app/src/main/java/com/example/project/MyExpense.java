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
import dao.OutaccountDAO;
import model.Tb_inaccount;
import model.Tb_outaccount;

public class MyExpense extends AppCompatActivity {
    ListView lvinfoout;
    String strType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expense);

        lvinfoout=(ListView)findViewById(R.id.lvmoduleinfoout);
        String[] strInfos=null;
        OutaccountDAO outaccountinfo=new OutaccountDAO(MyExpense.this);
        List<Tb_outaccount> listinfoouts=outaccountinfo.getScrollData(0,(int)outaccountinfo.getCount());
        strInfos=new String[listinfoouts.size()];
        int m=0;
        for(Tb_outaccount tb_outaccount:listinfoouts){
            strInfos[m]=tb_outaccount.getId()+"|";
            strInfos[m]=strInfos[m]+tb_outaccount.getType()+" "+tb_outaccount.getMoney()+" ";
            strInfos[m]=strInfos[m]+tb_outaccount.getTime()+"---"+tb_outaccount.getDue_time();
            m++;
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
            lvinfoout.setAdapter(arrayAdapter);

            lvinfoout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String strInfo=String.valueOf(((TextView)view).getText());
                    String strid=strInfo.substring(0,strInfo.indexOf("|"));
                    Bundle bundle=new Bundle();
                    bundle.putString("id",strid);
                    Intent intent=new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(MyExpense.this,ShowExpense.class);
                    startActivity(intent);
                }
            });
        }
    }
}
