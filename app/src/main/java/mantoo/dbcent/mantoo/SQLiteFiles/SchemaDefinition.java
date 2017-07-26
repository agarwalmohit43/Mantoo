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
    private static final int DATABASE_VERSION=2;

   // private static final String CREATE_TABLE="CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
    //private static final String DROP_TABLE="DROP TABLE  IF EXISTS parties";

    private static final String CREATE_TABLE="CREATE TABLE inventory (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT,name TEXT NOT NULL UNIQUE,firmId TEXT, mantooProductid TEXT, tax DECIMAL(10,5) NOT NULL, gstRate DECIMAL(10,5) NOT NULL,rate DECIMAL(10,5) NOT NULL,mrp DECIMAL(10,5) NOT NULL, purcahsePrice DECIMAL(10,5) NOT NULL,createdAt INTEGER,updatedAt INTEGER);";
    private static final String DROP_TABLE="DROP TABLE  IF EXISTS inventory";


     public SchemaDefinition(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        Toast.makeText(context,"Constructor Called of insertdata",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Message.message(context,"onCreate Called");
        }catch (Exception e){
            //e.printStackTrace();
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Message.message(context,"onUpgrade Called");
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Message.message(context,""+e);
        }
    }
}
