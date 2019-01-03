package com.hamilton.nathan.nhamiltonlab6;

import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
    Model mModel;
    ViewPager mPager;
    SectionPagerAdapter mPagerAdapter;

    public class SectionPagerAdapter extends FragmentPagerAdapter
    {
        public SectionPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position)
        {
            if (position == 0)
                return ListFragment.newInstance(mManufacturers);
            else
                return new DetailFragment();
        }

        @Override
        public int getCount()
        {
            return 2;
        }

        @Override
        public int getItemPosition(Object object)
        {
            return POSITION_NONE;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManufacturers = new ArrayList<>();
        boolean parsed = parseFile("muscle_cars.txt");

        if (parsed) {
            if (savedInstanceState != null) {
                mManufacturers = (ArrayList<Manufacturer>) savedInstanceState.getSerializable("Manufacturer");
                mModel = (Model) savedInstanceState.get("Model");
            }

            Adapter adapter = new Adapter(this, mManufacturers);
        }
        else
            Toast.makeText(this, "File Parsing Failed", Toast.LENGTH_SHORT).show();
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
        savedInstanceState.putSerializable("Manufacturer", mManufacturers);
        savedInstanceState.putSerializable("Model", mModel);
        super.onSaveInstanceState(savedInstanceState);
    }

    private boolean parseFile(String filename)
    {
        try
        {
            AssetManager manager = getResources().getAssets();
            InputStream stream = manager.open(filename);
            Scanner input = new Scanner(stream);

            Manufacturer manufacturer = new Manufacturer("temp");

            while(input.hasNextLine()) {
                String line = input.nextLine();
                if (line.compareTo("END") != 0) {
                    String[] tokenized = line.split(",");

                    if (tokenized.length == 1)
                    {
                        manufacturer = new Manufacturer(tokenized[0]);
                        mManufacturers.add(manufacturer);
                    }
                    else
                    {
                        int picID = getResources().getIdentifier(tokenized[0].toLowerCase().replaceAll(" ", ""), "drawable", getPackageName());
                        manufacturer.addModel(tokenized[0], tokenized[1], tokenized[2], picID);
                    }
                }
            }
            mModel = new Model("Gran Sport", "1965-1975", "401-455 cu in", getResources().getIdentifier("gransport", "drawable", getPackageName()));
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
        String model = mManufacturers.get(groupIndex).getModel(childIndex).getModel();

        Toast.makeText(this, make + " " + model, Toast.LENGTH_SHORT).show();

        return true;
    }

    void changePage(Model model)
    {
        mPager = findViewById(R.id.pager);
        mModel = model;
        mPagerAdapter.notifyDataSetChanged();
        mPager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed()
    {
        int page = mPager.getCurrentItem();

        if (page == 1)
            mPager.setCurrentItem(0);
        else
            super.onBackPressed();
    }

    public Model getSelectedModel()
    {
        return this.mModel;
    }
}

