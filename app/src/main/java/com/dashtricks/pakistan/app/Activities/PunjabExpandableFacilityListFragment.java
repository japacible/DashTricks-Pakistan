package com.dashtricks.pakistan.app.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.Model.PunjabFacilityListLab;
import com.dashtricks.pakistan.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PunjabExpandableFacilityListFragment extends Fragment {
    private HashMap<String, List<ListTypeFacility>> DistrictToFacilities;
    private static final String TAG = "FacilityListFragment";

    ExpandableListAdapterPunjab listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DistrictToFacilities = PunjabFacilityListLab.get(getActivity()).getFacilitiesList();
        prepareListData();

        // get the listview
        expListView = (ExpandableListView) getActivity().findViewById(R.id.punjab_expandable_list);
        expListView.setGroupIndicator(null);
        listAdapter = new ExpandableListAdapterPunjab(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header and child data

        for (ListTypeFacility ltf: DistrictToFacilities.get("Attock")) {
            listDataHeader.add(ltf.getFacilityName());

            // List of strings for the facility details
            List<String> facilityDetails = new ArrayList<String>();
            facilityDetails.add("Facility ID: " + ltf.getFacilityID());
            facilityDetails.add("Current Refrigerator Capacity: " + ltf.getCurrentCapacity());
            facilityDetails.add("Required Refrigerator Capacity: " + ltf.getRequiredCapacity());
            facilityDetails.add("Population: " + ltf.getPopulation());

            listDataChild.put(ltf.getFacilityName(), facilityDetails);

        }
    }
}
