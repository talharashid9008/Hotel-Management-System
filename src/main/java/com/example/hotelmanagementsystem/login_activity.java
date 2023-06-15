package com.example.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_username;
    EditText edt_password;
    AppCompatButton btn_login ;
    CheckBox chk_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

         edt_username=findViewById(R.id.edt_username);
         edt_password=findViewById(R.id.edt_password);
         btn_login = findViewById(R.id.btn_login);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("username", "");
        String s2 = sh.getString("password", "");

// We can then use the data
        edt_username.setText(s1);
        edt_password.setText(s2);

         btn_login.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view==btn_login)
        {
            String username=edt_username.getText().toString();
            String password=edt_password.getText().toString();

                if((username.equals("talha663") && password.equals("tr1986")) || (username.equals("faiq") && password.equals("hamza123")) )
                {


                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);


                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("username", edt_username.getText().toString());
                    myEdit.putString("password", edt_password.getText().toString());

                try {

                    myEdit.commit();
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                }catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                }else{
                    Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }


        }
    }
}