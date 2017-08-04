package com.tshibakela.newway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditMembers extends AppCompatActivity {

    DbAdapter db;
    String id,name,number,email,address, program,module,test,exam;
    EditText name1,number1,email1,address1, program1,module1,test1,exam1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contacts);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        email = intent.getStringExtra("EMAIL");
        address = intent.getStringExtra("ADDRESS");

        ((EditText) findViewById(R.id.name)).setText(name);
        ((EditText) findViewById(R.id.number)).setText(number);
        ((EditText) findViewById(R.id.email)).setText(email);
        ((EditText) findViewById(R.id.address)).setText(address);
        ((EditText) findViewById(R.id.programEdit)).setText(program);
        ((EditText) findViewById(R.id.moduleEdit)).setText(module);
        ((EditText) findViewById(R.id.testEdit)).setText(test);
        ((EditText) findViewById(R.id.examEdit)).setText(exam);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //get data from text feld
        name1 =(EditText)findViewById(R.id.name);
        number1 =(EditText)findViewById(R.id.number);
        email1 =(EditText)findViewById(R.id.email);
        address1 = (EditText)findViewById(R.id.address);

        program1 =(EditText)findViewById(R.id.programEdit);
        module1 =(EditText)findViewById(R.id.moduleEdit);
        test1 =(EditText)findViewById(R.id.testEdit);
        exam1 = (EditText)findViewById(R.id.examEdit);
    }
    public void Save(View v){
        name=name1.getText().toString();
        number=number1.getText().toString();
        email=email1.getText().toString();
        address=address1.getText().toString();

        program=program1.getText().toString();
        module=module1.getText().toString();
        test=test1.getText().toString();
        exam=exam1.getText().toString();
        db.update(Integer.parseInt(id),name, number, email, address,program,module,test,exam);
        Toast.makeText(getApplicationContext(),"Update success", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
