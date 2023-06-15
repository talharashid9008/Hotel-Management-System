package com.example.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import business_Logic.Hotel;

public class Activity_Add_Payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    Hotel hotel;
    String date;
    String room_No;
    String comments;
    String reservation_id;
    String amount;
    Spinner spinner_res_from_add_acc;
    EditText edt_comments_From_add_acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_payment);

        hotel=(Hotel)getIntent().getSerializableExtra("hotel");

        spinner_res_from_add_acc=findViewById(R.id.spinner_res_from_add_acc);

        ArrayList<String> list=  hotel.getReservationIDforPayments();
        String []Res = list.toArray(new String[list.size()]);
        ArrayAdapter<String>arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, Res);
        spinner_res_from_add_acc.setAdapter(arrayAdapter);
        spinner_res_from_add_acc.setOnItemSelectedListener(this);

        Button btn_date_from_add_acc=findViewById(R.id.btn_date_from_add_acc);
        Button btn_add_acc_from_add_acc=findViewById(R.id.btn_add_acc_from_add_acc);
        edt_comments_From_add_acc=findViewById(R.id.edt_comments_from_add_acc);
        btn_date_from_add_acc.setOnClickListener(this);
        btn_add_acc_from_add_acc.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btn_date_from_add_acc)
        {
            Calendar calendar=Calendar.getInstance();
            int y=calendar.get(Calendar.YEAR);
            int m=calendar.get(Calendar.MONTH);
            int d=calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    ((AppCompatButton)findViewById(R.id.btn_date_from_add_acc)).setText(i+"/"+(i1+1)+"/"+i2);
                    date=i+"-"+(i1+1)+"-"+i2;

                }
            },y,m,d);
            datePickerDialog.show();
        }
        else if(view.getId()==R.id.btn_add_acc_from_add_acc){
            room_No=hotel.getReservationsforPayments().get(spinner_res_from_add_acc.getSelectedItemPosition()).getRoom_No();
            amount=hotel.getReservationsforPayments().get(spinner_res_from_add_acc.getSelectedItemPosition()).getAmount();
            reservation_id=hotel.getReservationsforPayments().get(spinner_res_from_add_acc.getSelectedItemPosition()).getReservation_ID();
            comments=edt_comments_From_add_acc.getText().toString();

            Boolean b= hotel.makePayment(amount,date,comments,reservation_id);

//                ((Room)hotel.getRoom(room_No)).
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("hotel", hotel);
            startActivity(i);
            Toast.makeText(this, "payment added", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}