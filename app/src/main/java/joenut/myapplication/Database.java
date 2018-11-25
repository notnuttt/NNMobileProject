package joenut.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database";
    private static final String TABLE_NAME = "person";
    private static final String COLUMN_0 = "id";
    private static final String COLUMN_1 = "name";
    private static final String COLUMN_2 = "img";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_1 + " TEXT NOT NULL, " + COLUMN_2 + " BLOB NOT NULL)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
        Log.i("Table..","Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertImage(String img, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            FileInputStream fs = new FileInputStream(img);
            byte[] imgbyte = new byte[fs.available()];
            fs.read(imgbyte);
            ContentValues values = new ContentValues();
            values.put(COLUMN_1, name);
            values.put(COLUMN_2, imgbyte);
            db.insert(TABLE_NAME, null, values);
            fs.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Person> getPersonList(){
        ArrayList<Person> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from person", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Person p  = new Person(cursor.getString(1), cursor.getBlob(2));
            list.add(p);
            cursor.moveToNext();
        }
        return list;
    }

}
