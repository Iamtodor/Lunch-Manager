package com.todor.lunchmanager.callback;

import com.parse.ParseObject;
import com.parse.ParseUser;

import com.parse.ParseException;

/**
 * Created by todor on 25.07.15.
 */
public interface FindUserListener {
    void getUserSuccess(ParseObject object);
    void getUserError(ParseException e);
}
