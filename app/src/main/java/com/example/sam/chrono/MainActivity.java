package com.example.sam.chrono;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button[] pitons;
    Chronometer focus;
    private long pause;
    ListView listeSauvegarder;

    private String test;
    private String[] mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        // Initialiser les bouttons
        pitons = new Button[9];

        pitons[0] = (Button) findViewById(R.id.btnMinutesPlus);
        pitons[1] = (Button) findViewById(R.id.btnMinutesMoins);
        pitons[2] = (Button) findViewById(R.id.btnSecondesPlus);
        pitons[3] = (Button) findViewById(R.id.btnSecondesMoins);
        pitons[4] = (Button) findViewById(R.id.btndebut);
        pitons[5] = (Button) findViewById(R.id.btnarret);
        pitons[6] = (Button) findViewById(R.id.btnReinitialiser);
        pitons[7] = (Button) findViewById(R.id.tglTemps);
        pitons[8] = (Button) findViewById(R.id.btnSauvegarder);

        // Initialiser le timer
        focus = (Chronometer) findViewById(R.id.chrTemps);

        // Permet de pouvoir partir le chronomètre
        pitons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pitons[4].setText("Continuer");

                if (pause > 1){
                    focus.setBase(focus.getBase() + SystemClock.elapsedRealtime() - pause);
                    focus.start();
                    pitons[0].setEnabled(false);
                    pitons[1].setEnabled(false);
                    pitons[2].setEnabled(false);
                    pitons[3].setEnabled(false);
                    pitons[4].setEnabled(false);
                    pitons[5].setEnabled(true);
                    pitons[6].setEnabled(true);
                    pitons[7].setEnabled(true);
                    pitons[8].setEnabled(true);
                }
                else {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.start();
                    pitons[0].setEnabled(false);
                    pitons[1].setEnabled(false);
                    pitons[2].setEnabled(false);
                    pitons[3].setEnabled(false);
                    pitons[4].setEnabled(false);
                    pitons[5].setEnabled(true);
                    pitons[6].setEnabled(true);
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
                pitons[0].setEnabled(false);
                pitons[1].setEnabled(false);
                pitons[2].setEnabled(false);
                pitons[3].setEnabled(false);
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
                pitons[7].setEnabled(false);
                pitons[8].setEnabled(false);
            }
        });

        // Permet de pouvoir arrêter le chronomètre
        pitons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pitons[0].setEnabled(true);
                pitons[1].setEnabled(true);
                pitons[2].setEnabled(true);
                pitons[3].setEnabled(true);
            }
        });

        // Permet de pouvoir sauvergarder le temps afficher
        pitons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pitons[0].setEnabled(false);
                pitons[1].setEnabled(false);
                pitons[2].setEnabled(false);
                pitons[3].setEnabled(false);

                for (int i = 0;i <= 3;i++){
                    mStrings[i] = (test = Objects.toString(pause));
                    listeSauvegarder = (ListView) findViewById(R.id.listSauvegarder);

                    //Création de l'adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout., mStrings);

                    //Récupération du ListView présent dans notre IHM
                    ListView list = (ListView)findViewById(R.id.listSauvegarder);

                    //On passe nos données au composant ListView
                    list.setAdapter(adapter);
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
