package com.example.sam.chrono;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

import java.util.Objects;

public class ChronoSuite extends AppCompatActivity {

    Button[] pitons;
    Chronometer focus;
    private long pause;
    ListView listeSauvegarder;
    private long bloque = 0;

    private String test;
    private String[] mStrings;

    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono_suite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        pitons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pitons[4].setText("Continuer");

                if (pause > 1){
                    focus.setBase(focus.getBase() + SystemClock.elapsedRealtime() - pause);
                    focus.start();
                    pitons[4].setEnabled(false);
                    pitons[5].setEnabled(true);
                    pitons[6].setEnabled(true);
                    pitons[7].setEnabled(true);
                    pitons[7].setText("off");
                    pitons[8].setEnabled(true);
                }
                else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                    pitons[4].setEnabled(false);
                    pitons[5].setEnabled(true);
                    pitons[6].setEnabled(true);
                    pitons[7].setText("off");
                    pitons[7].setEnabled(true);
                    pitons[8].setEnabled(true);
                }
            }
        });

        // Permet de pouvoir arrêter le chronomètre
        pitons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause = SystemClock.elapsedRealtime();
                focus.stop();
                pitons[4].setEnabled(true);
                pitons[5].setEnabled(false);
            }
        });

        // Permet de pouvoir réinitialiser
        pitons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pitons[4].setText("Début");
                pause = 0;
                bloque = 0;
                focus.setBase(SystemClock.elapsedRealtime());
                focus.clearFocus();
                focus.stop();
                pitons[0].setEnabled(false);
                pitons[1].setEnabled(false);
                pitons[2].setEnabled(false);
                pitons[3].setEnabled(false);
                pitons[4].setEnabled(true);
                pitons[5].setEnabled(false);
                pitons[6].setEnabled(false);
                pitons[7].setText("off");
                pitons[7].setEnabled(false);
                pitons[8].setEnabled(false);
            }
        });

        // Permet de pouvoir arrêter le chronomètre
        pitons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bloque == 0) {
                    pitons[0].setEnabled(true);
                    pitons[1].setEnabled(true);
                    pitons[2].setEnabled(true);
                    pitons[3].setEnabled(true);
                    bloque = 1;
                } else if (bloque == 1){
                    pitons[0].setEnabled(false);
                    pitons[1].setEnabled(false);
                    pitons[2].setEnabled(false);
                    pitons[3].setEnabled(false);
                    bloque = 0;
                }
            }
        });

        // Permet de pouvoir sauvergarder le temps afficher
        pitons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i <= 3;i++){
                    mStrings[i] = (test = Objects.toString(pause));
                    listeSauvegarder = (ListView) findViewById(R.id.listSauvegarder);
                    //listeSauvegarder.get
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}