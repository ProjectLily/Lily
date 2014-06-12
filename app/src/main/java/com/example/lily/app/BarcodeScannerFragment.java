package com.example.lily.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by gwoo on 2014. 6. 12..
 */
public class BarcodeScannerFragment extends Fragment implements SearchBookClient.SearchBookClientListener {

    public static final String TAG = BarcodeScannerFragment.class.getSimpleName();

    BookDbHelper bookDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentIntegrator.forFragment(this).initiateScan();
    }


    protected BookDbHelper getHelper() {
        if (bookDbHelper == null) {
            bookDbHelper =
                    OpenHelperManager.getHelper(getActivity(), BookDbHelper.class);
        }
        return bookDbHelper;
    }

    @Override
    public void onDestroy() {

        if (bookDbHelper != null) {
            OpenHelperManager.releaseHelper();
            bookDbHelper = null;
        }
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == Activity.RESULT_OK) {

            Bundle b = data.getExtras();
            Set<String> keys = b.keySet();
            for (String key : keys) {
                Log.d("gr8woo", key);
                Log.d("gr8woo", b.get(key).toString());
            }

            BarcodeResult barcodeResult = new BarcodeResult();
            barcodeResult.result = b.getString("SCAN_RESULT");
            barcodeResult.upc_ean_extension = b.getString("SCAN_RESULT_UPC_EAN_EXTENSION");
            barcodeResult.format = BarcodeFormat.valueOf(b.getString("SCAN_RESULT_FORMAT"));

            SearchBookClient searchBookClient = new SearchBookClient(getActivity(), barcodeResult, this);
            searchBookClient.startRequest();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStartedRequest() {

    }

    @Override
    public void onSuccessRequest(Book2 book2) {
        Book book = new Book(book2);
        try {
            getHelper().getBookDao().createOrUpdate(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailRequest(String error) {

    }
}
