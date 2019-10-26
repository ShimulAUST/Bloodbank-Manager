package com.example.user.bloodbankmanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseHandler;
import Donor.donor;

public class LoginActivity extends AppCompatActivity {
    public static String USER_NAME= "UserName";
    public static String USER_Password= "UserPassword";
    private EditText username;
    private EditText password;
    private Button login_bt;
    private Button signUp;
    final DatabaseHandler db=new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username_login_et);
        password=(EditText)findViewById(R.id.password_login_et);
        login_bt=(Button)findViewById(R.id.login_bt);
        signUp=(Button)findViewById(R.id.sign_up_bt);


        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue=username.getText().toString().trim();
                String passwordValue=password.getText().toString().trim();

                if (nameValue.equals("") || passwordValue.equals("")) {
                    Toast.makeText(LoginActivity.this, "Name or Password Field is Empty!", Toast.LENGTH_LONG).show();
                }
                else {

                    try{


                        if(db. loginValidation(new donor(nameValue,passwordValue))) {
                            Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, SearchDonorActivity.class);

                            i.putExtra(USER_NAME , nameValue);
                            i.putExtra(USER_Password,passwordValue);
                            startActivity(i);
                            LoginActivity.this.finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                    }
                    catch (Exception ex)
                    {

                        Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

    }

}
