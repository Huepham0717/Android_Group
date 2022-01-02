package com.android.group.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.group.F0HomeActivity;
import com.android.group.R;
import com.android.group.controller.MedicalRecordController;
import com.android.group.controller.UserController;
import com.android.group.model.MedicalRecord;
import com.android.group.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFrag extends Fragment {
    EditText emailInput, usernameInput, passwordInput, addressInput, dobInput, phoneInput,
            idInput, emergencyInput;
    RadioButton maleRadio, femaleRadio, doctorRadio, patientRadio;
    TextView errorTxt;
    String type= "Doctor";
    String sex = "Male";
    String infection_status = "No dose";
    String vaccination_status = "Not infected with Covid";
    FirebaseAuth firebaseAuth;
    TextView role;
    User user;
    MedicalRecord medicalRecord;
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1;
    TextView textView,textView1;
    TextInputLayout textInputLayout,textInputLayout1;


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
        firebaseAuth = FirebaseAuth.getInstance();
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
        doctorRadio = view.findViewById(R.id.frag_register_doctor_radio);
        patientRadio = view.findViewById(R.id.frag_register_patient_radio);

        emailInput = view.findViewById(R.id.frag_register_email_input);
        usernameInput = view.findViewById(R.id.frag_register_username_input);
        passwordInput = view.findViewById(R.id.frag_register_password_input);
        addressInput = view.findViewById(R.id.frag_f0_add_report_disease_input);
        dobInput = view.findViewById(R.id.frag_register_dob_input);
        phoneInput = view.findViewById(R.id.frag_register_phone_input);
        idInput = view.findViewById(R.id.frag_register_passport_input);
        emergencyInput = view.findViewById(R.id.frag_register_emergency_input);
        role = view.findViewById(R.id.textView_form9);

        errorTxt = view.findViewById(R.id.frag_register_error_txt);


        autoCompleteTextView=view.findViewById(R.id.drop_items);
        autoCompleteTextView1 = view.findViewById(R.id.drop_items1);
        textView=view.findViewById(R.id.itemSelected);
        textView1=view.findViewById(R.id.itemSelected1);
        textInputLayout=view.findViewById(R.id.menu_drop);
        textInputLayout1=view.findViewById(R.id.menu_drop1);

        emergencyInput.setVisibility(View.GONE);
        role.setVisibility(View.GONE);
        autoCompleteTextView.setVisibility(View.GONE);
        autoCompleteTextView1.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textInputLayout.setVisibility(View.GONE);
        textInputLayout1.setVisibility(View.GONE);

        maleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "Male";
            }
        });
        femaleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "Female";
            }
        });
        doctorRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type ="Doctor";

                emergencyInput.setVisibility(View.GONE);
                role.setVisibility(View.GONE);
                autoCompleteTextView.setVisibility(View.GONE);
                autoCompleteTextView1.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                textInputLayout.setVisibility(View.GONE);
                textInputLayout1.setVisibility(View.GONE);
            }
        });
        patientRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type ="Patient";

                emergencyInput.setVisibility(View.VISIBLE);
                role.setVisibility(View.VISIBLE);
                autoCompleteTextView.setVisibility(View.VISIBLE);
                autoCompleteTextView1.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textInputLayout.setVisibility(View.VISIBLE);
                textInputLayout1.setVisibility(View.VISIBLE);

                String [] items={"One dose","Two doses", "No dose"};
                String [] items1={"Infected with Covid","Not infected with Covid"};
                ArrayAdapter<String> itemAdapter= new ArrayAdapter<>(getContext(), R.layout.item_list, items);
                autoCompleteTextView.setAdapter(itemAdapter);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        vaccination_status = (String)parent.getItemAtPosition(position);
                    }
                });

                ArrayAdapter<String> itemAdapter1= new ArrayAdapter<>(getContext(), R.layout.item_list, items1);
                autoCompleteTextView1.setAdapter(itemAdapter1);
                autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        infection_status = (String)parent.getItemAtPosition(position);
                    }
                });
            }
        });
        dobInput.setOnClickListener(v -> setDatePickerDialog((EditText) v));

        return view;
    }

    public void enterApp() {
        user = new User();
        medicalRecord = new MedicalRecord();
        // Check before entering the home screen
        String emailStr = emailInput.getText().toString();
        String usernameStr = usernameInput.getText().toString();
        String passwordStr = passwordInput.getText().toString();
        String addressStr = addressInput.getText().toString();
        String dobStr = dobInput.getText().toString();
        String phoneStr = phoneInput.getText().toString();
        String emergencyStr = emergencyInput.getText().toString();
        String idStr = idInput.getText().toString();
        if (emailStr.equals("") || usernameStr.equals("") || passwordStr.equals("") ||
                addressStr.equals("") || dobStr.equals("") || phoneStr.equals("")
                || idStr.equals("")) {
            errorTxt.setVisibility(View.VISIBLE);
        } else {
            errorTxt.setVisibility(View.GONE);
            // TODO move to HomeActivity, add to database and call PostRequest
            // Create an account use FirebaseAuth
            firebaseAuth.createUserWithEmailAndPassword(emailStr,passwordStr)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // After registered successfully, the system added the account to Restful API
                            user.setUsername(usernameStr);
                            user.setEmail(emailStr);
                            user.setUUID(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            user.setAddress(addressStr);
                            user.setdOB(dobStr);
                            user.setAddress(addressStr);
                            user.setPassport(idStr);
                            user.setPhone(phoneStr);
                            user.setSex(sex);
                            user.setType(type);
                            UserController userController = new UserController(getContext());
                            userController.addUser(user, new UserController.VolleyResponseListener1() {
                                @Override
                                public void onError(String message) {
                                    Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String message) {
                                    Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                                }
                            });
                            if(type.equals("Patient")){
                                medicalRecord.setPatientUUID(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                medicalRecord.setrTimestamp(Calendar.getInstance().getTime().toString());
                                medicalRecord.setfVaxStat(vaccination_status);
                                medicalRecord.setfInfectStat(infection_status);
                                medicalRecord.setfEmergency(emergencyStr);
                                MedicalRecordController medicalRecordController = new MedicalRecordController(getContext());
                                medicalRecordController.addMedicalRecord(medicalRecord, new MedicalRecordController.VolleyResponseListener1() {
                                    @Override
                                    public void onError(String message) {
                                        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String message) {
                                        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"Failed to Create Account",Toast.LENGTH_SHORT).show();
                }
            });
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

        @SuppressLint("SetTextI18n")
        DatePickerDialog dialog = new DatePickerDialog(
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