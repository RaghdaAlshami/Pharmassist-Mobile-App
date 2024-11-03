package com.example.pharmassest;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    Database db;
    EditText et_medName;
    Button btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        db = new Database(this);
        et_medName = findViewById(R.id.et_MedName);
        btn_delete = findViewById(R.id.btn_Delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medName = et_medName.getText().toString().trim().toLowerCase();
                if(db.checkMedicine(medName)){
                    boolean deleteStorage = db.deleteStorage(medName);
                    if(deleteStorage) {
                        boolean deleteMedicine = db.deleteMedicine(medName);
                        if (deleteMedicine) {
                            Toast.makeText(DeleteActivity.this,
                                    "Medicine deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            showMessage("Error", "Error occurred 1!");
                        } }
                    else {
                        showMessage("Error", "Error occurred 2!");
                    }}
                else {
                    showMessage("Error", "No such medicine found!");
                }
            }
        });
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.baseline_error_24);
        builder.show();

    }
}