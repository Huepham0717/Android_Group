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

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private final Context context;
    private int num;

    public ReportAdapter(Context context, int num) {
        this.context = context;
        this.num = num;
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
            ((F0HomeActivity) context).switchToViewReportFrag(nameStr);
        });
    }

    @Override
    public int getItemCount() {
        return num;
    }

    static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageButton viewBtn;
        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_appointment_start_txt);
            viewBtn = itemView.findViewById(R.id.adapter_report_view_btn);
        }
    }
}
