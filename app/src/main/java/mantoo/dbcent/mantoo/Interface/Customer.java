package mantoo.dbcent.mantoo.Interface;

import android.content.ContentValues;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Information.CustomerInformation;

/**
 * Created by dbcent91 on 25/7/17.
 */

public interface Customer {

    //Create
    public void addPartiesvalue();


    //Read
    public ArrayList<CustomerInformation> getPartyList();


    //Update
    public void updateParty(ContentValues contentValues, String partyId);

    //Delete
}