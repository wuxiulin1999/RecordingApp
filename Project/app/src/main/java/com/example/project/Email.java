package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) findViewById(R.id.from)).setText("");
                ((EditText) findViewById(R.id.to)).setText("");
                ((EditText) findViewById(R.id.cc)).setText("");
                ((EditText) findViewById(R.id.bcc)).setText("");
                ((EditText) findViewById(R.id.subject)).setText("");
                ((EditText) findViewById(R.id.emailbody)).setText("");
            }
        });
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from=((EditText)findViewById(R.id.from)).getText().toString();
                String to=((EditText)findViewById(R.id.to)).getText().toString();
                String cc=((EditText)findViewById(R.id.cc)).getText().toString();
                String bcc=((EditText)findViewById(R.id.bcc)).getText().toString();
                String subject=((EditText)findViewById(R.id.subject)).getText().toString();
                String email_body=((EditText)findViewById(R.id.emailbody)).getText().toString();
                Intent intent1=new Intent(Intent.ACTION_SEND);
                String reTo[]={to};
                String reCc[]={cc};
                String reBcc[]={bcc};
                String Sub=subject;
                String Eb=email_body;
                intent1.putExtra(Intent.EXTRA_EMAIL,reTo);
                intent1.putExtra(Intent.EXTRA_CC,reCc);
                intent1.putExtra(Intent.EXTRA_BCC,reBcc);
                intent1.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent1.putExtra(Intent.EXTRA_TEXT,Eb);
                intent1.setType("message/rfc882");
                startActivity(intent1);
            }
        });
    }
}
