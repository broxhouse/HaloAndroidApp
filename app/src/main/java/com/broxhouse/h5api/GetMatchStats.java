package com.broxhouse.h5api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.broxhouse.h5api.models.metadata.Map;
import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.stats.common.MedalAward;
import com.broxhouse.h5api.models.stats.matches.Match;
import com.broxhouse.h5api.models.stats.reports.BaseCarnageReport;
import com.broxhouse.h5api.models.stats.reports.SpartanRankedPlayerStats;
import com.google.gson.Gson;

import org.json.JSONObject;

import static com.broxhouse.h5api.gameType.ARENA;

public class GetMatchStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_match_stats);

        TextView largeText = (TextView) findViewById(R.id.matchLargeText);
        try {
            largeText.setText(testPlayerMatches());
        }catch (Exception e){

        }
    }

    public String testPlayerMatches() throws Exception
    {
        double totalGames = HaloApi.totalGames(ARENA, MainActivity.GAMERTAG);
        String playerMatches = MainActivity.GAMERTAG + "has played a total of " + totalGames;
        Medal[] medals = HaloApi.getMedals();
        Map[] maps = HaloApi.getMaps();
        double kdRatio = 0;
        double averageKills = 0;
        double averageDeaths = 0;
        String bestMatchID = null;
        int positiveCount = 0;
        Gson gson = new Gson();
        Match[] matches = HaloApi.getMatches();
        for (int i = 0; i < matches.length; i++){
            double totalKills = matches[i].getPlayers().get(0).getTotalKills();
            double totalDeaths = matches[i].getPlayers().get(0).getTotalDeaths();
            double currentKD = totalKills / totalDeaths;
            String matchID = matches[i].getId().getMatchId();
            if (totalDeaths == 0){
                totalDeaths = 1;
            }
            averageKills += totalKills;
            averageDeaths += totalDeaths;
            if (! HaloApi.getMapName(matches[i].getMapId(), maps).equalsIgnoreCase("Breakout Arena")) {
                if (currentKD > kdRatio && totalKills > 4) {
                    kdRatio = currentKD;
                    bestMatchID = matchID;
                }
            }
            if (currentKD >= 1){
                positiveCount++;
            }
        }
        kdRatio = (double)Math.round(kdRatio *1000d) / 1000d;
//        System.out.println("Number of iterations: " + iterations);
        float percentagePositive = (float) ((positiveCount * 100.0f) / totalGames);
        playerMatches += ("\nYour total number of kills: " + averageKills + " Your total number of deaths: " + averageDeaths) + ("\nYour average K/D spread is: " + averageKills / averageDeaths) +
        ("\nYou've had a positive K/D spread " + positiveCount + " times. That's " + percentagePositive + "% of your games!") + ("\nYour best Kill/Death ratio in any Arena game is: " + kdRatio);

        JSONObject obj2 = new JSONObject(HaloApi.postGameCarnage(bestMatchID));
        BaseCarnageReport carnageReport = gson.fromJson(obj2.toString(), BaseCarnageReport.class);
        SpartanRankedPlayerStats[] playerStats = gson.fromJson(obj2.getJSONArray("PlayerStats").toString(), SpartanRankedPlayerStats[].class);
        String mapName = HaloApi.getMapVariant(carnageReport.getMapVariantId()).getName();
        playerMatches += ("Your best game was played on map: " + mapName) + ("\nHere are the medals you earned in that game: \n");
        for (int i = 0; i < playerStats.length; i++){
            for (MedalAward medal : playerStats[i].getMedalAwards()){
                for (int k = 0; k < medals.length; k++){
                    if (medal.getMedalId() == medals[k].getId()){
                        medal.setName(medals[k].getName());
                    }
                }
                if (playerStats[i].getPlayer().getGamertag().equalsIgnoreCase(MainActivity.GAMERTAG)){
                    playerMatches += ("\n" + medal.getName() + " x " + medal.getCount());
                }
            }
        }
        return playerMatches;
    }
}
