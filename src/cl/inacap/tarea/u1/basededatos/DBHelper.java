package cl.inacap.tarea.u1.basededatos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
 
public class DBHelper extends SQLiteOpenHelper {
 
   private static int DATABASE_VERSION = 1;
   private static String DATABASE_NOMBRE = "TareaDb" ;
  // private static CursorFactory factory = null;
 
   public DBHelper(Context context)
   {
      super(context, DATABASE_NOMBRE,null,DATABASE_VERSION);
   }
 
   @Override
   public void onCreate(SQLiteDatabase db)
   {
	   
	   db.execSQL(DBmanager.CREATE_TABLE1);
	   db.execSQL(DBmanager.CREATE_TABLE2);
	   db.execSQL(DBmanager.CREATE_TABLE3);
	   db.execSQL(DBmanager.CREATE_TABLE4);
 
   }
 
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   {
	   db.execSQL("DROP TABLE IF EXISTS" + DBmanager.CREATE_TABLE1);
	   db.execSQL("DROP TABLE IF EXISTS" + DBmanager.CREATE_TABLE2);
	   db.execSQL("DROP TABLE IF EXISTS" + DBmanager.CREATE_TABLE3);
	   db.execSQL("DROP TABLE IF EXISTS" + DBmanager.CREATE_TABLE4);
	   onCreate(db);

 
   }
}