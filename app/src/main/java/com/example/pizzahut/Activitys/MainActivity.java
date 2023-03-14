package com.example.pizzahut.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizzahut.Fragments.LoginFragment;
import com.example.pizzahut.R;
import com.example.pizzahut.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.Auth_frmelayout_id,new LoginFragment()).commit();

    }
}