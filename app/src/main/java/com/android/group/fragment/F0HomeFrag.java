package com.android.group.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.group.R;
import com.android.group.adapter.ReportAdapter;
import com.android.group.controller.MedicalRecordController;
import com.android.group.controller.UserController;
import com.android.group.model.MedicalRecord;
import com.android.group.model.Report;
import com.android.group.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link F0HomeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class F0HomeFrag extends Fragment {
    TextView txtUserName;
    FirebaseAuth firebaseAuth;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewSiteList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public F0HomeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F0HomeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static F0HomeFrag newInstance(String param1, String param2) {
        F0HomeFrag fragment = new F0HomeFrag();
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
        View view = inflater.inflate(R.layout.fragment_f0_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        txtUserName = view.findViewById(R.id.txt_username);
        recyclerViewSiteList = view.findViewById(R.id.frag_f0_home_container);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // Get the user's detail
            UserController userController = new UserController(getContext());
            userController.getUser(FirebaseAuth.getInstance().getCurrentUser().getUid(), new UserController.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(User user) {
                    txtUserName.setText(user.getUsername());
                }
            });
            MedicalRecordController medicalRecordController = new MedicalRecordController(getContext());
            medicalRecordController.findMedicalRecordListByPatient(firebaseAuth.getCurrentUser().getUid(),new MedicalRecordController.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(MedicalRecord medicalRecord,List<Report> reportList) {
                    recyclerViewSite(medicalRecord,reportList);
                }
            });
        }

        return view;
    }
    private void recyclerViewSite(MedicalRecord medicalRecord, List<Report> reportList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        adapter2 = new ReportAdapter(medicalRecord,reportList,getContext());
        recyclerViewSiteList.setLayoutManager(linearLayoutManager);
        recyclerViewSiteList.setAdapter(adapter2);

    }
}