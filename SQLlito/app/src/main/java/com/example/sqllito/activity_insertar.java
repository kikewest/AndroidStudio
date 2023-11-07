package com.example.sqllito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** @noinspection ALL*/
public class activity_insertar extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText dniEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Carga el diseño landscape
            setContentView(R.layout.activity_insertar);
        } else {
            // Carga el diseño portrait (el diseño predeterminado)
            setContentView(R.layout.activity_insertar);
        }
        // Obtén referencias a los elementos de la interfaz
        nombreEditText = findViewById(R.id.editTextText);
        dniEditText = findViewById(R.id.editTextText2);
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finaliza la actividad actual y vuelve a la MainActivity
                finish();
            }
        });
        Button btnInsertar = findViewById(R.id.button);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén los valores ingresados por el usuario
                String nombre = nombreEditText.getText().toString();
                String dni = dniEditText.getText().toString();

                // Crea una instancia de DbHelper
                DbHelper dbHelper = new DbHelper(activity_insertar.this);

                // Obtén una referencia a la base de datos para escritura
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Crea un objeto ContentValues para insertar los datos
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("dni", dni);

                // Inserta los valores en la tabla "usuarios"
                long newRowId = db.insert("usuarios", null, values);

                // Verifica si la inserción fue exitosa
                if (newRowId != -1) {
                    Toast.makeText(activity_insertar.this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                    nombreEditText.setText(null);
                    dniEditText.setText(null);
                } else {
                    Toast.makeText(activity_insertar.this, "Error al insertar datos", Toast.LENGTH_SHORT).show();
                }

                // Cierra la base de datos
                db.close();
            }
        });
    }
}