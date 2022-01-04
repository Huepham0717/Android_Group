package com.android.group.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.group.R;

public class MsgGroupAdapter extends RecyclerView.Adapter<MsgGroupAdapter.MsgGroupViewHolder> {
    private final Context context;
    private final int num;

    public MsgGroupAdapter(Context context, int num) {
        this.context = context;
        this.num = num;
    }

    @NonNull
    @Override
    public MsgGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_msg_group, parent, false);
        return new MsgGroupAdapter.MsgGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgGroupViewHolder holder, int position) {
        holder.nameTxt.setText("User " + position);
        holder.lastMsgTxt.setText("This is last text no." + position);
        holder.adapter.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return num;
    }

    static class MsgGroupViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, lastMsgTxt;
        LinearLayout adapter;

        public MsgGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.adapter_msg_group_name_txt);
            lastMsgTxt = itemView.findViewById(R.id.adapter_msg_group_last_msg_txt);
            adapter = itemView.findViewById(R.id.adapter_msg_group_adapter);
        }
    }
}
