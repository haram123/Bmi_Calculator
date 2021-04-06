package com.example.bmi_calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName,etAge, etGender, etHeight, etWeight;

    Button btnCalculate;
    TextView tvSample;
    final int RESULT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check().equals(true))
                {
                  //  Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    String keyName = etName.getText().toString();
                    String keyGender = etGender.getText().toString();
                    String age = etAge.getText().toString();
                    Double keyAge = Double.parseDouble(age);
                    String height = etHeight.getText().toString();
                    Double keyHeight = Double.parseDouble(height);
                    String weight = etWeight.getText().toString();
                    Double keyWeight = Double.parseDouble(weight);
                    Intent intent = new Intent(MainActivity.this,com.example.bmi_calculator.Result.class);
                    intent.putExtra("keyHeight",keyHeight);
                    intent.putExtra("keyWeight",keyWeight);
                    intent.putExtra("keyAge",keyAge);
                    intent.putExtra("keyGender",keyGender);
                    intent.putExtra("keyName",keyName);
                    startActivity(intent);
                    finish();




                }
            }
        });
    }

    private void init() {
        etName = findViewById(R.id.etName);
        etGender = findViewById(R.id.etGender);
        etAge= findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnCalculate = findViewById(R.id.btnCalculate);

    }
    private Boolean check()
    {
        String Name = etName.getText().toString();
        String Gender = etGender.getText().toString();
        String Age = etAge.getText().toString();
        String Height = etHeight.getText().toString();
        String Weight = etWeight.getText().toString();

        if(Name.isEmpty())
        {
            etName.setError("Name can not be left empty");

        }
        else if(Gender.isEmpty())
        {
            etGender.setError("Gender can not be left empty");

        }
        else if(Age.isEmpty())
        {
            etAge.setError("Age can not be left empty");

        }
        else if(Height.isEmpty())
        {
            etHeight.setError("Height can not be left empty");

        }
        else if(Weight.isEmpty())
        {
            etWeight.setError("Weight can not be left empty");
        }
        else
        {
            return true;
        }
        return false;
    }
}