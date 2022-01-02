package com.android.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.group.fragment.LoginFrag;
import com.android.group.fragment.RegisterFrag;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    LoginFrag loginFrag;
    RegisterFrag registerFrag;
    Button loginBtn, registerBtn;
    boolean isLoginFrag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.main_login_btn);
        registerBtn = findViewById(R.id.main_register_btn);

        loginFrag = new LoginFrag();
        registerFrag = new RegisterFrag();

        loginBtn.setOnClickListener(v -> switchToLoginFrag());
        registerBtn.setOnClickListener(v -> switchToRegisterFrag());
        switchToLoginFrag();
    }

    public void switchToLoginFrag() {
        isLoginFrag = true;
        loginBtn.setVisibility(View.GONE);
        registerBtn.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.main_frag_container, loginFrag);
        ft.commit();
    }

    public void switchToRegisterFrag() {
        isLoginFrag = false;
        loginBtn.setVisibility(View.VISIBLE);
        registerBtn.setVisibility(View.GONE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.main_frag_container, registerFrag);
        ft.commit();
    }

    public void enterApp(View view) {
        if (isLoginFrag) loginFrag.enterApp();
        else registerFrag.enterApp();
    }
    @Override
    public void onStart(){
        super.onStart();
        // Check the user signs in or not
        // If the user does not sign in, the system to move LoginActivity
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            switchToLoginFrag();
        } else {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), F0HomeActivity.class);
            startActivity(intent);
        }
    }
}