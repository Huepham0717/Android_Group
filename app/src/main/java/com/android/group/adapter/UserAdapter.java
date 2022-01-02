package com.android.group.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.group.R;
import com.android.group.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context mContext;
    private List<User> mListUser;
    public UserAdapter(Context mContext){
        this.mContext=mContext;
    }

    public void setData(List<User> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
         User user = mListUser.get(position);
         if (user == null){
             return;
         }
         holder.imgUser.setImageResource(user.getResourceID());
         holder.tvName.setText(user.getName());
         holder.tvAge.setText(user.getAge ()+"");
         holder.tvSex.setText(user.getSex());
         holder.tvVax.setText(user.getVax());
         holder.tvCovid.setText(user.getCovid());
         holder.tvStatus.setText(user.getStatus());
    }

    @Override
    public int getItemCount() {
        if (mListUser != null){
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgUser;
        private TextView tvName;
        private TextView tvAge;
        private TextView tvSex;
        private TextView tvVax;
        private TextView tvCovid;
        private TextView tvStatus;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvSex = itemView.findViewById(R.id.tv_sex);
            tvVax = itemView.findViewById(R.id.tv_vax);
            tvCovid = itemView.findViewById(R.id.tv_covid);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
