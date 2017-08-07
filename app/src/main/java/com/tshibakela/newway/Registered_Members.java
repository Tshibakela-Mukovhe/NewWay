package com.tshibakela.newway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.tshibakela.newway.R.layout.activity_register_details;

public class Registered_Members extends AppCompatActivity {
    DbAdapter db;
    String id,name,number,email,address, program, module, test, exam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register_details);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        email = intent.getStringExtra("EMAIL");
        address = intent.getStringExtra("ADDRESS");

        program = intent.getStringExtra("PROGRAM");
        module = intent.getStringExtra("MODULE");
        test = intent.getStringExtra("TEST");
        exam = intent.getStringExtra("EXAM");


        ((TextView) findViewById(R.id.name)).setText(name);
        ((TextView) findViewById(R.id.number)).setText(number);
        ((TextView) findViewById(R.id.email)).setText(email);
        ((TextView) findViewById(R.id.address)).setText(address);

        ((TextView) findViewById(R.id.program)).setText(program);
        ((TextView) findViewById(R.id.module)).setText(module);
        ((TextView) findViewById(R.id.test)).setText(test);
        ((TextView) findViewById(R.id.exam)).setText(exam);


        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Edit(View v){
        //go to EditContact page
        Intent editRegister = new Intent(this, EditMembers.class);
        editRegister.putExtra("ID", id);
        editRegister.putExtra("NAME", name);
        editRegister.putExtra("NUMBER", number);
        editRegister.putExtra("EMAIL", email);
        editRegister.putExtra("ADDRESS",address);

        editRegister.putExtra("PROGRAM", program);
        editRegister.putExtra("MODULE", module);
        editRegister.putExtra("TEST", test);
        editRegister.putExtra("EXAM",exam);
        startActivity(editRegister);
    }





    public void Delete(View v){
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"deleted", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
