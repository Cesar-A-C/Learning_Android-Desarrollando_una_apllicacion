package com.example.activity1_mod2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ConfirmData extends AppCompatActivity {

    Button btnConfirmData;
    TextView tvName, tvBirthDate, tvPhone, tvEmail, tvDescription;
    String name,phone,email,description,birthDate;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_data);

        initializeComponents();
        getViewParameters();

        tvName.setText(tvName.getText().toString()+": "+name);
        tvBirthDate.setText(tvBirthDate.getText().toString()+": "+birthDate);
        tvPhone.setText(tvPhone.getText().toString()+": "+phone);
        tvEmail.setText(tvEmail.getText().toString()+": "+email);
        tvDescription.setText(description);

        btnConfirmData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmData.this, MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("description", description);
                intent.putExtra("birthDate", birthDate);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initializeComponents(){
        tvName = findViewById(R.id.tv_name);
        tvBirthDate = findViewById(R.id.tv_birthdate);
        tvPhone = findViewById(R.id.tv_telephone);
        tvEmail = findViewById(R.id.tv_email);
        tvDescription = findViewById(R.id.tv_descrip_text);
        btnConfirmData = findViewById(R.id.btnEditData);
    }

    public void getViewParameters(){
        Bundle parameters = getIntent().getExtras();
        assert parameters!=null;
        name = parameters.getString("name");
        phone = parameters.getString("phone");
        email = parameters.getString("email");
        description = parameters.getString("description");
        birthDate = parameters.getString("birthDate");
    }

}