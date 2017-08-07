package com.tshibakela.newway;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    //calling variables
    DbAdapter db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //initially insert some data

        //display data
        ListView listView = (ListView) findViewById(R.id.listView1);
        int layoutstyle=R.layout.liststyle;
        int[] xml_id = new int[] {
                R.id.txtname,
                R.id.txtnumber
        };
        String[] column = new String[] {
                "name",
                "number"
        };
        Cursor row = db.fetchAllData();
    adapter = new SimpleCursorAdapter(this, layoutstyle,row,column, xml_id, 0);



        listView.setAdapter(adapter);
        //onClick function
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String name = row.getString(row.getColumnIndexOrThrow("name"));
                String number = row.getString(row.getColumnIndexOrThrow("number"));
                String email = row.getString(row.getColumnIndexOrThrow("email"));
                String address = row.getString(row.getColumnIndexOrThrow("address"));

                String proram = row.getString(row.getColumnIndexOrThrow("program"));
                String module = row.getString(row.getColumnIndexOrThrow("module"));
                String test = row.getString(row.getColumnIndexOrThrow("test"));
                String exam = row.getString(row.getColumnIndexOrThrow("exam"));
               // String address = row.getString(row.getColumnIndexOrThrow("address"));

                //go to detailsContact page
                Intent detailsIntent = new Intent(MainActivity.this, Registered_Members.class);
                detailsIntent.putExtra("ID",_id);
                detailsIntent.putExtra("NAME", name);
                detailsIntent.putExtra("NUMBER",number);
                detailsIntent.putExtra("EMAIL",email);
                detailsIntent.putExtra("ADDRESS",address);

                detailsIntent.putExtra("PROGRAM", proram);
                detailsIntent.putExtra("MODULE",module);
                detailsIntent.putExtra("TEST",test);
                detailsIntent.putExtra("EXAM",exam);
                startActivity(detailsIntent);
            }
        });
        //Show data by filter search @using name
        EditText editSearch = (EditText) findViewById(R.id.myFilter);
        editSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
       adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(),"name");
            }
   });
    }
    public void addStudent(View v){
        Intent addNewContact1 = new Intent(getApplicationContext(), registerNew.class);
        startActivity(addNewContact1);


    }

}
