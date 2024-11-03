package com.example.pharmassest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database db;
    EditText et_username, et_password;
    Button btn_login;
    ImageView iv_setting;
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_setting = findViewById(R.id.setting_icon);
        tv_title= findViewById(R.id.tv_title);
        db = new Database(this);
        // GETTING OBJECTS REFERENCES
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_username.getText().toString().trim().toLowerCase();
                String password = et_password.getText().toString().trim().toLowerCase();
                if (name.length() == 0 || password.length() == 0 ) {
                    showMessage("Error", "Please enter all required data!");
                }
                else {

                    if (name.equals("admin")|| password.equals("123" )  ){
                            Toast.makeText(MainActivity.this, "Logged in as admin", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);}
                    if (name.equals("worker")|| password.equals("3111")  ){
                            Toast.makeText(MainActivity.this, "Logged in as worker", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), WorkerActivity.class);
                            startActivity(intent);}
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