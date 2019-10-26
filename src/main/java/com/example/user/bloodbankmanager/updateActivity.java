package com.example.user.bloodbankmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseHandler;

public class updateActivity extends AppCompatActivity {
String name__,pass__;
EditText userold,userpassold,usernew,usernewpass,usernewpasscon;
Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        final DatabaseHandler db=new DatabaseHandler(this);
        userold=(EditText)findViewById(R.id.oldusername);
        userpassold=(EditText)findViewById(R.id.oldpass);
        usernew=(EditText)findViewById(R.id.newusername);
        usernewpass=(EditText)findViewById(R.id.newpass);
        usernewpasscon=(EditText)findViewById(R.id.confirmnewpass);
        update=(Button)findViewById(R.id.update_usenameandpass);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            name__ = bundle.getString("OldUsername");
            pass__=bundle.getString("OldPass");
        }

        userold.setText(name__);
        userpassold.setText(pass__);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPass=usernewpass.getText().toString();
                String confirmPass=usernewpasscon.getText().toString();
                String  username=usernew.getText().toString();
                if(newPass.equals("") || confirmPass.equals("")|| username.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "ALL FIELDS ARE REQUIRED", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(newPass.equals(confirmPass))
                    {
                        try{
                            if(db.updateProfile(name__,username,newPass)>0)
                            {
                                Toast.makeText(getApplicationContext(), "Profile Updated !", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(updateActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else{

                                Toast.makeText(getApplicationContext(), "Failed To Update Profile!", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error Updating Profile Info!", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password Mismatch !", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}
