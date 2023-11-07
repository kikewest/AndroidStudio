package com.example.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Fragment FragmentInicio, FragmentRojo, FragmentVerde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentInicio = new FragmentInicio();
        FragmentRojo = new FragmentRojo();
        FragmentVerde = new FragmentVerde();
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, FragmentInicio).commit();

        Button buttonVerde = findViewById(R.id.button);
        Button buttonRojo = findViewById(R.id.button1);

        buttonVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reemplazar el fragmento actual con el FragmentVerde
                replaceFragment(new FragmentVerde());
            }
        });

        buttonRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reemplazar el fragmento actual con el FragmentRojo
                replaceFragment(new FragmentRojo());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null); // Opcional: permite volver al fragmento anterior
        transaction.commit();
    }
}

    


