package com.example.trabalhoacademico;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import android.os.Bundle;

public class AlunoActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText editText_id, editText_nome, editText_aps1, editText_av1, editText_nav1, editText_aps2,editText_av2, editText_nav2,editText_av3, editText_media, editText_status;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        myDb = new DataBaseHelper(this);

        editText_id = (EditText)findViewById(R.id.editText_id);
        editText_nome = (EditText)findViewById(R.id.editText_nome);
        editText_av1= (EditText)findViewById(R.id.editText_av1);
        editText_aps1= (EditText)findViewById(R.id.editText_aps1);
        editText_nav1= (EditText)findViewById(R.id.editText_nav1);
        editText_aps2= (EditText)findViewById(R.id.editText_aps2);
        editText_av2 = (EditText)findViewById(R.id.editText_av2);
        editText_nav2 = (EditText)findViewById(R.id.editText_nav2);
        editText_av3 = (EditText)findViewById(R.id.editText_av3);
        editText_media = (EditText)findViewById(R.id.editText_media);
        editText_status = (EditText)findViewById(R.id.editText_status);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editText_id.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(AlunoActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AlunoActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editText_id.getText().toString(),
                                editText_aps1.getText().toString(),
                                editText_av1.getText().toString(),editText_nav1.getText().toString(), editText_aps2.getText().toString(),editText_av2.getText().toString(),
                                editText_nav2.getText().toString(), editText_av3.getText().toString(), editText_media.getText().toString(), editText_status.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(AlunoActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AlunoActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editText_nome.getText().toString(),
                                editText_aps1.getText().toString(),
                                editText_av1.getText().toString(),editText_nav1.getText().toString(), editText_aps2.getText().toString(),editText_av2.getText().toString(),
                                editText_nav2.getText().toString(), editText_av3.getText().toString(), editText_media.getText().toString(), editText_status.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(AlunoActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AlunoActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Nome :"+ res.getString(0)+"\n");
                            buffer.append("APS 1 :"+ res.getString(1)+"\n");
                            buffer.append("Av1 :"+ res.getString(2)+"\n");
                            buffer.append("Nota AV1 :"+ res.getString(3)+"\n");
                            buffer.append("APS 2 :"+ res.getString(4)+"\n");
                            buffer.append("Av2 :"+ res.getString(5)+"\n");
                            buffer.append("Nota Av2 :"+ res.getString(6)+"\n\n");
                            buffer.append("Av3 :"+ res.getString(7)+"\n\n");
                            buffer.append("Média :"+ res.getString(8)+"\n\n");
                            buffer.append("Status :"+ res.getString(9)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}