package com.hamilton.nathan.nhamiltonlab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends AppCompatActivity {

    TextView helloName;
    EditText ageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("name");

        helloName = findViewById(R.id.helloName);
        helloName.setText(helloName.getText() + " " + firstName);

        ageInput = findViewById(R.id.ageInput);


    }

    public void buttonSubmitClick(View view)
    {
        String ageString = ageInput.getText().toString();

        if (ageString.compareTo("") == 0)
            Toast.makeText(this,"Please enter your age.", Toast.LENGTH_SHORT).show();
        else
        {
            int ageInt = Integer.parseInt(ageString);
            Intent intent = new Intent();
            intent.putExtra("ageInt", ageInt);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
