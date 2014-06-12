package com.example.lily.app;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by gwoo on 2014. 6. 12..
 */

@Root
public class Book2 {

    @Element
    public channel channel;


    public static class channel {

        @Element
        public int total;

        @Element
        public item item;

//        @Element(name = "item")
//        public List<item> items;


    }

    public static class item {

        @Element
        public String title;

        @Element(required = false)
        public String link;

        @Element(required = false)
        public String image;

        @Element
        public String author;

        @Element(required = false)
        public String price;

        @Element(required = false)
        public String discount;

        @Element(required = false)
        public String publisher;

        @Element(required = false)
        public String pubdate;

        @Element
        public String isbn;

        @Element(required = false)
        public String description;
    }

}
