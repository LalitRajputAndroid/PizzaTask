package com.example.pizzahut.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pizzahut.Adapters.PizzaitemAdapter;
import com.example.pizzahut.Modals.PizzaModal;
import com.example.pizzahut.databinding.ActivityUserHome3Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserHomeActivity3 extends AppCompatActivity {

    ActivityUserHome3Binding binding;
    DatabaseReference reference;
    PizzaitemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserHome3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("Pizza");
        binding.itemrecyclerId.setLayoutManager(new LinearLayoutManager(this));


        adapter = new PizzaitemAdapter(this);

        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    PizzaModal pizzaModal = snapshot1.getValue(PizzaModal.class);

                    adapter.add(pizzaModal);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UserHomeActivity3.this, "children not get", Toast.LENGTH_SHORT).show();
            }
        });

        binding.itemrecyclerId.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        binding.logoutbtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                SharedPreferences preferences =getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("login",false);
                editor.apply();
                startActivity(new Intent(UserHomeActivity3.this, MainActivity.class));
                finish();
            }
        });

    }
}