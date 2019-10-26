package com.example.user.bloodbankmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Donor.donor;

public class DonorAdapter extends ArrayAdapter<donor> {
    public DonorAdapter(Context context, ArrayList<donor> d)
    {
        super(context , 0 , d);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        donor d = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_view, parent, false);
        }

        TextView nameTV = (TextView) convertView.findViewById(R.id.donorNameET);
        TextView bloodGrpTV = (TextView) convertView.findViewById(R.id.donorGroupTV);
        TextView phoneTV = (TextView) convertView.findViewById(R.id.donorPhoneTV);
        TextView locationTV = (TextView) convertView.findViewById(R.id.donorAddressTV);

        String name=d.getFirstname()+" "+d.getLastname();

        nameTV.setText(name.toUpperCase());
        bloodGrpTV.setText(d.getBloodGroup());
        phoneTV.setText(d.getPhoneno());
        locationTV.setText(d.getAddress().toUpperCase());

        return convertView;
    }
}
