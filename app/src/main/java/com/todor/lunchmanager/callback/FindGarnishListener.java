package com.todor.lunchmanager.callback;


import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Created by todor on 26.07.15.
 */
public interface FindGarnishListener {
    void getGarnishSuccess(ParseObject object) throws ParseException;
    void getGarnishError(ParseException e);
}
