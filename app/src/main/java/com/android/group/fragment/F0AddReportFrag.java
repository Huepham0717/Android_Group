package com.android.group.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.group.R;
import com.android.group.controller.ReportController;
import com.android.group.model.Report;
import com.android.group.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F0AddReportFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F0AddReportFrag extends Fragment {
    Spinner coughSpinner, breathSpinner, throatSpinner, pneumoniaSpinner;
    EditText tempInput, diseaseInput, symptomInput;
    Report report;
    Button btn_add_report;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F0AddReportFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F0AddReportFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static F0AddReportFrag newInstance(String param1, String param2) {
        F0AddReportFrag fragment = new F0AddReportFrag();
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

        View view = inflater.inflate(R.layout.fragment_f0_add_report, container, false);
        btn_add_report = view.findViewById(R.id.btn_add_report);

        String[] level = {"Normal", "Mild", "Medium", "High", "Extreme"};
        coughSpinner = view.findViewById(R.id.frag_f0_add_report_cough_spinner);
        breathSpinner = view.findViewById(R.id.frag_f0_add_report_breath_spinner);
        throatSpinner = view.findViewById(R.id.frag_f0_add_report_throat_spinner);
        pneumoniaSpinner = view.findViewById(R.id.frag_f0_add_report_pneumonia_spinner);
        tempInput = view.findViewById(R.id.frag_f0_add_report_temp_input);
        diseaseInput = view.findViewById(R.id.frag_f0_add_report_disease_input);
        symptomInput = view.findViewById(R.id.frag_f0_add_report_symptom_input);

        coughSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.adapter_spinner, level));
        breathSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.adapter_spinner, level));
        throatSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.adapter_spinner, level));
        pneumoniaSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.adapter_spinner, level));

        btn_add_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterApp1();
            }
        });

        return view;
    }
    public void enterApp1() {
        report = new Report();
        report.setPatientUUID(FirebaseAuth.getInstance().getCurrentUser().getUid());
        report.setTemperature(tempInput.getText().toString());
        report.setrTimestamp(Calendar.getInstance().getTime().toString());
        report.setDiseases(diseaseInput.getText().toString());
        report.setSymptoms(symptomInput.getText().toString());
        report.setCough(coughSpinner.getSelectedItem().toString());
        report.setBreath(breathSpinner.getSelectedItem().toString());
        report.setsThroat(throatSpinner.getSelectedItem().toString());
        report.setPneumonia(pneumoniaSpinner.getSelectedItem().toString());
        // Call the function addSite in SiteService to add a site
        ReportController reportController = new ReportController(getContext());
        reportController.addReport(report, new ReportController.VolleyResponseListener1() {
            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}