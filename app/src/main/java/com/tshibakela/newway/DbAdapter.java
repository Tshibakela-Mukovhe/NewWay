package com.tshibakela.newway;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tshibakela on 2017/08/02.
 */

public class DbAdapter {



    //define static variable
    public static int dbversion = 19;
    public static String dbname = "Register_db";
    public static String dbTable = "register";
    //establsh connection with SQLiteDataBase
    private final Context c;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqliteDb;
    public DbAdapter(Context context) {
        this.c = context;
    }

    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(c);
        sqliteDb = dbHelper.getWritableDatabase();
        return this;
    }

    //insert data
    public void insert(String text2,String text3,String text4,String text5,String text6,String text7,String text8,String text9) {
        if(!isExist(text3)) {
            sqliteDb.execSQL("INSERT INTO register (name,number,email,address, program, module,test, exam ) VALUES('" + text2 + "','" + text3 + "','" + text4 + "','" + text5+ "','" + text6 + "','" + text7+ "','" + text8 + "','" + text9 + "')");
        }
    }

    //check entry already in database or not
    public boolean isExist(String num){
        String query = "SELECT number FROM register WHERE number='"+num+"' LIMIT 1";
        Cursor row = sqliteDb.rawQuery(query, null);
        return row.moveToFirst();
    }

    //edit data entire Student details by admin
    public void update(int id,String text2,String text3,String text4,String text5, String text6,String text7,String text8,String text9) {
        sqliteDb.execSQL("UPDATE "+dbTable+" SET name='"+text2+"', number='"+text3+"', email='"+text4+"', address='"+text5+"', program='"+text6+"', module='"+text7+"', test='"+text8+"', exam='"+text9+"'   WHERE _id=" + id);
    }

    //delete data
    public void delete(int id) {
        sqliteDb.execSQL("DELETE FROM "+dbTable+" WHERE _id="+id);
    }

    //fetch data
    public Cursor fetchAllData() {
        String query = "SELECT * FROM "+dbTable;
        Cursor row = sqliteDb.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }

    //fetch data by filter
    public Cursor fetchdatabyfilter(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+dbTable;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = sqliteDb.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = sqliteDb.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, dbname, null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + dbTable + " (_id INTEGER PRIMARY KEY autoincrement,name, number, email, address, program, module, test, exam, UNIQUE(number))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + dbTable);
            onCreate(db);
        }
    }

}
