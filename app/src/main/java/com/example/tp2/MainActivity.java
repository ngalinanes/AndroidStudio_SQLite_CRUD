package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText City, Country, People;
    Button buttonGetCity, buttonNewCity, buttonDeleteCity;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGetCity = (Button) findViewById(R.id.buttonGetCity);
        buttonNewCity = (Button) findViewById(R.id.buttonNewCity);
        buttonDeleteCity = (Button) findViewById(R.id.buttonDeleteCity);

        City = (EditText) findViewById(R.id.city);
        Country = (EditText) findViewById(R.id.country);
        People = (EditText) findViewById(R.id.people);

        DB = new DBHelper(this);

        buttonNewCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = City.getText().toString();
                String country = Country.getText().toString();
                String people = People.getText().toString();

                if (city.equals("") || country.equals("") || people.equals(""))
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkcity = DB.checkcity(city);
                    if (checkcity)
                        Toast.makeText(MainActivity.this, "Esa ciudad ya se encuentra cargada.", Toast.LENGTH_SHORT).show();
                    else {
                        Boolean insert = DB.insertData(city, country, people);
                        if (insert)
                            Toast.makeText(MainActivity.this, "Se añadió la ciudad con éxito.", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(MainActivity.this, "Ocurrio un error al añadir la ciudad.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        buttonGetCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.getcity(City, Country, People);
            }
        });

        buttonDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deletecity(City, Country, People);
            }
        });
    }
}