package com.example.lily.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Created by gwoo on 2014. 6. 12..
 */
public class SearchBookClient implements Response.Listener, Response.ErrorListener {

    public static final String BASE_URL = "http://openapi.naver.com/search";
    public static final String KEY_NAVER = "99fbdb7ddf888905ebfa1407e858ad72";

    BarcodeResult barcodeResult;

    RequestQueue volleyQueue;

    SearchBookClientListener clientListener;

    public SearchBookClient(Context context, BarcodeResult barcodeResult) {
        this.barcodeResult = barcodeResult;

        volleyQueue = Volley.newRequestQueue(context);
    }

    public SearchBookClient(Context context, BarcodeResult barcodeResult, SearchBookClientListener clientListener) {
        this.barcodeResult = barcodeResult;

        volleyQueue = Volley.newRequestQueue(context);

        this.clientListener = clientListener;
    }

    public void setListener(SearchBookClientListener listener) {
        this.clientListener = listener;
    }

    public void startRequest() {
        String reqUrl = getReqURL();
        Log.d("gr8woo","req url = " + reqUrl);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, reqUrl, this, this);
        volleyQueue.add(stringRequest);

        if (clientListener != null) {
            clientListener.onStartedRequest();
        }
    }

    private String getReqURL() {
        StringBuilder b = new StringBuilder();
        b.append(BASE_URL);

        b.append("?");
        b.append("key=");
        b.append(KEY_NAVER);

        b.append("&");
        b.append("target=");
        b.append("book");

        b.append("&");
        b.append("query=");
        b.append(barcodeResult.result);

        b.append("&");
        b.append("d_isbn=");
        b.append(barcodeResult.result);

        return b.toString();
    }


    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Log.d("gr8woo","volleyError " + volleyError.getLocalizedMessage());

        if (clientListener != null) {
            clientListener.onFailRequest(volleyError.getLocalizedMessage());
        }

    }

    @Override
    public void onResponse(Object o) {
        Log.d("gr8woo","onResponse " + o.toString());
        if( o instanceof String) {


            String xmlString = (String)o;

            try {
                Serializer serializer = new Persister();
                Book2 book = serializer.read(Book2.class, xmlString, false);
                Log.d("gr8woo", "serial book " + book.channel.item.title);

                if (clientListener != null) {
                    clientListener.onSuccessRequest(book);
                }
            }catch (Exception e) {
                Log.d("gr8woo", "xml error");
                e.printStackTrace();

                if (clientListener != null) {
                    clientListener.onFailRequest(e.getLocalizedMessage());
                }
            }
        }

    }

    public interface SearchBookClientListener {
        public void onStartedRequest();

        public void onSuccessRequest(Book2 book2);

        public void onFailRequest(String error);
    }


}
