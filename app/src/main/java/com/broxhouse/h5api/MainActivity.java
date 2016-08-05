package com.broxhouse.h5api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.content.res.Resources;
import android.util.TypedValue;
import android.util.Log;

import org.w3c.dom.Text;


enum gameType {WARZONE, ARENA, CUSTOM}

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "brocksMessage";
    public static String GAMERTAG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.champion);

        setContentView(R.layout.activity_main);

        //creating a button object that references the button we created in XML -- findViewById finds the widget by it's id
        Button mainButton = (Button) findViewById(R.id.mainButton);

        //Tells button to wait to be clicked and then tells it what to do when it is clicked
        mainButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView mainText = (TextView) findViewById(R.id.mainText);
                        EditText gtInputMain = (EditText) findViewById(R.id.gtInputMain);
                        GAMERTAG = gtInputMain.getText().toString();
                        mainText.setText(GAMERTAG);
                    }
                }
        );

        mainButton.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v) {
                        TextView mainText = (TextView) findViewById(R.id.mainText);
                        EditText gtInputMain = (EditText) findViewById(R.id.gtInputMain);
                        GAMERTAG = gtInputMain.getText().toString();
                        mainText.setText(GAMERTAG);
                        return true;
                    }
                }
        );
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
}
