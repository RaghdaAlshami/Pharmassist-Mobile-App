package com.example.pharmassest;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCompanyActivity extends AppCompatActivity {
    Database db;
    EditText et_name,et_closet;
    Button btn_addCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        db = new Database(this);
        // GETTING OBJECTS REFERENCES
        et_name = findViewById(R.id.et_CompanyName);
        et_closet = findViewById(R.id.et_Closet);
        btn_addCompany = findViewById(R.id.btn_AddCompany);
        btn_addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameCo = et_name.getText().toString().toLowerCase().trim();
                String closet = et_closet.getText().toString().toLowerCase().trim();
                if (nameCo.length() == 0 || closet.length() == 0 ) {
                    showMessage("Error", "Please enter all required data!");
                }
                else {
                    Company company = new Company(nameCo,closet);
                    if(db.checkCompany(nameCo)){
                        showMessage("Error", "Company is already exists! ");
                    }
                    else{
                        boolean addCompany = db.insertCompany(company);
                        if(addCompany) {
                            Toast.makeText(AddCompanyActivity.this, "Company added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                            startActivity(intent);
                        }
                        else{
                            showMessage("Error", "Error occurred!");
                        }
                    }
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