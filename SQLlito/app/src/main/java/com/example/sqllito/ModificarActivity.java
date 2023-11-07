package com.example.sqllito;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ModificarActivity extends AppCompatActivity {

    private EditText editTextUserID;
    private EditText editTextNewName;
    private EditText editTextNewDNI;
    private Button modifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        editTextUserID = findViewById(R.id.editTextUserID);
        editTextNewName = findViewById(R.id.editTextNewName);
        editTextNewDNI = findViewById(R.id.editTextNewDNI);
        modifyButton = findViewById(R.id.modifyButton);
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finaliza la actividad actual y vuelve a la MainActivity
                finish();
            }
        });
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén los valores ingresados por el usuario
                String userID = editTextUserID.getText().toString();
                String newName = editTextNewName.getText().toString();
                String newDNI = editTextNewDNI.getText().toString();

                if (userID.isEmpty() || newName.isEmpty() || newDNI.isEmpty()) {
                    // Asegúrate de que se ingresen todos los datos necesarios
                    Toast.makeText(ModificarActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Realiza la modificación en la base de datos utilizando el ID y los nuevos datos
                    modificarUsuario(userID, newName, newDNI);

                    // Muestra un mensaje de confirmación
                    Toast.makeText(ModificarActivity.this, "Usuario modificado con éxito", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void modificarUsuario(String userID, String newName, String newDNI) {
        // Crea o abre la base de datos en modo escritura
        DbHelper dbHelper = new DbHelper(ModificarActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Valores a actualizar
        ContentValues values = new ContentValues();
        values.put("nombre", newName);
        values.put("dni", newDNI);

        // Condición para actualizar un usuario específico (basado en su ID)
        String selection = "id = ?";
        String[] selectionArgs = { userID };

        // Realiza la actualización en la base de datos
        int count = db.update("usuarios", values, selection, selectionArgs);

        // Cierra la base de datos
        db.close();
    }
}