package nicosantomartino.a15apiclima;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nicol on 02/11/2017.
 */

public class SqLiteHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
   // private static final String TABLE_NAME = "ciudades";

    String SQL_CREATE_CITY = "CREATE TABLE " + DbConstantes.TABLE_CIUDADES + "' (`"+ DbConstantes.COLUMN_ID +"`	INTEGER PRIMARY KEY AUTOINCREMENT,`"+ DbConstantes.COLUMN_NOMBRE +"` TEXT NOT NULL)";


    public SqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       super(context, name, factory, version);
       // super(context,DbConstantes.DATABASE_NAME,null,DbConstantes.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstantes.TABLE_CIUDADES);
        db.execSQL(SQL_CREATE_CITY);
    }



}