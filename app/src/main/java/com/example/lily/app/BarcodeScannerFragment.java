package com.example.lily.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.Set;

/**
 * Created by gwoo on 2014. 6. 12..
 */
public class BarcodeScannerFragment extends Fragment {

    public static final String TAG = BarcodeScannerFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentIntegrator.forFragment(this).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == Activity.RESULT_OK ) {

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

            SearchBookClient searchBookClient = new SearchBookClient(getActivity(),barcodeResult);
            searchBookClient.startRequest();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
