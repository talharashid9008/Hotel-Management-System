package com.example.hotelmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import business_Logic.Hotel;

public class Activity_Accounts extends AppCompatActivity implements View.OnClickListener {
    Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_accounts);
try {
    hotel = (Hotel) getIntent().getSerializableExtra("hotel");

    Button btn_add_acc_from_acc = findViewById(R.id.btn_add_acc_from_acc);
    Button btn_view_acc_from_acc = findViewById(R.id.btn_view_acc_from_acc);


    btn_add_acc_from_acc.setOnClickListener(this);
    btn_view_acc_from_acc.setOnClickListener(this);
}catch (Exception e)
{
    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
}
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_add_acc_from_acc)
        {   try{
            Intent i = new Intent(this,Activity_Add_Payment.class);
            i.putExtra("hotel",hotel);
            startActivity(i);
        }catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        }
        if(view.getId()==R.id.btn_view_acc_from_acc)
        {   try{
            Intent i = new Intent(this,View_payments.class);
            i.putExtra("hotel",hotel);
            startActivity(i);
        }catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        }
    }
}