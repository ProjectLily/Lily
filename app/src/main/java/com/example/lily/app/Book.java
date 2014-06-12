package com.example.lily.app;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Element;

/**
 * Created by gwoo on 2014. 6. 12..
 */

@Element(name = "item")
@DatabaseTable(tableName = "book")
public class Book {

    @DatabaseField(generatedId = true)
    public int _id;

    @Element
    @DatabaseField
    public String title;

    @Element
    @DatabaseField
    public String link;

    @Element(name = "image")
    @DatabaseField
    public String image_url;

    @DatabaseField
    public Bitmap image;

    @Element
    @DatabaseField
    public String author;

    @Element
    @DatabaseField
    public String price;

    @Element
    @DatabaseField
    public String discount;

    @Element
    @DatabaseField
    public String publisher;

    @Element
    @DatabaseField
    public String pubdate;

    @Element
    @DatabaseField
    public String isbn;

    @Element
    @DatabaseField
    public String description;


}
