package com.example.bmi_calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import static android.widget.Toast.LENGTH_SHORT;
import java.io.FileOutputStream;
import java.io.File;
import android.widget.*;



public class Result extends AppCompatActivity {

    TextView tvHeading,tvResult,tvInstructions,tvInstructions2;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        Intent intent = getIntent();
        Double getHeight = intent.getDoubleExtra("keyHeight", -1);
        Double getWeight = intent.getDoubleExtra("keyWeight", -1);
        int getAge = intent.getIntExtra("keyAge", -1);
        String getName = intent.getStringExtra("keyName");
        String getGender = intent.getStringExtra("keyGender");
        calculate_BMI(getWeight,getHeight);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file = "BMI_result.txt";
                String allData =  getName+ ", "+ getAge+ ", "+ getGender+", "+getHeight+ ", "+ getWeight+"\n";
                FileOutputStream fileOutputStream = null;
                try {

                    fileOutputStream = openFileOutput(file,MODE_APPEND);
                    fileOutputStream.write(allData.getBytes());
                    fileOutputStream.close();
                    File filDir = new File(getFilesDir(),file);
                    Toast.makeText(Result.this,"File Saved at "+filDir, LENGTH_SHORT).show();



                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        });


    }
     void calculate_BMI( Double kW, Double kH)
    {
        DecimalFormat df = new DecimalFormat(".##");
        Double BMI=0.0;
        kH = kH/100;
        kH = kH*kH;  //converting into meter square
        BMI = kW/kH;

        String  resultBMI = String.valueOf(df.format(BMI));

        tvResult.setText(resultBMI);
        if(BMI<18.5 )
        {
            tvInstructions.setText("Body Mass Defect");
            tvInstructions2.setText("Low Risk of Developing Assoicated illness\n but higher risk of other illness");
        }
        else if(BMI>=18.5 && BMI<=24.9)
        {
            tvInstructions.setText("Normal Body Mass ");
            tvInstructions2.setText("Average Risk of Developing Assoicated illness");
        }

        else if(BMI>=25.0 && BMI<=29.9)
        {
            tvInstructions.setText("Excessive Body Mass (pre-obesity)");
            tvInstructions2.setText("Heightened Risk of Developing Assoicated illness");
        }
        else if(BMI>=30.0 && BMI<=34.9)
        {
            tvInstructions.setText("Obesity 1st Degree");
            tvInstructions2.setText("High Risk of Developing Assoicated illness\n ");
        }
        else if(BMI>=35.0 && BMI<=39.9)
        {
            tvInstructions.setText("Obesity 2nd Degree");
            tvInstructions2.setText("Very High Risk of Developing Assoicated illness\n ");
        }
        else
        {
            tvInstructions.setText("Obesity 3rd Degree");
            tvInstructions2.setText("Extremely High Risk of Developing Assoicated illness\n");
        }
    }
    private void init() {
        tvHeading = findViewById(R.id.tvHeading);
        tvResult = findViewById(R.id.tvResult);
        tvInstructions = findViewById(R.id.tvInstructions);
        tvInstructions2 = findViewById(R.id.tvInstructions2);
        btnBack = findViewById(R.id.btnBack);


    }

}
