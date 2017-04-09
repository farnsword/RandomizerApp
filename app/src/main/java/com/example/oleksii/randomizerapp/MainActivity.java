package com.example.oleksii.randomizerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinnerPosition;
    private Spinner spinnerAttack;
    private Spinner spinnerTechnic;
    private Spinner spinnerWeapon;

    private Button generateAllRandomButton;

    private CheckBox checkBoxPositionRandom;
    private CheckBox checkBoxAttackRandom;
    private CheckBox checkBoxTechnicRandom;
    private CheckBox checkBoxWeaponRandom;

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        spinnerPosition = initializeSpinner(R.id.spin_position, R.array.positions);
        checkBoxPositionRandom = (CheckBox) findViewById(R.id.chb_position_random);

        spinnerAttack = initializeSpinner(R.id.spin_attack, R.array.attack);
        checkBoxAttackRandom = (CheckBox) findViewById(R.id.chb_attack_random);

        spinnerTechnic = initializeSpinner(R.id.spin_technic, R.array.technic);
        checkBoxTechnicRandom = (CheckBox) findViewById(R.id.chb_technic_random);

        spinnerWeapon = initializeSpinner(R.id.spin_weapon, R.array.weapon);
        checkBoxWeaponRandom = (CheckBox) findViewById(R.id.chb_weapon_random);

        generateAllRandomButton = (Button) findViewById(R.id.btn_generate_all_random);
        generateAllRandomButton.setEnabled(false);
    }

    /**
     * Method to initialize specified spinner.
     *
     * @param spinnerID to initialize
     * @param values    array of values to set to spinner
     * @return initialized spinner
     */
    private Spinner initializeSpinner(int spinnerID, int values) {
        Spinner spinner = (Spinner) findViewById(spinnerID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                values, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);
        return spinner;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onCheckBoxClick(View view) {

        boolean isChecked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.chb_position_random:
                responseToCheckBoxStateChange(isChecked, spinnerPosition);
                break;
            case R.id.chb_attack_random:
                responseToCheckBoxStateChange(isChecked, spinnerAttack);
                break;
            case R.id.chb_technic_random:
                responseToCheckBoxStateChange(isChecked, spinnerTechnic);
                break;
            case R.id.chb_weapon_random:
                responseToCheckBoxStateChange(isChecked, spinnerWeapon);
                break;
        }
    }

    /**
     * Method enable or disable spinner and "Generate All Random" button depends on checkbox state.
     *
     * @param isChecked state of checkbox
     * @param sp        spinner to enable/disable
     */
    private void responseToCheckBoxStateChange(boolean isChecked, Spinner sp) {

        if (isChecked) {
            sp.setEnabled(false);
        } else {
            sp.setEnabled(true);
        }
        enableGenerateAllRandomButton();
    }

    /**
     * Method make Generate "All Random button enabled" or disabled depends on "Random"
     * checkboxes states.
     * If all checkboxes are checked button will be disabled because "Generate" button will make
     * same result as "Generate All Random".
     */
    private void enableGenerateAllRandomButton() {
        if (checkBoxPositionRandom.isChecked()
                && checkBoxAttackRandom.isChecked()
                && checkBoxTechnicRandom.isChecked()
                && checkBoxWeaponRandom.isChecked()) {
            if (generateAllRandomButton.isEnabled()) {
                generateAllRandomButton.setEnabled(false);
            }
        } else if (!generateAllRandomButton.isEnabled()) {
            generateAllRandomButton.setEnabled(true);
        }

    }

    public void onGenerateButtonClick(View view) {
        generateResult(false);
    }

    public void onGenerateAllRandomButton(View view) {
        generateResult(true);
    }

    private void generateResult(boolean allRandom) {
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle extras = new Bundle();

        gatherSpinnersValues(extras, allRandom);

        intent.putExtras(extras);

        startActivity(intent);
    }

    /**
     * Method for setting all spinners values to Bundle extras.
     *
     * @param extras bundle to set values to
     */
    private void gatherSpinnersValues(Bundle extras, boolean allRandom) {
        View[] checkBoxes = {findViewById(R.id.chb_position_random),
                findViewById(R.id.chb_attack_random),
                findViewById(R.id.chb_technic_random),
                findViewById(R.id.chb_weapon_random)};
        for (View view :
                checkBoxes) {
            getSpinnerValue(view, extras, allRandom);
        }
    }


    /**
     * Method for setting spinner value to Bundle extras.
     * If Random option is checked random spinner value will be taken.
     *
     * @param view   current (main) view
     * @param extras bundle to set value to
     */
    private void getSpinnerValue(View view, Bundle extras, boolean allRandom) {

        boolean isChecked = ((CheckBox) view).isChecked();
        int valuesArrayId;
        String value;
        Spinner spinner;
        String extraKey;

        switch (view.getId()) {
            case R.id.chb_position_random:
                valuesArrayId = R.array.positions;
                spinner = spinnerPosition;
                extraKey = "EXTRA_POSITION";
                break;
            case R.id.chb_attack_random:
                valuesArrayId = R.array.attack;
                spinner = spinnerAttack;
                extraKey = "EXTRA_ATTACK";
                break;
            case R.id.chb_technic_random:
                valuesArrayId = R.array.technic;
                spinner = spinnerTechnic;
                extraKey = "EXTRA_TECHNIC";
                break;
            case R.id.chb_weapon_random:
                valuesArrayId = R.array.weapon;
                spinner = spinnerWeapon;
                extraKey = "EXTRA_WEAPON";
                break;
            default:
                throw new IllegalArgumentException("Wrong checkbox ID");
        }

        if (isChecked || allRandom) {
            String[] allValues = getResources().getStringArray(valuesArrayId);
            value = allValues[new Random().nextInt(allValues.length)];
        } else {
            value = spinner.getSelectedItem().toString();
        }
        extras.putString(extraKey, value);
    }


}


//    public String getPositionFromDB(){
//        DBHandler dbHandler = new DBHandler(this);
//        try {
//            dbHandler.createDatabase();
//        } catch (IOException e) {
//            throw new Error("Unable to create database");
//        }
//
//        try {
//            dbHandler.openDataBase();
//        } catch (SQLException e) {
//            throw e;
//        }
//
//
//    }

