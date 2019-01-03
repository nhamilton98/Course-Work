package com.hamilton.nathan.nhamiltonlab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private BouncyMass bouncyMass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bouncyMass = findViewById(R.id.bouncyMass);
    }

    public void onButtonClick(View view)
    {
        if (this.bouncyMass.isRunning())
            this.bouncyMass.pause();
        else
            this.bouncyMass.go();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Toast.makeText(this,
                    "Lab 5, Winter 2018, Nathan E Hamilton",
                    Toast.LENGTH_SHORT)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
