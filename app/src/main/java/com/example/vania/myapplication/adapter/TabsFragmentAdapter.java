package com.example.vania.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vania.myapplication.fragment.AbstractTabFragment;
import com.example.vania.myapplication.fragment.CurrentEventFragment;
import com.example.vania.myapplication.fragment.DoneEventFragment;
import com.example.vania.myapplication.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vania on 02.03.2016.
 */
public class TabsFragmentAdapter extends FragmentPagerAdapter{

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;
    private CurrentEventFragment currentEventFragment;
    private DoneEventFragment doneEventFragment;

    private List<Event> data;
    private List<Event> doneData;
    private List<Event> currentData;
    private List<Event> usersEvent;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.data = new ArrayList<>();
        this.doneData = new ArrayList<>();
        this.currentData = new ArrayList<>();
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {

        currentEventFragment = CurrentEventFragment.getInstance(context, data);
        doneEventFragment = DoneEventFragment.getInstance(context, data);

        tabs = new HashMap<>();
        tabs.put(0, currentEventFragment);
        tabs.put(1, doneEventFragment);

    }

    public void setData(List<Event> data) throws ParseException {
        this.data = data;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        Date currentDate = null;

        currentDate = formatter.parse(getCurrentDate());

        //розділення івентів на потоні і минулі
        for (Event item: data) {
            if ((currentDate.compareTo(formatter.parse(item.getEventDate())) < 0) || (currentDate.compareTo(formatter.parse(item.getEventDate())) == 0)  ){
                currentData.add(item);
            } else {
                doneData.add(item);
            }
        }

        currentEventFragment.refreshData(currentData);
        doneEventFragment.refreshData(doneData);
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + String.valueOf(calendar.get(Calendar.YEAR));
    }
}
