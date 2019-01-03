package com.hamilton.nathan.nhamiltonlab3;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener
{
    ArrayList<Manufacturer> mManufacturers;
    ExpandableListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManufacturers = new ArrayList<>();
        boolean parsed = parseFile("muscleCars.txt");

        if (savedInstanceState != null)
            mManufacturers = (ArrayList<Manufacturer>) savedInstanceState.getSerializable("manufacturer");
        else {
            if (parsed) {
                Adapter adapter = new Adapter(this, mManufacturers);
                mList = findViewById(R.id.list);

                mList.setAdapter(adapter);
                mList.setOnChildClickListener(this);
            } else
                Toast.makeText(this, "File Parsing Failed", Toast.LENGTH_SHORT).show();
        }
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
                    "Lab 3, Winter 2018, Nathan E Hamilton",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putSerializable("manufactuerer", mManufacturers);

        super.onSaveInstanceState(savedInstanceState);
    }

    private boolean parseFile(String filename)
    {
        try
        {
            AssetManager manager = getResources().getAssets();
            InputStream stream = manager.open(filename);
            Scanner input = new Scanner(stream);

            String line;
            String[] tokenized;
            int groupIndex = 0;

            while(input.hasNextLine())
            {
                ArrayList<String> models = new ArrayList<>();
                line = input.nextLine();
                tokenized = line.split(",");

                mManufacturers.add(new Manufacturer(tokenized[0], models));

                for (int x = 1; x < tokenized.length; x++)
                    mManufacturers.get(groupIndex).addModel(tokenized[x]);

                groupIndex++;
            }

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View view, int groupIndex, int childIndex, long id)
    {
        String make = mManufacturers.get(groupIndex).getName();
        String model = mManufacturers.get(groupIndex).getModel(childIndex);

        Toast.makeText(this, make + " " + model, Toast.LENGTH_SHORT).show();

        return true;
    }
}
