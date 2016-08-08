package com.broxhouse.h5api;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.broxhouse.h5api.models.metadata.Map;
import com.broxhouse.h5api.models.metadata.MapVariant;
import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.metadata.Playlist;
import com.broxhouse.h5api.models.metadata.Weapon;
import com.broxhouse.h5api.models.stats.common.MedalAward;
import com.broxhouse.h5api.models.stats.common.WeaponStats;
import com.broxhouse.h5api.models.stats.matches.Match;
import com.broxhouse.h5api.models.stats.reports.BaseCarnageReport;
import com.broxhouse.h5api.models.stats.reports.BaseStats;
import com.broxhouse.h5api.models.stats.reports.SpartanRankedPlayerStats;
import com.broxhouse.h5api.models.stats.servicerecords.ArenaStat;
import com.broxhouse.h5api.models.stats.servicerecords.BaseServiceRecordResult;
import com.google.gson.Gson;

import org.codehaus.groovy.tools.groovydoc.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.broxhouse.h5api.gameType.ARENA;
import static com.broxhouse.h5api.gameType.CUSTOM;
import static com.broxhouse.h5api.gameType.WARZONE;

public class DataService extends Service {

    private static final String PLAYER_UF = MainActivity.GAMERTAG;
    private static final String PLAYER = formatString(PLAYER_UF);
    private static final String TOKEN = "293bb4a86da743bdb983b97efa5bb265";
    private static final String BASE_URL = "https://www.haloapi.com/";
    private static final String STATS_URL = "https://www.haloapi.com/stats/h5/";
    private static final String META_URL = "https://www.haloapi.com/metadata/h5/metadata/";
    private static final String PLAYER_MATCHES = STATS_URL + "players/" + PLAYER + "/matches";
    private static final String CUSTOM_STATS = STATS_URL + "servicerecords/custom?players=%s";
    private static final String ARENA_STATS = STATS_URL + "servicerecords/arena?players=%s";
    private static final String WARZONE_STATS = STATS_URL + "servicerecords/warzone?players=%s";
    private static final String META_WEAPONS = META_URL + "weapons";
    private static final String META_MEDALS = META_URL + "medals";
    private static final String META_PLAYLISTS = META_URL + "playlists";
    private static final String META_MAPS = META_URL + "maps";
    private static final String META_MAP_VARIANTS = META_URL + "map-variants/%s";
    private static final String POST_GAME_CARNAGE = BASE_URL + "stats/h5/arena/matches/%s";
    private static String responseString = null;
    private static String TAG = "test";
    private final IBinder binder = new MyLocalBinder();

    public DataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public static String formatString(String string)
    {
        string = string.replaceAll("\\s+", "%20");
        return string;
    }

    public static String postGameCarnage(String matchID) throws Exception
    {
        return api(String.format(POST_GAME_CARNAGE, matchID));
    }

    public static String playerMatches(String gt, String modes, int start, int count) throws Exception {
        String pURL = PLAYER_MATCHES;
        pURL = pURL +"?modes=" + modes + "&";
        pURL = pURL + "start=" + start + "&";
        pURL = pURL + "count=" + count;

        return api(pURL);
    }

    public static String warzoneMatches(String gt) throws Exception{
        return api(String.format(WARZONE_STATS, gt));
    }

    public static String customMatches(String gt) throws Exception
    {
        return api(String.format(CUSTOM_STATS, gt));
    }

    public static String arenaStats(String gt) throws Exception
    {
        return api(String.format(ARENA_STATS, gt));
    }

    public static String listMedals() throws Exception
    {
        String medals = api(META_MEDALS);
        Log.i(TAG, "listMedals() method");
        return medals;
    }

    public static String listWeapons() throws Exception
    {
        return api(META_WEAPONS);
    }

    public static String listPlaylists() throws Exception
    {
        return api(META_PLAYLISTS);
    }

    public static String listMaps() throws Exception
    {
        return api(META_MAPS);
    }

    public static String listMapVariants(String mapVariantID) throws Exception
    {
        return api(String.format(META_MAP_VARIANTS, mapVariantID));
    }

    private static String api(final String url) throws Exception
    {
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .header("Ocp-Apim-Subscription-Key", TOKEN)
                        .url(url)
                        .build();

                try{
                    Response response = client.newCall(request).execute();
                    Log.i("api", response.body().string());
                    Log.i("api", "hit the api method");
                    responseString = response.body().string();
                    return response.body().string();
                }catch (Exception e){}

                return null;
            }
        }.execute();
        return responseString;
    }


    public static void testJSONWeapons() throws Exception
    {
        Gson gson = new Gson();
        Weapon[] data = gson.fromJson(listWeapons(), Weapon[].class);
        System.out.println(Arrays.toString(data));
        for (int i = 0; i < data.length; i++)
        {
            System.out.println(data[i].getName() + " ID:  " + data[i].getId());
        }
    }

    public static void testJSONMedals() throws Exception
    {
        Gson gson = new Gson();
        Medal[] data = gson.fromJson(listMedals(), Medal[].class);
        System.out.println(Arrays.toString(data));
        for (int i = 0; i < data.length; i++)
        {
            System.out.println(data[i].getName() + " ID:  " + data[i].getId());
        }
    }

    public static Medal[] getMedals() throws Exception
    {
        Gson gson = new Gson();
        String medalData = listMedals();
        Medal[] medals = gson.fromJson(medalData, Medal[].class);
        Log.i("test", "hit getMedals() method");
        return medals;
    }

    public static Map[] getMaps() throws Exception
    {
        Gson gson = new Gson();
        String mapData = listMaps();
        Map[] maps = gson.fromJson(mapData, Map[].class);
        return maps;
    }

    public static Weapon[] getWeapons() throws Exception
    {
        Gson gson = new Gson();
        String weaponData = listWeapons();
        Weapon[] weapons = gson.fromJson(weaponData, Weapon[].class);
        return weapons;
    }

    public static Match[] getMatches() throws Exception
    {
        JSONObject obj = null;
        String var = null;
        String var2 = "";
        Integer start = 0;
        double totalGames = totalGames(ARENA, PLAYER_UF);
        TimeUnit.SECONDS.sleep(8);
        double iterations = totalGames / 25;
        Gson gson = new Gson();
        for (int i = 1; i < iterations; i++)
        {
            obj = new JSONObject(playerMatches(PLAYER, "arena", start, 25));
            JSONArray obj2 = obj.getJSONArray("Results");
            var = obj2.toString();
            if (i < iterations)
            {
                var = var.substring(1, var.length() - 1);
                var2 = var2.concat(var);
                var2 = var2 + ",";
            }
            else
            {
                var2 = var2.concat(var);
            }
            start = start + obj.getInt("Count");
            if (i % 10 == 0)
            {
                TimeUnit.SECONDS.sleep(10);
            }
        }
        var2 = var2.substring(0, var2.length() - 1);
        var2 = "[" + var2 + "]";
        Match[] matches = gson.fromJson(var2, Match[].class);
        return matches;
    }

    public static MapVariant getMapVariant(String mapVariantID) throws Exception
    {
        Gson gson = new Gson();
        String mapData = listMapVariants(mapVariantID);
        MapVariant mapVariant = gson.fromJson(mapData, MapVariant.class);
        return mapVariant;
    }

    public static String getMedalName(long medalID, Medal[] medals) throws Exception
    {
        String medalName = null;
        for (int i = 0; i < medals.length; i++)
        {
            if (medals[i].getId() == medalID)
            {
                medalName = medals[i].getName();
            }

        }
        return medalName;
    }

    public static String getMedalDescription(long medalID, Medal[] medals) throws Exception
    {
        String medalDesc = null;
        for (int i = 0; i < medals.length; i++)
        {
            if (medals[i].getId() == medalID)
            {
                medalDesc = medals[i].getDescription();
            }

        }
        return medalDesc;
    }

    public static long getMedalID(String medalName, Medal[] medals)
    {
        long medalID = 0;
        for (int i = 0; i < medals.length; i++)
        {
            if (medals[i].getName().equalsIgnoreCase(medalName))
            {
                medalID = medals[i].getId();
            }
        }
        return medalID;
    }

    public static String getWeaponName(long weaponID, Weapon[] weapons) throws Exception
    {
        String weaponName = null;
        for (int i = 0; i < weapons.length; i++)
        {
            if (weapons[i].getId() == weaponID)
            {
                weaponName = weapons[i].getName();
            }

        }
        return weaponName;
    }

    public static String getMapName(String id, Map[] maps) throws Exception
    {
        String mapName = null;
        for (int i = 0; i < maps.length; i++)
        {
            if (maps[i].getId().equalsIgnoreCase(id))
            {
                mapName = maps[i].getName();
            }
        }
        return mapName;
    }


    public static JSONObject getPlayerStatsJSON (Enum gameType) throws Exception
    {
        JSONObject obj = null;
        if (gameType == WARZONE)
        {
            obj = new JSONObject(warzoneMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("WarzoneStat");
        }
        if (gameType == ARENA)
        {
            obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats");
        }
        if (gameType == CUSTOM)
        {
            obj = new JSONObject(customMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("CustomStats");
        }

        return obj;
    }

    public static JSONObject getPlayerStatsJSON (Enum gameType, String gt) throws Exception
    {
        JSONObject obj = null;
        if (gameType == WARZONE)
        {
            obj = new JSONObject(warzoneMatches(gt)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("WarzoneStat");
        }
        if (gameType == ARENA)
        {
            obj = new JSONObject(arenaStats(gt)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats");
        }
        if (gameType == CUSTOM)
        {
            obj = new JSONObject(customMatches(gt)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("CustomStats");
        }

        return obj;
    }

    public static String testMedalStats(Enum gameType) throws Exception
    {
        Log.i(TAG, "testing medal stats");
        JSONArray obj = HaloApi.getPlayerStatsJSON(gameType).getJSONArray("MedalAwards");
        String medalStats = "test";
        String mostEarnedMedal = null;
        double average = 0;
        int highestMedalCount = 0;
        String var = obj.toString();
        System.out.println(var);
        Log.i(TAG, "getting total games");
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
//        for (int row = 0; row < stats.length; row++)
//        {
//            double medalCount = stats[row].getCount()/games;
//            medalCount = (double)Math.round(medalCount *1000d) / 1000d;
//            medalStats+= ("\n" + stats[row].getName() + ": " + stats[row].getCount() + " ||  Earned per game: " + medalCount);
//        }
//        for (int i = 0; i < stats.length; i++)
//        {
//            for (int k = i + 1; k < stats.length; k++)
//            {
//                if (stats[i].getCount() > stats[k].getCount() && stats[i].getCount() > highestMedalCount) {
//                    highestMedalCount = stats[i].getCount();
//                    mostEarnedMedal = stats[i].getName();
//                }
//            }
//        }
//        average = highestMedalCount / games;
//        average = (double)Math.round(average *1000d) / 1000d;
//        medalStats+= ("\nYour most earned medal is the " + mostEarnedMedal + " medal with a total of " + highestMedalCount + " and an average of " + average + " per game");
        return medalStats;
    }

    public static void testPlayerStats() throws Exception
    {
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(arenaStats(PLAYER), ArenaStat.class);
        System.out.println(stats.getEnemyKills());
    }

    public static double totalGames(Enum gameType, String gt) throws Exception
    {
        JSONObject obj = getPlayerStatsJSON(gameType, formatString(gt));
        String gType = gameType.toString();
        String var = obj.toString();
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(var, BaseStats.class);
        System.out.println(gt + " has played a total of " + stats.getTotalGamesCompleted() + " " + gType + " games : ");
        return stats.getTotalGamesCompleted();
    }

    public static double totalGamesAll() throws Exception
    {
        double totalGames = totalGames(CUSTOM, PLAYER) + totalGames(WARZONE, PLAYER) + totalGames(ARENA, PLAYER);
        return totalGames;
    }

    public static String totalWins(Enum gameType) throws Exception
    {
        JSONObject obj = getPlayerStatsJSON(gameType);
        String gType = gameType.toString();
        String var = obj.toString();
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(var, BaseStats.class);
        System.out.println("Total " + gType + " games won: " + stats.getTotalGamesWon() + " Total losses: " + stats.getTotalGamesLost());
        String winLoss = ("Total number of Wins: " + stats.getTotalGamesWon() + " Total losses: " + stats.getTotalGamesLost());
        return winLoss;
    }

    public static void testWeaponKills(Enum gameType) throws Exception
    {
        JSONArray obj = getPlayerStatsJSON(gameType).getJSONArray("WeaponStats");
        String favWeapon = null;
        int totalKills = 0;
        Gson gson = new Gson();
        String var = obj.toString();
        Weapon[] weapons = getWeapons();
        WeaponStats[] stats = gson.fromJson(var, WeaponStats[].class);
        double games = totalGames(gameType, PLAYER);
        String wl = totalWins(gameType);
        games = (double)Math.round(games *1000d) / 1000d;

        for (int row = 0; row < stats.length; row++)
        {
            stats[row].setName(getWeaponName(stats[row].getWeaponId().getStockId(), weapons));
        }
        for (int i = 0; i < stats.length; i++)
        {
            for (int k = i + 1; k < stats.length; k++)
            {
                if (stats[i].getTotalKills() > stats[k].getTotalKills() && stats[i].getTotalKills() > totalKills) {
                    totalKills = stats[i].getTotalKills();
                    favWeapon = stats[i].getName();
                }
            }
        }
//        System.out.println("Total kills per weapon for " + PLAYER_UF);
//        for (int i = 0; i < stats.length; i++)
//        {
//            double killCount = stats[i].getTotalKills()/games;
//            killCount = (double)Math.round(killCount * 1000d) / 1000d;
//            System.out.println(stats[i].getName() + ": " + stats[i].getTotalKills() + "  ||  Avg kills per game: " + killCount);
//        }
        System.out.println("Your favorite weapon is the " + favWeapon + " with a kill total of: " + totalKills);
    }

    public static void testBestMap() throws Exception
    {
        Match matches[] = getMatches();

    }


    public static void testBaseStats(Enum gameType) throws Exception
    {
        JSONObject obj = getPlayerStatsJSON(gameType);
        String gType = gameType.toString();
        String var = obj.toString();
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(var, BaseStats.class);
        System.out.println("Total " + gType + " games played: " + stats.getTotalKills());

        List<WeaponStats> weaponList = stats.getWeapons();
        for(WeaponStats en : weaponList)
        {
            System.out.println(en.getName() + " " + en.getTotalKills());
        }
    }

    public static void testBaseResults() throws Exception
    {
        JSONObject obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result");
        String var = obj.toString();
        Gson gson = new Gson();
        BaseServiceRecordResult stats = gson.fromJson(var, BaseServiceRecordResult.class);
        List<ArenaStat.ArenaPlaylistStats> arenaStats = stats.getArenaStat().getArenaPlaylistStats();
        for (ArenaStat.ArenaPlaylistStats sta : arenaStats)
        {
            System.out.println(sta.getPlaylistId() + " " + sta.getTotalGamesCompleted());
        }
    }

    public static void testActivePlaylists() throws Exception
    {
        JSONArray obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats").getJSONArray("ArenaPlaylistStats");
        String var = obj.toString();
        System.out.println(var);
        Gson gson = new Gson();
        String playlistData = listPlaylists();
        ArenaStat.ArenaPlaylistStats[] playlistStats = gson.fromJson(var, ArenaStat.ArenaPlaylistStats[].class);
        Playlist[] playlists = gson.fromJson(playlistData, Playlist[].class);
        for (int i = 0; i < playlistStats.length; i++)
        {
            for (int k = 0; k < playlists.length; k++)
            {
                if (playlists[k].isActive() == true)
                {
                    System.out.println(playlists[k].getName());
                }
                if (playlists[k].getId().equalsIgnoreCase(playlistStats[i].getPlaylistId()))
                {
                    playlistStats[i].setName(playlists[k].getName());
                }
            }
        }

//        for (int i = 0; i < playlistStats.length; i++)
//        {
//            System.out.println(playlistStats[i].getName() + " " + " " + playlistStats[i].getTotalKills());
//        }
    }

    public static void comparePlayers(String player1, String player2) throws Exception
    {
        JSONArray obj = getPlayerStatsJSON(ARENA, formatString(player1)).getJSONArray("MedalAwards");
        JSONArray obj2 = getPlayerStatsJSON(ARENA, formatString(player2)).getJSONArray("MedalAwards");
        Gson gson = new Gson();
        String var1 = obj.toString();
        String var2 = obj2.toString();
        MedalAward[] p1Medals = gson.fromJson(var1, MedalAward[].class);
        MedalAward[] p2Medals = gson.fromJson(var2, MedalAward[].class);
        Medal[] medals = getMedals();
        String[] betterMedals = new String [p1Medals.length + p2Medals.length];
        String[] worseMedals = new String[p1Medals.length];
        String[] allMedals1 = new String [p1Medals.length];
        String[] allMedals2 = new String [p2Medals.length];
        double p1totalMedals = 0;
        double p2totalMedals = 0;
        double totalGames1 = totalGames(ARENA, player1);
        double totalGames2 = totalGames(ARENA, player2);
        double gamePercentage1 = 0;
        double gamePercentage2 = 0;
        long[] p1Has = null;
        long[] p2Has = null;

        for (int i = 0; i < p1Medals.length; i++)
        {
            allMedals1[i] = getMedalName(p1Medals[i].getMedalId(), medals);
            for (int k = 0; k < p2Medals.length; k++)
            {
                allMedals2[k] = getMedalName(p2Medals[k].getMedalId(), medals);
                if (p1Medals[i].getMedalId() == p2Medals[k].getMedalId()) {
                    p1totalMedals = p1Medals[i].getCount();
                    p2totalMedals = p2Medals[k].getCount();
                    gamePercentage1 = p1totalMedals/totalGames1;
                    gamePercentage2 = p2totalMedals/totalGames2;
                    gamePercentage1 = (double)Math.round(gamePercentage1 *1000d) / 1000d;
                    gamePercentage2 = (double)Math.round(gamePercentage2 *1000d) / 1000d;


                    if (gamePercentage1 > gamePercentage2) {
//                        betterMedals[i] = (player1 + " has " + p1Medals[i].getCount() + " " + getMedalName(p1Medals[i].getMedalId(), medals) + " medals \n\t-" + player2 + " only has : " + p2Medals[k].getCount());
                        betterMedals[i] = getMedalName(p1Medals[i].getMedalId(), medals) + " : " + "Earned per game: " + gamePercentage1 + " " + player2 + " is a loser and only earns " + gamePercentage2 + " per game";
                    }
                    else if (gamePercentage2 > gamePercentage1)
                    {
                        //worseMedals[i] = (player2 + " has " + p2Medals[k].getCount() + " " + getMedalName(p2Medals[k].getMedalId(), medals) + " medals \n\t-" + player1 + " only has : " + p1Medals[i].getCount());
                        worseMedals[i] = getMedalName(p2Medals[k].getMedalId(), medals) + " : " + "Earned per game: " + gamePercentage2 + " " + player1 + " is a loser and only earns " + gamePercentage1 + " per game";
                        //getMedalDescription(p2Medals[k].getMedalId(), medals)
                    }
                }
            }
        }

        System.out.println(player1 + " has earned these medals more than " + player2);
        for (int i = 0; i < betterMedals.length; i++)
        {
            if (betterMedals[i] != null) {
                System.out.println(betterMedals[i]);
            }
        }
        System.out.println("\n" + player2 + " has earned these medals more than " + player1);
        for (int i = 0; i < worseMedals.length; i++){
            if (worseMedals[i] != null){
                System.out.println(worseMedals[i]);
            }
        }
        Arrays.sort(allMedals1);
        Arrays.sort(allMedals2);
        p1Has = new long[allMedals1.length];
        p2Has = new long[allMedals2.length];
        for (int i = 0; i < allMedals1.length; i++)
        {
            if (! Arrays.asList(allMedals2).contains(allMedals1[i]))
            {
                p1Has[i] = getMedalID(allMedals1[i], medals);
            }
        }

        for (int i = 0; i < allMedals2.length; i++)
        {
            if (! Arrays.asList(allMedals1).contains(allMedals2[i]))
            {
                p2Has[i] = getMedalID(allMedals2[i], medals);
            }
        }
        System.out.println("\nMedals that " + player1 + " has earned that " + player2 + " hasn't: ");
        for (int k = 0; k < p1Medals.length; k++) {
            for (int i = 0; i < p1Has.length; i++) {
                if (p1Medals[k].getMedalId() == p1Has[i])
                {
//                    System.out.println(player2 + " hasn't earned any " + getMedalName(p1Has[i], medals) + " medals but " + player1 + " has " + p1Medals[k].getCount());
                    System.out.println(getMedalName(p1Has[i], medals) + " : " + getMedalDescription(p1Has[i], medals));
                }
            }
        }
        System.out.println("\nMedals that " + player2 + " has earned that " + player1 + " hasn't: ");
        for (int k = 0; k < p2Medals.length; k++) {
            for (int i = 0; i < p2Has.length; i++) {
                if (p2Medals[k].getMedalId() == p2Has[i])
                {
//                    System.out.println(player1 + " hasn't earned any " + getMedalName(p2Has[i], medals) + " medals but " + player2 + " has " + p2Medals[k].getCount());
                    System.out.println(getMedalName(p2Has[i], medals) + " : " + getMedalDescription(p2Has[i], medals));
                }
            }
        }
    }

    public static void testPlayerMatches() throws Exception
    {
        double totalGames = totalGames(ARENA, PLAYER_UF);
        Medal[] medals = getMedals();
        Map[] maps = getMaps();
        double kdRatio = 0;
        double averageKills = 0;
        double averageDeaths = 0;
        String bestMatchID = null;
        int positiveCount = 0;
        Gson gson = new Gson();
        Match[] matches = getMatches();
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
            if (! getMapName(matches[i].getMapId(), maps).equalsIgnoreCase("Breakout Arena")) {
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
        System.out.println("Your total number of kills: " + averageKills + " Your total number of deaths: " + averageDeaths);
        System.out.println("Your average K/D spread is: " + averageKills / averageDeaths);
        System.out.println("You've had a positive K/D spread " + positiveCount + " times. That's " + percentagePositive + "% of your games!");
        System.out.println("Your best Kill/Death ratio in any Arena game is: " + kdRatio);

        JSONObject obj2 = new JSONObject(postGameCarnage(bestMatchID));
        BaseCarnageReport carnageReport = gson.fromJson(obj2.toString(), BaseCarnageReport.class);
        SpartanRankedPlayerStats[] playerStats = gson.fromJson(obj2.getJSONArray("PlayerStats").toString(), SpartanRankedPlayerStats[].class);
        String mapName = getMapVariant(carnageReport.getMapVariantId()).getName();
        System.out.println("Your best game was played on map: " + mapName);
        System.out.println("Here are the medals you earned in that game: \n");
        for (int i = 0; i < playerStats.length; i++){
            for (MedalAward medal : playerStats[i].getMedalAwards()){
                for (int k = 0; k < medals.length; k++){
                    if (medal.getMedalId() == medals[k].getId()){
                        medal.setName(medals[k].getName());
                    }
                }
                if (playerStats[i].getPlayer().getGamertag().equalsIgnoreCase(PLAYER_UF)){
                    System.out.println(medal.getName() + " x " + medal.getCount());
                }
            }
        }
    }

    public static String testString() {
        return "[{\"name\":\"Urban\",\"description\":\"Andesia was the crucible for countless heroes and villains caught in the throes of seething rebellion and righteous excess.\",\"supportedGameModes\":[\"Warzone\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/warzone/warzone_maps_array08-b2c5175d0e8446c29e4fc6888492e5ff.jpg\",\"id\":\"c93d708f-f206-11e4-a815-24be05e24f7e\",\"contentId\":\"c93d708f-f206-11e4-a815-24be05e24f7e\"},{\"name\":\"Raid on Apex 7\",\"description\":\"This unbroken ring is a symbol of the discovery that shook the galaxy and changed the course of both human and Covenant destiny.\",\"supportedGameModes\":[\"Warzone\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/warzone/apex-04a7ffdd6aca4e48bc159ae754ca6585.jpg\",\"id\":\"cb251c51-f206-11e4-8541-24be05e24f7e\",\"contentId\":\"cb251c51-f206-11e4-8541-24be05e24f7e\"},{\"name\":\"March on Stormbreak\",\"description\":null,\"supportedGameModes\":[\"Warzone\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/warzone/stormbreak-cbacd64e24bb432db9cf30ac66602284.jpg\",\"id\":\"c854e54f-f206-11e4-bddc-24be05e24f7e\",\"contentId\":\"c854e54f-f206-11e4-bddc-24be05e24f7e\"},{\"name\":\"Escape from A.R.C.\",\"description\":\"Scientists flocked to this Forerunner excavation in search of new beginnings. What they unearthed will lead to their inevitable end.\",\"supportedGameModes\":[\"Warzone\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/warzone/arc-f3f60a132fa746899ce9c2d340152ddb.jpg\",\"id\":\"c8d69870-f206-11e4-b477-24be05e24f7e\",\"contentId\":\"c8d69870-f206-11e4-b477-24be05e24f7e\"},{\"name\":\"Osiris\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array01-9c1ff3f00e364bfaa0e059a90fa37d92.jpg\",\"id\":\"73ed1fd0-45e5-4bb9-ab6a-d2852c04ea7d\",\"contentId\":\"73ed1fd0-45e5-4bb9-ab6a-d2852c04ea7d\"},{\"name\":\"Blue Team\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array02-b6e6f433fbbf4011856722afba3cba67.jpg\",\"id\":\"96c3e3dd-7703-4086-9e64-e3a23932bdc4\",\"contentId\":\"96c3e3dd-7703-4086-9e64-e3a23932bdc4\"},{\"name\":\"Glassed\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array03-56e025da746b4a7cb0662d3412fbcb1f.jpg\",\"id\":\"1c4f8e19-b046-4f78-9e2d-959cba84663d\",\"contentId\":\"1c4f8e19-b046-4f78-9e2d-959cba84663d\"},{\"name\":\"Unconfirmed\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array05-4fda17114e954fc293dbc788a3771917.jpg\",\"id\":\"825065cf-df57-42e3-b845-830e7340ea43\",\"contentId\":\"825065cf-df57-42e3-b845-830e7340ea43\"},{\"name\":\"Alliance\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array09-a9daf8297db04798b071c7061b12b4b8.jpg\",\"id\":\"9a188f67-1664-4d7b-83ca-1d74f714f764\",\"contentId\":\"9a188f67-1664-4d7b-83ca-1d74f714f764\"},{\"name\":\"Before the Storm\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array11-f715334a2a68435fba6dc7467f561c50.jpg\",\"id\":\"2702ea83-2c3e-4fd5-8370-60d9a6e0422f\",\"contentId\":\"2702ea83-2c3e-4fd5-8370-60d9a6e0422f\"},{\"name\":\"Genesis\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.com/media/Default/games/halo-5-guardians/map-images/campaign/campaign_missions_array13-c60a8c104a4e4f409c8d275a570730a4.jpg\",\"id\":\"82f8471c-a2ef-4089-ac04-7e829fdec912\",\"contentId\":\"82f8471c-a2ef-4089-ac04-7e829fdec912\"},{\"name\":\"The Breaking\",\"description\":null,\"supportedGameModes\":[\"Campaign\"],\"imageUrl\":\"https://content.halocdn.";
    }

    public class MyLocalBinder extends Binder {
        DataService getService(){
            return DataService.this;
        }
    }
}
