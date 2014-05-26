package com.dashtricks.pakistan.app.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.R;

import java.util.ArrayList;

public class FacilityListFragment extends ListFragment {
    private ArrayList<ListTypeFacility> mFacilities;

    private static final String TAG = "FacilityListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFacilities = ListTypeFacilityLab.get(getActivity()).getFacilitiesList();

        FacilityListAdapter adapter = new FacilityListAdapter(mFacilities);
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

    private class FacilityListAdapter extends ArrayAdapter<ListTypeFacility> {

        public FacilityListAdapter(ArrayList<ListTypeFacility> facilities) {
            super(getActivity(), 0, facilities);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_facility_row, null);
            }

            ListTypeFacility ltf = getItem(position);
            RelativeLayout facilityRow = (RelativeLayout)convertView.findViewById(R.id.facility_row);

            if((position % 2) == 0) {
                facilityRow.setBackgroundColor(Color.parseColor("#D3EAF8"));
            }
            else{
                facilityRow.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

            // Set the facility name
            TextView facilityText = (TextView)convertView.findViewById(R.id.facility_row_name);
            facilityText.setText(ltf.getFacilityName());

            // Set the facility percentage coverage
            TextView facilityPercentage = (TextView)convertView.findViewById(
                    R.id.facility_row_percentage);
            facilityPercentage.setText(String.format("%" + 2 + "." + 2 + "f",
                    ltf.getPercentageCapacity()) + "%");

            return convertView;

        }
    }
}
