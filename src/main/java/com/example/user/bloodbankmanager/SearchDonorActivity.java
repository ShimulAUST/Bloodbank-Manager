package com.example.user.bloodbankmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.bloodbankmanager.LoginActivity.USER_NAME;
import static com.example.user.bloodbankmanager.LoginActivity.USER_Password;

public class SearchDonorActivity extends AppCompatActivity {
    String name__,pass__;
    Button search_;
    private DrawerLayout mdrawer;
    Toolbar mtoolbar;
    Spinner bldgrp,diss;
    ActionBarDrawerToggle mtoggle;
    NavigationView mnavigationView;
    TextView don;
    String bloodstr,disstr;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_donor);

        bldgrp=(Spinner)findViewById(R.id.blood_group_in_donor_search);
        diss=(Spinner)findViewById(R.id.district_in_donor_search);
        search_=(Button)findViewById(R.id.search_donor_bt);

        setUpToolbar();
        mnavigationView=(NavigationView)findViewById(R.id.navigationmenu);
        mnavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.nav_dash:  dash();
                        break;
                    case R.id.About: about();
                        break;
                    case R.id.Logout: logout();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

         Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            name__ = bundle.getString("UserName");
            pass__=bundle.getString("UserPassword");
        }


        search_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bloodstr=bldgrp.getSelectedItem().toString();
                disstr=diss.getSelectedItem().toString();


               Intent i=new Intent(SearchDonorActivity.this,DonorListView.class);
                i.putExtra("BloodGroup",bloodstr);
                i.putExtra("District",disstr);
                startActivity(i);
            }
        });


    }
    private void setUpToolbar() {
        mdrawer=(DrawerLayout)findViewById(R.id.drawer_nav);
        mtoolbar=(Toolbar)findViewById(R.id.toolbar);

        mtoggle= new ActionBarDrawerToggle(SearchDonorActivity.this,mdrawer,mtoolbar,R.string.open,R.string.close);
        mdrawer.addDrawerListener(mtoggle);
        mtoggle.syncState();

    }
    public void about()
    {
        Intent intent=new Intent(SearchDonorActivity.this,AboutUsActivity.class);
        startActivity(intent);

    }
public void dash()
{

Intent intent=new Intent(SearchDonorActivity.this,DashBoardActivity.class);
intent.putExtra("UserName",name__);
intent.putExtra("Password",pass__);
startActivity(intent);

}
public void logout()
{
    Toast.makeText(SearchDonorActivity.this,"Logged out",Toast.LENGTH_SHORT).show();
    Intent intent=new Intent(SearchDonorActivity.this,LoginActivity.class);
    startActivity(intent);
    SearchDonorActivity.this.finish();

}
}
