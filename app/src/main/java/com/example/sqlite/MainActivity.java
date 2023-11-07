package com.example.sqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nombre = findViewById(R.id.input_usuario);
        EditText dni = findViewById(R.id.input_dni);

        final DbHelper devel=new DbHelper(getApplicationContext());

        Button btn = (Button) findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nom1 = nombre.getText().toString();
                String dni1 = dni.getText().toString();
                DbHelper dbhelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                if (db!=null){

                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this, "ERROR EN BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
                devel.insertar(nom1, dni1);
            }
        });

    }
}