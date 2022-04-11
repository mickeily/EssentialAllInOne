package com.example.essentialallinone.Data;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.essentialallinone.Essential;

import java.util.ArrayList;
import java.util.List;

public class DataManager
{
    private  static  SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_STATUS_EXAMPLE = "statusExample";
    public static final String TABLE_ROW_STATUS_DEFINITION = "statusDefinition";
    public static final String TABLE_ROW_STATUS_MULTICHOISE = "statusMultichoise";
    public static final String TABLE_ROW_STATUS_COMPLETE = "statusComplete";
    public static final String TABLE_ROW_STATUS_READ = "statusRead";
    public static final String TABLE_ROW_STATUS_LISTEN = "statusListen";
    public static final String TABLE_ROW_STATUS_MATCH = "statusMatch";
    public static final String TABLE_ROW_STATUS_HANG = "statusHang";
    public static final String TABLE_ROW_STATUS_ACTIVE = "statusActive";
    public static final String TABLE_ROW_SCORE = "score";
    public static final String TABLE_ROW_DATE = "_date";
    public static final String TABLE_ROW_EXAMPLE = "example";
    public static final String TABLE_ROW_DEFINITION = "definition";
    public static final String TABLE_ROW_BOOK = "book";
    public static final String TABLE_ROW_UNIT = "unit";
    public static final String TABLE_ROW_WORD = "word";
    public static final String TABLE_ROW_TYPE = "type";



    public static final String DB_NAME = "AddressBookDb";
    private static int DB_VERSION= 1;
    public static final String TABLE_NAME_AND_ADDRESS = "NamesAndAddresses";


   public DataManager(Context context)
    {
        MyCustomSQLiteOpenHelper helper = new MyCustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }
    public static void insert(Activity activity)
    {
        List<Essential> lista = new ArrayList<>();
        lista = Data.leerArchivo(activity);
        String query="";
        for(Essential ess: lista)
        {
            query = "INSERT INTO " +
                    TABLE_NAME_AND_ADDRESS + " ("+
                    TABLE_ROW_ID + ", "+
                    TABLE_ROW_STATUS_EXAMPLE + ", "+
                    TABLE_ROW_STATUS_DEFINITION + ", "+
                    TABLE_ROW_STATUS_MULTICHOISE + ", "+

                    TABLE_ROW_STATUS_COMPLETE + ", "+
                    TABLE_ROW_STATUS_READ + ", "+
                    TABLE_ROW_STATUS_LISTEN + ", "+
                    TABLE_ROW_STATUS_MATCH + ", "+

                    TABLE_ROW_STATUS_HANG + ", "+
                    TABLE_ROW_STATUS_ACTIVE + ", "+
                    TABLE_ROW_SCORE + ", "+
                    TABLE_ROW_DATE + ", "+

                    TABLE_ROW_EXAMPLE + ", "+
                    TABLE_ROW_DEFINITION + ", "+
                    TABLE_ROW_BOOK + ", "+
                    TABLE_ROW_UNIT + ", "+

                    TABLE_ROW_WORD + ", "+
                    TABLE_ROW_TYPE + ") "+

                    "VALUES (" + ess.getOrder() + ", "+
                    ess.getStatusExample()+ ", "+
                    ess.getStatusDefinition()+ ", "+
                    ess.getStatusMultiChoise()+ ", "+

                    ess.getStatusComplete()+ ", "+
                    ess.getStatusRead()+ ", "+
                    ess.getStatusListen()+ ", "+
                    ess.getStatusMatch()+ ", "+

                    ess.getStatusHang()+ ", "+
                    ess.getStatusActive()+ ", "+
                    ess.getPointPrincipal()+ ", "+
                    "'" +ess.getDate()+ "'"+ ", "+

                    "'" +ess.getExample()+ "'"+ ", "+
                    "'" +ess.getMeaning()+ "'"+ ", "+
                    "'" +ess.getBook()+ "'"+ ", "+
                    "'" +ess.getUnit()+ "'"+ ", "+

                    "'" + ess.getWord()+ "'"+ ", "+
                    "'" + ess.getType()+ "'"+
                    ");";
            db.execSQL(query);

        }
        Log.i("insert()=",query);
    }
    public static Cursor search(Activity activity)
    {
        int a =0;
        try
        {
            String query = "SELECT * FROM "+TABLE_NAME_AND_ADDRESS;
            Cursor c =db.rawQuery(query,null);
            if(c.getCount()>0)
            {
                return  c;
            }
            else
            {
                insert(activity);
                return c;
            }
        }catch (Exception e)
        {
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();
        }
       return null;
    }
    public static void update(List<Essential> lista, Activity activity)
    {
        String query = "";

        try
        {
            for(Essential ess: lista)
            {
                query = "UPDATE " + TABLE_NAME_AND_ADDRESS+
                        " SET " + TABLE_ROW_STATUS_EXAMPLE + " = "+ ess.getStatusExample()+ ", "+
                        TABLE_ROW_STATUS_DEFINITION + " = "+ ess.getStatusDefinition()+ ", "+
                        TABLE_ROW_STATUS_MULTICHOISE + " = "+ ess.getStatusMultiChoise()+ ", "+

                        TABLE_ROW_STATUS_COMPLETE + " = "+ ess.getStatusComplete()+ ", "+
                        TABLE_ROW_STATUS_READ + " = "+ ess.getStatusRead()+ ", "+
                        TABLE_ROW_STATUS_LISTEN + " = "+ ess.getStatusListen()+ ", "+
                        TABLE_ROW_STATUS_MATCH + " = "+ ess.getStatusMatch()+ ", "+

                        TABLE_ROW_STATUS_HANG + " = "+ ess.getStatusHang()+ ", "+
                        TABLE_ROW_STATUS_ACTIVE + " = "+ ess.getStatusActive()+ ", "+
                        TABLE_ROW_DATE + " = "+ "'"+ess.getDate()+"'"+ ", "+
                        TABLE_ROW_SCORE + " = "+ ess.getPointPrincipal()+ " WHERE " +
                        TABLE_ROW_ID  + " = " +ess.getOrder()+ ";";


                db.execSQL(query);

            }
        }catch (Exception e)
        {
            Toast.makeText(activity, ""+e, Toast.LENGTH_SHORT).show();
        }


    }

    public class MyCustomSQLiteOpenHelper extends SQLiteOpenHelper
    {

        public MyCustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String newTableQuery = "CREATE TABLE " + TABLE_NAME_AND_ADDRESS + " (" +
                    TABLE_ROW_ID +  " INTEGER NOT NULL, " +

                    TABLE_ROW_STATUS_EXAMPLE +  " INTEGER NOT NULL, " +
                    TABLE_ROW_STATUS_DEFINITION +  " INTEGER NOT NULL, " +
                    TABLE_ROW_STATUS_MULTICHOISE  +  " INTEGER NOT NULL, " +

                    TABLE_ROW_STATUS_COMPLETE  +  " INTEGER NOT NULL, " +
                    TABLE_ROW_STATUS_READ  +  " INTEGER NOT NULL, " +
                    TABLE_ROW_STATUS_LISTEN +  " INTEGER NOT NULL, " +
                    TABLE_ROW_STATUS_MATCH +  " INTEGER NOT NULL, " +

                    TABLE_ROW_STATUS_HANG +  " INTEGER NOT NULL, " +
                    TABLE_ROW_STATUS_ACTIVE +  " INTEGER NOT NULL, " +
                    TABLE_ROW_SCORE +  " INTEGER NOT NULL, " +
                    TABLE_ROW_DATE + " TEXT NOT NULL, "+

                    TABLE_ROW_EXAMPLE + " TEXT NOT NULL, "+
                    TABLE_ROW_DEFINITION + " TEXT NOT NULL, "+
                    TABLE_ROW_BOOK + " TEXT NOT NULL, "+
                    TABLE_ROW_UNIT + " TEXT NOT NULL, "+

                    TABLE_ROW_WORD + " TEXT NOT NULL, "+
                    TABLE_ROW_TYPE + " TEXT NOT NULL); ";
            db.execSQL(newTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}
