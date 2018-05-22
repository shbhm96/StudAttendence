package com.android.shubham.presentsir;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class Verification extends AppCompatActivity {
    private String mobile,otp;
    private EditText ed1,ed2;
    private ProgressDialog progressDialog;
    private Button setOTP,verifyOTP;
    long time1,time2;
    private Random rand =new Random(  );
    AlertDialog alertDialog;
    private  int otp_no=367611;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_verification );
        setOTP=findViewById( R.id.button5 );
        verifyOTP=findViewById( R.id.button6 );
        ed1=findViewById( R.id.editText7 );

        ed2=findViewById(  R.id.editText8 );
        Toast.makeText( Verification.this, "Yahan aaaya?", Toast.LENGTH_SHORT ).show();
        verifyOTP.setEnabled( false );
    }
    public void createOTP(){
        otp_no=otp_no+13263;
    }

    public void getOTP11(View view){
        mobile=ed1.getText().toString();

        if(mobile.isEmpty()){
            Toast.makeText( Verification.this, "Please Enter Mobile number", Toast.LENGTH_SHORT ).show();
            return;
        }
        if(mobile.length()!=10){
            Toast.makeText( Verification.this, "Please enter a valid phone number", Toast.LENGTH_SHORT ).show();
            return;
        }

       /* while(otp_no<1000){
            otp_no=(int)rand.nextDouble()*10000;
        }*/

        new AlertDialog.Builder( Verification.this )
                .setIcon( R.drawable.common_google_signin_btn_icon_dark )
                .setTitle( "!!Alert!!" )
                .setMessage( "Your OTP is:\n"+otp_no +"\n\nWill be valid for 5 mins only.")
                .setPositiveButton( "OK",null )
                .setNegativeButton( "",null )
                .show();
        time1=System.currentTimeMillis();
        verifyOTP.setEnabled( true );
        ed1.setEnabled( false );
        setOTP.setEnabled( false );

    }
    public void getVerified11(View v){
        String otp=ed2.getText().toString();
        time2=System.currentTimeMillis();
        if(otp.isEmpty()||otp.length()!=6){
            Toast.makeText( Verification.this, "Please neter the details correctly", Toast.LENGTH_SHORT ).show();
            return;
        }
        else if(Integer.parseInt( otp)!=otp_no){
            Toast.makeText( Verification.this,"Please enter correct details",Toast.LENGTH_LONG );
        }
        else if((time2-time1)>300030){
         alertDialog=new AlertDialog.Builder( Verification.this )
                    .setIcon( android.R.drawable.ic_dialog_alert )
                    .setTitle( "Expired OTP" )
                    .setMessage( "You have entered an Expired OTP" )
                    .setPositiveButton( "OK",null )
                    .setNegativeButton( "",null )
                    .show();
            //otp_no=0;
            /*while(otp_no<1000){
                otp_no=(int) rand.nextDouble()*10000;
            }*/
            createOTP();
            alertDialog=new AlertDialog.Builder( Verification.this )
                    .setIcon( android.R.drawable.ic_dialog_alert )
                    .setTitle( "!!New OTP!!" )
                    .setMessage( "Your New OTP is:"+otp_no )
                    .setPositiveButton( "OK" ,null)
                    .setNegativeButton( "",null )
                    .show();
        }
        else{
            Intent i=new Intent( Verification.this,MainActivity.class );
            i.putExtra( "value" ,1);
            startActivity( i );
        }
    }
}