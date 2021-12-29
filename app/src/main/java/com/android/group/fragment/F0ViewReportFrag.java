package com.android.group.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.group.R;

public class F0ViewReportFrag extends Fragment {
    private final String name;

    public F0ViewReportFrag(String name) {
        this.name = name;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f0_view_report, container, false);
        TextView nameTxt = view.findViewById(R.id.frag_f0_view_report_name_txt);
        nameTxt.setText(name);
        return view;
    }
}