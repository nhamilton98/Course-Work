package com.hamilton.nathan.nhamiltonfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GameUpdate, MediaPlayer.OnCompletionListener
{
    private TextView mScore;
    private TextView mStart;
    private TextView mLevel;
    private Maze mMaze;

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMaze = findViewById(R.id.maze);
        ImageView up = findViewById(R.id.up_button);
        ImageView down = findViewById(R.id.down_button);
        ImageView left = findViewById(R.id.left_button);
        ImageView right = findViewById(R.id.right_button);

        mStart = findViewById(R.id.instruction);
        mScore = findViewById(R.id.cakes_remaining);
        mLevel = findViewById(R.id.level);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) / 2, 0);

        up.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View view)
           {
               mMaze.changeDirection(1);
           }
        });

        left.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mMaze.changeDirection(2);
            }
        });

        down.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mMaze.changeDirection(3);
            }
        });

        right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mMaze.changeDirection(4);
            }
        });

        mStart.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View view)
           {
               if (mMaze.isGameOver())
               {
                   mLevel.setText("Level 1");
                   mMaze.reset();
               }
               else
               {
                   if (mMaze.isRunning())
                   {
                       mMaze.pause();
                       toast();
                   }
                   else
                       mMaze.go();
               }
           }
        });

        mStart.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                mMaze.cheat();
                return true;
            }
        });
    }

    @Override
    public void setCakeCount(int cakes)
    {
        mScore.setText(cakes + "Cakes Remaining");
    }

    @Override
    public void nextLevel(int level)
    {
        Toast.makeText(this, "Starting Level 2", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLevel(int level)
    {
        mLevel.setText("Level " + level);
    }

    @Override
    public void win()
    {
        Toast.makeText(this, "Congratulations! You win!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lose()
    {
        Toast.makeText(this, "Game Over.", Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public void sound(String sound)
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }

        if (sound.equals("eatCake"))
        {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.eatCake);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setVolume(0.5f, 0.5f);
            mMediaPlayer.start();
        }
        else if (sound.equals("nextLevel"))
        {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nextLevel);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setVolume(0.5f, 0.5f);
            mMediaPlayer.start();
        }
        else if (sound.equals("lose"))
        {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lose);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setVolume(0.5f, 0.5f);
            mMediaPlayer.start();
        }
        else
        {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.win);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setVolume(0.5f, 0.5f);
            mMediaPlayer.start();
        }
    }*/

    @Override
    public void onCompletion(MediaPlayer mediaPlayer)
    {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
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

        if (id == R.id.action_about)
        {
            Toast.makeText(this, "Final Project, Winter 2018, Nathan E Hamilton", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_settings)
        {
            mMaze.pause();
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mMaze.setGhosts(sharedPreferences.getInt("ghosts_per_level", 2));
        mMaze.setNewGhosts(sharedPreferences.getInt("new_ghosts", 2));
    }

    private void toast()
    {
        Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();
    }
}
