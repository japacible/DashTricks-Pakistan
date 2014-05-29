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
    private static final String TAG = "FacilityListFragment";

    ExpandableListAdapterPunjab listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String wvClicked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Grab the url passed in from the webview
        if(savedInstanceState != null) {
            wvClicked = getArguments().getString("clicked");
            Log.d("1", "2");
        }
        
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

        int i = 0;
        for (ListTypeFacility ltf: DistrictToFacilities.get("Attock")) {
            String facilityName;
            boolean test = false;
            if(wvClicked != null && wvClicked.equals("Okay")) {
                facilityName = ltf.getFacilityName();
                Log.d("3", "4");
            }
            else {
                facilityName = ListTypeFacility.getRandomFacilityNameOther(i);
                Log.d("5", "6");
            }

            // Insert the header facility name
            listDataHeader.add(facilityName);

            // List of strings for the facility details
            List<String> facilityDetails = new ArrayList<String>();
            facilityDetails.add("Facility ID: " + ltf.getFacilityID());
            facilityDetails.add("Current Refrigerator Capacity: " + ltf.getCurrentCapacity());
            facilityDetails.add("Required Refrigerator Capacity: " + ltf.getRequiredCapacity());
            facilityDetails.add("Population: " + ltf.getPopulation());

            listDataChild.put(facilityName, facilityDetails);

            i++;

        }
    }
}
