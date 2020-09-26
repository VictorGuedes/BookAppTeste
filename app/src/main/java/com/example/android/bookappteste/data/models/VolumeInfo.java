
package com.example.android.bookappteste.data.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "VolumeInfo")
public class VolumeInfo implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    @TypeConverters(AuthorTypeConverter.class)
    @SerializedName("authors")
    @Expose
    private List<String> authors;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;

    @SerializedName("description")
    @Expose
    private String description;

    @Embedded
    @Expose
    private ImageLinks imageLinks;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

}
