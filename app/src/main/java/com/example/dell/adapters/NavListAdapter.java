package com.example.dell.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.models.NavItem;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Dell on 10/16/2016.
 */

public class NavListAdapter extends ArrayAdapter<NavItem> {
    Context context;
    int resLayout;
    List <NavItem> listNavItems;
    public NavListAdapter(Context context,int resLayout,List <NavItem> listNavItems) {
        super(context, resLayout, listNavItems);
        this.context=context;
        this.resLayout=resLayout;
        this.listNavItems=listNavItems;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context,resLayout,null);
        TextView tvTitle=(TextView)v.findViewById(R.id.title);
       // TextView tvSubtitle=(TextView)v.findViewById(R.id.subtitle);
        ImageView tvIcon=(ImageView)v.findViewById(R.id.nav_icon);

        NavItem navItem=listNavItems.get(position);
        tvTitle.setText(navItem.getTitle());
     //   tvSubtitle.setText(navItem.getSubTitle());
        tvIcon.setImageResource(navItem.getResIcon());


        return v;
    }
}
