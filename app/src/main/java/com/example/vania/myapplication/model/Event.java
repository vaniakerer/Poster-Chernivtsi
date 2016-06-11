package com.example.vania.myapplication.model;

public class Event {

    long id;
    String title;
    String description;
    String imageUrl;
    String postDate;
    String eventDate;
    long id_author;
    long id_admin;
    long id_type;
    long id_rate;

    public Event() {
    }

    public Event(long id, String title, String description, String imageUrl, String postDate, String eventDate, long id_author, long id_admin, long id_type, long id_rate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.postDate = postDate;
        this.eventDate = eventDate;
        this.id_author = id_author;
        this.id_admin = id_admin;
        this.id_type = id_type;
        this.id_rate = id_rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public long getId_author() {
        return id_author;
    }

    public void setId_author(long id_author) {
        this.id_author = id_author;
    }

    public long getId_admin() {
        return id_admin;
    }

    public void setId_admin(long id_admin) {
        this.id_admin = id_admin;
    }

    public long getId_type() {
        return id_type;
    }

    public void setId_type(long id_type) {
        this.id_type = id_type;
    }

    public long getId_rate() {
        return id_rate;
    }

    public void setId_rate(long id_rate) {
        this.id_rate = id_rate;
    }

}
