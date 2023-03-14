package com.example.pizzahut.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzahut.Activitys.AdminHomeActivity2;
import com.example.pizzahut.databinding.FragmentAdminLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminLoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminLoginFragment newInstance(String param1, String param2) {
        AdminLoginFragment fragment = new AdminLoginFragment();
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

    FragmentAdminLoginBinding binding ;
    FirebaseAuth auth ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAdminLoginBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();

        binding.adminloginbtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.adminEmailIdId.getEditText().getText().toString();
                String password = binding.adminpasswordId.getEditText().getText().toString();

                if (email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getContext(), "Fill the filed", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "Admin Login Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), AdminHomeActivity2.class));
                                binding.adminEmailIdId.getEditText().setText("");
                                binding.adminpasswordId.getEditText().setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

       return binding.getRoot();
    }
}