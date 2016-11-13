package com.example.dell.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.slidingmenu_tabhostviewpager.R;

/**
 * Created by Dell on 10/16/2016.
 */

public class MyAbout extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_about,container,false);
        this.getActivity().setTitle("SATVIK");
        return view;
    }
}
