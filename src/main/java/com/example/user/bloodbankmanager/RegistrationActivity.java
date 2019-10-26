package com.example.user.bloodbankmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import Database.DatabaseHandler;
import Donor.donor;


public class RegistrationActivity extends AppCompatActivity {

    private static final String Tag="RegistrationActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
   private EditText mDisplayDate,FIRSTNAME,LASTNAME,DATEOFBIRTH,EMAILID,PHONENO,ADDRESS,USERNAME,PASSWORD,CONFIRMPASSWORD;
   private  Spinner BLOODGROUP,DISTRICT;
private RadioGroup GENDER;
   private Button SIGNUPBTN;
private  RadioButton CHECK;
   final DatabaseHandler db=new DatabaseHandler(this);
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);
            BLOODGROUP=(Spinner)findViewById(R.id.blood_group);
            FIRSTNAME = (EditText) findViewById(R.id.first_name);
            LASTNAME = (EditText) findViewById(R.id.last_name);
            DATEOFBIRTH = (EditText) findViewById(R.id.setDate_et);
            DISTRICT = (Spinner) findViewById(R.id.district);
            ADDRESS = (EditText) findViewById(R.id.address);
            EMAILID = (EditText) findViewById(R.id.email_id);
            PHONENO = (EditText) findViewById(R.id.phone_no);
            USERNAME = (EditText) findViewById(R.id.username_registration);
            PASSWORD = (EditText) findViewById(R.id.password_registration);
            CONFIRMPASSWORD = (EditText) findViewById(R.id.confirm_password_registration);
            SIGNUPBTN = (Button) findViewById(R.id.sign_up_bt);
            GENDER = (RadioGroup) findViewById(R.id.gender);

            mDisplayDate = (EditText) findViewById(R.id.setDate_et);
            mDisplayDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int Year = cal.get(cal.YEAR);
                    int Month = cal.get(cal.MONTH);
                    int Day = cal.get(cal.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
                            mDateSetListener,
                            Year, Month, Day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    dialog.show();

                }
            });
            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    mDisplayDate.setText(date);
                }
            };
            SIGNUPBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String gender=null;
                    String fname=FIRSTNAME.getText().toString();
                    String lname=LASTNAME.getText().toString();
                 int selectedId = GENDER.getCheckedRadioButtonId();
                    CHECK = (RadioButton) findViewById(selectedId);
                    try {
                         gender = CHECK.getText().toString();

                    }
                    catch (Exception e)
                    {
                        Toast.makeText(RegistrationActivity.this,"Please Select Gender",Toast.LENGTH_SHORT).show();
                    }
                    String bloodgroup = BLOODGROUP.getSelectedItem().toString();
                    String dateofbirth=DATEOFBIRTH.getText().toString();
                    String dist= DISTRICT.getSelectedItem().toString();
                    String add=ADDRESS.getText().toString();
                    String email=EMAILID.getText().toString();
                    String Phone=PHONENO.getText().toString();
                    String  user=USERNAME.getText().toString();
                 String pass=PASSWORD.getText().toString();
                  String pass_con=CONFIRMPASSWORD.getText().toString();
                    Log.v("Regis:",fname);
                    Log.v("Regis:",lname);
                    if(fname.equals("") || lname.equals("") || bloodgroup.equals("") || dateofbirth.equals("") || dist.equals("")|| add.equals("") || email.equals("") || Phone.equals("") || user.equals("")|| pass.equals("") || pass_con.equals(""))
                    {

                        Toast.makeText(RegistrationActivity.this,"Please Fill Up All the Fields",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(pass.equals(pass_con)) {

                            donor d=new donor(fname,lname,gender,bloodgroup,dateofbirth,dist,add,email,Phone,user,pass);
                            boolean isInserted=db.addDonor(d);
                            if(isInserted==true)
                            {
                                Toast.makeText(RegistrationActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                                startActivity(intent);
                                RegistrationActivity.this.finish();
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                            }}
                            else {
                            Toast.makeText(RegistrationActivity.this,"Passwords didn't match",Toast.LENGTH_SHORT).show();
                            }





                    }

                }
            });
        }


}
