package com.dashtricks.pakistan.app.Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dashtricks.pakistan.app.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dan on 5/28/2014.
 */
public class ExpandableListAdapterPunjab extends BaseExpandableListAdapter {


    private HashMap<String, Double> headerData = new HashMap<String, Double>();

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private List<String> percentages;

    public ExpandableListAdapterPunjab(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,
                                 List<String> percentages) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.percentages = percentages;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.punjab_facility_list_child, null);
        }

        // Set the child text
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.punjab_child_row_name);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.punjab_facility_list_group, null);
        }

        // Set alternating colors
        RelativeLayout facilityRow = (RelativeLayout) convertView.findViewById(R.id.punjab_facility_row);
        if((groupPosition % 2) == 0) {
            facilityRow.setBackgroundColor(Color.parseColor("#D3EAF8"));
        }
        else{
            facilityRow.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        // Set the percentage
        TextView percentage = (TextView) convertView.findViewById(R.id.punjab_facility_row_percentage);
        percentage.setText(percentages.get(groupPosition));

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.punjab_facility_row_name);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
