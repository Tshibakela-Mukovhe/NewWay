package com.tshibakela.newway;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tshibakela on 2017/07/31.
 */

public class registerNew extends AppCompatActivity {
    //calling variables
    DbAdapter db;
    EditText name1,number1,email1,address1, program1, module1, test1, exam1, password1;
    String name,number,email,address, program, module, test, exam, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contacts);
        //get data from text feld
        name1 =(EditText)findViewById(R.id.name);
        number1 =(EditText)findViewById(R.id.number);
        email1 =(EditText)findViewById(R.id.email);
        address1 = (EditText)findViewById(R.id.address);

        //for student performance
        program1=(EditText)findViewById(R.id.programEdit);
        module1=(EditText)findViewById(R.id.moduleEdit);
        test1=(EditText)findViewById(R.id.testEdit);
        exam1=(EditText)findViewById(R.id.examEdit);

        //for password
        password1=(EditText)findViewById(R.id.passwordEdit);



        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Save(View v){
        if(db.isExist(number)){
            Toast.makeText(getApplicationContext(),"already exist", Toast.LENGTH_SHORT).show();
        }else{
            name=name1.getText().toString();
            number=number1.getText().toString();
            email=email1.getText().toString();
            address=address1.getText().toString();
            //db.insert(name,number,email,address);


            program=program1.getText().toString();
            module=module1.getText().toString();
            test=test1.getText().toString();
            exam=exam1.getText().toString();
            db.insert(name,number,email,address,program,module,test,test);


            Toast.makeText(getApplicationContext(),"Contact added", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
