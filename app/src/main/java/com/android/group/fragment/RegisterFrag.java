package com.android.group.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.group.F0HomeActivity;
import com.android.group.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFrag extends Fragment {
    EditText nameInput, usernameInput, passwordInput, addressInput, dobInput, phoneInput,
            idInput, emergencyInput;
    RadioButton maleRadio, femaleRadio;
    TextView errorTxt;
    boolean isMale = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFrag newInstance(String param1, String param2) {
        RegisterFrag fragment = new RegisterFrag();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        maleRadio = view.findViewById(R.id.frag_register_male_radio);
        femaleRadio = view.findViewById(R.id.frag_register_female_radio);

        nameInput = view.findViewById(R.id.frag_register_name_input);
        usernameInput = view.findViewById(R.id.frag_register_username_input);
        passwordInput = view.findViewById(R.id.frag_register_password_input);
        addressInput = view.findViewById(R.id.frag_f0_add_report_disease_input);
        dobInput = view.findViewById(R.id.frag_register_dob_input);
        phoneInput = view.findViewById(R.id.frag_register_phone_input);
        idInput = view.findViewById(R.id.frag_register_passport_input);
        emergencyInput = view.findViewById(R.id.frag_register_emergency_input);

        errorTxt = view.findViewById(R.id.frag_register_error_txt);

        maleRadio.setOnClickListener(v -> isMale = true);
        maleRadio.setOnClickListener(v -> isMale = false);
        dobInput.setOnClickListener(v -> setDatePickerDialog((EditText) v));

        return view;
    }

    public void enterApp() {
        // Check before entering the home screen
        String nameStr = nameInput.getText().toString();
        String usernameStr = usernameInput.getText().toString();
        String passwordStr = passwordInput.getText().toString();
        String addressStr = addressInput.getText().toString();
        String dobStr = dobInput.getText().toString();
        String phoneStr = phoneInput.getText().toString();
        String emergencyStr = emergencyInput.getText().toString();
        String idStr = idInput.getText().toString();
        if (nameStr.equals("") || usernameStr.equals("") || passwordStr.equals("") ||
                addressStr.equals("") || dobStr.equals("") || phoneStr.equals("") ||
                emergencyStr.equals("") || idStr.equals("")) {
            errorTxt.setVisibility(View.VISIBLE);
        } else {
            errorTxt.setVisibility(View.GONE);
            // TODO move to HomeActivity, add to database and call PostRequest
            Intent intent = new Intent(getContext(), F0HomeActivity.class);
            startActivity(intent);
        }
    }

    // for the dob input
    private void setDatePickerDialog(EditText dateInput) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog dialog = new DatePickerDialog(
                requireActivity(),
                android.R.style.Theme_Holo_Dialog_MinWidth,
                (datePicker, year1, month1, day1) -> {
                    dateInput.setText(String.format(Locale.US, "%02d", day1) + "-" +
                            String.format(Locale.US, "%02d", month1 + 1) + year1);
                },
                year, month, day
        );
        dialog.setTitle("Select date of birth:");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}