package com.broxhouse.h5api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.stats.common.MedalAward;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.w3c.dom.Text;


public class GetMedals extends AppCompatActivity {
//
//
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };

    DataService brockService;
    boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_medals);

        RelativeLayout rView = (RelativeLayout) findViewById(R.id.medalsLayout);

        TextView myText = new TextView(this);
        myText.setText(ChooseOptionsActivity.medalStats);

        rView.addView(myText);

//        GetMedals.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                TextView largeText = (TextView) findViewById(R.id.medalsLargeText);
//                try {
//                    medalStats = brockService.testMedalStats(gameType.ARENA);
//                }catch(Exception e){}
//                try {
//                    largeText.setText(medalStats);
//                }catch (Exception e){}
//            }
//        });

//        Log.i("api", MedalsIntentService.dbHandler.getMedalDetails() + "testing DB access");

//        GetMedals.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                TextView largeText = (TextView) findViewById(R.id.medalsLargeText);
//                try {
//                    largeText.setText();
//                }catch (Exception e){}
//            }
//        });
    }

    public void setText(View view){
        try{
            Toast toast = Toast.makeText(getApplicationContext(), ChooseOptionsActivity.medalStats, Toast.LENGTH_SHORT);
            toast.show();
            TextView textView = (TextView) findViewById(R.id.medalsLargeText);
            textView.setText(ChooseOptionsActivity.medalStats);
        }catch(Exception e){}
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataService.MyLocalBinder binder = (DataService.MyLocalBinder) service;
            brockService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
