package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import mantoo.dbcent.mantoo.Extra.Message;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class SchemaDefinition extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME="mantoo";
    private static final int DATABASE_VERSION=7;

   // private static final String CREATE_TABLE="CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
    //private static final String DROP_TABLE="DROP TABLE  IF EXISTS parties";

//    private static final String CREATE_TABLE_PARTIES = "CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
//    private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE inventory (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT,name TEXT NOT NULL UNIQUE,firmId TEXT, mantooProductid TEXT, tax DECIMAL(10,5) NOT NULL, gstRate DECIMAL(10,5) NOT NULL,rate DECIMAL(10,5) NOT NULL,mrp DECIMAL(10,5) NOT NULL, purcahsePrice DECIMAL(10,5) NOT NULL,createdAt INTEGER,updatedAt INTEGER);";
//    private static final String CREATE_TABLE_USER = "CREATE TABLE user (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL,firmId TEXT, createdAt INTEGER,updatedAt INTEGER)";
    private static final String CREATE_TABLE_FIRM = "CREATE TABLE firm (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, mantooId TEXT, userId TEXT NOT NULL, createdAt INTEGER,updatedAt INTEGER, FOREIGN KEY (userId) REFERENCES user(id))";


//    private static final String DROP_TABLE_PARTIES = "DROP TABLE  IF EXISTS parties";
//    private static final String DROP_TABLE_INVENTORY = "DROP TABLE  IF EXISTS inventory";
//    private static final String DROP_TABLE_USER = "DROP TABLE  IF EXISTS user";
    private static final String DROP_TABLE_FIRM = "DROP TABLE  IF EXISTS firm";


     public SchemaDefinition(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        //Toast.makeText(context,"Constructor Called of insertdata",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Message.message(context,"onCreate Called");

        try{

              sqLiteDatabase.execSQL(CREATE_TABLE_FIRM);
              Message.message(context,"Table Created FIRM");

        }catch (Exception e){
            //e.printStackTrace();
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Message.message(context,"onUpgrade Called");
        try{
             sqLiteDatabase.execSQL(DROP_TABLE_FIRM);

             Message.message(context," Table Droped \n onCreate Will be called Now");
             onCreate(sqLiteDatabase);

        }catch (Exception e){
            Message.message(context,""+e);
        }
    }
}
