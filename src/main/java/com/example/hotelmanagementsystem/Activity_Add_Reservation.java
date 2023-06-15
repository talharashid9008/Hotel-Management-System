package com.example.hotelmanagementsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;

import business_Logic.Hotel;

public class Activity_Add_Reservation extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner spinner_price;
    private Spinner spinner_rooms;
//    private Spinner spinner_c

    private Boolean changed=false;
    private Hotel hotel;
    private String[] prices;

    String room_No;
    String from_date;
    Calendar from;
    Calendar to;
    String to_date;
    String customer_CNIC;
    String amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_reservation);

        //Getting Attribute from previous Activity
        hotel=(Hotel)getIntent().getSerializableExtra("hotel");

        //Setting first Spinner, i.e. Capacity
        String [] capacity ={"1","2","3"};
        Spinner spinner_capacity=findViewById(R.id.spinner_res_from_add_acc);
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this, R.layout.spinner_item,capacity);
        spinner_capacity.setAdapter(arrayAdapter);
        spinner_capacity.setOnItemSelectedListener(this);


        //Setting Second Spinner, i.e. Prices
            spinner_price =findViewById(R.id.spinner_price);
            ArrayList<String> list=  hotel.getPrices(1);
            prices = list.toArray(new String[list.size()]);
            arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, prices);
            spinner_price.setAdapter(arrayAdapter);
            spinner_price.setOnItemSelectedListener(this);



        //Setting Second Spinner , i.e. Rooms
            spinner_rooms=findViewById(R.id.spinner_rooms);
            list = hotel.getAvailableRooms(1,5000);
            String [] rooms=list.toArray(new String[list.size()]);
            arrayAdapter=new ArrayAdapter<>(this,R.layout.spinner_item,rooms);
            spinner_rooms.setAdapter(arrayAdapter);
            spinner_rooms.setOnItemSelectedListener(this);

        //From Date Picker
            AppCompatButton btn_date_from = findViewById(R.id.btn_date_from_add_acc);
            btn_date_from.setOnClickListener(this);

            AppCompatButton btn_date_to = findViewById(R.id.btn_date_to);
            btn_date_to.setOnClickListener(this);

            AppCompatButton btn_add_res_from_add_res = findViewById(R.id.btn_add_res_from_add_res);
            btn_add_res_from_add_res.setOnClickListener(this);


            EditText edt_customer_cnic_from_add_res=findViewById(R.id.edt_cnic_from_add_res);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        //Toast.makeText(this,Integer.toString(adapterView.getId()),Toast.LENGTH_LONG).show();
        Integer id_price_spinner=2131231113;
        Integer id_rooms_spinner=2131231114;
        if(adapterView.getId()==id_price_spinner && changed)
        {
            //Changing Price Based on Capacity
                ArrayList<String> list= hotel.getPrices(i+1);
                prices = list.toArray(new String[list.size()]);
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, R.layout.spinner_item,prices);
                spinner_price.setAdapter(arrayAdapter);
                //spinner_price.setOnItemSelectedListener(this);


            //Changing Rooms Based on Capacity and Price
                ArrayList<String> ls = hotel.getAvailableRooms(i+1,Double.parseDouble(prices[0]));
                String [] rooms = ls.toArray(new String[ls.size()]);
                arrayAdapter=new ArrayAdapter<>(this,R.layout.spinner_item,rooms);
                spinner_rooms.setAdapter(arrayAdapter);
                //  spinner_rooms.setOnItemSelectedListener(this);

        }

        else changed=true;


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    ((AppCompatButton)findViewById(R.id.btn_date_from_add_acc)).setText("From:   "+i+"/"+(i1+1)+"/"+i2);
                    from_date=i+"-"+(i1+1)+"-"+i2;
                    from=Calendar.getInstance();
                    from.set(i,i1,i2);
                }
            },y,m,d);
            datePickerDialog.show();
        }
        else if(view.getId()==R.id.btn_date_to)
        {
            Calendar calendar=Calendar.getInstance();
            int y=calendar.get(Calendar.YEAR);
            int m=calendar.get(Calendar.MONTH);
            int d=calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    ((AppCompatButton)findViewById(R.id.btn_date_to)).setText("To:      "+i+"/"+(i1+1)+"/"+i2);
                    to_date=i+"-"+(i1+1)+"-"+i2;
                    to=Calendar.getInstance();
                    to.set(i,i1,i2);

                }
            },y,m,d);
            datePickerDialog.show();
        }
        else if(view.getId()==R.id.btn_add_res_from_add_res)
        {

            try {

                room_No=spinner_rooms.getSelectedItem().toString();
                customer_CNIC=((EditText) findViewById(R.id.edt_cnic_from_add_res)).getText().toString();
                amount=prices[0];
//                if(hotel.)

               if(!customer_CNIC.matches("^[0-9]{5}-[0-9]{7}-[0-9]{1}$"))
                   Toast.makeText(this,"Invalid CNIC",Toast.LENGTH_SHORT).show();
               else if(from.getTime().before(Calendar.getInstance().getTime()) || to.getTime().before(Calendar.getInstance().getTime()))
                   Toast.makeText(this,"You cannot make a reservation in past",Toast.LENGTH_SHORT).show();
               else if(to.getTime().before(from.getTime()))
                   Toast.makeText(this,"End Date is before Start Date",Toast.LENGTH_SHORT).show();
               else if(customer_CNIC.equals(""))
                   Toast.makeText(this,"Enter all fields",Toast.LENGTH_SHORT).show();
               else{


                Boolean b= hotel.makeReservation(room_No, from_date, to_date, customer_CNIC, amount);

//                ((Room)hotel.getRoom(room_No)).
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("hotel", hotel);
                startActivity(i);
                if(b)
                    Toast.makeText(this,"Reservation Added",Toast.LENGTH_SHORT).show();
               }

            }catch (Exception e)
            {
            Toast.makeText(this,"Enter all Fields",Toast.LENGTH_SHORT).show();
            }

        }
    }
}