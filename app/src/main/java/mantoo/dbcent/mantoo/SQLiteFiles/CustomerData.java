package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class CustomerData implements CustomerGet,CustomerSet{

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public CustomerData(Context context) {
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    public void addPartiesvalue() {
        sqLiteDatabaseObj.beginTransaction();

        try {
            String name = "Customer number -> ";
            for (int i = 6; i <= 2000; i++) {

                UUID partyId = UUID.randomUUID();
                UUID mantooId = UUID.randomUUID();
                long millisecond = System.currentTimeMillis();

                ContentValues contentValues = new ContentValues();

                contentValues.put("id", partyId.toString());
                contentValues.put("mantooId", mantooId.toString());
                contentValues.put("name", name + i);
                contentValues.put("address", "Pune");
                contentValues.put("phoneNumber", "8981871984");
                contentValues.put("dueAmount", 20500);
                contentValues.put("createdAt", millisecond);
                contentValues.put("updatedAt", millisecond);

                Log.d("Mohit", name + i);
                sqLiteDatabaseObj.insert("parties", null, contentValues);
            }
            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Successfull");
        } catch (Exception e) {
            Message.message(context, "Un-Successfull");
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }

    //get All columns equivalent to columns string
    public ArrayList<CustomerInformation> getPartyList() {
        ArrayList<CustomerInformation> customerInformation = new ArrayList<>();

        String[] columns = {"id","mantooId","name","address","dueAmount","phoneNumber"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            CustomerInformation obj=new CustomerInformation();
            obj.setCustomerId(cursor.getString(0));
            obj.setCustomerMantooId(cursor.getString(1));
            obj.setCustomerName(cursor.getString(2));
            obj.setCustomerAddress(cursor.getString(3));
            obj.setCustomerBalance(cursor.getDouble(4));
            obj.setCustomerContact(cursor.getString(5));

            customerInformation.add(obj);
        }
        return customerInformation;
    }

}
