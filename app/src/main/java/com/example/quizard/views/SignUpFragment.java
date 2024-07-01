package com.example.quizard.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizard.R;
import com.example.quizard.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment {


    private AuthViewModel viewModel;
    private NavController navController;
    private EditText editEmail, editPass;

    private TextView signInText;
    private Button signUpBtn;



    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        editEmail = view.findViewById(R.id.signUpEditEmail);
        editPass = view.findViewById(R.id.signUpEditPassword);
        signInText =view.findViewById(R.id.signInText);
        signUpBtn = view.findViewById(R.id.signUpbtn);

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String pass = editPass.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    viewModel.signUp(email, pass);
                    Toast.makeText(getContext(), "You have been registered successfully", Toast.LENGTH_SHORT).show();
                    viewModel.getFirebaseUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) {
                            if(firebaseUser != null){
                                navController.navigate(R.id.action_signUpFragment_to_signInFragment);
                            }
                        }
                    });


                }else{
                    Toast.makeText(getContext(),"Please enter a valid email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}