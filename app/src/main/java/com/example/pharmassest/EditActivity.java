package com.example.pharmassest;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    Database db;
    EditText et_MedName,et_Description,et_Shelf;
    Button btn_update;
    Spinner sp_company;
    ArrayList<Company> companies;
    String selectedCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        db = new Database(this);
        // GETTING OBJECTS REFERENCES
        et_MedName = findViewById(R.id.et_MedName);
        // et_Company = findViewById(R.id.et_Company);
        et_Description= findViewById(R.id.et_Description);
        et_Shelf= findViewById(R.id.et_Shelf);
        btn_update = findViewById(R.id.btn_Update);
        sp_company = findViewById(R.id.sp_Company);

        ArrayList<String> coNames = new ArrayList<>();
        companies = db.getAllCompanies();
        for(Company company : companies) {
            coNames.add(company.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,coNames);
        sp_company.setAdapter(adapter);
        sp_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCompany = coNames.get(position);}
                // Toast.makeText(getBaseContext(), ""+ selectedCompany, Toast.LENGTH_SHORT).show();
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medName = et_MedName.getText().toString().trim().toLowerCase();
                String desc = et_Description.getText().toString().trim().toLowerCase();
                int shelf = Integer.parseInt(et_Shelf.getText().toString().trim().toLowerCase());
                if (medName.length() == 0 || desc.length() == 0  || et_Shelf.getText().toString().trim().length() == 0
                        || selectedCompany.length()==0) {
                    showMessage("Error", "Please enter all required data!");
                }
                else {
                    Medicine medicine = new Medicine(medName,desc);
                    Company company = new Company(selectedCompany.toLowerCase(),db.getCloset(selectedCompany));
                    if(db.checkMedicine(medicine)){
                        boolean updateStorage = db.updateStorage(medName, selectedCompany, shelf);
                        if (updateStorage) {
                            if(db.updateMedicine(medicine)){
                                Toast.makeText(EditActivity.this, "Medicine updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                showMessage("Error", "Error occurred!");
                            }}
                    }else {
                        showMessage("Error", "Medicine is not found!");
                    }
                }}
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