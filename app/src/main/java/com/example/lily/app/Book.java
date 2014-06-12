package com.example.lily.app;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by gwoo on 2014. 6. 12..
 */

@DatabaseTable(tableName = "book")
public class Book {

    @DatabaseField(generatedId = true)
    public int _id;

    @DatabaseField
    public String title;

    @DatabaseField
    public String link;

    @DatabaseField
    public String image_url;

    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    public byte[] image;

    @DatabaseField
    public String author;

    @DatabaseField
    public String price;

    @DatabaseField
    public String discount;

    @DatabaseField
    public String publisher;

    @DatabaseField
    public String pubdate;

    @DatabaseField
    public String isbn;

    @DatabaseField
    public String description;


    public Book() {

    }

    public Book(Book2 book2) {
        this.title = book2.channel.item.title;
        this.link = book2.channel.item.link;
        this.image_url = book2.channel.item.image;
        this.author = book2.channel.item.author;
        this.price = book2.channel.item.price;
        this.discount = book2.channel.item.discount;
        this.publisher = book2.channel.item.publisher;
        this.pubdate = book2.channel.item.pubdate;
        this.isbn = book2.channel.item.isbn;
        this.description = book2.channel.item.description;

    }
}
