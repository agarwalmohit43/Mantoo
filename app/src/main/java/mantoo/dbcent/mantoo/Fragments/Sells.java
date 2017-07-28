package mantoo.dbcent.mantoo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.InventoryData;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class Sells extends Fragment {

    AutoCompleteTextView textView;

    InventoryData inventoryDataObj;

    ArrayList<InventoryInformation> inventoryInformationsList;

    ArrayList<String> inventoryNames;

    ///CustomDataSet customDataSetAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sells, container, false);

        textView = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextView1);

        inventoryDataObj = new InventoryData(getActivity());

        inventoryInformationsList = inventoryDataObj.getInventoryList();

        inventoryNames = new ArrayList<>();

        for(InventoryInformation obj: inventoryInformationsList){
            inventoryNames.add(obj.getInventoryName());
        }





        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Sells");

        //ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,inventoryNames);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,inventoryNames);


        textView.setAdapter(adapter);
        textView.setThreshold(1);

    }
}


