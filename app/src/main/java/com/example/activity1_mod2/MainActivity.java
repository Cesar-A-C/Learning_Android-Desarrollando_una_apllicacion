package com.example.activity1_mod2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button btnCheckData;
    EditText nameEditText,phoneEditText,emailEditText,descriptionEditText;
    DatePicker birthDatePicker;
    String name,phone,email,description,birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeComponents();

        btnCheckData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getComponentsValue();
                if(checkData(v)){
                    Intent intent = new Intent(MainActivity.this, ConfirmData.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("email", email);
                    intent.putExtra("description", description);
                    intent.putExtra("birthDate", birthDate);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Intent intent = getIntent();

        if(intent.hasExtra("name")){
            nameEditText.setText(intent.getStringExtra("name"));
            phoneEditText.setText(intent.getStringExtra("phone"));
            emailEditText.setText(intent.getStringExtra("email"));
            descriptionEditText.setText(intent.getStringExtra("description"));

            String[] dateParts = Objects.requireNonNull(intent.getStringExtra("birthDate")).split("/");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]) - 1;
            int year = Integer.parseInt(dateParts[2]);
            birthDatePicker.updateDate(year, month, day);
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initializeComponents(){
        btnCheckData = findViewById(R.id.btnCheckData);
        nameEditText = findViewById(R.id.et_name);
        phoneEditText = findViewById(R.id.et_phone);
        emailEditText = findViewById(R.id.et_email);
        descriptionEditText = findViewById(R.id.et_description);
        birthDatePicker = findViewById(R.id.dp_date);
    }

    public void getComponentsValue(){
        name = nameEditText.getText().toString();
        phone = phoneEditText.getText().toString();
        email = emailEditText.getText().toString();
        description = descriptionEditText.getText().toString();
        birthDate = birthDatePicker.getDayOfMonth() + "/" + (birthDatePicker.getMonth() + 1) + "/" + birthDatePicker.getYear();
    }

    public boolean checkData(View v) {
        return !name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !description.isEmpty();
    }
}
