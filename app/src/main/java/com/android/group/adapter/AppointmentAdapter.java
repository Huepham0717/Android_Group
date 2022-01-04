package com.android.group.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.group.F0HomeActivity;
import com.android.group.R;
import com.android.group.model.Appointment;

import java.util.Locale;
import java.util.Random;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private final Context context;
    private final int num;

    public AppointmentAdapter(Context context, int num) {
        this.context = context;
        this.num = num;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        // TODO remove this when you add actual data
        String dateStr = getRandomDate();
        String startStr = "08:30";
        String endStr = "10:30";
        Random random = new Random();
        int[] randomImg = new int[]{android.R.drawable.presence_online,
                android.R.drawable.presence_away, android.R.drawable.presence_offline};
        holder.indicatorImg.setImageResource(randomImg[random.nextInt(3)]);

        /***
         * Logic:
         * start time not empty, end time empty -> doctor already accepted => choose orange
         * start time empty -> doctor has not accept => choose gray
         * end time not empty -> appointment ended => choose green
         */
        holder.dateTxt.setText("Date: " + dateStr);
        holder.startTxt.setText("Start: " + startStr);
        holder.endTxt.setText("End: " + endStr);
        holder.viewBtn.setOnClickListener(v -> {
            ((F0HomeActivity) context).switchToViewAppointmentFrag(0.0, 0.0);
        });
    }

    @Override
    public int getItemCount() {
        return num;
    }

    // TODO remove this when you add actual data
    public String getRandomDate() {
        Random random = new Random();
        return String.format(Locale.US, "%02d", random.nextInt(28) + 1) + "-" +
                String.format(Locale.US, "%02d", random.nextInt(12) + 1) + "-2021";
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView doctorTxt, dateTxt, startTxt, endTxt;
        ImageView indicatorImg;
        FrameLayout viewBtn;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorTxt = itemView.findViewById(R.id.adapter_appointment_doctor_txt);
            dateTxt = itemView.findViewById(R.id.adapter_appointment_date_txt);
            startTxt = itemView.findViewById(R.id.adapter_appointment_start_txt);
            endTxt = itemView.findViewById(R.id.adapter_appointment_end_txt);
            indicatorImg = itemView.findViewById(R.id.adapter_appointment_indicator_img);
            viewBtn = itemView.findViewById(R.id.adapter_appointment_view_btn);
        }
    }
}
