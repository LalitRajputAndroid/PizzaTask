package com.example.pizzahut.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzahut.Modals.PizzaModal;
import com.example.pizzahut.databinding.FragmentAddpizzaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddpizzaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddpizzaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddpizzaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddpizzaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddpizzaFragment newInstance(String param1, String param2) {
        AddpizzaFragment fragment = new AddpizzaFragment();
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

    FragmentAddpizzaBinding binding;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddpizzaBinding.inflate(inflater, container, false);

        reference = FirebaseDatabase.getInstance().getReference();

        binding.addpizzabtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p_name = binding.pizzanameId.getEditText().getText().toString();
                String p_price = binding.pizzapriceId.getEditText().getText().toString();
                String p_img = "https://firebasestorage.googleapis.com/v0/b/pizza-hut-e022f.appspot.com/o/16862939_90em_fek6_210524.jpg?alt=media&token=cd399580-520b-4cdb-83ce-efb0a6a916c6";

                if (p_name.isEmpty() || p_price.isEmpty()) {
                    Toast.makeText(getContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    String p_id = reference.push().getKey();
                    PizzaModal pizzaModal = new PizzaModal(p_name, p_price, p_img, p_id);

                    reference.child("Pizza").child(p_id).setValue(pizzaModal).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "Add Success", Toast.LENGTH_SHORT).show();
                                binding.pizzanameId.getEditText().setText("");
                                binding.pizzapriceId.getEditText().setText("");
                            }
                        }
                    });
                }
            }
        });


        return binding.getRoot();
    }
}