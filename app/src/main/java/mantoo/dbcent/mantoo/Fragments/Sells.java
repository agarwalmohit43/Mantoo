package mantoo.dbcent.mantoo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.InventoryData;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class Sells extends Fragment implements TextWatcher {

    AutoCompleteTextView customerTextView, inventoryTextView;

    InventoryData inventoryDataObj;
    CustomerData customerDataObj;

    Map<String,CustomerInformation> customerInformationMap;
    Map<String,InventoryInformation> inventoryInformationMap;

    EditText qty,rate,discount,tax;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sells, container, false);

        customerTextView = (AutoCompleteTextView) rootView.findViewById(R.id.partyAutoCompleteSells);
        inventoryTextView = (AutoCompleteTextView) rootView.findViewById(R.id.productAutoCompleteSells);

        customerDataObj = new CustomerData(getActivity());
        customerInformationMap = new HashMap<>();

        for(CustomerInformation obj:customerDataObj.getPartyList()){
            customerInformationMap.put(obj.getCustomerName(),obj);
        }

        inventoryDataObj = new InventoryData(getActivity());
        inventoryInformationMap = new HashMap<>();

        for(InventoryInformation obj: inventoryDataObj.getInventoryList()){
            inventoryInformationMap.put(obj.getInventoryName(),obj);
        }


        qty = (EditText) rootView.findViewById(R.id.qty_sells_editext);
        rate = (EditText) rootView.findViewById(R.id.rate_sells_editext);
        discount = (EditText) rootView.findViewById(R.id.dis_sells_editext);
        tax = (EditText) rootView.findViewById(R.id.tax_sells_editext);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Sells");

        ArrayAdapter partyAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,customerInformationMap.keySet().toArray());
        ArrayAdapter productAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,inventoryInformationMap.keySet().toArray());

        customerTextView.setAdapter(partyAdapter);
        customerTextView.setThreshold(1);

        customerTextView.addTextChangedListener(this);

        inventoryTextView.setAdapter(productAdapter);
        inventoryTextView.setThreshold(1);

        inventoryTextView.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if(inventoryInformationMap.containsKey(inventoryTextView.getText().toString())){
           // Message.message(getActivity(), customerInformationMap.get(customerTextView.getText().toString()).getCustomerName());

            rate.setText(inventoryInformationMap.get(inventoryTextView.getText().toString()).getRate().toString());
            discount.setText(inventoryInformationMap.get(inventoryTextView.getText().toString()).getDiscount().toString());
            tax.setText(inventoryInformationMap.get(inventoryTextView.getText().toString()).getGstRate().toString());

        }else{
            Message.message(getActivity(),"Not Found");
        }




    }
}


