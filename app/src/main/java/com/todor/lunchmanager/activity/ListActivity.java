package com.todor.lunchmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.todor.lunchmanager.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by todor on 24.07.15.
 */
public class ListActivity extends AppCompatActivity {
    ListView soupView;
    ListView garnishView;
    ListView starterView;
    ListView saladView;
    ListView drinkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_view);
        soupView = (ListView) findViewById(R.id.soup);
        garnishView = (ListView) findViewById(R.id.garnish);
        starterView = (ListView) findViewById(R.id.starter);
        saladView = (ListView) findViewById(R.id.salad);
        drinkView = (ListView) findViewById(R.id.drink);

        final List<String> soupList = new ArrayList<>();
        final List<String> garnishList = new ArrayList<>();
        final List<String> starterList = new ArrayList<>();
        final List<String> saladList = new ArrayList<>();
        final List<String> drinkList = new ArrayList<>();

        ParseQuery<ParseObject> querySoup = ParseQuery.getQuery("Dishes");
        querySoup.selectKeys(Arrays.asList("soup"));
        querySoup.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            soupList.add(object.fetchIfNeeded().getString("soup"));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_single_choice, soupList);
                        soupView.setAdapter(adapter);
                    }
                }
            }
        });

        ParseQuery<ParseObject> queryGarnish = ParseQuery.getQuery("Dishes");
        queryGarnish.selectKeys(Arrays.asList("garnish"));
        queryGarnish.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            garnishList.add(object.fetchIfNeeded().getString("garnish"));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_single_choice, garnishList);
                        garnishView.setAdapter(adapter);
                    }
                }
            }
        });

        ParseQuery<ParseObject> queryStarter = ParseQuery.getQuery("Dishes");
        queryStarter.selectKeys(Arrays.asList("starter"));
        queryStarter.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            starterList.add(object.fetchIfNeeded().getString("starter"));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_single_choice, starterList);
                        starterView.setAdapter(adapter);
                    }
                }
            }
        });

        ParseQuery<ParseObject> querySalad = ParseQuery.getQuery("Dishes");
        querySalad.selectKeys(Arrays.asList("salad"));
        querySalad.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            saladList.add(object.fetchIfNeeded().getString("salad"));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_single_choice, saladList);
                        saladView.setAdapter(adapter);
                    }
                }
            }
        });

        ParseQuery<ParseObject> queryDrink = ParseQuery.getQuery("Dishes");
        queryDrink.selectKeys(Arrays.asList("drink"));
        queryDrink.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            drinkList.add(object.fetchIfNeeded().getString("drink"));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_single_choice, drinkList);
                        drinkView.setAdapter(adapter);
                    }
                }
            }
        });

    }
}
