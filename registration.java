package com.android.shubham.presentsir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {
    private EditText name, email, pass, con_pass;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(this);
        name = findViewById( R.id.editText );
        email = findViewById( R.id.editText2 );
        progressDialog=new ProgressDialog( this );

        pass = findViewById( R.id.editText3 );
        con_pass = findViewById( R.id.editText4 );

        Toast.makeText( this, "AAya gaya? check??", Toast.LENGTH_LONG ).show();

    }

    public void gotoVerification(View view) {
        String name1=name.getText().toString();
        String con_pass1=con_pass.getText().toString();
        String email1=email.getText().toString();
        String pass1=pass.getText().toString();

        if (name1.isEmpty()) {
            Toast.makeText( registration.this, "Please enter your name...", Toast.LENGTH_SHORT ).show();
            return;
        } else if (email1.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher( email1 ).matches()) {
            Toast.makeText( registration.this, "Please enter your e-mail_id...", Toast.LENGTH_LONG ).show();
            return;
        } else if (pass1.isEmpty()) {
            Toast.makeText( registration.this, "Please enter your password..", Toast.LENGTH_LONG ).show();
            return;
        } else if (con_pass1.isEmpty()) {
            Toast.makeText( registration.this, "Please enter your confirm-password..", Toast.LENGTH_SHORT ).show();
            return;
        }else if(con_pass1.equals( pass )){
            Toast.makeText( registration.this, "Password does't Mathches", Toast.LENGTH_SHORT ).show();
            return;
        } else {
            try {
                progressDialog.setMessage( "Registering Your data" );
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword( email1,pass1 )
                        .addOnCompleteListener(  new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    Intent i=new Intent( registration.this,Verification.class );
                                    startActivity(i);
                                }else{

                                    Toast.makeText( registration.this, "Some Err occured", Toast.LENGTH_SHORT ).show();

                                }

                            }
                        } );
            } catch (Exception e) {
                Toast.makeText( this, "" + e.toString(), Toast.LENGTH_SHORT ).show();
            }
        }
    }
}

                /*mAuth.createUserWithEmailAndPassword( email1, pass1 )
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity( new Intent( registration.this, Verification.class ) );
                                } else {
                                    Toast.makeText( registration.this, "Bc kya ho gaya", Toast.LENGTH_SHORT ).show();
                                }
                            }
                        } );
            }catch(Exception e){
                Toast.makeText( registration.this, ""+e.toString(), Toast.LENGTH_SHORT ).show();
            }
        }
    }
}*/