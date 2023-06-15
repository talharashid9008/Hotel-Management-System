package com.example.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import Utility.MyAdapterReservations;
import business_Logic.Hotel;
import business_Logic.Reservation;

public class view_reservations extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv_reservations_from_view_reservations;
    List<Reservation> reservationList;
    Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_reservations);
        try {
            hotel = (Hotel) getIntent().getSerializableExtra("hotel");

            lv_reservations_from_view_reservations = findViewById(R.id.lv_reservations_from_view_reservations);


            reservationList = hotel.getReservations();
            MyAdapterReservations adapter = new MyAdapterReservations(getApplicationContext(), reservationList);
            adapter.notifyDataSetChanged();
            lv_reservations_from_view_reservations.setAdapter(adapter);

            lv_reservations_from_view_reservations.setOnItemClickListener(this);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try{
            Intent in = new Intent(this,Update_reservation.class);
            in.putExtra("hotel",hotel);
            in.putExtra("index",i);
            startActivity(in);
        }catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}