package com.todor.lunchmanager;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.todor.lunchmanager.callback.FindDrinkListener;
import com.todor.lunchmanager.callback.FindGarnishListener;
import com.todor.lunchmanager.callback.FindSaladListener;
import com.todor.lunchmanager.callback.FindSoupListener;
import com.todor.lunchmanager.callback.FindStarterListener;
import com.todor.lunchmanager.callback.FindUserListener;

import java.util.Arrays;
import java.util.List;

public class ParseService {

    private static ParseService instance;

    private ParseService() {

    }

    public static ParseService getInstance() {
        if (instance == null) {
            instance = new ParseService();
        }
        return instance;
    }

    private FindUserListener findUserListener;
    private FindSoupListener findSoupListener;
    private FindGarnishListener findGarnishListener;
    private FindStarterListener findStarterListener;
    private FindSaladListener findSaladListener;
    private FindDrinkListener findDrinkListener;

    public void findUser(String email, String password, FindUserListener listener) {
        this.findUserListener = listener;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Person");
        query.whereEqualTo("email", email);
        query.whereEqualTo("password", password);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    findUserListener.getUserSuccess(object);
                } else {
                    findUserListener.getUserError(e);
                }
            }
        });
    }

    public void findSoup(FindSoupListener listener) {
        this.findSoupListener = listener;
        ParseQuery<ParseObject> querySoup = ParseQuery.getQuery("Dishes");
        querySoup.selectKeys(Arrays.asList("soup"));
        querySoup.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            if(!object.toString().isEmpty()) {
                                findSoupListener.getSoupSuccess(object);
                            } else {
                                Log.d("Null: ", "object is null");
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else
                    findSoupListener.getSoupError(e);
            }
        });
    }

    public void findGarnish(FindGarnishListener listener) {
        this.findGarnishListener = listener;
        ParseQuery<ParseObject> queryGarnish = ParseQuery.getQuery("Dishes");
        queryGarnish.selectKeys(Arrays.asList("garnish"));
        queryGarnish.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            if(!object.toString().isEmpty()) {
                                findGarnishListener.getGarnishSuccess(object);
                            } else {
                                Log.d("Null: ", "object is null");
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    findGarnishListener.getGarnishError(e);
                }
            }
        });
    }

    public void findStarter(FindStarterListener listener) {
        this.findStarterListener = listener;
        ParseQuery<ParseObject> queryStarter = ParseQuery.getQuery("Dishes");
        queryStarter.selectKeys(Arrays.asList("starter"));
        queryStarter.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            if(!object.toString().isEmpty()) {
                                findStarterListener.getStarterSuccess(object);
                            } else {
                                Log.d("Null: ", "object is null");
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    findStarterListener.getStarterError(e);
                }
            }
        });
    }

    public void findSalad(FindSaladListener listener) {
        this.findSaladListener = listener;
        ParseQuery<ParseObject> querySalad = ParseQuery.getQuery("Dishes");
        querySalad.selectKeys(Arrays.asList("salad"));
        querySalad.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            if(!object.toString().isEmpty()) {
                                findSaladListener.getSaladSuccess(object);
                            } else {
                                Log.d("Null: ", "object is null");
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    findSaladListener.getSaladError(e);
                }
            }
        });
    }

    public void findDrink(FindDrinkListener listener) {
        this.findDrinkListener = listener;
        ParseQuery<ParseObject> queryDrink = ParseQuery.getQuery("Dishes");
        queryDrink.selectKeys(Arrays.asList("drink"));
        queryDrink.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        try {
                            if(!object.toString().isEmpty()) {
                                findDrinkListener.getDrinkSuccess(object);
                            } else {
                                Log.d("Null: ", "object is null");
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    findDrinkListener.getDrinkError(e);
                }
            }
        });
    }
}
