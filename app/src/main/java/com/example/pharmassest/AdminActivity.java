package com.example.pharmassest;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    CardView cv_Add,cv_Edit,cv_Search,cv_Delete;  ImageView iv_setting;
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        iv_setting = findViewById(R.id.setting_icon);
        tv_title= findViewById(R.id.tv_title);
        // GETTING OBJECTS REFERENCES
        cv_Add = findViewById(R.id.cv_Add);
        cv_Edit = findViewById(R.id.cv_Edit);
        cv_Search = findViewById(R.id.cv_Search);
        cv_Delete = findViewById(R.id.cv_Delete);
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        // Listeners
        cv_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
        cv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(intent);
            }
        });
        cv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        cv_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DeleteActivity.class);
                startActivity(intent);
            }
        });




    }
}