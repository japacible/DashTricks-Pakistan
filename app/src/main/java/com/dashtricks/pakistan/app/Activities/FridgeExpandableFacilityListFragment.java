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
import java.util.Map;

public class FridgeExpandableFacilityListFragment extends Fragment {

    // Refrigerator Data
    String yearIntervalClicked;
    // Map from Year interval to refrigerators
    private Map<String, FridgeAgeData> yearIntervalToRefrigerators;

    // Temporary Refrigerator data
    // TODO Get rid of this data once we get Dov's actual data
    private List<FridgeAgeData> fridgeData;

    ExpandableListAdapterFridge listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO replace this with a call to Dov's data
        fridgeData = FridgeListLab.get(getActivity()).FridgeListSetup();
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

    public void setYearInterval(String yi) {

        this.yearIntervalClicked = yi;
        fridgeData = ExpandableListAdapterFridge.getFridgeData(yearIntervalClicked);
    }
}
