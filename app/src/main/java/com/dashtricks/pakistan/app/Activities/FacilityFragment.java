package com.dashtricks.pakistan.app.Activities;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.R;

public class FacilityFragment extends Fragment {
    public static final String EXTRA_FACILITY_ID = "facility_id";

    private ListTypeFacility fac;
    private TextView facilityNameField;
    private TextView facilityIDField;
    private TextView currentCapacityField;
    private TextView requiredCapacityField;
    private TextView populationField;
    private TextView powerSource1Field;
    private TextView powerSource2Field;
    private TextView powerSource3Field;

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

        // Set the facility ID text
        facilityIDField = (TextView)v.findViewById(R.id.facility_id);
        facilityIDField.setText(fac.getFacilityID());

        // Set the current capacity text
        currentCapacityField = (TextView)v.findViewById(R.id.current_capacity);
        currentCapacityField.setText("Current Capacity: " + fac.getCurrentCapacity());

        // Set the required capacity text
        requiredCapacityField = (TextView)v.findViewById(R.id.required_capacity);
        requiredCapacityField.setText("Required Capacity: " + fac.getRequiredCapacity());

        // Set the population text
        populationField = (TextView)v.findViewById(R.id.population);
        populationField.setText("" + fac.getPopulation());

        // Set power source #1
        powerSource1Field = (TextView)v.findViewById(R.id.power_source_1);
        powerSource1Field.setText(fac.getPowerSource1());

        // Set power source #2
        powerSource1Field = (TextView)v.findViewById(R.id.power_source_2);
        powerSource1Field.setText(fac.getPowerSource2());

        // Set power source #3
        powerSource1Field = (TextView)v.findViewById(R.id.power_source_3);
        powerSource1Field.setText(fac.getPowerSource3());

        return v;
    }
}
