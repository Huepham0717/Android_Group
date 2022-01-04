package com.android.group.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.group.R;
import com.android.group.adapter.AppointmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F0AllAppointmentFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F0AllAppointmentFrag extends Fragment {
    private Spinner statusSpinner, sortSpinner;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F0AllAppointmentFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F0AllAppointmentFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static F0AllAppointmentFrag newInstance(String param1, String param2) {
        F0AllAppointmentFrag fragment = new F0AllAppointmentFrag();
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
        View view = inflater.inflate(R.layout.fragment_f0_all_appointment, container, false);
        RecyclerView appointmentContainer = view.findViewById(R.id.frag_f0_all_appointment_container);
        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(getContext(), 10);
        appointmentContainer.setLayoutManager(new GridLayoutManager(getContext(),1));
        appointmentContainer.setAdapter(appointmentAdapter);

        statusSpinner = view.findViewById(R.id.frag_f0_all_appointment_status_spinner);
        sortSpinner = view.findViewById(R.id.frag_f0_all_appointment_sort_spinner);

        statusSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.adapter_spinner,
                new String[]{"Unaccepted", "Pending", "Finished"}));
        sortSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.adapter_spinner,
                new String[]{"Date", "Doctor's name"}));

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
}