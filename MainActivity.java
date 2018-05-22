package com.android.shubham.presentsir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Intent i=getIntent();
        String value=i.getStringExtra( "value" );
        if(value.equals( "1" )){
            Toast.makeText( getApplicationContext(), "You have Registered successfully", Toast.LENGTH_SHORT ).show();
        }
    }
    public void gotoRegister(View view){
        startActivity( new Intent( MainActivity.this,registration.class ) );
    }
    public void gotoLogin(View view){

        startActivity( new Intent( MainActivity.this,Login.class));
    }
}
