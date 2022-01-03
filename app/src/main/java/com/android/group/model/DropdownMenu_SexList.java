package com.android.group.model;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.group.R;
import com.google.android.material.textfield.TextInputLayout;

public class DropDownmenu_SexList extends AppCompatActivity {
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update);

        textInputLayout=findViewById(R.id.menu_drop);
        autoCompleteTextView=findViewById(R.id.drop_items);


        String [] items={"Male","Female", "Unknown"};
        ArrayAdapter<String> itemAdapter= new ArrayAdapter<>(DropDownmenu_SexList.this, R.layout.sex_list_update_profile_doctor, items);
        autoCompleteTextView.setAdapter(itemAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText((String)parent.getItemAtPosition(position));
            }
        });



    }
}
