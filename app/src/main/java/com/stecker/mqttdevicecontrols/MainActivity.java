package com.stecker.mqttdevicecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.stecker.mqttdevicecontrols.settings.Control;
import com.stecker.mqttdevicecontrols.settings.Server;
import com.stecker.mqttdevicecontrols.settings.SettingsAPI;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import static android.util.Log.ASSERT;

public class MainActivity extends AppCompatActivity {
    public String getTestConfig() {
        LinkedList<Server> servers = new LinkedList<>();
        Server s = new Server();

        s.enabled = true;
        s.port = 1883;
        s.url = "192.178.178.25";
        s.protocol = "tcp";
        s.controls.add(new Control());
        s.controls.get(0).enabled = true;
        s.controls.get(0).structure = "Carsten";
        s.controls.get(0).controlID = "0";
        s.controls.get(0).deviceType = 8;
        s.controls.get(0).MQTTtopic = "home/carsten/deckenlampe";
        s.controls.get(0).title = "Lichtschalter";
        s.controls.get(0).subtitle = "Schlafzimmer";
        s.controls.get(0).template.templateType = "rangetemplate";
        s.controls.get(0).template.minValue = 0.0f;
        s.controls.get(0).template.maxValue = 100.0f;
        s.controls.get(0).template.stepValue = 1.0f;
        s.controls.get(0).template.formatString = "%.0f";

        s.controls.add(new Control());
        s.controls.get(1).enabled = true;
        s.controls.get(1).structure = "Carsten";
        s.controls.get(1).controlID = "1";
        s.controls.get(1).deviceType = 8;
        s.controls.get(1).MQTTtopic = "home/carsten/deckenlampe";
        s.controls.get(1).title = "Lichtschalter";
        s.controls.get(1).subtitle = "Schlafzimmer";
        s.controls.get(1).template.templateType = "rangetemplate";
        s.controls.get(1).template.minValue = 0.0f;
        s.controls.get(1).template.maxValue = 100.0f;
        s.controls.get(1).template.stepValue = 1.0f;
        s.controls.get(1).template.formatString = "%.0f";

        servers.add(s);
        Gson gson = new Gson();
        return gson.toJson(servers);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String testConfig = getTestConfig();
        Log.println(Log.ASSERT, "serversJSON", testConfig);
        SettingsAPI s = new SettingsAPI(getFilesDir() + "/" + getString(R.string.config_file));
        try {
            s.saveSettings(getTestConfig());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}