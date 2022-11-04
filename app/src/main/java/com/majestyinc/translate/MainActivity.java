package com.majestyinc.translate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    fragmentCalculator fragmentCalc = new fragmentCalculator();

    FragmentConstruct construct = new FragmentConstruct();

    FragmentStats stats = new FragmentStats();

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bundle = getIntent().getExtras();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNav);

        if(bundle!=null) {
            if (bundle.getString("fragmentc") != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,construct).commitNow();
                bottomNavigationView.setSelectedItemId(R.id.construct);
            }

            else if(bundle.getString("fragments") != null)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,stats).commitNow();
                bottomNavigationView.setSelectedItemId(R.id.arch);
            }
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragmentCalc).commitNow();
            bottomNavigationView.setSelectedItemId(R.id.cal);

        }







    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment Selected = new Fragment();
            switch (item.getItemId()) {


                case R.id.cal:
                    Selected = new fragmentCalculator();
                    break;

                case R.id.arch:
                    Selected = new FragmentStats();
                    break;


                case R.id.construct:
                    Selected = new FragmentConstruct();
                    break;

                case R.id.info:
                    Selected = new FragmentInfo();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,Selected).commitNow();


            return true;

        }
    };


}

