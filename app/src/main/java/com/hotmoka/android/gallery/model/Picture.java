package com.hotmoka.android.gallery.model;

public class Picture {
    public final String title;
    public final String url;
    public final String urlLowRes;

    public Picture(String title, String url, String urlLowRes) {
        this.title = title;
        this.url = url;
        this.urlLowRes =urlLowRes;
    }
}
