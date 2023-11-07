package com.example.sqllito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private Button deleteButton;
    private Button backButton; // Botón para volver a MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Carga el diseño landscape
            setContentView(R.layout.activity_eliminar);
        } else {
            // Carga el diseño portrait (el diseño predeterminado)
            setContentView(R.layout.activity_eliminar);
        }

        userIdEditText = findViewById(R.id.userIdEditText);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton); // Botón para volver a MainActivity

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén la ID ingresada por el usuario
                String userId = userIdEditText.getText().toString();

                if (userId.isEmpty()) {
                    // Validación simple: si el campo está vacío, muestra un mensaje de error
                    Toast.makeText(EliminarActivity.this, "Por favor, ingrese una ID válida", Toast.LENGTH_SHORT).show();
                    return; // Sale de la función sin realizar ninguna acción
                }

                // Convierte la ID a un entero (si no es un número válido, mostrará una excepción)
                int id = Integer.parseInt(userId);

                // Realiza la eliminación en la base de datos utilizando una sentencia SQL DELETE
                DbHelper dbHelper = new DbHelper(EliminarActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Define la tabla y la cláusula WHERE para la eliminación
                String table = "usuarios";
                String whereClause = "id = ?";
                String[] whereArgs = {String.valueOf(id)};

                // Intenta eliminar el usuario
                int rowsDeleted = db.delete(table, whereClause, whereArgs);

                if (rowsDeleted > 0) {
                    // Se eliminó al menos una fila, lo que significa que el usuario se eliminó con éxito
                    Toast.makeText(EliminarActivity.this, "Usuario eliminado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    // No se eliminó ninguna fila, lo que significa que no se encontró un usuario con esa ID
                    Toast.makeText(EliminarActivity.this, "No se encontró un usuario con esa ID", Toast.LENGTH_SHORT).show();
                }

                // Cierra la base de datos
                db.close();
            }
        });

        // Configura el botón para volver a MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cierra la actividad y regresa a MainActivity
            }
        });
    }
}