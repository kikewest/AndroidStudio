package com.example.asignaturas;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculo);
        TextView TextView2 = findViewById(R.id.textView2);

        // Obtiene la media pasada como extra desde MainActivity
        double media = getIntent().getDoubleExtra("media", 0.0);

        // Muestra la media en el TextView
        TextView2.setText("" + (media));

        Button ButtonV = findViewById(R.id.buttonV);
        ButtonV.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
        });}
}