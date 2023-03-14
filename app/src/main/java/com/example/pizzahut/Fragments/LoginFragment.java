package com.example.pizzahut.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzahut.Activitys.UserHomeActivity3;
import com.example.pizzahut.R;
import com.example.pizzahut.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    FragmentLoginBinding binding;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();

        SharedPreferences preferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        boolean check = preferences.getBoolean("login",false);

        if (check){

            Intent intent = new Intent(getContext(), UserHomeActivity3.class);
            startActivity(intent);
        }

        binding.loginbtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.loginEmailIdId.getEditText().getText().toString();
                String password = binding.loginpasswordId.getEditText().getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "LogIn Success", Toast.LENGTH_SHORT).show();

                                SharedPreferences preferences1 = getActivity().getSharedPreferences("User",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor= preferences1.edit();
                                editor.putBoolean("login",true);
                                editor.apply();

                                startActivity(new Intent(getContext(),UserHomeActivity3.class));

                            }else {
                                Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "task Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        binding.signupTextId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Auth_frmelayout_id, new SIgnFragment()).commit();
            }
        });

        binding.adminBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Auth_frmelayout_id,new AdminLoginFragment()).commit();
            }
        });


//
//        binding.signBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager=getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                Bundle bundle=new Bundle();
//                bundle.putString("l","lodu");
//                SIgnFragment fragment=new SIgnFragment();
//                fragment.setArguments(bundle);
//                fragmentTransaction.replace(R.id.Auth_frmelayout_id,fragment).commit();
//
//
//            }
//        });

        return binding.getRoot();
    }
}