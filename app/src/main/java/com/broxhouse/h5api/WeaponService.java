package com.broxhouse.h5api;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import android.util.Log;

import com.broxhouse.h5api.models.metadata.Map;
import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.metadata.Weapon;

public class WeaponService extends Service {

    private static final String TAG = "com.broxhouse.h5api";

    public WeaponService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand is being called");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                //This is what the service does
                try {
                    Medal[] medals = HaloApi.getMedals();
                    Log.i(TAG, "Getting medals");
                    for (int i = 0; i < medals.length; i++) {
                        MainActivity.dbHandler.addMedal(medals[i]);
                        Log.i(TAG, "adding medals");
                    }
                }catch (Exception e) {}
                try {
                    Weapon[] weapons = HaloApi.getWeapons();
                    Log.i(TAG, "Getting Weapons");
                    for (int i = 0; i < weapons.length; i++) {
                        MainActivity.dbHandler.addWeapon(weapons[i]);
                    }
                }catch (Exception e) {}
                try{
                    Map[] maps = HaloApi.getMaps();
                    Log.i(TAG, "Getting Maps");
                    for (int i = 0; i < maps.length; i++) {
                        MainActivity.dbHandler.addMap(maps[i]);
                    }
                    Log.i(TAG, maps[1].getName());
                } catch (Exception e) {}
            }
        };

        Thread brocksThread = new Thread(r);
        brocksThread.start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy is being called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
