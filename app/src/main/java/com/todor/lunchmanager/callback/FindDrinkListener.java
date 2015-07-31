package com.todor.lunchmanager.callback;

import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Created by todor on 26.07.15.
 */
public interface FindDrinkListener {
    void getDrinkSuccess(ParseObject object) throws ParseException;
    void getDrinkError(ParseException e);
}
