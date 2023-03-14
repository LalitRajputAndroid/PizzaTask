package com.example.pizzahut.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pizzahut.Fragments.AddpizzaFragment;
import com.example.pizzahut.R;
import com.example.pizzahut.Fragments.SalecheckAFragment;
import com.example.pizzahut.databinding.ActivityAdminHome2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminHomeActivity2 extends AppCompatActivity {

    ActivityAdminHome2Binding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHome2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.admintoobarId);
        getSupportActionBar().setTitle("Admin");
        getSupportFragmentManager().beginTransaction().replace(R.id.adminhome_framelayout_id,new AddpizzaFragment()).commit();

        binding.navigationViewId.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){

                    case R.id.add_pizza_id:
                        fragment = new AddpizzaFragment();
                        Toast.makeText(AdminHomeActivity2.this, "add", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.salepizza_id:
                        fragment = new SalecheckAFragment();
                        Toast.makeText(AdminHomeActivity2.this, "sale", Toast.LENGTH_SHORT).show();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.adminhome_framelayout_id,fragment).commit();
                return true;
            }
        });

    }
}