package com.broxhouse.h5api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.stats.common.MedalAward;
import com.google.gson.Gson;

import org.json.JSONArray;


public class GetMedals extends AppCompatActivity {
//
//
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_medals);

        Log.i("api", MedalsIntentService.dbHandler.getMedalDetails() + "testing DB access");

        GetMedals.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView largeText = (TextView) findViewById(R.id.medalsLargeText);
                try {
                    largeText.setText(testMedalStats(gameType.CUSTOM));
                }catch (Exception e){}
            }
        });
    }

    public String testMedalStats(Enum gameType) throws Exception
    {
        JSONArray obj = HaloApi.getPlayerStatsJSON(gameType).getJSONArray("MedalAwards");
        String medalStats = "";
        String mostEarnedMedal = null;
        double average = 0;
        int highestMedalCount = 0;
        String var = obj.toString();
        System.out.println(var);
        double games = HaloApi.totalGames(gameType, MainActivity.GAMERTAG);
        games = (double)Math.round(games *1000d) / 1000d;
        Gson gson = new Gson();
        Medal[] medals = HaloApi.getMedals();
        MedalAward[] stats = gson.fromJson(var, MedalAward[].class);
        for (int row = 0; row < stats.length; row++)
        {
            stats[row].setName(HaloApi.getMedalName(stats[row].getMedalId(), medals));
        }
        medalStats+= ("\nShowing medal stats for " + MainActivity.GAMERTAG);
        for (int row = 0; row < stats.length; row++)
        {
            double medalCount = stats[row].getCount()/games;
            medalCount = (double)Math.round(medalCount *1000d) / 1000d;
            medalStats+= ("\n" + stats[row].getName() + ": " + stats[row].getCount() + " ||  Earned per game: " + medalCount);
        }
        for (int i = 0; i < stats.length; i++)
        {
            for (int k = i + 1; k < stats.length; k++)
            {
                if (stats[i].getCount() > stats[k].getCount() && stats[i].getCount() > highestMedalCount) {
                    highestMedalCount = stats[i].getCount();
                    mostEarnedMedal = stats[i].getName();
                }
            }
        }
        average = highestMedalCount / games;
        average = (double)Math.round(average *1000d) / 1000d;
        medalStats+= ("\nYour most earned medal is the " + mostEarnedMedal + " medal with a total of " + highestMedalCount + " and an average of " + average + " per game");
        return medalStats;
    }
}
