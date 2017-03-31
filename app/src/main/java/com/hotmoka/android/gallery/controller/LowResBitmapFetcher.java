package com.hotmoka.android.gallery.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.hotmoka.android.gallery.MVC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alessio on 30/03/2017.
 */

public class LowResBitmapFetcher implements Runnable {

    private String url;

    public LowResBitmapFetcher(String url){
        this.url = url;
    }

    public void run(){
        Bitmap bitmap = null;
        try {
            byte[] bitmapBytes = getUrlBytes(url);
            bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
        }
        catch (IOException e) {}
        if (bitmap != null)
            MVC.model.setBitmap(url, bitmap);
    }

    private byte[] getUrlBytes(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        ByteArrayOutputStream out = null;

        try {
            out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return new byte[0];

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0)
                out.write(buffer, 0, bytesRead);

            return out.toByteArray();
        } finally {
            if (out != null)
                out.close();

            connection.disconnect();
        }
    }
}
