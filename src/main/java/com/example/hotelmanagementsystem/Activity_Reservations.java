package com.example.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import business_Logic.Hotel;

public class Activity_Reservations extends AppCompatActivity implements View.OnClickListener {
    Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reservations);

        hotel=(Hotel)getIntent().getSerializableExtra("hotel");


        Button btn_add_res_from_res = findViewById(R.id.btn_res_from_res);
        btn_add_res_from_res.setOnClickListener(this);

        Button btn_view_res_from_res=findViewById(R.id.btn_view_res_from_res);
        btn_view_res_from_res.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_res_from_res)
        {   try{
            Intent i = new Intent(this,Activity_Add_Reservation.class);
            i.putExtra("hotel",hotel);
            startActivity(i);
        }catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        }
        else if(view.getId()==R.id.btn_view_res_from_res)
        {
            try{
                Intent i = new Intent(this,view_reservations.class);
                i.putExtra("hotel",hotel);
                startActivity(i);
            }catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}