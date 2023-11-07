package com.example.asignaturas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText matematicasEditText, inglesEditText, fisicaEditText;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matematicasEditText = findViewById(R.id.editTextText2);
        inglesEditText = findViewById(R.id.editTextText3);
        fisicaEditText = findViewById(R.id.editTextText6);
        calcularButton = findViewById(R.id.button);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Obtén las notas ingresadas por el usuario
                String matematicasStr = matematicasEditText.getText().toString();
                String inglesStr = inglesEditText.getText().toString();
                String fisicaStr = fisicaEditText.getText().toString();

                // Verifica si se ingresaron todas las notas
                if (matematicasStr.isEmpty() || inglesStr.isEmpty() || fisicaStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, no deje los campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    // Calcula la media
                    double matematicas = Double.parseDouble(matematicasStr);
                    double ingles = Double.parseDouble(inglesStr);
                    double fisica = Double.parseDouble(fisicaStr);
                    double media = (matematicas + ingles + fisica) / 3.0;
                    Toast.makeText(getApplicationContext(), "La Media es"+media, Toast.LENGTH_SHORT).show();
                    // Inicia la segunda actividad y pasa la media como extra
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("media", media);
                    startActivity(intent);
                }
            }
        });
    }
}
