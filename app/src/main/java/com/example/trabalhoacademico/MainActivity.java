package com.example.trabalhoacademico;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.criarBancoAutenticacao();
    }

    public void criarBancoAutenticacao(){
        SQLiteDatabase myDB = openOrCreateDatabase("login.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS usuario (login VARCHAR(20), senha VARCHAR(20))");
        ContentValues registro = new ContentValues();
        ContentValues registro1 = new ContentValues();
        registro.put("login", "admin");
        registro.put("senha", "12345678");
        registro.put("login", "admin1");
        registro.put("senha", "12345678");

        myDB.insert("usuario", null, registro);
        myDB.insert("usuario", null, registro1);

        myDB.close();
    }
    public void autenticarBanco(View v){
        String login = ((EditText)findViewById(R.id.login)).getText().toString();
        String senha = ((EditText)findViewById(R.id.senha)).getText().toString();

        SQLiteDatabase myDB = openOrCreateDatabase("login.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS usuario (login VARCHAR(20), senha VARCHAR(20))");


        Cursor myCursor = myDB.rawQuery("select login, senha from usuario", null);

        myCursor.moveToNext();
        String loginString = myCursor.getString(0);
        String senhaString = myCursor.getString(1);

        if(loginString.equalsIgnoreCase(login) && senhaString.equalsIgnoreCase(senha)){
            Toast.makeText(this, "SQL OK", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AlunoActivity.class);
            startActivity(intent);
        }else if(loginString.equalsIgnoreCase(login) && senhaString.equalsIgnoreCase(senha)){
            Toast.makeText(this, "SQL OK", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ProfessorActivity.class);
            startActivity(intent);}
        else
            Toast.makeText(this, "SQL Error", Toast.LENGTH_LONG).show();


        myDB.close();


    }


}