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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import business_Logic.Hotel;
import business_Logic.Reservation;

public class Update_reservation extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spinner_price;
    private Spinner spinner_rooms;
//    private Spinner spinner_c

    Boolean changed=false;
    private Hotel hotel;
    private String[] prices;
    int index;
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
        setContentView(R.layout.activity_update_reservation);

        hotel = (Hotel) getIntent().getSerializableExtra("hotel");
        index= getIntent().getIntExtra("index",0);

        Reservation res=hotel.getReservation(index);

        //Setting first Spinner, i.e. Capacity
        String [] capacity ={"1","2","3"};//,4,5,6,7,8,9,10};
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
        btn_date_from.setText(res.getFrom_date());

        AppCompatButton btn_date_to = findViewById(R.id.btn_date_to);
        btn_date_to.setOnClickListener(this);
        btn_date_to.setText(res.getTo_date());

        AppCompatButton btn_del_res=findViewById(R.id.btn_del_res_from_add_res);
        btn_del_res.setOnClickListener(this);

        AppCompatButton btn_add_res_from_add_res = findViewById(R.id.btn_add_res_from_add_res);
        btn_add_res_from_add_res.setOnClickListener(this);


        EditText edt_customer_cnic_from_add_res=findViewById(R.id.edt_cnic_from_add_res);
        edt_customer_cnic_from_add_res.setText(res.getCustomer_CNIC());


        room_No=res.getRoom_No();
        from_date=res.getFrom_date();
        to_date=res.getTo_date();
        customer_CNIC=res.getCustomer_CNIC();
        amount=res.getAmount();

        from=Calendar.getInstance();
        to=Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            from.setTime(sdf.parse(from_date));
            to.setTime(sdf.parse(to_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

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
                    from.set(i,i1+1,i2);
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
                    to.set(i,i1+1,i2);

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

                else if(from.before(Calendar.getInstance().getTime()) || to.before(Calendar.getInstance().getTime()))
                    Toast.makeText(this,"You cannot make a reservation in past",Toast.LENGTH_SHORT).show();
                else if(to_date.equals("2022-3-23"))
                    Toast.makeText(this,"End Date is before Start Date",Toast.LENGTH_SHORT).show();
                else if(customer_CNIC.equals(""))
                    Toast.makeText(this,"Enter all fields",Toast.LENGTH_SHORT).show();
                else{


                    Boolean b= hotel.updateReservation(index,room_No, from_date, to_date, customer_CNIC, amount);

//                ((Room)hotel.getRoom(room_No)).
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("hotel", hotel);
                    startActivity(i);
                    if(b)
                        Toast.makeText(this,"Reservation Updated",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                EditText obj=(EditText)findViewById(R.id.edt_cnic_from_add_res);
                obj.setText(e.getMessage());
            }

        }
        else if(view.getId()==R.id.btn_del_res_from_add_res)
        {

            hotel.deleteReservation(index);
            Toast.makeText(this,"Reservation Deleted",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("hotel", hotel);
            startActivity(i);

        }
    }

}