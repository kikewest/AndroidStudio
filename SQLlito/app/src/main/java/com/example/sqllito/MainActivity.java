package com.example.sqllito;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button btn1;
    private Button btnModificar;
    private Button btnEliminar;
    private Button btnVer;
    private Button btnSalir;
    private Button btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encuentra y configura tus botones
        btn =findViewById(R.id.button4);
        btn1 = findViewById(R.id.button2);
        btnModificar = findViewById(R.id.button5);
        btnEliminar = findViewById(R.id.button6);
        btnVer = findViewById(R.id.button7);
        btnSalir = findViewById(R.id.salir);
        btnBorrar = findViewById(R.id.borrar);

        // Verifica si la base de datos existe
        File archivo = new File("/data/data/com.example.sqllito/databases/ejemplo.db");
        if (archivo.exists()) {
            btn.setEnabled(false);
            btn.setTextColor(Color.YELLOW);
            btn.setText("La base de datos ya está creada");
        } else {
            btn.setEnabled(true);
            btn1.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnVer.setEnabled(false);
            btnBorrar.setEnabled(false);
        }

        // Configura los listeners de los botones
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreBaseDeDatos = "ejemplo.db";
                if (deleteDatabase(nombreBaseDeDatos)) {
                    Toast.makeText(MainActivity.this, "Base de datos '" + nombreBaseDeDatos + "' borrada con éxito", Toast.LENGTH_LONG).show();
                    btn.setEnabled(true);
                    btn1.setEnabled(false);
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnVer.setEnabled(false);
                    btnBorrar.setEnabled(false);
                    btn.setText("Crear base de datos");
                    btn.setTextColor(Color.WHITE);
                } else {
                    Toast.makeText(MainActivity.this, "No se pudo borrar la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¡Nos merecemos un 10!")
                        .setCancelable(false)
                        .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VerActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DbHelper dbhelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                File archivo = new File("/data/data/com.example.sqllito/databases/ejemplo.db");
                if (db != null) {
                    btn.setEnabled(false);
                    btn1.setEnabled(true);
                    btnModificar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnVer.setEnabled(true);
                    btnSalir.setEnabled(true);
                    btnBorrar.setEnabled(true);
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                    btn.setText("La base de datos ya está creada");
                    btn.setTextColor(Color.YELLOW);
                } else {
                    btn.setEnabled(true);
                    btn1.setEnabled(false);
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnVer.setEnabled(false);
                    btnSalir.setEnabled(false);
                    btnBorrar.setEnabled(false);
                    Toast.makeText(MainActivity.this, "ERROR EN BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_insertar.class);
                startActivity(intent);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModificarActivity.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EliminarActivity.class);
                startActivity(intent);
            }
        });
    }


}
