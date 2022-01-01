package com.android.group;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.group.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class DoctorHomeActivity extends AppCompatActivity {

    private RecyclerView rcvUser;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.DoctorHomeActivity);

        rcvUser = findViewById(R.id.rcv_user);
        userAdapter = new UserAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvUser.setLayoutManager(linearLayoutManager);

        userAdapter.setData(getListUser());
        rcvUser.setAdapter(userAdapter);
    }
    private List<User> getListUser(){
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.img1, "User Name 1",20, "Male", "1 dose", "Not infected with Covid","alive"));
        list.add(new User(R.drawable.img2, "User Name 2",40, "Female", "3 dose", "Infected with Covid","critical"));
        list.add(new User(R.drawable.img3, "User Name 3",30, "Male", "1 dose", "Not infected with Covid","alive"));
        list.add(new User(R.drawable.img4, "User Name 4",70, "Female", "2 doses", "Infected with Covid","deceased"));

        list.add(new User(R.drawable.img1, "User Name 1",20, "Male", "1 dose", "Not infected with Covid","alive"));
        list.add(new User(R.drawable.img2, "User Name 2",40, "Female", "3 dose", "Infected with Covid","critical"));
        list.add(new User(R.drawable.img3, "User Name 3",30, "Male", "1 dose", "Not infected with Covid","alive"));
        list.add(new User(R.drawable.img4, "User Name 4",70, "Female", "2 doses", "Infected with Covid","deceased"));

        list.add(new User(R.drawable.img1, "User Name 1",20, "Male", "1 dose", "Not infected with Covid","alive"));
        list.add(new User(R.drawable.img2, "User Name 2",40, "Female", "3 dose", "Infected with Covid","critical"));
        list.add(new User(R.drawable.img3, "User Name 3",30, "Male", "1 dose", "Not infected with Covid","alive"));
        list.add(new User(R.drawable.img4, "User Name 4",70, "Female", "2 doses", "Infected with Covid","deceased"));
        return list;
    }
}