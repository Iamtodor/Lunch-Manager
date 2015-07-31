package com.todor.lunchmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by todor on 23.07.15.
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<ParseObject> list;
    String dish;
    int selectedPosition;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = layoutInflater.inflate(R.layout.dish, parent, false);

        ParseObject object = getObject(position);
        ((TextView) view.findViewById(R.id.dishView)).setText(object.getString(dish));
        RadioButton desireDish = (RadioButton) view.findViewById(R.id.radioButton);
        desireDish.setChecked(position == selectedPosition);
        desireDish.setTag(position);

        desireDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = (int) v.getTag();
                Toast.makeText(context, getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        desireDish.setTag(position);
        return null;
    }

    ListAdapter(Context context, ArrayList<ParseObject> list, String dish) {
        this.context = context;
        this.list = list;
        this.dish = dish;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    ParseObject getObject(int position) {
        return ((ParseObject) getItem(position));
    }
}