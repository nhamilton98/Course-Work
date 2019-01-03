package com.hamilton.nathan.nhamiltonlab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SevenSegment topNum = findViewById(R.id.topNum);
        final SevenSegment bottomOne = findViewById(R.id.bottomOne);
        final SevenSegment bottomTwo = findViewById(R.id.bottomTwo);
        final SevenSegment bottomThree = findViewById(R.id.bottomThree);
        final SevenSegment bottomFour = findViewById(R.id.bottomFour);

        bottomOne.setValue(6);
        bottomTwo.setValue(9);
        bottomThree.setValue(6);
        bottomFour.setValue(9);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onAddButtonClick(topNum);
                onAddButtonClick(bottomOne);
                onAddButtonClick(bottomTwo);
                onAddButtonClick(bottomThree);
                onAddButtonClick(bottomFour);
            }
        });
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
                    "Lab 4, Winter 2018, Nathan E Hamilton",
                    Toast.LENGTH_SHORT)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddButtonClick(SevenSegment segment)
    {
        int value = segment.getValue() + 1;

        if (value > 10)
            value = 0;

        segment.setValue(value);
    }
}
