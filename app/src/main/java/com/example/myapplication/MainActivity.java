package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.loginbutton);
        ed1=(EditText)findViewById(R.id.edit1);
        ed2=(EditText)findViewById(R.id.edit2);
        b2=(Button)findViewById(R.id.signupbutton);

        try {
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                   DatabaseHelper Mydb= new DatabaseHelper(getApplicationContext());
                    SQLiteDatabase db=Mydb.getReadableDatabase();

                    String[] columns={"gmail","password"};
                    String[] cValues={ed1.getText().toString(),ed2.getText().toString()};
                    Cursor cursor= db.query("Adminm",columns,"gmail=? AND password=?",cValues,null,null,null);
                   if(cursor!=null){
                      if(cursor.moveToFirst()){
                          Toast.makeText(getApplicationContext(), "Succesfull Login !....", Toast.LENGTH_SHORT).show();
                          Intent intent=new Intent(MainActivity.this,Calculator.class);
                          startActivity(intent);
                      }
                      else{
                         Toast.makeText(getApplicationContext(), "Invalid Entry !....", Toast.LENGTH_SHORT).show();
                      }

                   }

//
//                   if ((ed1.getText().toString().equals(""))) {
//                        Toast.makeText(getApplicationContext(), "Email  can not be empty", Toast.LENGTH_SHORT).show();
//                   } else if ((ed2.getText().toString().equals(""))) {
//                       Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
//                   } else if (ed1.getText().toString().equals("sakshilodha56@gmail.com") &&
//                           ed2.getText().toString().equals("112189")) {
//
//                       Toast.makeText(getApplicationContext(), "Login Succesfull!....", Toast.LENGTH_SHORT).show();
//
//
//                   } else {
//                        Toast.makeText(getApplicationContext(), "Invalid attempt !....", Toast.LENGTH_SHORT).show();
//                   }
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v1){
                Intent sIntent=new Intent(MainActivity.this,Register.class);
                startActivity(sIntent);
                }

            });
        }


        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error !....", Toast.LENGTH_SHORT).show();
        }
    }

}


