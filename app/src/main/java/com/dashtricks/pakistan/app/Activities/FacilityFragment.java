package com.dashtricks.pakistan.app.Activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.R;

import org.w3c.dom.Text;

import java.util.UUID;


public class FacilityFragment extends Fragment {
    public static final String EXTRA_FACILITY_ID = "facility_id";

    private ListTypeFacility fac;
    private TextView facilityNameField;
    private TextView currentCapacityField;
    private TextView requiredCapacityField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int facilityPosition = getActivity().getIntent()
                .getIntExtra(EXTRA_FACILITY_ID, 0);

        fac = ListTypeFacilityLab.get(getActivity()).getFacility(facilityPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_facility, container, false);

        // Set the facility name text
        facilityNameField = (TextView)v.findViewById(R.id.facility_name);
        facilityNameField.setText(fac.getFacilityName());

        // Set the current capacity text
        currentCapacityField = (TextView)v.findViewById(R.id.current_capacity);
        currentCapacityField.setText("Current Capacity: " + fac.getCurrentCapacity());

        // Set the required capacity text
        requiredCapacityField = (TextView)v.findViewById(R.id.required_capacity);
        requiredCapacityField.setText("Required Capacity: " + fac.getRequiredCapacity());

        return v;
    }
}
