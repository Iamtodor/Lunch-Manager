package com.todor.lunchmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.todor.lunchmanager.ParseService;
import com.todor.lunchmanager.R;
import com.todor.lunchmanager.callback.FindDrinkListener;
import com.todor.lunchmanager.callback.FindGarnishListener;
import com.todor.lunchmanager.callback.FindSaladListener;
import com.todor.lunchmanager.callback.FindSoupListener;
import com.todor.lunchmanager.callback.FindStarterListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by todor on 23.07.15.
 */
public class CabinetActivity extends AppCompatActivity implements FindSoupListener, FindGarnishListener,
                                                                  FindStarterListener, FindSaladListener,
                                                                  FindDrinkListener{

    TextView name;
    TextView date;
    ListView soupView;
    ListView garnishView;
    ListView starterView;
    ListView saladView;
    ListView drinkView;
    Button save;

    final List<String> soupList = new ArrayList<>();
    final List<String> garnishList = new ArrayList<>();
    final List<String> starterList = new ArrayList<>();
    final List<String> saladList = new ArrayList<>();
    final List<String> drinkList = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cabinet);

        name = (TextView) findViewById(R.id.userName);
        date = (TextView) findViewById(R.id.date);

        save = (Button) findViewById(R.id.save);

        soupView = (ListView) findViewById(R.id.soup);
        garnishView = (ListView) findViewById(R.id.garnish);
        starterView = (ListView) findViewById(R.id.starter);
        saladView = (ListView) findViewById(R.id.salad);
        drinkView = (ListView) findViewById(R.id.drink);

        SimpleDateFormat format = new SimpleDateFormat("dd " + "MMMM");
        Date currentDate = new Date();

        date.setText("Menu on the " + format.format(currentDate));

        final String emailIntent = getIntent().getStringExtra("email");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Person");

        query.whereEqualTo("email", emailIntent);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        for (ParseObject person : list) {
                            name.setText("Hello, " + person.getString("name"));
                            Toast.makeText(CabinetActivity.this, "All right", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Log.d("Error", String.valueOf(e));
                }
            }
        });

        soupView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("soup", soupList.get(position));
            }
        });

        garnishView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("garnish", garnishList.get(position));
            }
        });

        starterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("starter", starterList.get(position));
            }
        });

        saladView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("salad", saladList.get(position));
            }
        });

        drinkView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("drink", drinkList.get(position));
            }
        });

        ParseService.getInstance().findSoup(CabinetActivity.this);
        ParseService.getInstance().findGarnish(CabinetActivity.this);
        ParseService.getInstance().findStarter(CabinetActivity.this);
        ParseService.getInstance().findSalad(CabinetActivity.this);
        ParseService.getInstance().findDrink(CabinetActivity.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Order: ", String.valueOf(map));
                ParseQuery<ParseObject> query = new ParseQuery<>("order");
                query.whereEqualTo("email", emailIntent);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        if (e == null) {
                            parseObject.put("soup", map.get("soup"));
                            parseObject.put("garnish", map.get("garnish"));
                            parseObject.put("starter", map.get("starter"));
                            parseObject.put("salad", map.get("salad"));
                            parseObject.put("drink", map.get("drink"));
                            parseObject.saveInBackground();
                        } else {
                            if(e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                                ParseObject object = new ParseObject("order");
                                object.put("email", emailIntent);
                                object.put("soup", map.get("soup"));
                                object.put("garnish", map.get("garnish"));
                                object.put("starter", map.get("starter"));
                                object.put("salad", map.get("salad"));
                                object.put("drink", map.get("drink"));
                                object.saveInBackground();
                            } else {
                                Log.d("Parse error: ", e.toString());
                            }

                        }
                    }
                });
            }
        });
    }

    @Override
    public void getSoupSuccess(ParseObject object) throws ParseException {
        soupList.add(object.fetchIfNeeded().getString("soup"));
    }

    @Override
    public void getSoupError(ParseException e) {
        Log.d("Error: ", e.toString());
    }


    @Override
    public void getGarnishSuccess(ParseObject object) throws ParseException {
        garnishList.add(object.fetchIfNeeded().getString("garnish"));
    }

    @Override
    public void getGarnishError(ParseException e) {
        Log.d("Error: ", e.toString());
    }

    @Override
    public void getDrinkSuccess(ParseObject object) throws ParseException {
        drinkList.add(object.fetchIfNeeded().getString("drink"));
    }

    @Override
    public void getDrinkError(ParseException e) {
        Log.d("Error: ", e.toString());
    }

    @Override
    public void getSaladSuccess(ParseObject object) throws ParseException {
        saladList.add(object.fetchIfNeeded().getString("salad"));
    }

    @Override
    public void getSaladError(ParseException e) {
        Log.d("Error: ", e.toString());
    }

    @Override
    public void getStarterSuccess(ParseObject object) throws ParseException {
        starterList.add(object.fetchIfNeeded().getString("starter"));
    }

    @Override
    public void getStarterError(ParseException e) {
        Log.d("Error: ", e.toString());
    }
}
