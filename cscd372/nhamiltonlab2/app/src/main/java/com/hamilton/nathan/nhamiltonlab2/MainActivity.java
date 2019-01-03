package com.hamilton.nathan.nhamiltonlab2;

import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    DrawerLayout drawer_layout;
    ListView left_drawer;
    TextView row;
    ImageView imageDisplay;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = findViewById(R.id.drawer_layout);
        left_drawer = findViewById(R.id.left_drawer);
        String[] typedImages = getResources().getStringArray(R.array.photo_names);

        if(savedInstanceState != null) {
            imageDisplay = findViewById(R.id.displayImage);
            imageDisplay.setImageResource(savedInstanceState.getInt("position"));
            imageDisplay.setTag(savedInstanceState.getInt("position"));
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open, R.string.drawer_close)
        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                setTitle(getTitle());
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                setTitle("Select a Page");
                invalidateOptionsMenu();
            }
        };
        drawer_layout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ArrayAdapter<String> stringArray = new ArrayAdapter<>(this, R.layout.nav_list_row, R.id.row, typedImages);
        left_drawer.setAdapter(stringArray);
        row = findViewById(R.id.row);
        left_drawer.setOnItemClickListener(this);
        drawerToggle.syncState();
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
                    "Lab 2, Winter 2018, Nathan E Hamilton",
                    Toast.LENGTH_SHORT)
                    .show();
            return true;
        }
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TypedArray typedImages = getResources().obtainTypedArray(R.array.image_ids);
        int[] images = new int[typedImages.length()];
        for(int x = 0; x < typedImages.length(); x++)
            images[x] = typedImages.getResourceId(x, 0);

        typedImages.recycle();

        imageDisplay = findViewById(R.id.displayImage);
        imageDisplay.setImageResource(images[i]);
        imageDisplay.setTag(images[i]);
        left_drawer.setItemChecked(i, true);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if (drawer_layout.isDrawerOpen(R.id.drawer_layout))
            menu.findItem(R.id.action_about).setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        int background = (int) imageDisplay.getTag();
        savedInstanceState.putInt("position", background);
        super.onSaveInstanceState(savedInstanceState);
    }
}
