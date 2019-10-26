package com.example.user.bloodbankmanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHandler;
import Donor.donor;

public class DonorListView extends AppCompatActivity {
String  bloodstr,disstr;
private ListView donorList;
    final DatabaseHandler db = new DatabaseHandler(this);

    List<donor> availableDonorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list_view);

        donorList=(ListView)findViewById(R.id.donorListLV);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
           bloodstr = bundle.getString("BloodGroup");
            disstr=bundle.getString("District");
        }

        donorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final donor d = availableDonorList.get(position);
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+d.getPhoneno()));
                startActivity(callIntent);
            }
        });
        getDonorList();
        setDonorList();


    }
    public void getDonorList() {

        try {
            availableDonorList = db.getAllDonor(bloodstr,disstr);

            if (availableDonorList.size() == 0)
                Toast.makeText(getApplicationContext(), "Sorry! No Donor Available !", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
        }
    }
    public void setDonorList(){
        DonorAdapter itemsAdapter = new DonorAdapter(this, (ArrayList<donor>) availableDonorList);

        donorList.setAdapter(itemsAdapter);

    }

}
