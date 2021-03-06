package com.android.group.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.group.R;
import com.android.group.controller.MedicalRecordController;
import com.android.group.controller.ReportController;
import com.android.group.model.MedicalRecord;
import com.android.group.model.Report;

import java.util.List;

public class F0ViewReportFrag extends Fragment {
    private final Long reportId;
    private final int position;
    EditText tempView, diseaseView, symptomView,coughView,breathView,throatView,pneumoniaView;

    public F0ViewReportFrag(int position,Long reportId) {
        this.reportId = reportId;
        this.position = position;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f0_view_report, container, false);
        TextView nameTxt = view.findViewById(R.id.frag_f0_view_report_name_txt);
        TextView dateTxt = view.findViewById(R.id.frag_f0_view_report_date_txt);
        tempView = view.findViewById(R.id.frag_f0_view_report_temp_input);
        diseaseView = view.findViewById(R.id.frag_f0_view_report_disease_input);
        symptomView = view.findViewById(R.id.frag_f0_view_report_symptom_input);
        coughView = view.findViewById(R.id.frag_f0_view_report_cough_input);
        breathView = view.findViewById(R.id.frag_f0_view_report_breath_input);
        throatView = view.findViewById(R.id.frag_f0_view_report_throat_input);
        pneumoniaView = view.findViewById(R.id.frag_f0_view_report_pneumonia_input);

        nameTxt.setText("Report: " + position);
        //Get the report
        ReportController reportController = new ReportController(getContext());
        reportController.getReport(reportId, new ReportController.VolleyResponseListener(){
            @Override
            public void onError(String message) {
                Toast.makeText(getContext(),"Something wrong",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Report report) {
                dateTxt.setText("DATE AND TIME: " + report.getrTimestamp());
                tempView.setText(report.getTemperature());
                diseaseView.setText(report.getDiseases());
                symptomView.setText(report.getSymptoms());
                coughView.setText(report.getCough());
                breathView.setText(report.getBreath());
                throatView.setText(report.getsThroat());
                pneumoniaView.setText(report.getPneumonia());
            }
        });
        return view;
    }
}