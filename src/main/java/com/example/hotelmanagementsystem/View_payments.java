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

import Utility.MyAdapterPayment;
import Utility.MyAdapterReservations;
import business_Logic.Hotel;
import business_Logic.Payment;
import business_Logic.Reservation;

public class View_payments extends AppCompatActivity implements AdapterView.OnItemClickListener{


    ListView lv_accs_from_view_acc;
    List<Payment> paymentList;
    Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_payments);
        try {
            hotel = (Hotel) getIntent().getSerializableExtra("hotel");

            lv_accs_from_view_acc = findViewById(R.id.lv_accs_from_view_accs);

            paymentList = hotel.getPayments();
            MyAdapterPayment adapter = new MyAdapterPayment(getApplicationContext(), paymentList);
            adapter.notifyDataSetChanged();
            lv_accs_from_view_acc.setAdapter(adapter);

            lv_accs_from_view_acc.setOnItemClickListener(this);



        }catch (Exception e){
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try{
            Intent in = new Intent(this,Activity_Update_Payment.class);
            in.putExtra("hotel",hotel);
            in.putExtra("index",i);
            startActivity(in);
        }catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}