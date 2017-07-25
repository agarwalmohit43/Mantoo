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

public class CustomerData {

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

    /*public long insertPartydata(ContentValues contentValues) {

        long id = sqLiteDatabaseObj.insert("parties", null, contentValues);
        return id;
    }*/

    public ArrayList<String> getPartyName() {
        ArrayList<String> alPartyName = new ArrayList<String>();

        String[] columns = {"name"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            alPartyName.add(name);
        }

        return alPartyName;
    }

    public ArrayList<String> getPartyAddress() {
        ArrayList<String> alPartyAddress = new ArrayList<String>();

        String[] columns = {"address"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String address = cursor.getString(0);
            alPartyAddress.add(address);
        }

        return alPartyAddress;
    }

    public ArrayList<Integer> getPartyBalance() {
        ArrayList<Integer> alPartyBalance = new ArrayList<>();

        String[] columns = {"dueAmount"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int balance = cursor.getInt(0);
            alPartyBalance.add(balance);
        }

        return alPartyBalance;
    }

    public ArrayList<String> getPartyContact() {
        ArrayList<String> alPartyContact = new ArrayList<>();

        String[] columns = {"phoneNumber"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String contact=cursor.getString(0);
            alPartyContact.add(contact);
        }

        return alPartyContact;
    }
    public ArrayList<CustomerInformation> getPartyList() {
        ArrayList<CustomerInformation> customerInformation = new ArrayList<>();

        String[] columns = {"name","address","dueAmount","phoneNumber"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            CustomerInformation obj=new CustomerInformation();
            obj.setCustomerAddress(cursor.getString(1));
            obj.setCustomerBalance(cursor.getDouble(2));
            obj.setCustomerContact(cursor.getString(3));
            obj.setCustomerName(cursor.getString(0));

            customerInformation.add(obj);
        }
        return customerInformation;
    }

}
