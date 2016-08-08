package com.broxhouse.h5api;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.broxhouse.h5api.models.metadata.Map;
import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.metadata.Weapon;

public class MedalsIntentService extends IntentService {

    private static final String TAG = "test";
    public static DBHandler dbHandler;

    public MedalsIntentService(){
        super("Brock's Intent Service");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        dbHandler  = new DBHandler(this, null, null, 1);
        //This is what the service does
        try {
            Medal[] medals = HaloApi.getMedals();
            Log.i(TAG, "Getting medals");
            for (int i = 0; i < medals.length; i++) {
                dbHandler.addMedal(medals[i]);
                Log.i(TAG, "adding medals");
            }
        }catch (Exception e) {}
        try {
            Weapon[] weapons = HaloApi.getWeapons();
            Log.i(TAG, "Getting Weapons");
            for (int i = 0; i < weapons.length; i++) {
                dbHandler.addWeapon(weapons[i]);
            }
        }catch (Exception e) {}
        try{
            Map[] maps = HaloApi.getMaps();
            Log.i(TAG, "Getting Maps");
            for (int i = 0; i < maps.length; i++) {
                dbHandler.addMap(maps[i]);
            }
            Log.i(TAG, maps[1].getName());
        } catch (Exception e) {}

    }
}
