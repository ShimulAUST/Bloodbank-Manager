package com.example.user.bloodbankmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Database.DatabaseHandler;
import Donor.donor;

public class DashBoardActivity extends AppCompatActivity {
   TextView fname,lname,sex,dateof,addres,blood,dis,username,phoneno,email;
   String  name__,pass__;
    Button updt;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

       lname=(TextView)findViewById(R.id.pro_lname);
        sex=(TextView)findViewById(R.id.pro_gender);
        dateof=(TextView)findViewById(R.id.pro_date);
        addres=(TextView)findViewById(R.id.pro_add);
        blood=(TextView)findViewById(R.id.pro_blood_group);
        dis=(TextView)findViewById(R.id.pro_dis);
        username=(TextView)findViewById(R.id.pro_username);
        phoneno=(TextView)findViewById(R.id.pro_phone);
        email=(TextView)findViewById(R.id.pro_email);
        fname=(TextView)findViewById(R.id.pro_fname);
        updt=(Button)findViewById(R.id.button_update);
        Intent intent = getIntent();
       Bundle bundle = intent.getExtras();

       if(bundle != null){
           name__ = bundle.getString("UserName");
           pass__=bundle.getString("Password");
       }


        DatabaseHandler db=new DatabaseHandler(this);

        donor d=db.getProfileInfo(name__);
        if(d!=null)
        {


            fname.setText(d.getFirstname());
            lname.setText(d.getLastname());
            sex.setText(d.getGender());
            dateof.setText(d.getDateofbirth());
            addres.setText(d.getAddress());
            dis.setText(d.getDist());
            blood.setText(d.getBloodGroup());
            email.setText(d.getEmail_id());
            phoneno.setText(d.getPhoneno());
            username.setText(d.getUsername());
        }
        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,updateActivity.class);
                intent.putExtra("OldUsername",name__);
                intent.putExtra("OldPass",pass__);
                startActivity(intent);
            }
        });

    }
}
