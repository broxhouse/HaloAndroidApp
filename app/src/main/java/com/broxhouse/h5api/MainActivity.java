package com.broxhouse.h5api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import com.broxhouse.h5api.DataService.MyLocalBinder;

import okhttp3.Response;


enum gameType {WARZONE, ARENA, CUSTOM}

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "brocksMessage";
    public static String GAMERTAG = null;
    public static DBHandler dbHandler;

//    DataService brockService;
//    boolean isBound = false;

//    private static final String PLAYER_UF = "That Ax Guy";
//    private static final String TOKEN = "293bb4a86da743bdb983b97efa5bb265";
//    private static final String BASE_URL = "https://www.haloapi.com/";
//    private static final String STATS_URL = "https://www.haloapi.com/stats/h5/";
//    private static final String META_URL = "https://www.haloapi.com/metadata/h5/metadata/";
//    private static final String CUSTOM_STATS = STATS_URL + "servicerecords/custom?players=%s";
//    private static final String ARENA_STATS = STATS_URL + "servicerecords/arena?players=%s";
//    private static final String WARZONE_STATS = STATS_URL + "servicerecords/warzone?players=%s";
//    private static final String META_WEAPONS = META_URL + "weapons";
//    private static final String META_MEDALS = META_URL + "medals";
//    private static final String META_PLAYLISTS = META_URL + "playlists";
//    private static final String META_MAPS = META_URL + "maps";
//    private static final String META_MAP_VARIANTS = META_URL + "map-variants/%s";
//    private static final String POST_GAME_CARNAGE = BASE_URL + "stats/h5/arena/matches/%s";
//    private static String responseString = null;

//    Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_main);
//        Intent i = new Intent(this, DataService.class);
//        bindService(i, connection, Context.BIND_AUTO_CREATE);
        Intent intent = new Intent(this, MedalsIntentService.class);
        startService(intent);

//        Intent i = new Intent(this, WeaponService.class);
//        startService(i);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.champion);

//        dbHandler  = new DBHandler(this, null, null, 1);
//
//        MainActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Medal[] medals = HaloApi.getMedals();
//                    Log.i(TAG, "Getting Medals");
//                    for (int i = 0; i < medals.length; i++) {
//                        dbHandler.addMedal(medals[i]);
//                    }
//                    Weapon[] weapons = HaloApi.getWeapons();
//                    Log.i(TAG, "Getting Weapons");
//                    for (int i = 0; i < weapons.length; i++) {
//                        dbHandler.addWeapon(weapons[i]);
//                    }
//                    Map[] maps = HaloApi.getMaps();
//                    Log.i(TAG, "Getting Maps");
//                    for (int i = 0; i < maps.length; i++) {
//                        dbHandler.addMap(maps[i]);
//                    }
//                } catch (Exception e) {
//                }
//            }
//        });






        //creating a button object that references the button we created in XML -- findViewById finds the widget by it's id
//        Button mainButton = (Button) findViewById(R.id.mainButton);

        //Tells button to wait to be clicked and then tells it what to do when it is clicked
//        mainButton.setOnClickListener(
//                new Button.OnClickListener(){
//                    public void onClick(View v){
//                        EditText gtInputMain = (EditText) findViewById(R.id.gtInputMain);
//                        GAMERTAG = gtInputMain.getText().toString();
//                    }
//                }
//        );
//
//        mainButton.setOnLongClickListener(
//                new Button.OnLongClickListener(){
//                    public boolean onLongClick(View v) {
//                        EditText gtInputMain = (EditText) findViewById(R.id.gtInputMain);
//                        GAMERTAG = gtInputMain.getText().toString();
//                        return true;
//                    }
//                }
//        );
//        //Creating interface programmatically
//
//        //Layout
//        RelativeLayout brocksLayout = new RelativeLayout(this);
//        brocksLayout.setBackgroundColor(Color.WHITE);
//
//        //Button
//        Button brocksButton = new Button(this);
//        brocksButton.setText("GO!");
//        brocksButton.setBackgroundColor(Color.BLACK);
//        brocksButton.setTextColor(Color.WHITE);
//        brocksButton.setId(1);
//
//        //Setting up Layout details for the button
//        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
//                //This sets the height and width to WRAP_CONTENT
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);
//
//        //Gamertag Input
//        EditText gamertag = new EditText(this);
//        gamertag.setId(2);
//
//        //Gets information about the app
//        Resources r = getResources();
//        //Converts DIP to pixels -- This is the unit that we're trying to convert, how many do you want to convert
//        //This will also makes the size of our items consistent across all devices
//        int pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
//
//        gamertag.setWidth(pixels);
//
//        RelativeLayout.LayoutParams gamertagDetails = new RelativeLayout.LayoutParams(
//                //This sets the height and width to WRAP_CONTENT
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        gamertagDetails.addRule(RelativeLayout.ABOVE, brocksButton.getId());
//        gamertagDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        gamertagDetails.setMargins(0, 0, 0, 50);
//
//        //adding Button to layout
//        brocksLayout.addView(brocksButton, buttonDetails);
//        brocksLayout.addView(gamertag, gamertagDetails);
//
//        //Setting this activity's content/display to this view
//        setContentView(brocksLayout);
    }

    public void onClick(View view){
        EditText gtInputMain = (EditText) findViewById(R.id.gtInputMain);
        GAMERTAG = gtInputMain.getText().toString();
        Intent i = new Intent(this, ChooseOptionsActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyLocalBinder binder = (MyLocalBinder) service;
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
