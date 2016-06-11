package com.example.vania.myapplication.rest_api;

import com.example.vania.myapplication.model.Event;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;


public interface PosterService {

    // get all events
    @GET("/events")
    Call<List<Event>> getEvents();

    //get event by id
    @GET("/events/{id}")
    Call<Event> getEventById(@Path("id") long id);

    //add new event
    @POST("/events")
    Call<Event> createEvent(@Body Event event);

    //delete event by id
    @POST("/events/{id}")
    Call<Event> deleteEvent(@Path("id") long id);




    //user posts
    @GET("/user_events/{id}")
    Call<List<Event>> getUserPosts(@Path("id") long id);

    //create users post
    @POST("user_events")
    Call<Event> createUserEvent(@Body Event event);


    //get all user posts
    @GET("/user_events")
    Call<List<Event>> getAllUsersPosts();

    //delete user event by id
    @POST("/user_events/{id}")
    Call<Event> deleteUserEvent(@Path("id") long id);
}