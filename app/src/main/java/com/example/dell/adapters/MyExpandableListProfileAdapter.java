package com.example.dell.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.dell.models.ProfileItems;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.HashMap;
import java.util.List;

public class MyExpandableListProfileAdapter extends BaseExpandableListAdapter{
    private List<String> header_titles;
    private HashMap<String,ProfileItems> child_titles;
    private Context context;

    public MyExpandableListProfileAdapter(Context context, List<String> header_titles, HashMap<String, ProfileItems> child_titles) {
        this.context=context;
        this.header_titles = (List<String>) header_titles;
        this.child_titles = (HashMap<String, ProfileItems>) child_titles;
    }

    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child_titles.get(header_titles.get(groupPosition)).getItem().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get(header_titles.get(groupPosition)).getItem().get(childPosition);
    }

    public Object getChilddata(int groupPosition, int childPosition) {
        return child_titles.get(header_titles.get(groupPosition)).getPrice().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
     String title=(String)this.getGroup(groupPosition);
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.profiletitle_layout,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.heading_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title=(String)this.getChild(groupPosition,childPosition);
        String price=(String)this.getChilddata(groupPosition,childPosition);

        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.profiledata_layout,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.subheading_caption);
        TextView textView2=(TextView)convertView.findViewById(R.id.subheading_output);

        // / textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);
        textView2.setText(price);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
