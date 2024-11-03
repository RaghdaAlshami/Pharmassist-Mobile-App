package com.example.pharmassest;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkerActivity extends AppCompatActivity {
    Database db;
    TextView tv_medicine, tv_company, tv_closet, tv_shelf, tv_closetShelf;
    EditText et_medicine, et_company, et_closet, et_shelf, et_clsetShelf;
    Button btn_search;
    Spinner sp_search;
    RecyclerView rv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = new Database(this);
        // GETTING OBJECTS REFERENCES
        tv_medicine = findViewById(R.id.tv_medicine);
        tv_company = findViewById(R.id.tv_company);
        tv_closet = findViewById(R.id.tv_closet);
        tv_shelf = findViewById(R.id.tv_shelf);
        tv_closetShelf = findViewById(R.id.tv_closetShelf);
        et_medicine = findViewById(R.id.et_medicine);
        et_company = findViewById(R.id.et_Company);
        et_closet = findViewById(R.id.et_closet);
        et_shelf = findViewById(R.id.et_shelf);
        et_clsetShelf = findViewById(R.id.et_closetShelf);
        btn_search = findViewById(R.id.btn_search);
        sp_search = findViewById(R.id.sp_search);
        rv_data = findViewById(R.id.rv_data);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        sp_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Medicine
                        tv_medicine.setVisibility(View.VISIBLE);
                        et_medicine.setVisibility(View.VISIBLE);
                        tv_company.setVisibility(View.GONE);
                        et_company.setVisibility(View.GONE);
                        tv_closet.setVisibility(View.GONE);
                        et_closet.setVisibility(View.GONE);
                        tv_shelf.setVisibility(View.GONE);
                        et_shelf.setVisibility(View.GONE);
                        tv_closetShelf.setVisibility(View.GONE);
                        et_clsetShelf.setVisibility(View.GONE);
                        break;
                    case 1:
                        // Company
                        tv_medicine.setVisibility(View.GONE);
                        et_medicine.setVisibility(View.GONE);
                        tv_company.setVisibility(View.VISIBLE);
                        et_company.setVisibility(View.VISIBLE);
                        tv_closet.setVisibility(View.GONE);
                        et_closet.setVisibility(View.GONE);
                        tv_shelf.setVisibility(View.GONE);
                        et_shelf.setVisibility(View.GONE);
                        tv_closetShelf.setVisibility(View.GONE);
                        et_clsetShelf.setVisibility(View.GONE);
                        break;
                    case 2:
                        // Closet
                        tv_medicine.setVisibility(View.GONE);
                        et_medicine.setVisibility(View.GONE);
                        tv_company.setVisibility(View.GONE);
                        et_company.setVisibility(View.GONE);
                        tv_closet.setVisibility(View.VISIBLE);
                        et_closet.setVisibility(View.VISIBLE);
                        tv_shelf.setVisibility(View.GONE);
                        et_shelf.setVisibility(View.GONE);
                        tv_closetShelf.setVisibility(View.GONE);
                        et_clsetShelf.setVisibility(View.GONE);
                        break;
                    case 3:
                        // Shelf
                        tv_medicine.setVisibility(View.GONE);
                        et_medicine.setVisibility(View.GONE);
                        tv_company.setVisibility(View.GONE);
                        et_company.setVisibility(View.GONE);
                        tv_closet.setVisibility(View.GONE);
                        et_closet.setVisibility(View.GONE);
                        tv_shelf.setVisibility(View.VISIBLE);
                        et_shelf.setVisibility(View.VISIBLE);
                        tv_closetShelf.setVisibility(View.VISIBLE);
                        et_clsetShelf.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = sp_search.getSelectedItemPosition();
                ArrayList<Model> models = new ArrayList<>();
                switch (index) {
                    case 0:
                        String medName = et_medicine.getText().toString().toLowerCase().trim();
                        if (medName.length() == 0) {
                            showMessage("Error", "Please enter required data!");
                        } else {
                            if (db.checkMedicine(medName)) {

                                Medicine medicine = db.getMedicine(medName);
                                Storage storage = db.getStorage(medName);
                                Company company = db.getCompany(storage.getCom_id());
                                Model model = new Model(medName, "Company: " + company.getName(), "Description: " + medicine.getDescription(),
                                        "Closet: " + company.getCloset(), "Shelf No.: " + storage.getShelf());
                                models.add(model);
                                MyAdapter adapter = new MyAdapter(models);
                                rv_data.setAdapter(adapter);
                            } else {
                                showMessage("Error", "No such medicine found!");
                            }
                        }

                        break;
                    case 1:
                        String coName = et_company.getText().toString().toLowerCase().trim();
                        if (coName.length() == 0) {
                            showMessage("Error", "Please enter required data!");
                        } else {
                            if (db.checkCompany(coName)) {
                                Company company = db.getCompany(coName);
                                Model model = new Model("Company: " + company.getName(),
                                        "Closet: " + company.getCloset(), "", "", "");
                                models.add(model);
                                MyAdapter adapter = new MyAdapter(models);
                                rv_data.setAdapter(adapter);

                            } else {
                                showMessage("Error", "No such company found!");
                            }
                        }

                        break;
                    case 2:
                        String closet = et_closet.getText().toString().toLowerCase().trim();
                        if (closet.length() == 0) {
                            showMessage("Error", "Please enter required data!");
                        } else {
                            if (db.checkCloset(closet)) {
                                Company company = db.getComCloset(closet);
                                ArrayList<Storage> storage = new ArrayList<>();
                                storage = db.getMedStorage(company.getName());
                                for (Storage s : storage) {
                                    int medID = s.getMed_id();
                                    Medicine medicine = db.getMedicine(medID);
                                    Model model = new Model(
                                            "Closet: " + closet, "Shelf no.: " + s.getShelf(), medicine.getName(), company.getName(), medicine.getDescription());
                                    models.add(model);
                                    MyAdapter adapter = new MyAdapter(models);
                                    rv_data.setAdapter(adapter);
                                }
                            } else {
                                showMessage("Error", "Closet is empty!");
                            }
                        }


                        break;
                    case 3:
                        String shelf = et_shelf.getText().toString().toLowerCase().trim();
                        String closetShelf = et_clsetShelf.getText().toString().toLowerCase().trim();
                        if (shelf.length() == 0 || closetShelf.length() == 0) {
                            showMessage("Error", "Please enter all required data!");
                        } else {

                            ArrayList<Storage> storages = db.getStorageShelf(shelf);
                            if (db.checkCloset(closetShelf)) {
                                Company company = db.getComCloset(closetShelf);
                                if (db.checkShelf(closetShelf, shelf)) {
                                    storages = db.getMedStorage(company.getName(), shelf);
                                    for (Storage s : storages) {
                                        int medID = s.getMed_id();
                                        Medicine medicine = db.getMedicine(medID);
                                        Model model = new Model("Closet : " + closetShelf,
                                                medicine.getName(), company.getName(), medicine.getDescription(), "");
                                        models.add(model);
                                        MyAdapter adapter = new MyAdapter(models);
                                        rv_data.setAdapter(adapter);
                                    }
                                } else {
                                    showMessage("Error", "Shelf is empty!");
                                }

                            } else {
                                showMessage("Error", "Closet is empty!");
                            }
                        }

                        break;

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