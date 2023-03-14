package com.example.pizzahut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pizzahut.databinding.ActivityConfirmOderBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

public class ConfirmOderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;

    String date, ordarID;
    String pname, Pprice, pimg, pQunty, DiscountP;
    int price, qunty, ans, order, Total;
    ActivityConfirmOderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmOderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        pname = getIntent().getStringExtra("PName");
        Pprice = getIntent().getStringExtra("PPrice");
        pQunty = getIntent().getStringExtra("quntity");
        pimg = getIntent().getStringExtra("PIMAge");


        Picasso.get().load(pimg).into(binding.orderPizzaImageId);
        binding.orderPizzaNameId.setText(pname);
        binding.orderPizzaPriceId.setText(Pprice);
        binding.orderQuntyId.setText(pQunty);
        binding.orderPriceId.setText(Pprice);

        price = Integer.parseInt(Pprice);
        qunty = Integer.parseInt(pQunty);



        String Pizzatype = pname;

        switch (Pizzatype) {

            case "Small Pizza":
                small();
                break;
            case "Medium Pizza":
                mediam();
                break;

            case "Large Pizza":
                large();
                break;

            case "Monster Pizza":
                monster();
                break;
        }

        binding.placeorderBTNId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = FirebaseDatabase.getInstance().getReference();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("d-m-yyyy");
                date = format.format(calendar.getTime()).toString();
                ordarID = reference.push().getKey();

                PizzaitemModal modal = new PizzaitemModal(pname, String.valueOf(qunty), DiscountP, String.valueOf(order), date, String.valueOf(Total), auth.getCurrentUser().getEmail(), ordarID, pimg);

                reference.child("MyOrdar").child(auth.getCurrentUser().getUid()).child(ordarID).setValue(modal)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(ConfirmOderActivity.this, "Order Success", Toast.LENGTH_SHORT).show();
                                    reference.child("AdminRoom").child(date).child(ordarID).setValue(modal);
                                    startActivity(new Intent(ConfirmOderActivity.this,UserHomeActivity3.class));
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(ConfirmOderActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private void monster() {
        if (qunty >= 1) {

            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            ;
            ans = qunty / 1;
            order = ans * 200;
            binding.orderDicountId.setText("2 Coke 700ml - 1Ltr 80+120");
            DiscountP = "2 Coke 700ml - 1Ltr";
            total();
        } else {
            total();
        }
    }

    private void large() {

        if (qunty >= 2) {

            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            ans = qunty / 2;
            order = ans * 160;
            binding.orderDicountId.setText("2 Coke 700ml 80" + "\u20B9");
            DiscountP = "2 Coke 700ml";
            total();
        } else {
            total();
        }
    }

    private void mediam() {

        if (qunty >= 3) {

            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            ans = qunty / 3;
            order = ans * 120;
            binding.orderDicountId.setText("Coke 1ltr 120" + "\u20B9");
            DiscountP = "Coke 1Ltr";
            total();
        } else {
            total();
        }
    }

    private void small() {

        if (qunty >= 4) {

            binding.orderDiscountCardId.setVisibility(View.VISIBLE);
            ans = qunty / 4;
            order = ans * 80;
            binding.orderDicountId.setText("Coke 700ml 80" + "\u20B9");
            DiscountP = "Coke 700ml";
            total();
        } else {
            total();
        }
    }

    private void total() {

        Total = qunty * price;
        binding.orderGrandTotalId.setText(String.valueOf(Total));
    }
}