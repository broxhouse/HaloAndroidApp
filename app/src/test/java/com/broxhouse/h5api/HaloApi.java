package com.broxhouse.h5api;

import com.broxhouse.h5api.models.metadata.Medal;
import com.broxhouse.h5api.models.metadata.Weapon;
import com.broxhouse.h5api.models.stats.common.EnemyKill;
import com.broxhouse.h5api.models.stats.common.MedalAward;
import com.broxhouse.h5api.models.stats.common.WeaponStats;
import com.broxhouse.h5api.models.stats.reports.BaseStats;
import com.broxhouse.h5api.models.stats.servicerecords.ArenaStat;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.broxhouse.h5api.gameType.ARENA;
import static com.broxhouse.h5api.gameType.CUSTOM;
import static com.broxhouse.h5api.gameType.WARZONE;


enum gameType {WARZONE, ARENA, CUSTOM}

public class HaloApi {

    private static final String TOKEN = "293bb4a86da743bdb983b97efa5bb265";
    private static final String STATS_URL = "https://www.haloapi.com/stats/h5/";
    private static final String META_URL = "https://www.haloapi.com/metadata/h5/metadata/";
    private static final String PLAYER_MATCHES = STATS_URL + "players/%s/matches";
    private static final String CUSTOM_STATS = STATS_URL + "servicerecords/custom?players=%s";
    private static final String ARENA_STATS = STATS_URL + "servicerecords/arena?players=%s";
    private static final String WARZONE_STATS = STATS_URL + "servicerecords/warzone?players=%s";
    private static final String META_WEAPONS = META_URL + "weapons";
    private static final String META_MEDALS = META_URL + "medals";
    private static final String PLAYER_UF = "That Brock Guy";
    private static final String PLAYER = formatString(PLAYER_UF);

    public static String formatString(String string)
    {
        string = string.replaceAll("\\s+", "%20");
        return string;
    }

    //https://www.haloapi.com/stats/h5/players/that%20brock%20guy/matches?modes=arena,custom&start=10&count=26
    public static String playerMatches(String gt, String modes, String start, String count) throws Exception {
        String pURL = String.format(PLAYER_MATCHES, gt);
        if (modes != null)
            pURL.concat("modes=" + modes + "&");
        if (start != null)
            pURL.concat("start=" + start + "&");
        if (count != null)
            pURL.concat("count=" + count);

        return api(String.format(PLAYER_MATCHES, gt));
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
        return api(META_MEDALS);
    }

    public static String listWeapons() throws Exception
    {
        return api(META_WEAPONS);
    }

//    private static String api(String url) throws Exception {
//        System.out.println(url);
//        URL apiUrl = new URL(url);
//        HttpURLConnection urlConn = (HttpURLConnection)apiUrl.openConnection();
//        urlConn.setRequestMethod("GET");
//        urlConn.setRequestProperty("Ocp-Apim-Subscription-Key", TOKEN);
//
//        System.out.println(urlConn.getResponseCode());
//
//        InputStream response = urlConn.getInputStream();
//
//            Scanner scanner = new Scanner(response);
//            String responseBody = scanner.useDelimiter("\\A").next();
//            System.out.println(responseBody);
//            return responseBody;
//
//    }

    private static String api(String url) throws Exception
    {
        HttpClient httpclient = HttpClients.createDefault();
        String getResponse;

            URIBuilder builder = new URIBuilder(url);


            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", TOKEN);


            // Request body
//            StringEntity reqEntity = new StringEntity("{body}");
//            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                getResponse = EntityUtils.toString(entity);
                System.out.println(getResponse);
                return getResponse;
            }
        else
            {
                return null;
            }
    }

    public static void main(String[] args) throws Exception {
        try
        {
//            testJSONWeapons();
//            testJSONMedals();
//            testMedalStats(ARENA);
//            testWeaponKills(WARZONE);
//            totalGames();
//            testPlayerStats();
            testBaseStats(ARENA);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
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

    public static void testMedalStats(Enum gameType) throws Exception
    {
        JSONArray obj = null;
        String mostEarnedMedal = null;
        double average = 0;
        int highestMedalCount = 0;
        if (gameType == WARZONE)
        {
            obj = new JSONObject(warzoneMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("WarzoneStat").getJSONArray("MedalAwards");
        }
        if (gameType == ARENA)
        {
            obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats").getJSONArray("MedalAwards");
        }
        if (gameType == CUSTOM)
        {
            obj = new JSONObject(customMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("CustomStats").getJSONArray("MedalAwards");
        }
        String var = obj.toString();
        System.out.println(var);
        double games = totalGames(gameType);
        games = (double)Math.round(games *1000d) / 1000d;
        Gson gson = new Gson();
        String medalData = listMedals();
        Medal[] medals = gson.fromJson(medalData, Medal[].class);
        MedalAward[] stats = gson.fromJson(var, MedalAward[].class);
        for (int row = 0; row < stats.length; row++)
        {
            for (int i = 0; i < medals.length; i++)
            {
                if (medals[i].getId() == stats[row].getMedalId())
                {
                    stats[row].setName(medals[i].getName());
                }
            }
        }
        System.out.println("Showing medal stats for " + PLAYER_UF);
        for (int row = 0; row < stats.length; row++)
        {
            double medalCount = stats[row].getCount()/games;
            medalCount = (double)Math.round(medalCount *1000d) / 1000d;
            System.out.println(stats[row].getName() + "\n   Count: " + stats[row].getCount() + "\n   Earned per game: " + medalCount);
        }
        for (int row = 0; row < stats.length; row++)
        {
            if (stats[row].getName().equalsIgnoreCase("Top Gun"))
            {
                System.out.println("Brock has earned: " + stats[row].getCount() + " Top Gun medals, which is way more than Axel");
            }
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
        System.out.println("Your most earned medal is the " + mostEarnedMedal + " medal with a total of " + highestMedalCount + " and an average of " + average + " per game");
    }

    public static void testPlayerStats() throws Exception
    {
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(arenaStats(PLAYER), ArenaStat.class);
        System.out.println(stats.getEnemyKills());
    }

    public static double totalGames(Enum gameType) throws Exception
    {
        JSONObject obj = null;
        String gType = null;
        if (gameType == WARZONE)
        {
            obj = new JSONObject(warzoneMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("WarzoneStat");
            gType = "Warzone";
        }
        if (gameType == ARENA)
        {
            obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats");
            gType = "Arena";
        }
        if (gameType == CUSTOM)
        {
            obj = new JSONObject(customMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("CustomStats");
            gType = "Custom Games";
        }
        String var = obj.toString();
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(var, BaseStats.class);
        System.out.println("Total games played: " + stats.getTotalGamesCompleted());
        return stats.getTotalGamesCompleted();
    }

    public static void testWeaponKills(Enum gameType) throws Exception
    {
        JSONArray obj = null;
        String favWeapon = null;
        int totalKills = 0;
        if (gameType == WARZONE)
        {
            obj = new JSONObject(warzoneMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("WarzoneStat").getJSONArray("WeaponStats");
        }
        if (gameType == ARENA)
        {
            obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats").getJSONArray("WeaponStats");
        }
        if (gameType == CUSTOM)
        {
            obj = new JSONObject(customMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("CustomStats").getJSONArray("WeaponStats");
        }
        Gson gson = new Gson();
        String var = obj.toString();
        WeaponStats[] stats = gson.fromJson(var, WeaponStats[].class);
        String weaponData = listWeapons();
        Weapon[] weapons = gson.fromJson(weaponData, Weapon[].class);
        double games = totalGames(gameType);
        games = (double)Math.round(games *1000d) / 1000d;

        for (int row = 0; row < stats.length; row++)
        {
            for (int i = 0; i < weapons.length; i++)
            {
                if (weapons[i].getId() == stats[row].getWeaponId().getStockId())
                {
                    stats[row].setName(weapons[i].getName());
                }
            }
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
        System.out.println("Your favorite weapon is the " + favWeapon + " with a kill total of: " + totalKills);
        System.out.println("Total kills per weapon for " + PLAYER_UF);
        for (int i = 0; i < stats.length; i++)
        {
            double killCount = stats[i].getTotalKills()/games;
            killCount = (double)Math.round(killCount * 1000d) / 1000d;
            System.out.println(stats[i].getName() + "\n   Total Kills: " + stats[i].getTotalKills() + "\n   Avg kills per game: " + killCount);
        }
    }

    public static void testBaseStats(Enum gameType) throws Exception
    {
        JSONObject obj = null;
        String gType = null;
        if (gameType == WARZONE)
        {
            obj = new JSONObject(warzoneMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("WarzoneStat");
            gType = "Warzone";
        }
        if (gameType == ARENA)
        {
            obj = new JSONObject(arenaStats(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("ArenaStats");
            gType = "Arena";
        }
        if (gameType == CUSTOM)
        {
            obj = new JSONObject(customMatches(PLAYER)).getJSONArray("Results").getJSONObject(0).getJSONObject("Result").getJSONObject("CustomStats");
            gType = "Custom Games";
        }
        String var = obj.toString();
        Gson gson = new Gson();
        BaseStats stats = gson.fromJson(var, BaseStats.class);
        System.out.println("Total games played: " + stats.getTotalKills());

        List<WeaponStats> weaponList = stats.getWeapons();
        for(WeaponStats en : weaponList)
        {
            System.out.println(en.getName() + " " + en.getTotalKills());
        }
    }

}