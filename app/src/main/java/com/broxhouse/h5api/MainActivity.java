package com.broxhouse.h5api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.broxhouse.h5api.models.metadata.*;
import com.broxhouse.h5api.models.stats.common.MedalAward;
import com.broxhouse.h5api.models.stats.common.WeaponStats;
import com.broxhouse.h5api.models.stats.matches.Match;
import com.broxhouse.h5api.models.stats.reports.BaseCarnageReport;
import com.broxhouse.h5api.models.stats.reports.BaseStats;
import com.broxhouse.h5api.models.stats.reports.SpartanRankedPlayerStats;
import com.broxhouse.h5api.models.stats.servicerecords.ArenaStat;
import com.broxhouse.h5api.models.stats.servicerecords.BaseServiceRecordResult;
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
import java.util.concurrent.TimeUnit;

import static com.broxhouse.h5api.gameType.ARENA;
import static com.broxhouse.h5api.gameType.CUSTOM;
import static com.broxhouse.h5api.gameType.WARZONE;


enum gameType {WARZONE, ARENA, CUSTOM}

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendGamertag(View view)
    {
        Intent intent = new Intent(this, GetPlayerStats.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
