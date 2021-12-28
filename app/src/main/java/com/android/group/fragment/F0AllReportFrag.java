package com.android.group.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.group.R;
import com.android.group.adapter.ReportAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F0AllReportFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F0AllReportFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F0AllReportFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F0AllReportFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static F0AllReportFrag newInstance(String param1, String param2) {
        F0AllReportFrag fragment = new F0AllReportFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f0_report, container, false);
        RecyclerView reportContainer = view.findViewById(R.id.frag_f0_report_container);
        ReportAdapter reportAdapter = new ReportAdapter(getContext(), 10);
        reportContainer.setLayoutManager(new GridLayoutManager(getContext(),1));
        reportContainer.setAdapter(reportAdapter);
        return view;
    }
}