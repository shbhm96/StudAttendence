package com.android.shubham.presentsir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

public class Login extends AppCompatActivity {
    private EditText email,pass;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        email=findViewById( R.id.editText5 );
        pass=findViewById( R.id.editText6 );
        mAuth=FirebaseAuth.getInstance();
    }
    public void gotoDashboard(View view) {
        String email1=email.getText().toString();
        String pass1=pass.getText().toString();
        Toast.makeText( this, "Button Pressed", Toast.LENGTH_SHORT ).show();
        //progressDialog.setMessage( "Fetching your data" );
        //progressDialog.show();
        /*if(email.getText().toString().equals( "root" )&&pass.getText().toString().equals( "admin123" )){
            startActivity( new Intent( Login.this, Dashboard.class ) );
        }else{
            Toast.makeText( this, "Hadd hogayo yeh toh", Toast.LENGTH_SHORT ).show();
            
        }*/try {
            Toast.makeText( this, "In the Try", Toast.LENGTH_SHORT ).show();
            mAuth.signInWithEmailAndPassword( email1, pass1 )
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText( Login.this, "In side onComplete fu8nction", Toast.LENGTH_SHORT ).show();
                            if (task.isSuccessful()) {
                                Intent i = new Intent( Login.this, Dashboard.class );
                                startActivity( i );
                                Toast.makeText( Login.this, "Lets ufcgfcydxyrdxrddrfdcfc   the error are:", Toast.LENGTH_SHORT ).show();
                            } else {
                                Toast.makeText( Login.this, "Lets wants the error are:", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
        }catch(Exception e){
            Toast.makeText( this, ""+e.toString(), Toast.LENGTH_SHORT ).show();
        }


        /*mAuth.signInWithEmailAndPassword( email1,pass1 )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Intent i=new Intent(Login.this,Dashboard.class);


                        }else{
                            Toast.makeText( Login.this, "Ab toh mara bc \nMar Gayo meri", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );*/
    }
}
