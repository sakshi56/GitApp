package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;
import java.text.DateFormat;
import java.util.Calendar;

import android.text.TextUtils;
public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button b1,b2,b3;
    EditText ed1,ed2,ed3,ed4,passcnfm;
    TextView txv;
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDate= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        txv=(TextView) findViewById(R.id.dobtxt);
        txv.setText(currentDate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txv=(TextView) findViewById(R.id.dobtxt);
        b1 = (Button) findViewById(R.id.registerButton);
        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.gmail);
        ed3 = (EditText) findViewById(R.id.pass);
        passcnfm=(EditText)findViewById(R.id.passcnfrm);
        ed4 = (EditText) findViewById(R.id.contact);
            ed4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()==10) {
//            ed4.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        closeKeyboard();

//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//             imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


//        String cnct=ed4.getText().toString().trim();



        b2 = (Button) findViewById(R.id.backButton);
        b3= (Button) findViewById(R.id.calcButton);
        txv=(TextView) findViewById(R.id.dobtxt);
        txv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker=new com.example.myapplication.DatePicker();
                datepicker.show(getSupportFragmentManager(),"Date of  Birth");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String Iname = ed1.getText().toString();
                final String gmail = ed2.getText().toString();
                final String passw = ed3.getText().toString();
                final String contact = ed4.getText().toString();
                final String date = txv.getText().toString();

                DatabaseHelper Mydb = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = Mydb.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", Iname);
                values.put("gmail", gmail);
                values.put("password", passw);
                values.put("contact", contact);
                values.put("date", date);

                boolean name, email, pass, cpass, cntct, dateBool;

                name = validateName();
                if (name == true) {
                    email = validateEmail();
                    if (email == true) {
                        pass = validatePassword();
                        if (pass == true) {
                            cntct = validateContact();
                            if (cntct == true) {
                                cpass = validateCnfmPassword();
                                if (cpass == true) {

                                    dateBool = validateDate();
                                    if (dateBool == true) {
                                        db.insert("Adminm", null, values);
                                        Toast.makeText(getApplicationContext(), "Data Added!=", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }
                    }

                }

//                    if(name==true && email==true && pass==true && cpass==true &&cntct==true && dateBool==true){
                else{
                    Toast.makeText(getApplicationContext(), "Please fill all the fields correctly!=", Toast.LENGTH_SHORT).show() ;
                }

            }


        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sIntent = new Intent(Register.this, MainActivity.class);
                startActivity(sIntent);
            }

        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sIntent = new Intent(Register.this, Calculator.class);
                startActivity(sIntent);
            }

        });
    }

    private void closeKeyboard() {
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show() ;

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }

    private boolean validateEmail(){
        String emailInput= ed2.getText().toString().trim();
        if(emailInput.isEmpty()){
            ed2.setError("can't be Empty");
            return false;
        }
        else if(!emailInput.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            ed2.setError("Please Enter valid Email");
            return false;
        }
        else{
            ed2.setError(null);
            return true;
        }
    }
    private boolean validateName(){
        String name=ed1.getText().toString().trim();
        if(name.isEmpty()){
            ed1.setError("Can't be Empty");
            return false;
        }

        else{
            ed1.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String passInput= ed3.getText().toString().trim();
        if(passInput.isEmpty()){
            ed3.setError("can't be Empty");
            return false;
        }
        else if(passInput.length()<6){
            ed3.setError("Minimum 6 character Required");
            return false;
        }
        else{
            ed3.setError(null);
            return true;
        }
    }

    private boolean validateCnfmPassword(){
        String strPass1 = ed3.getText().toString();
        String strPass2 = passcnfm.getText().toString();


        String cpassInput= passcnfm.getText().toString().trim();
        if(cpassInput.isEmpty()){
            passcnfm.setError("Please Confirm Password");
            return false;
        }
        else if(cpassInput.length()<6){
            passcnfm.setError("Minimum 6 character Required");
            return false;
        }
        else if (!strPass1.equals(strPass2)) {
            passcnfm.setError("Password does'nt match!=");

            return false;
        }
        else{
            passcnfm.setError(null);
            return true;
        }
    }
    private boolean validateContact(){
        String contact=ed4.getText().toString().trim();
        if(contact.isEmpty()){
            ed4.setError("Can't be Empty");
            return false;
        }
        else if(contact.length()<10){
            ed4.setError(" 10 character Required");
            return false;
        }
        else if(contact.length()>10){
            ed4.setError("Invalid Number");
            return false;
        }

        else{
            ed4.setError(null);
            return true;
        }
    }
    private boolean validateDate(){
        String date=txv.getText().toString().trim();
        if(date.isEmpty()){
            txv.setError("Can't be Empty");
            return false;
        }

        else{
            txv.setError(null);
            return true;
        }
    }
}
