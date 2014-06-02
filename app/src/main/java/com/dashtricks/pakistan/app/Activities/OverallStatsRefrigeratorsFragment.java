package com.dashtricks.pakistan.app.Activities;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dashtricks.pakistan.app.Model.ListTypeFacility;
import com.dashtricks.pakistan.app.Model.ListTypeFacilityLab;
import com.dashtricks.pakistan.app.Model.OverallRefrigeratorStatistics;
import com.dashtricks.pakistan.app.R;

public class OverallStatsRefrigeratorsFragment extends Fragment {

    private OverallRefrigeratorStatistics fridgeStats;
    private TextView fridgeCount;
    private TextView fridgeWorking;
    private TextView fridgeOutOfCommission;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fridgeStats = new OverallRefrigeratorStatistics();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_overall_stats_refrigerators, container, false);

        fridgeCount = (TextView)v.findViewById(R.id.numberOfRefrigerators);
        fridgeCount.setText(fridgeStats.getNumberOfRefrigerators() + "");

        fridgeWorking = (TextView) v.findViewById(R.id.number_working_refrigerators);
        fridgeWorking.setText(fridgeStats.getWorkingRefrigerators() + "");

        fridgeOutOfCommission = (TextView) v.findViewById(R.id.number_refrigerators_out_commission);
        fridgeOutOfCommission.setText(fridgeStats.getRefrigeratorsOutOfCommission() + "");

        return v;
    }
}
