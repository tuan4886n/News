package com.edu.news.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.edu.news.Adapter.Question_Adapter;
import com.edu.news.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CauHoiThuongGap extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> list;
    HashMap<String, List<String>> listHashMap;
    Question_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_thuong_gap);

        expandableListView = findViewById(R.id.expand_ask_question);
        list = new ArrayList<>();
        listHashMap = new HashMap<>();
        adapter = new Question_Adapter(this, list, listHashMap);
        expandableListView.setAdapter(adapter);
        initListData();
    }

    private void initListData() {
        list.add(getString(R.string.group1));
        list.add(getString(R.string.group2));
        list.add(getString(R.string.group3));


        String[] array;
        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group1);
        for (String item : array){
            list1.add(item);
        }

        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group2);
        for (String item : array){
            list2.add(item);
        }

        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group3);
        for (String item : array){
            list3.add(item);
        }


        listHashMap.put(list.get(0),list1);
        listHashMap.put(list.get(1),list2);
        listHashMap.put(list.get(2),list3);
        adapter.notifyDataSetChanged();
    }

    public void clickBack(View view) {
        finish();
    }
}
