package com.example.quizard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quizard.Adapter.QuizListAdapter;
import com.example.quizard.Model.QuizListModel;
import com.example.quizard.viewmodel.AuthViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private NavController navController;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);
        setContentView(R.layout.activity_main);
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            authViewModel.signOut();

            NavController navControllerL = Navigation.findNavController(this, R.id.nav_host_fragment);

            Toast.makeText(this, "Logged out succesfully", Toast.LENGTH_SHORT).show();
            navControllerL.navigate(R.id.action_listFragment_to_signInFragment);

        }


        return super.onOptionsItemSelected(item);
    }
}
