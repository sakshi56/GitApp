package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Calculator extends AppCompatActivity {
    Button b1, b2, b3, b4, b5;
    EditText ed1, ed2;
    TextView tv;
    double s1, s2, s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        b1 = (Button) findViewById(R.id.add);
        b2 = (Button) findViewById(R.id.sub);
        b3 = (Button) findViewById(R.id.mltp);
        b4 = (Button) findViewById(R.id.divi);
        b5 = (Button) findViewById(R.id.mdivi);
        ed1 = (EditText) findViewById(R.id.num1);
        ed2 = (EditText) findViewById(R.id.num2);
        tv = (TextView) findViewById(R.id.res);

        //addition button
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((ed1.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                } else if ((ed2.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    s1 = Double.parseDouble(ed1.getText().toString());
                    s2 = Double.parseDouble(ed2.getText().toString());
                    s3=s1+s2;
                    tv.setText(Double.toString(s3));
                }
            }
        });


        //Substraction button
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((ed1.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                } else if ((ed2.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    s1 = Double.parseDouble(ed1.getText().toString());
                    s2 = Double.parseDouble(ed2.getText().toString());
                    s3=s1-s2;
                    tv.setText(Double.toString(s3));
                }
            }
        });


        //Multiplication button
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((ed1.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                } else if ((ed2.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    s1 = Double.parseDouble(ed1.getText().toString());
                    s2 = Double.parseDouble(ed2.getText().toString());
                    s3=s1*s2;
                    tv.setText(Double.toString(s3));
                }
            }
        });


        //Divison button
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((ed1.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                } else if ((ed2.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    s1 = Double.parseDouble(ed1.getText().toString());
                    s2 = Double.parseDouble(ed2.getText().toString());
                    s3=s1/s2;
                    tv.setText(Double.toString(s3));
                }
            }
        });

        //Mod Division button
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sIntent=new Intent(Calculator.this,MainActivity.class);
                startActivity(sIntent);
            }
        });

    }
}



