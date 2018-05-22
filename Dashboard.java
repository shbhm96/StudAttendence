package com.android.shubham.presentsir;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Dashboard extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    String[] tags={"0"};
    String fname="";
    String body="";
    private ListView lv1;
    ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_2, arrayList );
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    final String[] studentData = {"00216404516  Aanchal Malholtra", "00316404516  Abhishek", "00416404516  Abhishek Sharma",
            "00616404516  Alankit Baweja", "00716404516  Alok Jha", "00816404516  Anjali Juneja", "00916404516 Ankur Singh",
            "01016404516 Anuj Duggal", "01116404516 Arsla Saleem", "01216404516 Ayushi Jain", "01316404516 Deepak Singh",
            "01416404516 Deepika Tiwari", "01516404516 Dhruv Oberoi", "01616404516 Diksha Dayal", "01716404516 Diksha Mehra",
            "01916404516 Divya Arora", "02116404516 Dushyant Rana", "02316404516 Himanshu", "02416404516 Jatin Goyal",
            "02516404516 Kanchan Sinha", "02616404516 Kunga Jigme", "02716404516 Mandeep Baliyan", "02816404516 Maninder Singh",
            "02916404516 Manish Parashar", "03116404516 Nabeel Hasan", "03316404516 Nidhi", "03416404516 Nisha Kanyal",
            "03516404516 Onkar Bansal", "03616404516 Piyush Gupta", "03816404516 Pritesh Kumar", "03916404516 Ragav Shadija",
            "04016404516 Rahul Kumar", "04116404516 Rajesh Kumar", "04216404516 Rakesh Patel", "04316404516 Rishabh Dutt",
            "04416404516 Rohit Kumar", "04516404516 Rohit Sehajpal", "04616404516 Shatakshi", "04716404516 Shikardeep", "04816404516 Souvik",
            "04916404516 Udit Singh", "05016404516 Varun Kumer", "05116404516 Vasundhra", "40316404516 Nitesh Kaushik", "40416404516 Vivek Dagar",
            "40716404516 Shubham", "40816404516 Anisha Goyal", "41016404516 Deepshika", "70116404516 Jeevan Prakash"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String currentDate= DateFormat.getDateInstance().format( "yyyy-MM-dd" );
        fname+="Attendence"+currentDate+".xls";
        lv1 = findViewById( R.id.listView1 );

        arrayList = new ArrayList<>( Arrays.asList( studentData ) );
        lv1.setAdapter( arrayAdapter );
        lv1.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getChildAt( position ).setBackgroundResource( R.drawable.common_google_signin_btn_icon_light);
                tags[position]="1";
            }
        } );

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.main_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onOptionsItemSelected( menuItem );
        switch (menuItem.getItemId()) {
            case R.id.sub1: {
                body="S.no\t Roll_No \t Name\t\n";
                for(int i=0;i<lv1.getChildCount();i++){
                    if(tags[i].equals( "1")) {
                        body += (i + 1) + studentData[i] + "Present";
                    }
                    else {
                        body += (i + 1) + studentData[i] + "Absent";
                    }
                }
                writeFileOnInternalStorage( getApplicationContext(),fname,body );
                return true;
            }

            case R.id.logg: {
                logOut();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public void logOut() {
        firebaseAuth.signOut();
        finish();
        startActivity( new Intent( Dashboard.this, MainActivity.class ) );

    }
    public void writeFileOnInternalStorage(Context context,String fname,String body){
        File file=new File( context.getFilesDir(),"StudAttendence" );
        if (!(file.exists())) {
            file.mkdir();
        }
        try{
            File gpxFile=new File(file,fname);
            FileWriter fileWriter=new FileWriter( gpxFile );
            fileWriter.append( body );
            fileWriter.close();
        }catch(Exception e){
            Toast.makeText( getApplicationContext(), "bs ISI ki Kammi thi", Toast.LENGTH_SHORT ).show();
        }
    }
}

