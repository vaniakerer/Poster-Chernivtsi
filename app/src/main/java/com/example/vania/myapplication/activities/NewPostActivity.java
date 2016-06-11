package com.example.vania.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vania.myapplication.Constants;
import com.example.vania.myapplication.DatePicker;
import com.example.vania.myapplication.MainActivity;
import com.example.vania.myapplication.R;
import com.example.vania.myapplication.model.Event;
import com.example.vania.myapplication.rest_api.ApiFactory;
import com.example.vania.myapplication.rest_api.PosterService;

import java.util.Calendar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class NewPostActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private EditText imageUrl;
    private TextView eventDate;
    private Button submit;
    private FloatingActionButton addEventFab;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        initViews();

    }

    private void initViews() {
        title = (EditText) findViewById(R.id.new_post_title);
        description = (EditText) findViewById(R.id.new_post_description);
        imageUrl = (EditText) findViewById(R.id.new_post_image_url);
        eventDate = (TextView) findViewById(R.id.new_post_event_date);
        spinner = (Spinner) findViewById(R.id.new_post_event_type);


        //Введення дати
        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getSupportFragmentManager(), "DatePicker");

            }
        });

        //Встановлення адаптера спінеру

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.event_types_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        //встановлення кнопці dropdown білого кольору
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.white_), PorterDuff.Mode.SRC_ATOP);

        //Відправка Event-а на сервер
        View.OnClickListener addNewEvent = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.loginSP, MODE_PRIVATE);

                if (sharedPreferences.getString(Constants.loginSt, "").equals("admin")) {
                    sendEvent(new Event(0, title.getText().toString(), description.getText().toString(), imageUrl.getText().toString(), getCurrentDate(), eventDate.getText().toString(), Integer.parseInt(sharedPreferences.getString(Constants.idUser, "")), 1, spinner.getSelectedItemPosition() - 1, 1));
                }else if (sharedPreferences.getString(Constants.loginSt, "").equals("user")){
                    sendUserEvent(new Event(0, title.getText().toString(), description.getText().toString(), imageUrl.getText().toString(), getCurrentDate(), eventDate.getText().toString(), Integer.parseInt(sharedPreferences.getString(Constants.idUser, "")), 1, spinner.getSelectedItemPosition() - 1, 1));
                }
            }
        };
        addEventFab = (FloatingActionButton) findViewById(R.id.fab_newPost_activity);
        addEventFab.setOnClickListener(addNewEvent);
    }

    private void sendEvent(Event event) {

        PosterService service = ApiFactory.getPosterService(Constants.serverURL + Constants.serverPORT);
        Call<Event> call = service.createEvent(event);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Response<Event> response) {
                Toast.makeText(getApplicationContext(), R.string.new_event_addToast, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.new_event_errorAddToast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendUserEvent(Event event){
        PosterService service = ApiFactory.getPosterService(Constants.serverURL + Constants.serverPORT);
        Call<Event> call = service.createUserEvent(event);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Response<Event> response) {
                Toast.makeText(getApplicationContext(), R.string.new_event_addToast, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.new_event_errorAddToast, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewPostActivity.this, MainActivity.class));
    }
}
