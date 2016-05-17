package com.zkx.sample.support_design;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zkx on 16/5/17.
 */
public class MyListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListAdapter(new SimpleAdapter(getContext(), getData(), android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1}));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("title", "com.zkx.sample");
            myData.add(temp);
        }
        return myData;
    }
}
