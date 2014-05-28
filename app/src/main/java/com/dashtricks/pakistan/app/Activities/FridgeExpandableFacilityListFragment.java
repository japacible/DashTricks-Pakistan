package com.dashtricks.pakistan.app.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.dashtricks.pakistan.app.Model.ExpandableListAdapterFridge;
import com.dashtricks.pakistan.app.Model.ExpandableListAdapterPunjab;
import com.dashtricks.pakistan.app.Model.FridgeAgeData;
import com.dashtricks.pakistan.app.Model.FridgeListLab;
import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.PunjabFacilityListLab;
import com.dashtricks.pakistan.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FridgeExpandableFacilityListFragment extends Fragment {
    private List<FridgeAgeData> fridgeData;
    private static final String TAG = "FacilityListFragment";

    ExpandableListAdapterFridge listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fridgeData = FridgeListLab.get(getActivity()).getFridgeData();
        prepareListData();

        // get the listview
        expListView = (ExpandableListView) getActivity().findViewById(R.id.fridge_expandable_list);
        expListView.setGroupIndicator(null);
        listAdapter = new ExpandableListAdapterFridge(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header and child data

        for (FridgeAgeData fad: fridgeData) {
            listDataHeader.add(fad.getRefrigeratorModel() + " / " + fad.getFacilityName());

            // List of strings for the facility details
            List<String> fridgeDetails = new ArrayList<String>();
            fridgeDetails.add("Age: " + fad.getAge() + " Years");
            fridgeDetails.add("Year Manufactured: " + fad.getYearMade());
            fridgeDetails.add("Province: " + fad.getProvince());

            listDataChild.put(fad.getRefrigeratorModel() + " / " + fad.getFacilityName(), fridgeDetails);

        }
    }
}
