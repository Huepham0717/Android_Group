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
 * Use the {@link F0AllMsgFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F0AllMsgFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F0AllMsgFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F0AllMsgFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static F0AllMsgFrag newInstance(String param1, String param2) {
        F0AllMsgFrag fragment = new F0AllMsgFrag();
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
        View view = inflater.inflate(R.layout.fragment_f0_all_msg, container, false);
        RecyclerView msgContainer = view.findViewById(R.id.frag_f0_all_msg_container);
        ReportAdapter msgAdapter = new ReportAdapter(getContext(), 5);
        msgContainer.setLayoutManager(new GridLayoutManager(getContext(),1));
        msgContainer.setAdapter(msgAdapter);
        return view;
    }
}