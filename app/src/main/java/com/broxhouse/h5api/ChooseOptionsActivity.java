package com.broxhouse.h5api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class ChooseOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_options);
//        matchesBtn.setOnClickListener(
//                {
//                    TextView mainText = (TextView) findViewById(R.id.mainText);
//                    EditText gtInputMain = (EditText) findViewById(R.id.gtInputMain);
//                    GAMERTAG = gtInputMain.getText().toString();
//                    mainText.setText(GAMERTAG);
//                }
//        );
    }

    public void getMatchStats(View view){
        Intent i = new Intent(this, GetMatchStats.class);
        startActivity(i);
    }

    public void getWeaponStats(View view){
        Intent i = new Intent(this, GetMatchStats.class);
        startActivity(i);
    }

    public void getMedalStats(View view){
        Intent i = new Intent(this, GetMedals.class);
        startActivity(i);
    }

    public void getWeaponStats(){

    }

    public void getMedalStats(){

    }
}
