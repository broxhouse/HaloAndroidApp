package com.broxhouse.h5api;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import com.broxhouse.h5api.models.metadata.Map;
import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.metadata.Weapon;

public class ChooseOptionsActivity extends AppCompatActivity {
    public static String medalStats = null;
//    DataService brockService;
//    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_options);
//        Intent i = new Intent(this, DataService.class);
//        bindService(i, connection, Context.BIND_AUTO_CREATE);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                //This is what the service does
                try {
                    medalStats = DataService.listMedals();
                } catch (Exception e) {
                }
            }
        };

        Thread brocksThread = new Thread(r);
        brocksThread.start();
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
        try{
            Intent i = new Intent(this, GetMedals.class);
            startActivity(i);
        }catch (Exception e){}
    }

    public void getWeaponStats(){

    }

    public void getMedalStats(){

    }

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            DataService.MyLocalBinder binder = (DataService.MyLocalBinder) service;
//            brockService = binder.getService();
//            isBound = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            isBound = false;
//        }
//    };
}
