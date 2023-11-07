package com.example.sqllito;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VerActivity extends AppCompatActivity {
    private TextView textViewData;
    private Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Carga el diseño landscape
            setContentView(R.layout.activity_ver);
        } else {
            // Carga el diseño portrait (el diseño predeterminado)
            setContentView(R.layout.activity_ver);
        }
        textViewData = findViewById(R.id.textViewData);
        volver = findViewById(R.id.Volver);
        //mostrar la base de datos al iniciar la actividad
        cargarDatosDesdeBD();
    }

    // Función para cargar y mostrar los datos desde la base de datos
    private void cargarDatosDesdeBD() {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "id",
                "nombre",
                "dni"
        };

        String sortOrder = "id ASC";  // Ordenar por dni o el campo que desees

        Cursor cursor = db.query(
                "usuarios",    // Reemplaza "usuarios" con el nombre de tu tabla
                projection,
                null,  // Puedes especificar una condición WHERE aquí
                null,  // Los valores para la condición WHERE (si se especifica)
                null,  // No agrupar las filas
                null,  // No filtrar por grupos de filas
                sortOrder
        );

        // Procesa los datos y muestra en la vista
        StringBuilder data = new StringBuilder();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range") String dni = cursor.getString(cursor.getColumnIndex("dni"));
            data.append("ID: ").append(userId).append("\n");
            data.append("Nombre: ").append(nombre).append("\n");
            data.append("DNI: ").append(dni).append("\n\n");
        }
        cursor.close();
        db.close();

        // Muestra los datos en la vista
        textViewData.setText(data.toString());

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cierra la actividad y regresa a MainActivity
            }
        });
    }

}