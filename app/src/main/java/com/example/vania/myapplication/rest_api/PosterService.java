package com.example.vania.myapplication.rest_api;

import com.example.vania.myapplication.model.Event;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface PosterService {

    // get all events
    @GET("host/0B9BivXKAYq-KSEtFcW1zc3hQUkE")
    Call<List<Event>> getEvents();

    //get event by id
    @GET("/events/{id}")
    Call<Event> getEventById(@Path("id") long id);

}