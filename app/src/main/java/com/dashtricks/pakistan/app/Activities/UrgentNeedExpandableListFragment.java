package com.dashtricks.pakistan.app.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.dashtricks.pakistan.app.Model.ExpandableListAdapterPunjab;
import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.PunjabFacilityListLab;
import com.dashtricks.pakistan.app.Model.UrgentNeedListLab;
import com.dashtricks.pakistan.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UrgentNeedExpandableListFragment extends Fragment {
    // OK and NEED ATTENTION Facility Lists
    // TODO replace the list types with Dov's Facility objects
    private List<ListTypeFacility> OkayToFacilities;
    private List<ListTypeFacility> UrgentNeedToFacilities;

    //private static final String TAG = "FacilityListFragment";
    private String barState = "Okay";

    ExpandableListAdapterPunjab listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO replace these calls with Dov's database calls
        OkayToFacilities = UrgentNeedListLab.get(getActivity()).getOkayFacilitiesList();
        UrgentNeedToFacilities = UrgentNeedListLab.get(getActivity()).getUrgentFacilitiesList();

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

        int i = 0;
        for (ListTypeFacility ltf: getListOfFacilities()) {
            listDataHeader.add(ltf.getFacilityName());

            // List of strings for the facility details
            List<String> facilityDetails = new ArrayList<String>();
            facilityDetails.add("Facility ID: " + ltf.getFacilityID());
            facilityDetails.add("Current Refrigerator Capacity: " + ltf.getCurrentCapacity());
            facilityDetails.add("Required Refrigerator Capacity: " + ltf.getRequiredCapacity());
            facilityDetails.add("Population: " + ltf.getPopulation());

            listDataChild.put(ltf.getFacilityName(), facilityDetails);
            i++;
        }
    }

    private List<ListTypeFacility> getListOfFacilities() {
        if(barState.equals("Okay")) {
            return OkayToFacilities;
        }

        return UrgentNeedToFacilities;
    }

    public void setState(String barName) {
        barState = barName;
    }
}
