package com.dashtricks.pakistan.app.Activities;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.Model.OverallFacilitiesStatistics;
import com.dashtricks.pakistan.app.Model.OverallRefrigeratorStatistics;
import com.dashtricks.pakistan.app.R;

public class OverallStatsFacilitiesFragment extends Fragment {

    OverallFacilitiesStatistics facilityStats;
    private TextView numberOfFacilities;
    private TextView facilitiesNeedOfAttention;
    private TextView population;
    private TextView populationCovered;
    private TextView populationCoveredPercentage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facilityStats = new OverallFacilitiesStatistics();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_overall_stats_facilities, container, false);

        // TODO: Use numbers from database
        numberOfFacilities = (TextView)v.findViewById(R.id.number_of_facilities);
        numberOfFacilities.setText("583" + ""); // getFacilitiesCount();

        facilitiesNeedOfAttention = (TextView)v.findViewById(R.id.facilities_need_attention);
        facilitiesNeedOfAttention.setText(facilityStats.getFacilitiesNeedOfAttention() + "");

        population = (TextView)v.findViewById(R.id.population_facility_stats);
        population.setText(facilityStats.getPopulation());

        populationCovered = (TextView)v.findViewById(R.id.population_covered_facility_stats);
        populationCovered.setText(facilityStats.getPopulationCovered());

        populationCoveredPercentage = (TextView)v.findViewById(R.id.population_covered_percentage);
        populationCoveredPercentage.setText(facilityStats.getPopulationCoveredPercentage());

        return v;
    }
}
