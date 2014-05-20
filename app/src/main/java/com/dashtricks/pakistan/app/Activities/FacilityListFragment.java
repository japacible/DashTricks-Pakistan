package com.dashtricks.pakistan.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.R;

import com.dashtricks.pakistan.app.Activities.dummy.DummyContent;

import java.util.ArrayList;

public class FacilityListFragment extends ListFragment {
    private ArrayList<ListTypeFacility> mFacilities;

    private static final String TAG = "FacilityListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFacilities = ListTypeFacilityLab.get(getActivity()).getFacilitiesList();

        ArrayAdapter<ListTypeFacility> adapter = new ArrayAdapter<ListTypeFacility>(getActivity(),
                android.R.layout.simple_list_item_1,
                mFacilities);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListTypeFacility ltf = (ListTypeFacility)(getListAdapter()).getItem(position);

        // Start FacilityActivity
        Intent i = new Intent(getActivity(), FacilityActivity.class);
        i.putExtra(FacilityFragment.EXTRA_FACILITY_ID, ltf.getCapacitySortPosition());
        startActivity(i);
    }
}
