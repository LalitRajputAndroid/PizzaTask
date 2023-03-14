package com.example.pizzahut;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.pizzahut.Adapters.PizzaitemAdapter;
import com.example.pizzahut.Adapters.SalePizzaAdapter;
import com.example.pizzahut.databinding.FragmentSalecheckABinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalecheckAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalecheckAFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SalecheckAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalecheckAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalecheckAFragment newInstance(String param1, String param2) {
        SalecheckAFragment fragment = new SalecheckAFragment();
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

    FragmentSalecheckABinding binding;
    String DateP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSalecheckABinding.inflate(inflater, container, false);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int mount = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        binding.calendericonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        binding.setdatetextviewId.setText(datePicker.getDayOfMonth()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getYear());
                        DateP = binding.setdatetextviewId.getText().toString();

                    }
                }, day, mount, year);

                dialog.show();
            }
        });


        binding.searchbtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AdminRoom");

                binding.salerecyclerviewId.setLayoutManager(new LinearLayoutManager(getContext()));

                FirebaseRecyclerOptions<PizzaitemModal> options = new FirebaseRecyclerOptions.Builder<PizzaitemModal>()
                        .setQuery(reference.child(DateP), PizzaitemModal.class)
                        .build();

                SalePizzaAdapter salePizzaAdapter = new SalePizzaAdapter(options);
                binding.salerecyclerviewId.setAdapter(salePizzaAdapter);
                salePizzaAdapter.startListening();
            }
        });


        return binding.getRoot();
    }
}