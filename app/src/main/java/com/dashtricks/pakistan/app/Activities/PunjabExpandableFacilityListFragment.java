package com.dashtricks.pakistan.app.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.dashtricks.pakistan.app.Model.ExpandableListAdapterPunjab;
import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.PunjabFacilityListLab;
import com.dashtricks.pakistan.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PunjabExpandableFacilityListFragment extends Fragment {
    private HashMap<String, List<ListTypeFacility>> DistrictToFacilities;
    private String districtClicked = "Attock";

    // Expandable list view data
    ExpandableListAdapterPunjab listAdapter;
    ExpandableListView expListView;
    private List<ListTypeFacility> temp;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private List<String> percentages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO Replace this with a call to Dov's data
        DistrictToFacilities = PunjabFacilityListLab.get(getActivity()).getFacilitiesList();
        prepareListData();

        // get the listview
        expListView = (ExpandableListView) getActivity().findViewById(R.id.punjab_expandable_list);
        expListView.setGroupIndicator(null);
        listAdapter = new ExpandableListAdapterPunjab(getActivity(), listDataHeader, listDataChild, percentages);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        percentages = new ArrayList<String>();

        for (ListTypeFacility ltf: temp) {

            // Set the facility name
            listDataHeader.add(ltf.getFacilityName());

            // Set te capacity percentage
            percentages.add(ltf.getPercentageCapacity() + "%");

            // List of strings for the facility details
            List<String> facilityDetails = new ArrayList<String>();
            facilityDetails.add("Facility ID: " + ltf.getFacilityID());
            facilityDetails.add("Current Refrigerator Capacity: " + ltf.getCurrentCapacity());
            facilityDetails.add("Required Refrigerator Capacity: " + ltf.getRequiredCapacity());
            facilityDetails.add("Population: " + ltf.getPopulation());

            // Store child data
            listDataChild.put(ltf.getFacilityName(), facilityDetails);
        }
    }

    public void setDistrict(String districtName) {

        this.districtClicked = districtName;
        temp = PunjabFacilityListLab.getPunjabFacilitiesList();
    }
}
