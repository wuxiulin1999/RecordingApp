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

import dao.PlanDAO;
import model.Tb_plan;

public class MyPlan extends AppCompatActivity {
    ListView lvinfopl;
    String strType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        lvinfopl=(ListView)findViewById(R.id.lvmoduleinfoplan);
        String[] strInfos=null;
        PlanDAO planinfo=new PlanDAO(MyPlan.this);
        List<Tb_plan> listinfopls=planinfo.getScrollData(0,(int)planinfo.getCount());
        strInfos=new String[listinfopls.size()];
        int m=0;
        for(Tb_plan tb_plan:listinfopls){
            strInfos[m]=tb_plan.getId()+"|";
            strInfos[m]=strInfos[m]+tb_plan.getTime()+" ";
            m++;
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfos);
        lvinfopl.setAdapter(arrayAdapter);

        lvinfopl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo=String.valueOf(((TextView)view).getText());
                String strid=strInfo.substring(0,strInfo.indexOf("|"));
                Bundle bundle=new Bundle();
                bundle.putString("id",strid);
                Intent intent=new Intent();
                intent.putExtras(bundle);
                intent.setClass(MyPlan.this,ShowPlan.class);
                startActivity(intent);
            }
        });
    }
}
