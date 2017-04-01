package com.hotmoka.android.gallery.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotmoka.android.gallery.R;

/**
 * Created by Alessio on 21/03/2017.
 */

public class PictureTextList extends ArrayAdapter<String> {

    private Activity context;
    private String [] names;
    private Bitmap[] images;

    public PictureTextList (Activity context, String[] names, Bitmap[] images)
    {
        super(context, R.layout.list_single, names);
        this.context = context;
        this.names = names;
        this.images = images;
    }

    @Override
    public View getView(int position, View view , ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(names[position]);
        if (images.length != 0 && images[position] != null)
        {
            imageView.setImageBitmap(images[position]);
            imageView.setVisibility(View.VISIBLE);
            rowView.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }

    public void setImages (Bitmap[] images)
    {
        this.images = images;
        notifyDataSetChanged();
    }
}
