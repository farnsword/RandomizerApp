package com.example.oleksii.randomizerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String position = extras.getString("EXTRA_POSITION");
        String attack = extras.getString("EXTRA_ATTACK");
        String technic = extras.getString("EXTRA_TECHNIC");
        String weapon = extras.getString("EXTRA_WEAPON");

        TextView resultPosition = ((TextView) findViewById(R.id.txtv_result_position));
        TextView resultAttack = ((TextView) findViewById(R.id.txtv_result_attack));
        TextView resultTechnic = ((TextView) findViewById(R.id.txtv_result_technic));
        TextView resultWeapon = ((TextView) findViewById(R.id.txtv_result_weapon));

        resultPosition.setText(position);
        resultAttack.setText(attack);
        resultTechnic.setText(technic);
        resultWeapon.setText(weapon);
    }

}
