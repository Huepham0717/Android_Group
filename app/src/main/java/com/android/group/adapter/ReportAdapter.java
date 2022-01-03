package com.android.group.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.group.F0HomeActivity;
import com.android.group.R;
import com.android.group.model.MedicalRecord;
import com.android.group.model.Report;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private final Context context;
    List<Report> reportList;
    String UID;
    MedicalRecord medicalRecord;

    public ReportAdapter(MedicalRecord medicalRecord,List<Report> reportList, Context context) {
        this.medicalRecord = medicalRecord;
        this.reportList = reportList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportViewHolder holder, int position) {
        String nameStr = "Health report " + position;
        holder.name.setText(nameStr);
        holder.viewBtn.setOnClickListener(v -> {
            ((F0HomeActivity) context).switchToViewReportFrag(position, reportList.get(position).getReportId());
        });
        holder.time.setText(reportList.get(position).getrTimestamp());
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView name,time;
        ImageButton viewBtn;
        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_report_datetime_txt);
            viewBtn = itemView.findViewById(R.id.adapter_report_view_btn);
            time= itemView.findViewById(R.id.adapter_report_time_txt);
        }
    }
}