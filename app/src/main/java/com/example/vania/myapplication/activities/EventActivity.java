package com.example.vania.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.vania.myapplication.Constants;
import com.example.vania.myapplication.MainActivity;
import com.example.vania.myapplication.R;
import com.squareup.picasso.Picasso;

public class EventActivity extends AppCompatActivity {

    private Intent intent;
    private TextView id;
    private TextView author;
    private TextView event_date;
    private TextView post_date;
    private TextView event_description;
    private TextView event_type;
    private ImageView bgHeader;
    private FloatingActionButton deleteFab;

    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView typeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout   = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        bgHeader = (ImageView) findViewById(R.id.bgheader);

        initContent();

        final ScrollView scrollView = (ScrollView) findViewById(R.id.SCROLLER_ID);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(0, author.getBottom());
            }
        });

    }

    private void saveEventToSP() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.eventSp, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.eventId, id.getText().toString());
        editor.apply();

        Log.d("asdasdasdasdasds", sharedPreferences.getString(Constants.eventId, ""));
    }


    public void initContent(){
        //set title, getting title from intent
        intent = getIntent();
        collapsingToolbarLayout.setTitle(intent.getStringExtra("title"));

        //initialising TextView's
        id = (TextView) findViewById(R.id.id_current_event);
        author = (TextView) findViewById(R.id.event_author);
        event_date = (TextView) findViewById(R.id.event_date);
        post_date = (TextView) findViewById(R.id.event_post_date);
        event_description = (TextView) findViewById(R.id.current_event_description);
        event_type = (TextView) findViewById(R.id.event_type);
        typeImage = (ImageView) findViewById(R.id.current_type_image);


        //setting info into TV's
        id.setText(intent.getStringExtra("id"));
        author.setText("Author: " + intent.getStringExtra("author"));
        event_date.setText("Event date: " + intent.getStringExtra("event_date"));
        post_date.setText("Post date: " + intent.getStringExtra("post_date"));
        event_description.setText(intent.getStringExtra("description"));
        event_type.setText("Event type: " + convertType());

        saveEventToSP();

        Log.d("INTENT_TEST", intent.getStringExtra("description"));

        initEventTypeIcon();

        Picasso.with(this)
                .load(intent.getStringExtra("imageUrl"))
                .resize(500, 280)
                .into(bgHeader);

    }

    private void initEventTypeIcon(){

        //Визначає іконку в залежності від типу івента
        switch (Integer.parseInt(intent.getStringExtra("type"))){
            case 0:
                typeImage.setImageResource(R.drawable.type_party);
                break;
            case 1:
                typeImage.setImageResource(R.drawable.type_it);
                break;
            case 2:
                typeImage.setImageResource(R.drawable.type_presentation);
                break;
            case 3:
                typeImage.setImageResource(R.drawable.type_presentation);
                break;
            case 4:
                typeImage.setImageResource(R.drawable.type_sport);
                break;
            case 5:
                typeImage.setImageResource(R.drawable.type_education);
            default:
                typeImage.setImageResource(R.drawable.type_default);
                break;
        }

    }

    //Метод для переводу типу івента
    /**
     0: disco party
     1: IT event
     2: rally event
     3: presentation
     4: sport
     5: education
     */
    private String convertType(){
        switch (Integer.parseInt(intent.getStringExtra("type"))){
            case 0:
                return getString(R.string.type_e_disco_party);
            case 1:
                return getString(R.string.type_e_it_event);
            case 2:
                return getString(R.string.type_e_rally);
            case 3:
                return getString(R.string.type_e_presentation);
            case 4:
                return getString(R.string.type_e_sport);
            case 5:
                return getString(R.string.type_e_education);
            default:
                return getString(R.string.type_e_default);
        }

    }





    @Override
    public void onBackPressed() {
        startActivity(new Intent(EventActivity.this, MainActivity.class));
    }
}
