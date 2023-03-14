package com.example.pizzahut.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzahut.Modals.UserModal;
import com.example.pizzahut.R;
import com.example.pizzahut.databinding.FragmentSIgnBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SIgnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SIgnFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SIgnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SIgnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SIgnFragment newInstance(String param1, String param2) {
        SIgnFragment fragment = new SIgnFragment();
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

    FragmentSIgnBinding binding;
    FirebaseAuth auth ;
    DatabaseReference reference ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSIgnBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        binding.signupbtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = binding.fullnameId.getEditText().getText().toString();
                String Email = binding.signupEmailIdId.getEditText().getText().toString().trim();
                String Pass = binding.signuppasswordId.getEditText().getText().toString().trim();

                if (Name.isEmpty() || Email.isEmpty() || Pass.isEmpty()) {
                    Toast.makeText(getContext(), "Fill the Field", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                                String uid = auth.getUid();
                                UserModal userModal = new UserModal(Name,Email,Pass,uid);

                                reference.child("Users").child(uid).setValue(userModal);
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.Auth_frmelayout_id,new LoginFragment()).commit();

                            }else {
                                Toast.makeText(getContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Signup Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        binding.loginTextId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Auth_frmelayout_id, new LoginFragment()).commit();
            }
        });

        return binding.getRoot();
    }
}