package com.example.vania.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.vania.myapplication.adapter.TabsFragmentAdapter;
import com.example.vania.myapplication.fragment.CurrentEventFragment;
import com.example.vania.myapplication.fragment.DoneEventFragment;
import com.example.vania.myapplication.model.Event;
import com.example.vania.myapplication.rest_api.ApiFactory;
import com.example.vania.myapplication.rest_api.PosterService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {

    private TabsFragmentAdapter adapter;
    public List<Event> testList = new ArrayList<>();

    //init UI
    Toolbar toolbar;

    ViewPager viewPager;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initProgressBar();

        new EventTask().execute();

        initTabs();


       //Убираєм непонятну іконку "назад"
       ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentEventFragment(), "Current");
        adapter.addFragment(new DoneEventFragment(), "Done");

        viewPager.setAdapter(adapter);
    }

    //---------------------init views---------------------

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }



    private void initProgressBar() {
        progress = new ProgressDialog(this);

        progress.setMessage("Downloading... Please wait ");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);

        progress.setProgressNumberFormat(null);
        progress.setProgressPercentFormat(null);

    }

    //---------------------init views---------------------

    class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


    //Таск для загрузки івентів
    private class EventTask extends AsyncTask<Void, Integer, List<Event>>{

        @Override
        protected List<Event> doInBackground(Void... params) {
            PosterService posterService = ApiFactory.getPosterService(Constants.serverURL);

            Call<List<Event>> call = posterService.getEvents();

            call.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(Response<List<Event>> response) {
                    if (response.isSuccess()) {
                        List<Event> events = (List<Event>) response.body();

                        try {
                            adapter.setData(events);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //do something here
                        testList = events;
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("LOAD EVENTS", "fail");
                }
            });
            Log.d("LOAD EVENTS", "rdy");

            return testList;
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            super.onPostExecute(events);
            progress.hide();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }
    }
}
