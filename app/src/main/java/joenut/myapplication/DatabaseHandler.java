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

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database";
    private static final String TABLE_NAME = "imgData";
    private static final String COLUMN_IMAGE = "img";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_IMAGE + " BLOB NOT NULL," + COLUMN_NAME + "TEXT NOT NULL" + ")";

    public DatabaseHandler(Context context) {
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
//        db.execSQL("DROP TABLE IF EXISTS "+ CREATE_TABLE);
//        onCreate(db);
        db.execSQL("drop table if exists imgData");
        onCreate(db);
    }

    public Boolean insertImage(String name, String img){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            FileInputStream fs = new FileInputStream(img);
            byte[] imgbyte = new byte[fs.available()];
            fs.read(imgbyte);
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_IMAGE, imgbyte);
            db.insert(TABLE_NAME, null, values);
            fs.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



//    public void insertBitmap(Bitmap bmp, String name)
//    {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
//        byte[] buffer=out.toByteArray();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.beginTransaction();
//        ContentValues values;
//
//        try
//        {
//            values = new ContentValues();
//            values.put(COLUMN_IMAGE,buffer);
//            values.put(COLUMN_NAME,name);
//
//            db.insert(TABLE_NAME, null, values);
//            db.setTransactionSuccessful();
//            Log.i("Image..","Inserted..");
//        }
//        catch (SQLiteException e)
//        {
//            e.printStackTrace();
//
//        }
//        finally
//        {
//            db.endTransaction();
//            // End the transaction.
//            db.close();
//            // Close database
//        }
//    }



//    public Bitmap getBitmap(String name)
//    {
//        Bitmap bitmap = null;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        db.beginTransaction();
//        try
//        {
//            String selectQuery = "SELECT * FROM "+ TABLE_NAME+" WHERE name = " + name;
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            int id_value = cursor.getInt(0);
//            POJO pojo = new POJO(id_value);
//            Log.i("AUTO ID..",cursor.getString(0));
//
//            if(cursor.getCount() >0)
//            {
//                while (cursor.moveToNext())
//                {
//                    byte[] blob = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
//                    bitmap= BitmapFactory.decodeByteArray(blob, 0, blob.length);
//                }
//
//            }
//            db.setTransactionSuccessful();
//        }
//        catch (SQLiteException e)
//        {
//            e.printStackTrace();
//
//        }
//        finally
//        {
//            db.endTransaction();
//            // End the transaction.
//            db.close();
//            // Close database
//        }
//        return bitmap;
//    }
//
//    public class POJO {
//        int id;
//
//        public POJO (int id1)
//        {
//            this.id = id1;
//        }
//
//        public int getId()
//        {
//            return id;
//        }
//    }

    //Test Krub

}
