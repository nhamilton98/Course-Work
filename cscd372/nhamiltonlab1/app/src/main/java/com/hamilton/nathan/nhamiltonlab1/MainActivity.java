package com.hamilton.nathan.nhamiltonlab1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TracerActivity {

    EditText inputName;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        result = findViewById(R.id.result);

        Intent intent = getIntent();
        if (intent != null)
        {
            String action = intent.getAction();
            String type = intent.getType();
            if (action.equals(Intent.ACTION_SEND) && type.equals("text/plain"))
                result.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(this,
                    "Lab 1, Winter 2018, Nathan E Hamilton",
                    Toast.LENGTH_SHORT)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void buttonClickTakeSurvey(View view)
    {
        String name = inputName.getText().toString();

        if (name.compareTo("") == 0)
            Toast.makeText(this,"Please enter your name.", Toast.LENGTH_SHORT).show();
        else
        {
            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtra("name", name);
            startActivityForResult(intent, 1);
        }
    }
    public void buttonClickGotoWebsite(View view)
    {
        Uri paulSite = Uri.parse("https://sites.google.com/site/pschimpf99/");
        Intent intent = new Intent(Intent.ACTION_VIEW, paulSite);
        startActivity(intent);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        int age = 0;
        if (requestCode == 1 && resultCode == RESULT_OK)
            age = data.getIntExtra("ageInt", 0);

        if (age < 40)
            result.setText("You are under 40, so you are trustworthy.");
        else
            result.setText("You are NOT under 40, so you are NOT trustworthy.");
    }
}
