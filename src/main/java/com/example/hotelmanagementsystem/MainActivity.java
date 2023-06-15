package com.example.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import business_Logic.Hotel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        hotel=(Hotel)getIntent().getSerializableExtra("hotel");

        AppCompatButton btn_res_from_home = findViewById(R.id.btn_res_from_home);
        btn_res_from_home.setOnClickListener(this);



        Button btn_acc_from_home=findViewById(R.id.btn_acc_from_home);
        btn_acc_from_home.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_res_from_home)
        {
            try {
                if(hotel==null)
                {
                    hotel = new Hotel();
                    hotel.setName("TFH");
                }

                Intent i = new Intent(this, Activity_Reservations.class);
                i.putExtra("hotel", hotel);
                startActivity(i);
            }catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

        if(view.getId()==R.id.btn_acc_from_home)
        {
            try {
                if(hotel==null)
                {
                    hotel = new Hotel();
                    hotel.setName("MBH");
                }

                Intent i = new Intent(this, Activity_Accounts.class);
                i.putExtra("hotel", hotel);
                startActivity(i);
            }catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
}