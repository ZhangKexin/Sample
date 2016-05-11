package com.zkx.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Map<String, Object> temp = (Map<String, Object>) l.getItemAtPosition(position);
        Intent intent = (Intent) temp.get("intent");
        startActivity(intent);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();
        try {
            ActivityInfo[] activities = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).activities;
            for (int i = 0; i < activities.length; i++) {
                if (!activities[i].name.contains(".MainActivity")) {
                    Map<String, Object> temp = new HashMap<>();
                    String name = activities[i].name;
                    temp.put("title", name.substring("com.zkx.sample".length()));
                    temp.put("intent", activityIntent(activities[i].applicationInfo.packageName, name));
                    myData.add(temp);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return myData;
    }

    private Intent activityIntent(String pkg, String componentName) {
        Intent intent = new Intent();
        intent.setClassName(pkg, componentName);
        return intent;
    }
}
